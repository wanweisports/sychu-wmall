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
                    alert(res.message);
                    if (res.code == 1) {
                        window.location.reload();
                    }
                    $(btn).prop("disabled", false).text("连接");
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
                                                <button type="button" class="btn btn-success" onclick="connectTcp(2, this)">连接大门</button>
                                            </div>
                                            <div <c:if test="${doorStatus=='2'}">style="display: none;"</c:if> id="closeTcp2">
                                                <button type="button" class="btn btn-success" onclick="closeTcp(2, this)">断开大门</button>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-md-6">
                                    <div class="card">
                                        <div class="card-body p-3 d-flex align-items-center">
                                            <div id="connectTcp1" <c:if test="${lockStatus=='1'}">style="display: none;"</c:if>>
                                                <button type="button" class="btn btn-success" onclick="connectTcp(1, this)">连接柜子</button>
                                            </div>
                                            <div <c:if test="${lockStatus=='2'}">style="display: none;"</c:if> id="closeTcp1">
                                                <button type="button" class="btn btn-success" onclick="closeTcp(1, this)">断开柜子</button>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>

                        </div>
                        <div class="card-block row">
                            <c:if test="${doorDeviceBean != null}">
                                <div class="col-md-6">
                                    <div class="card">
                                        <div class="card-body p-3 d-flex align-items-center">
                                            <i class="fa fa-lock bg-info p-3 font-2xl mr-3"></i>
                                            <div class="mr-5">
                                                <div class="text-value-sm text-primary">大门</div>
                                                <div class="text-muted text-uppercase font-weight-bold small">
                                                    <c:if test="${doorDeviceBean.status == 'Relayon'}">
                                                        <span class="badge badge-success">开门中</span>
                                                    </c:if>
                                                    <c:if test="${doorDeviceBean.status == 'Relayoff'}">
                                                        <span class="badge badge-danger">锁门中</span>
                                                    </c:if>
                                                </div>
                                            </div>
                                            <div>
                                                <c:if test="${doorDeviceBean.status == 'Relayon'}">
                                                    <button type="button" class="btn btn-danger">关 门</button>
                                                </c:if>
                                                <c:if test="${doorDeviceBean.status == 'Relayoff'}">
                                                    <button type="button" class="btn btn-success">开 门</button>
                                                </c:if>
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
                            </c:if>
                            <c:forEach var="d" items="${deviceControls}">
                                <div class="col-md-6">
                                    <div class="card">
                                        <div class="card-body p-3 d-flex align-items-center">
                                            <i class="fa fa-lock bg-info p-3 font-2xl mr-3"></i>
                                            <div class="mr-5">
                                                <div class="text-value-sm text-primary">${d.name}</div>
                                                <div class="text-muted text-uppercase font-weight-bold small">
                                                    <c:if test="${d.status == 'Relayon'}">
                                                        <span class="badge badge-success">开锁中</span>
                                                    </c:if>
                                                    <c:if test="${d.status == 'Relayoff'}">
                                                        <span class="badge badge-danger">关锁中</span>
                                                    </c:if>
                                                </div>
                                            </div>
                                            <div>
                                                <c:if test="${d.status == 'Relayon'}">
                                                    <button class="btn btn-danger">关 锁</button>
                                                </c:if>
                                                <c:if test="${d.status == 'Relayoff'}">
                                                    <button class="btn btn-success">开 锁</button>
                                                </c:if>
                                            </div>
                                        </div>
                                        <div class="card-footer px-3 py-2">
                                            <a class="btn-block text-muted d-flex justify-content-between align-items-center" href="#">
                                                <span class="small font-weight-bold">查看开关锁日志</span>
                                                <i class="fa fa-angle-right"></i>
                                            </a>
                                        </div>
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
