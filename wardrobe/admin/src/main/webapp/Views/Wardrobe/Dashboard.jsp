<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ page import="com.wardrobe.layout.Blocks" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> <%-- JSTL表达式（判断，循环，输出） --%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %> <%-- 方法表达式（字符串截取，替换） --%>
<%@ taglib uri="http://www.sychu.com/tags/tag" prefix="layout" %>

<layout:override name="<%=Blocks.BLOCK_HEADER_CSS%>">
    <style type="text/css"></style>
</layout:override>

<layout:override name="<%=Blocks.BLOCK_HEADER_SCRIPTS%>">
    <script type="text/javascript" src="Content/js/require.js?v=${static_resource_version}"
            data-main="Content/js/app/wardrobe/dashboard.js?v=${static_resource_version}"></script>
    <script type="text/javascript">
        function connectTcp(type, btn){
            if(window.confirm("确认连接吗？")){
                $(btn).prop("disabled", true).text("正在尝试连接...");
                $.post("/relay/connectServer", {type: type}, function(res) {
                    if (res.code == 1) {
                        window.location.reload();
                    }else {
                        $(btn).prop("disabled", false).text("连接");
                    }
                });
            }
        }

        function openDoop(btn, doorId, name){
            if(window.confirm("确认开启"+name+"吗？")){
                $(btn).prop("disabled", true).text("正在尝试开启"+name+"...");
                $.post("/relay/openDrive", {driveId: doorId}, function(res) {
                    if (res.code == 1) {
                        $("*[data-door-open-"+doorId+"]").show();
                        $("*[data-door-close-"+doorId+"]").hide();
                    }
                    $(btn).prop("disabled", false).text("开"+name);
                });
            }
        }

        function closeDoop(btn, doorId, name){
            if(window.confirm("确认关闭"+name+"吗？")){
                $(btn).prop("disabled", true).text("正在尝试关闭"+name+"...");
                $.post("/relay/closeDrive", {driveId: doorId}, function(res) {
                    if (res.code == 1) {
                        $("*[data-door-open-"+doorId+"]").hide();
                        $("*[data-door-close-"+doorId+"]").show();
                    }
                    $(btn).prop("disabled", false).text("关"+name);
                });
            }
        }

        function openLock(btn, lockId){
            if(window.confirm("确认开启锁吗？")){
                $(btn).prop("disabled", true).text("正在尝试开启锁...");
                $.post("/relay/openLock", {lockId: lockId}, function(res) {
                    if (res.code == 1) {
                        $("*[data-lock-open-"+lockId+"]").show();
                        $("*[data-lock-close-"+lockId+"]").hide();
                    }
                    $(btn).prop("disabled", false).text("开 锁");
                });
            }
        }

        function closeLock(btn, lockId){
            if(window.confirm("确认关闭锁吗？")){
                $(btn).prop("disabled", true).text("正在尝试关闭锁...");
                $.post("/relay/closeLock", {lockId: lockId}, function(res) {
                    if (res.code == 1) {
                        $("*[data-lock-open-"+lockId+"]").hide();
                        $("*[data-lock-close-"+lockId+"]").show();
                    }
                    $(btn).prop("disabled", false).text("关 锁");
                });
            }
        }

        function downlineDoor(btn){
            if(window.confirm("确认断开大门吗？")){
                $(btn).prop("disabled", true).text("正在尝试断开大门...");
                $.post("/relay/downlineDoor", function(res) {
                    alert(res.message);
                    if (res.code == 1) {
                        window.location.reload();
                        $(btn).prop("disabled", true);
                    }else {
                        $(btn).prop("disabled", false).text("断开大门");
                    }
                });
            }
        }

        function downlineLock(btn){
            if(window.confirm("确认断开柜子吗？")){
                $(btn).prop("disabled", true).text("正在尝试断开柜子...");
                $.post("/relay/downlineLock", function(res) {
                    alert(res.message);
                    if (res.code == 1) {
                        window.location.reload();
                        $(btn).prop("disabled", true);
                    }else {
                        $(btn).prop("disabled", false).text("断开柜子");
                    }
                });
            }
        }
    </script>
</layout:override>

<layout:override name="<%=Blocks.BLOCK_BODY%>">
    <div class="container-fluid">
        <div class="animated fadeIn">
            <div class="row">
                <div class="col-md-6">
                    <div class="card">
                        <div class="card-header">
                            <strong>${deviceInfo.name}</strong>
                            <%--<a href="javascript:;" class="btn btn-sm btn-primary pull-right">
                                <i class="fa fa-refresh"></i> 重新连接
                            </a>
                            <span class="badge badge-success">已连接</span>--%>
                            <div class="card-block row">
                                <div class="col-md-6">
                                    <div class="card">
                                        <div class="card-body p-3 d-flex align-items-center">
                                            <div id="connectTcp2" <c:if test="${doorStatus=='1'}">style="display: none;"</c:if>>
                                                <button type="button" class="btn btn-success" onclick="connectTcp(1, this)">连接大门</button>
                                            </div>
                                            <div <c:if test="${doorStatus=='2'}">style="display: none;"</c:if> id="closeTcp2">
                                                <button type="button" class="btn btn-success" onclick="downlineDoor(this)">断开大门</button>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-md-6">
                                    <div class="card">
                                        <div class="card-body p-3 d-flex align-items-center">
                                            <div id="connectTcp1" <c:if test="${lockStatus=='1'}">style="display: none;"</c:if>>
                                                <button type="button" class="btn btn-success" onclick="connectTcp(2, this)">连接柜子</button>
                                            </div>
                                            <div <c:if test="${lockStatus=='2'}">style="display: none;"</c:if> id="closeTcp1">
                                                <button type="button" class="btn btn-success" onclick="downlineLock(this)">断开柜子</button>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>

                        </div>
                        <div class="card-block row">
                                <c:forEach var="d" items="${deviceDoorControls}">
                                    <div class="col-md-6">
                                        <div class="card">
                                            <div class="card-body p-3 d-flex align-items-center">
                                                <i class="fa fa-lock bg-info p-3 font-2xl mr-3"></i>
                                                <div class="mr-5">
                                                    <div class="text-value-sm text-primary">${d.name}</div>
                                                    <div class="text-muted text-uppercase font-weight-bold small">
                                                        <span data-door-open-${d.lockId} class="badge badge-success" <c:if test="${d.status == 'Relayoff'}">style="display: none;" </c:if>>开${d.name}中</span>
                                                        <span data-door-close-${d.lockId} class="badge badge-danger" <c:if test="${d.status == 'Relayon'}">style="display: none;"</c:if>>关${d.name}中</span>
                                                    </div>
                                                </div>
                                                <div>
                                                    <button data-door-close-${d.lockId} class="btn btn-success" <c:if test="${d.status == 'Relayon'}">style="display: none;"</c:if> onclick="openDoop(this, '${d.lockId}', '${d.name}')">开${d.name}</button>
                                                    <button data-door-open-${d.lockId} class="btn btn-danger" <c:if test="${d.status == 'Relayoff'}">style="display: none;"</c:if> onclick="closeDoop(this, '${d.lockId}', '${d.name}')">关${d.name}</button>
                                                </div>
                                            </div>
                                                <%--<div class="card-footer px-3 py-2">
                                                    <a class="btn-block text-muted d-flex justify-content-between align-items-center" href="#">
                                                        <span class="small font-weight-bold">查看开关锁日志</span>
                                                        <i class="fa fa-angle-right"></i>
                                                    </a>
                                                </div>--%>
                                        </div>
                                    </div>
                                </c:forEach>

                            <c:forEach var="d" items="${deviceControls}">
                                <div class="col-md-6">
                                    <div class="card">
                                        <div class="card-body p-3 d-flex align-items-center">
                                            <i class="fa fa-lock bg-info p-3 font-2xl mr-3"></i>
                                            <div class="mr-5">
                                                <div class="text-value-sm text-primary">${d.name}</div>
                                                <div class="text-muted text-uppercase font-weight-bold small">
                                                    <span data-lock-open-${d.lockId} class="badge badge-success" <c:if test="${d.status == 'Relayoff'}">style="display: none;" </c:if>>开锁中</span>
                                                    <span data-lock-close-${d.lockId} class="badge badge-danger" <c:if test="${d.status == 'Relayon'}">style="display: none;"</c:if>>关锁中</span>
                                                </div>
                                            </div>
                                            <div>
                                                <button data-lock-close-${d.lockId} class="btn btn-success" <c:if test="${d.status == 'Relayon'}">style="display: none;"</c:if> onclick="openLock(this, '${d.lockId}')">开 锁</button>
                                                <button data-lock-open-${d.lockId} class="btn btn-danger" <c:if test="${d.status == 'Relayoff'}">style="display: none;"</c:if> onclick="closeLock(this, '${d.lockId}')">关 锁</button>
                                            </div>
                                        </div>
                                        <%--<div class="card-footer px-3 py-2">
                                            <a class="btn-block text-muted d-flex justify-content-between align-items-center" href="#">
                                                <span class="small font-weight-bold">查看开关锁日志</span>
                                                <i class="fa fa-angle-right"></i>
                                            </a>
                                        </div>--%>
                                    </div>
                                </div>
                            </c:forEach>
                        </div>
                    </div>
                </div>
                <!--/.col-->
            </div>
            <!--/.row-->
        </div>

    </div>
</layout:override>

<c:import url="../Shared/GeneralLayout.jsp">
    <c:param name="menu" value="wardrobe"/>
    <c:param name="subMenu" value="dashboard"/>
</c:import>
