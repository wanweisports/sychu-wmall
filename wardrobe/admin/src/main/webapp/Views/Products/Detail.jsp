<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ page import="com.wardrobe.layout.Blocks" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> <%-- JSTL表达式（判断，循环，输出） --%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %> <%-- 方法表达式（字符串截取，替换） --%>
<%@ taglib uri="http://www.sychu.com/tags/tag" prefix="layout" %>

<layout:override name="<%=Blocks.BLOCK_HEADER_CSS%>">
    <style type="text/css">
        .user-list th {
            padding: 0.75rem;
        }
        .user-list td {
            padding: 0.3rem 0.75rem;
        }
    </style>
</layout:override>

<layout:override name="<%=Blocks.BLOCK_HEADER_SCRIPTS%>">
    <script type="text/javascript" src="Content/js/require.js?v=${static_resource_version}"
            data-main="Content/js/app/products/list.js?v=${static_resource_version}"></script>
</layout:override>

<layout:override name="<%=Blocks.BLOCK_BODY%>">
    <div class="container-fluid">
        <div class="animated fadeIn">
            <div class="row">
                <div class="col-md-12">
                    <div class="card">
                        <div class="card-header">
                            <strong>商品详情</strong>
                            <small>Products Information</small>
                        </div>
                        <div class="card-block">
                            <table class="table table-responsive-sm table-bordered member-info">
                                <tbody>
                                <tr>
                                    <th>商品id：</th>
                                    <td>${product.cid}</td>
                                    <th>商品名称：</th>
                                    <td>${product.commName}</td>
                                    <th>销售数量：</th>
                                    <td>
                                        <a href="/admin/sku/list?cid=${product.cid}">${product.saleCount}</a>
                                    </td>
                                </tr>
                                <tr>
                                    <th>商品品类：</th>
                                    <td>${product.category}</td>
                                    <th>商品风格：</th>
                                    <td>${product.style}</td>
                                    <th>商品材质：</th>
                                    <td>${product.material}</td>
                                </tr>
                                <tr>
                                    <th>商品原价：</th>
                                    <td>${product.price}</td>
                                    <th>商品优惠价：</th>
                                    <td>${product.couPrice}</td>
                                    <th>商品组ID：</th>
                                    <td>${product.groupId}</td>
                                </tr>
                                <tr>
                                    <th>商品描述：</th>
                                    <td colspan="5">${product.productDesc}</td>
                                </tr>
                                <tr>
                                    <th>商品颜色：</th>
                                    <td></td>
                                </tr>
                                </tbody>
                            </table>
                        </div>
                    </div>
                    <div class="card">
                        <div class="card-header">
                            <strong>尺码列表</strong>
                            <small>Members Coupons</small>
                        </div>
                        <div class="card-block">
                            <table class="table table-responsive-sm table-bordered member-address">
                                <thead>
                                <tr>
                                    <th>##</th>
                                    <th>优惠券ID</th>
                                    <th>优惠券业务类型</th>
                                    <th>优惠券面值</th>
                                    <th>优惠券状态</th>
                                    <th>优惠券到期时间</th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach var="c" items="${userCoupon}" varStatus="status">
                                    <tr>
                                        <td>${status.index+1}</td>
                                        <td>${c.cpid}</td>
                                        <td>${c.dictValue}</td>
                                        <td>￥${c.couponPrice}</td>
                                        <td>
                                            <c:if test="${c.status == '1'}">已使用</c:if>
                                            <c:if test="${c.status != '1'}">未使用</c:if>
                                        </td>
                                        <td>${c.dueTime}</td>
                                    </tr>
                                </c:forEach>
                                </tbody>
                            </table>
                        </div>
                    </div>
                    <div class="card">
                        <div class="card-header">
                            <strong>收货地址</strong>
                            <small>Members Express Address</small>
                        </div>
                        <div class="card-block">
                            <table class="table table-responsive-sm table-bordered member-address">
                                <thead>
                                <tr>
                                    <th>##</th>
                                    <th>地址编号</th>
                                    <th>省市区</th>
                                    <th>详细地址</th>
                                    <th>邮编</th>
                                    <th>收货人姓名</th>
                                    <th>收货人手机</th>
                                    <th>是否默认</th>
                                    <th>设置时间</th>
                                </tr>
                                </thead>
                                <tbody>
                                <tr>
                                    <td>1</td>
                                    <td>U20180612101155</td>
                                    <td>山东省-潍坊市-坊子区</td>
                                    <td>舜王街道程戈庄村100号</td>
                                    <td>262202</td>
                                    <td>唐四毛</td>
                                    <td>158****3167</td>
                                    <td>是</td>
                                    <td>2018-07-24 14:46:33</td>
                                </tr>
                                <tr>
                                    <td>2</td>
                                    <td>U20180612101155</td>
                                    <td>山东省-潍坊市-坊子区</td>
                                    <td>舜王街道程戈庄村100号</td>
                                    <td>262202</td>
                                    <td>唐四毛</td>
                                    <td>158****3167</td>
                                    <td>是</td>
                                    <td>2018-07-24 14:46:33</td>
                                </tr>
                                <tr>
                                    <td>3</td>
                                    <td>U20180612101155</td>
                                    <td>山东省-潍坊市-坊子区</td>
                                    <td>舜王街道程戈庄村100号</td>
                                    <td>262202</td>
                                    <td>唐四毛</td>
                                    <td>158****3167</td>
                                    <td>是</td>
                                    <td>2018-07-24 14:46:33</td>
                                </tr>
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
    <c:param name="subMenu" value="details"/>
</c:import>
