<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ page import="com.wardrobe.layout.Blocks" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> <%-- JSTL表达式（判断，循环，输出） --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %> <%-- 方法表达式（字符串截取，替换） --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %> <%-- 方法表达式（字符串截取，替换） --%>
<%@ taglib uri="http://www.sychu.com/tags/tag" prefix="layout" %>

<layout:override name="<%=Blocks.BLOCK_HEADER_CSS%>">
    <style type="text/css">
        .orders-list th {
            padding: 0.75rem;
        }
        .orders-list td {
            padding: 0.75rem;
        }
    </style>
</layout:override>

<layout:override name="<%=Blocks.BLOCK_HEADER_SCRIPTS%>">
    <script type="text/javascript" src="Content/js/require.js?v=${static_resource_version}"
            data-main="Content/js/app/orders/list.js?v=${static_resource_version}"></script>

    <script type="text/javascript">
        function orderExpressFHShow(oid){
            $("#oid").val(oid);
        }

        function saveOrderExpressFH(){
            if(!$.trim($('#expressCompany').val())){
                alert("请输入快递公司名称！");
                return;
            }
            if(window.confirm("发货后不能修改，确认发货吗？(确认后，会给此订单用户发送消息推送)")){
                $.post("/admin/orders/orderExpressFH", $("#express_form").serialize(), function (res) {
                    if(res.code == 1){
                        window.location.reload();
                    }else{
                        alert(res.message);
                    }
                });
            }
        }
    </script>
</layout:override>

<layout:override name="<%=Blocks.BLOCK_BODY%>">
    <div class="modal fade" id="wardrobe_settings" tabindex="-1" role="dialog">
        <div class="modal-dialog modal-default" role="document">
            <div class="modal-content">
                <div class="modal-body">
                    <form id="express_form" method="post" class="form-horizontal" novalidate onsubmit="return false;">
                        <input type="hidden" id="oid" name="oid">
                        <div class="form-group row">
                            <label class="col-md-3 form-control-label" for="expressCompany">
                                <span class="text-danger">*</span> 快递公司
                            </label>
                            <div class="col-md-9">
                                <input type="text" class="form-control" id="expressCompany" placeholder="请输入快递公司名称" name="expressCompany"
                                       data-val="true" data-val-required="快递公司名称不能为空" autocomplete="off"
                                       data-val-length-max="30" data-val-length-min="2" data-val-length="快递公司名称必须包含 2~30 个字符">
                                <div data-valmsg-for="expressCompany" data-valmsg-replace="true"></div>
                            </div>
                        </div>
                        <%--<div class="form-group row">
                            <label class="col-md-3 form-control-label" for="wardrobe_name">
                                <span class="text-danger">*</span> 发货时间
                            </label>
                            <div class="col-md-9">
                                <!--年-月-日 小时:分钟:秒-->
                                <input type="text" id="endTime" name="endTime" class="form-control" placeholder="请选择发货时间" autocomplete="off">
                            </div>
                        </div>--%>
                    </form>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-sm btn-secondary" data-dismiss="modal">
                        <i class="fa fa-close"></i> 取 消
                    </button>
                    <button type="button" class="btn btn-sm btn-primary save-wardrobe" onclick="saveOrderExpressFH()">
                        <i class="fa fa-check"></i> 保 存
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
                            <strong>订单列表</strong>
                            <small>Orders List</small>
                        </div>
                        <div class="card-block">
                            <form id="members_query_form" method="post" class="form-horizontal" action="/admin/orders/list" novalidate <%--onsubmit="return false;"--%>>
                                <div class="form-group row">
                                    <div class="col-md-3">
                                        <input type="text" name="ono" class="form-control" placeholder="订单编号" value="${ono}">
                                    </div>
                                    <div class="col-md-9">
                                        <button type="submit" class="btn btn-primary members-query-btn">
                                            <i class="fa fa-search"></i> 检 索
                                        </button>
                                    </div>
                                </div>
                            </form>
                        </div>
                        <div class="card-footer text-right"></div>
                        <div class="card-block">
                            <table class="table table-striped table-bordered table-sm orders-list">
                                <thead>
                                <tr>
                                    <th>订单ID</th>
                                    <th>订单编号</th>
                                    <th>订单时间</th>
                                    <th>订单金额</th>
                                    <th>会员姓名</th>
                                    <th>支付状态</th>
                                    <th>订单状态</th>
                                    <th>发货信息</th>
                                    <th>操作</th>
                                </tr>
                                </thead>
                                <tbody>
                                    <c:forEach var="o" items="${page.list}">
                                        <tr>
                                            <td>${o.oid}</td>
                                            <td>
                                                <a href="/admin/orders/detail?oid=${o.oid}">${o.ono}</a>
                                            </td>
                                            <td><fmt:formatDate value="${o.createTime}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
                                            <td>${o.priceSum}</td>
                                            <td>${o.nickname}</td>
                                            <td>
                                                <c:if test="${o.payStatus=='1'}">
                                                    <span class="badge badge-success">${o.payStatusName}</span>
                                                </c:if>
                                                <c:if test="${o.payStatus=='2'}">
                                                    <span class="badge badge-danger" style="background-color: burlywood;">${o.payStatusName}</span>
                                                </c:if>
                                                <c:if test="${o.payStatus=='3'}">
                                                    <span class="badge badge-danger">${o.payStatusName}</span>
                                                </c:if>
                                            </td>
                                            <td>${o.statusName}</td>
                                            <td>
                                                <c:if test="${o.status == 5}">
                                                    ${o.expressCompany}：<fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${o.expressTime}" />
                                                </c:if>
                                                <c:if test="${o.status != 5}">-</c:if>
                                            </td>
                                            <td>
                                                <c:if test="${o.payStatus==1 && o.status==1}">
                                                    <a href="#wardrobe_settings" class="btn btn-sm btn-primary wardrobe-set" data-toggle="modal" onclick="orderExpressFHShow('${o.oid}')">
                                                        <i class="fa fa-cog"></i> 发货
                                                    </a>
                                                </c:if>
                                            </td>
                                        </tr>
                                    </c:forEach>
                                </tbody>
                            </table>
                            <div>
                                <%@ include file="../Shared/Pagination.jsp" %>
                            </div>
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
    <c:param name="menu" value="orders"/>
    <c:param name="subMenu" value="list"/>
</c:import>
