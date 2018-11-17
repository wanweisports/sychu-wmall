<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ page import="com.wardrobe.layout.Blocks" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> <%-- JSTL表达式（判断，循环，输出） --%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %> <%-- 方法表达式（字符串截取，替换） --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %> <%-- 方法表达式（字符串截取，替换） --%>
<%@ taglib uri="http://www.sychu.com/tags/tag" prefix="layout" %>

<layout:override name="<%=Blocks.BLOCK_HEADER_CSS%>">
    <style type="text/css">
        .size-list th {
            padding: 0.75rem;
        }
        .size-list td {
            padding: 0.75rem;
        }
        .product-info th {
            width: 9rem;
        }
        .img-rounded {
            height: 2rem;
        }
    </style>
</layout:override>

<layout:override name="<%=Blocks.BLOCK_HEADER_SCRIPTS%>">
    <script type="text/javascript" src="Content/js/require.js?v=${static_resource_version}"
            data-main="Content/js/app/orders/detail.js?v=${static_resource_version}"></script>
</layout:override>

<layout:override name="<%=Blocks.BLOCK_BODY%>">
    <div class="container-fluid">
        <div class="animated fadeIn">
            <div class="row">
                <div class="col-md-12">
                    <div class="card">
                        <div class="card-header">
                            <strong>预约订单详情</strong>
                            <small>Order Information</small>
                        </div>
                        <div class="card-block">
                            <table class="table table-responsive-sm table-bordered product-info">
                                <tbody>
                                <tr>
                                    <th>订单编号：</th>
                                    <td>${order.rno}</td>
                                    <th>订单状态：</th>
                                    <td>${order.status}</td>
                                </tr>
                                <tr>
                                    <th>试衣开始时间：</th>
                                    <td><fmt:formatDate value="${order.reserveStartTime}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
                                    <th>试衣结束时间：</th>
                                    <td><fmt:formatDate value="${order.reserveEndTime}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
                                </tr>
                                </tbody>
                            </table>
                        </div>
                    </div>
                    <div class="card">
                        <div class="card-header">
                            <strong>订单商品详情列表</strong>
                            <small>Commodity List</small>
                        </div>
                        <div class="card-block">
                            <table class="table table-striped table-sm table-bordered size-list">
                                <thead>
                                <tr>
                                    <th>商品图片</th>
                                    <th>商品编码</th>
                                    <th>商品名称</th>
                                    <th>商品颜色</th>
                                    <th>商品尺码</th>
                                    <th>商品单价</th>
                                    <th>购买数量</th>
                                    <th>商品总价</th>
                                </tr>
                                </thead>
                                <tbody id="tbody">
                                <c:forEach var="d" items="${order.reserveOrderDetails}">
                                    <tr data-id="${d.rdid}">
                                        <td>
                                            <img class="img-rounded" src="${d.resItemImg}">
                                        </td>
                                        <td>${d.commNo}</td>
                                        <td><a href="/admin/products/detail?cid=${d.cid}" class="btn btn-sm btn-link" title="${d.resItemName}">${d.resItemName}</a></td>
                                        <td>${d.resItemColor}</td>
                                        <td>${d.resItemSize}</td>
                                        <td>${d.resItemPrice}</td>
                                        <td>${d.resItemCount}</td>
                                        <td>${d.resItemPriceSum}</td>
                                    </tr>
                                </c:forEach>
                                </tbody>
                            </table>
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
    <c:param name="subMenu" value="reservation_detail"/>
</c:import>
