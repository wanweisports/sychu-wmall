<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ page import="com.wardrobe.layout.Blocks" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> <%-- JSTL表达式（判断，循环，输出） --%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %> <%-- 方法表达式（字符串截取，替换） --%>
<%@ taglib uri="http://www.sychu.com/tags/tag" prefix="layout" %>

<layout:override name="<%=Blocks.BLOCK_HEADER_CSS%>">
    <style type="text/css">
        .product-image-file {
            position: absolute;
            left: 0;
            top: 0;
            width: 100%;
            height: 100%;
            opacity: 0;
        }
        .size-list th {
            padding: 0.75rem;
        }
        .size-list td {
            padding: 0.3rem 0.75rem;
        }
        .product-info th {
            width: 9rem;
        }
    </style>
</layout:override>

<layout:override name="<%=Blocks.BLOCK_HEADER_SCRIPTS%>">
    <script type="text/javascript" src="Content/js/require.js?v=${static_resource_version}"
            data-main="Content/js/app/orders/detail.js?v=${static_resource_version}"></script>
    <script type="text/javascript">

    </script>
</layout:override>

<layout:override name="<%=Blocks.BLOCK_BODY%>">
    <div class="container-fluid">
        <div class="animated fadeIn">
            <div class="row">
                <div class="col-md-12">
                    <div class="card">
                        <div class="card-header">
                            <strong>订单详情</strong>
                            <small>Order Information</small>
                        </div>
                        <div class="card-block">
                            <table class="table table-responsive-sm table-bordered product-info">
                                <tbody>
                                <tr>
                                    <th>订单编号：</th>
                                    <td>${order.ono}</td>
                                    <th>订单类型：</th>
                                    <td colspan="5">
                                        <c:if test="${order.orderType=='1'}">
                                            商品订单
                                        </c:if>
                                    </td>
                                </tr>
                                <tr>
                                    <th>订单总金额：</th>
                                    <td>${order.priceSum}</td>
                                    <th>订单支付金额：</th>
                                    <td colspan="5">${order.payPrice}</td>
                                </tr>
                                <tr>
                                    <th>支付状态：</th>
                                    <td>${order.payStatus}</td>
                                    <th>订单状态：</th>
                                    <td>${order.status}</td>
                                </tr>
                                <tr>
                                    <th>支付时间</th>
                                    <td colspan="5">${order.payTime}</td>
                                </tr>
                                <tr>
                                    <th>收货人姓名：</th>
                                    <td>${order.expressName}</td>
                                    <th>收货人电话：</th>
                                    <td colspan="5">${order.expressMobile}</td>
                                </tr>
                                <tr>
                                    <th>收货人详细地址：</th>
                                    <td colspan="5">${order.expressAddress}</td>
                                </tr>
                                <tr>
                                    <th>运费：</th>
                                    <td colspan="5">${order.freight}</td>
                                </tr>
                                <tr>
                                    <th>使用优惠券：</th>
                                    <td colspan="5">${couponDesc}</td>
                                </tr>
                                <tr>
                                    <th>使用薏米：</th>
                                    <td colspan="5">${order.ycoid}</td>
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
                            <table class="table table-responsive-sm table-bordered size-list">
                                <thead>
                                <tr>
                                    <th>商品图片</th>
                                    <th>商品id</th>
                                    <th>尺码id</th>
                                    <th>商品名称</th>
                                    <th>商品颜色</th>
                                    <th>商品尺码</th>
                                    <th>商品单价</th>
                                    <th>购买数量</th>
                                    <th>商品总价</th>
                                </tr>
                                </thead>
                                <tbody id="tbody">
                                    <c:forEach var="d" items="${order.userOrderDetails}">
                                    <tr data-id="${d.odid}">
                                        <td>
                                            <img class="img-rounded" src="${d.itemImg}">
                                        </td>
                                        <td>${d.cid}</td>
                                        <td>${d.sid}</td>
                                        <td>${d.itemName}</td>
                                        <td>${d.itemColor}</td>
                                        <td>${d.itemSize}</td>
                                        <td>${d.itemPrice}</td>
                                        <td>${d.itemCount}</td>
                                        <td>${d.itemPriceSum}</td>
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
    <c:param name="menu" value="products"/>
    <c:param name="subMenu" value="detail"/>
</c:import>
