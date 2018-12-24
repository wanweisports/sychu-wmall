<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ page import="com.wardrobe.layout.Blocks" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> <%-- JSTL表达式（判断，循环，输出） --%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %> <%-- 方法表达式（字符串截取，替换） --%>
<%@ taglib uri="http://www.sychu.com/tags/tag" prefix="layout" %>

<layout:override name="<%=Blocks.BLOCK_HEADER_CSS%>">
    <style type="text/css">
        .distribution-list th {
            padding: 0.75rem;
        }
        .distribution-list td {
            padding: 0.3rem 0.75rem;
        }
    </style>
</layout:override>

<layout:override name="<%=Blocks.BLOCK_HEADER_SCRIPTS%>">
    <script type="text/javascript" src="Content/js/require.js?v=${static_resource_version}"
            data-main="Content/js/app/distribution/settings.js?v=${static_resource_version}"></script>

    <script type="text/javascript">
        function delDistributionCommodity(dbid){
            if(window.confirm("确认移除吗？")) {
                $.post("/admin/distribution/delLockCommodity", {dbid: dbid}, function (res) {
                    if(res.code == 1){
                        window.location.reload();
                    }else{
                        alert(res.message);
                    }
                });
            }
        }

        function saveDistributionCommodity(){
            if(!$('#distribution_cid').val()){
                alert("请选择衣服");
                return;
            }
            if(!$('#distribution_dcid').val()){
                alert("请选择柜子");
                return;
            }
            if(!$('#distribution_size').val()){
                alert("请选择尺码");
                return;
            }
            if(!$('#distribution_code').val()){
                alert("请扫描标签");
                return;
            }
            $("#distribution_dbTime").val($("#dbTime").val());
            if(!$("#distribution_dbTime").val()){
                alert("请选择日期");
                return;
            }
            if(window.confirm("确认保存吗？")) {
                $.post("/admin/distribution/saveLockCommodity", $("#distribution_form").serialize(), function (res) {
                    if(res.code == 1){
                        window.location.reload();
                    }else{
                        alert(res.message);
                    }
                });
            }
        }

        function showPushCommodity(dcid){
            $("#distribution_dcid").val(dcid);
        }

        function readEpcLabel(btn){
            if(!'${did}'){
                alert("请选择商场");
                return;
            }
            $(btn).prop("disabled", true).text("正在读取..");
            $("#distribution_code").val("");
            $("#rfidEpc_error").html("");
            $.post("/rfid/readEpcLabelCK", {did: '${did}'}, function(res){
                if(res.code == 1){
                    $("#distribution_code").val(res.data.epcs);
                }else{
                    $("#rfidEpc_error").html(res.message);
                }
                $(btn).prop("disabled", false).text("扫描标签");
            });
        }

        function updateRfidEpc(dbid, obj){
            if(window.confirm("确认保存标签码吗？")) {
                $.post("/admin/distribution/updateRfidEpc", {dbid: dbid, rfidEpc: $(obj).val()}, function (res) {
                    alert(res.message);
                    if(res.code != 1){
                        window.location.reload();
                    }
                });
            }
        }

        function updateDistributionDate(btn){
            var dbTime = $("#dbTime").val();
            if(!dbTime){
                alert("请选择配送时间");
                return;
            }
            if(window.confirm("确认将" + dbTime + "的配送商品更新到今天吗？")) {
                $(btn).prop("disabled", true).text("正在处理..");
                $.post("/admin/distribution/updateDistributionDate", {dbTime: dbTime}, function (res) {
                    alert(res.message);
                    if(res.code == 1){
                        window.location.reload();
                    }else{
                        $(btn).prop("disabled", false).html('<i class="fa"></i> 更 新');
                    }
                });
            }
        }
    </script>
</layout:override>

<layout:override name="<%=Blocks.BLOCK_BODY%>">
    <div class="modal fade" id="distribution_enter" tabindex="-1" role="dialog">
        <div class="modal-dialog modal-default" role="document">
            <div class="modal-content">
                <div class="modal-body">
                    <form id="distribution_form" method="post" class="form-horizontal" novalidate onsubmit="return false;">
                        <input type="hidden" class="form-control" id="distribution_cid" name="cid">
                        <input type="hidden" class="form-control" id="distribution_dcid" name="dcid">
                        <input type="hidden" class="form-control" id="distribution_dbTime" name="dbTime">
                        <div class="form-group row">
                            <label class="col-md-3 form-control-label" for="distribution_product">
                                <span class="text-danger">*</span> 选择商品
                            </label>
                            <div class="col-md-6">
                                <input type="text" class="form-control" id="distribution_product" placeholder="请选择商品" name="commName"
                                       data-val="true" data-val-required="请选择商品" autocomplete="off" readonly>
                                <div data-valmsg-for="commName" data-valmsg-replace="true"></div>
                            </div>
                            <div class="col-md-2">
                                <button type="button" class="btn btn-sm btn-primary" id="distribution_product_query" data-toggle="modal" data-target="#distribution_query_list">
                                    <i class="fa fa-search"></i> 检 索
                                </button>
                            </div>
                        </div>
                        <div class="form-group row">
                            <label class="col-md-3 form-control-label" for="distribution_roi">
                                <span class="text-danger">*</span> 选择预约单
                            </label>
                            <div class="col-md-9">
                                <select class="form-control" id="distribution_roi" name="roid" data-val="true" data-val-required="请选择预约单">
                                    <option value="">公共衣服</option>
                                    <c:forEach var="roi" items="${reserveOrders}">
                                        <option value="${roi.roid}">${roi.rno}</option>
                                    </c:forEach>
                                </select>
                                <div data-valmsg-for="size" data-valmsg-replace="true"></div>
                            </div>
                        </div>
                        <div class="form-group row">
                            <label class="col-md-3 form-control-label" for="distribution_size">
                                <span class="text-danger">*</span> 选择尺码
                            </label>
                            <div class="col-md-9">
                                <select class="form-control" id="distribution_size" name="sid" data-val="true" data-val-required="请选择尺码">
                                    <option value="">选择尺码</option>
                                </select>
                                <div data-valmsg-for="size" data-valmsg-replace="true"></div>
                            </div>
                        </div>
                        <div class="form-group row">
                            <label class="col-md-3 form-control-label" for="distribution_code">
                                <span class="text-danger">*</span> 射频编码
                            </label>
                            <div class="col-md-9">
                                <input type="text" class="form-control" id="distribution_code" placeholder="射频编码" name="rfidEpc"
                                       data-val="true" data-val-required="射频编码不能为空" autocomplete="off" <%--readonly--%>>
                                <div data-valmsg-for="code" data-valmsg-replace="true"></div>
                            </div>
                            <%--<div class="col-md-3">
                                <button class="btn" type="button" onclick="readEpcLabel(this)">扫描标签</button>
                            </div>--%>
                        </div>
                        <div class="form-group row">
                            <label class="col-md-3 form-control-label" for="distribution_code"></label>
                            <div class="col-md-6">
                                <div data-valmsg-for="code" data-valmsg-replace="true">
                                    <font color="red" id="rfidEpc_error"></font>
                                </div>
                            </div>
                        </div>
                    </form>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-sm btn-secondary" data-dismiss="modal">
                        <i class="fa fa-close"></i> 取 消
                    </button>
                    <button type="button" class="btn btn-sm btn-primary save-wardrobe" onclick="saveDistributionCommodity()">
                        <i class="fa fa-check"></i> 保 存
                    </button>
                </div>
            </div>
        </div>
    </div>

    <div class="modal fade" id="distribution_query_list" tabindex="-1" role="dialog">
        <div class="modal-dialog modal-default modal-lg" role="document" style="max-width: 1024px !important;">
            <div class="modal-content">
                <div class="modal-body">
                    <iframe src="/admin/distribution/products" style="width: 100%; height: 500px" id="distribution_iframe"></iframe>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-sm btn-secondary" id="distribution_query_list_hide" data-dismiss="modal">
                        <i class="fa fa-close"></i> 取 消
                    </button>
                </div>
            </div>
        </div>
    </div>

    <div class="container-fluid">
        <div class="animated fadeIn">
            <div class="row">
                <div class="col-md-12">
                    <div class="card">
                        <div class="card-header">
                            <strong>配送设置</strong>
                            <small>Distribution Settings</small>
                        </div>
                        <div class="card-block">
                            <form id="distribution_query_form" method="post" class="form-horizontal" action="/admin/distribution/settings" novalidate <%--onsubmit="return false;"--%>>
                                <div class="form-group row">
                                    <div class="col-md-2">
                                        <input type="text" class="form-control" placeholder="配送日期" value="${dbTime}" id="dbTime" name="dbTime">
                                    </div>
                                    <div class="col-md-3">
                                        <select class="form-control" name="did">
                                            <option value="">全部</option>
                                            <c:forEach var="device" items="${deviceList}">
                                                <option value="${device.did}" <c:if test="${device.did == did}">selected</c:if>>${device.name}</option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                    <div class="col-md-1">
                                        <button type="submit" class="btn btn-primary distribution-query-btn">
                                            <i class="fa fa-search"></i> 检 索
                                        </button>
                                        <button type="submit" class="btn btn-primary distribution-copy-btn">
                                            <i class="fa fa-copy"></i> 复制昨日数据
                                        </button>
                                    </div>
                                    <div class="col-md-1">
                                        <button type="button" class="btn" id="distribution_update_btn" onclick="updateDistributionDate(this)">
                                            <i class="fa"></i> 更 新
                                        </button>
                                    </div>
                                </div>
                            </form>
                        </div>
                        <div class="card-footer text-right"></div>
                        <div class="card-block row">
                            <c:forEach var="deviceControl" items="${deviceControlList}">
                            <div class="col-sm-6 col-md-6">
                                <div class="card">
                                    <div class="card-header">
                                        ${deviceControl.name}
                                        <span class="badge badge-success float-right">已放入${deviceControl.cdCount}件</span>
                                    </div>
                                    <div class="card-body">
                                        <table class="table table-striped table-sm distribution-list">
                                            <thead>
                                            <tr>
                                                <th>衣服</th>
                                                <th>尺码</th>
                                                <th>射频编码</th>
                                                <th>预约单号</th>
                                                <th>状态</th>
                                                <th>&nbsp;</th>
                                            </tr>
                                            </thead>
                                            <tbody>
                                            <c:forEach var="comm" items="${deviceControl.commoditys}">
                                                <tr data-id="">
                                                    <td>${comm.commName}</td>
                                                    <td>${comm.size}</td>
                                                    <td>
                                                        <input type="text" class="form-control" value="${comm.rfidEpc}" onchange="updateRfidEpc('${comm.dbid}', this)" />
                                                    </td>
                                                    <th>${comm.rno != null ? comm.rno : '-'}</th>
                                                    <td>
                                                        <c:if test="${comm.status == '1'}">
                                                            正常
                                                        </c:if>
                                                        <c:if test="${comm.status == '2'}">
                                                            已出售
                                                        </c:if>
                                                    </td>
                                                    <td>
                                                        <a href="javascript:;" class="btn btn-sm btn-danger" onclick="delDistributionCommodity('${comm.dbid}')">
                                                            <i class="fa fa-remove"></i> 移 除
                                                        </a>
                                                    </td>
                                                </tr>
                                            </c:forEach>
                                            </tbody>
                                        </table>
                                    </div>
                                    <div class="card-footer">
                                        <button type="button" class="btn btn-sm btn-primary" data-target="#distribution_enter" data-toggle="modal" onclick="showPushCommodity('${deviceControl.dcid}')">
                                            <i class="fa fa-plus"></i> 放入衣服
                                        </button>
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
    <c:param name="menu" value="distribution"/>
    <c:param name="subMenu" value="settings"/>
</c:import>
