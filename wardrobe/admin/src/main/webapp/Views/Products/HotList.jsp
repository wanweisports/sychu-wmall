<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ page import="com.wardrobe.layout.Blocks" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> <%-- JSTL表达式（判断，循环，输出） --%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %> <%-- 方法表达式（字符串截取，替换） --%>
<%@ taglib uri="http://www.sychu.com/tags/tag" prefix="layout" %>

<layout:override name="<%=Blocks.BLOCK_HEADER_CSS%>">
    <style type="text/css">
        .products-list th {
            padding: 0.75rem;
        }
        .products-list td {
            padding: 0.3rem 0.75rem;
        }
        .img-rounded {
            height: 2rem;
        }
    </style>
</layout:override>

<layout:override name="<%=Blocks.BLOCK_HEADER_SCRIPTS%>">
    <script type="text/javascript" src="Content/js/require.js?v=${static_resource_version}"
            data-main="Content/js/app/products/hotList.js?v=${static_resource_version}"></script>
</layout:override>

<layout:override name="<%=Blocks.BLOCK_BODY%>">
    <div class="container-fluid">
        <div class="animated fadeIn">
            <div class="row">
                <div class="col-md-12">
                    <div class="card">
                        <div class="card-header">
                            <ol class="breadcrumb" style="background: inherit;padding: 0;margin: 0;">
                                <c:if test="${hot=='1'}">
                                    <li class="breadcrumb-item">
                                        <i class="icon-tag"></i>
                                        <strong>热门商品</strong>
                                    </li>
                                    <li class="breadcrumb-item">
                                        <a href="/admin/products/hot/list?newly=1">
                                            <strong>最新商品</strong>
                                        </a>
                                    </li>
                                </c:if>
                                <c:if test="${newly=='1'}">
                                    <li class="breadcrumb-item">
                                        <a href="/admin/products/hot/list?hot=1">
                                            <strong>热门商品</strong>
                                        </a>
                                    </li>
                                    <li class="breadcrumb-item">
                                        <i class="icon-tag"></i>
                                        <strong>最新商品</strong>
                                    </li>
                                </c:if>
                            </ol>
                        </div>
                        <div class="card-block">
                            <table class="table table-striped table-sm table-bordered products-list">
                                <thead>
                                <tr>
                                    <th>商品ID</th>
                                    <th>商品编号</th>
                                    <th>商品图片</th>
                                    <th>商品名称</th>
                                    <th>商品品类</th>
                                    <th>商品材质</th>
                                    <th>原价</th>
                                    <th>优惠价</th>
                                    <th>已售</th>
                                    <th>商品状态</th>
                                    <th>操作</th>
                                </tr>
                                </thead>
                                <c:forEach var="c" items="${page.list}" varStatus="status">
                                    <tr data-id="">
                                        <td>${c.cid}</td>
                                        <td>${c.commNo}</td>
                                        <td><img src="${c.resourcePath}" alt="商品名称" class="img-rounded"></td>
                                        <td>
                                            <a href="/admin/products/detail?cid=${c.cid}" class="btn btn-sm btn-link" title="商品名称">${c.commName}</a>
                                        </td>
                                        <td>${c.styleName}</td>
                                        <td>${c.materialName}</td>
                                        <td>￥${c.price}</td>
                                        <td>￥${c.couPrice}</td>
                                        <td>${c.saleCount}件</td>
                                        <td>
                                            <span class="badge <c:if test="${c.status=='1'}">badge-success</c:if><c:if test="${c.status!='1'}">badge-danger</c:if>">${c.statusName}</span>
                                        </td>
                                        <td>
                                            <c:if test="${c.status=='1'}">
                                                <c:if test="${hot=='1'}">
                                                    <c:if test="${c.hot == '1'}">
                                                        <a href="javascript:;" class="btn btn-sm btn-danger product-hot-cancel js-hot-down" title="取消热门" data-id="${c.cid}">
                                                            <i class="fa fa-remove"></i> 取消
                                                        </a>
                                                    </c:if>
                                                </c:if>
                                                <c:if test="${newly=='1'}">
                                                    <c:if test="${c.newly == '1'}">
                                                        <a href="javascript:;" class="btn btn-sm btn-danger product-users-cancel js-newly-down" title="取消最新" data-id="${c.cid}">
                                                            <i class="fa fa-remove"></i> 取消
                                                        </a>
                                                    </c:if>
                                                </c:if>
                                                <c:if test="${banner=='1'}">
                                                    <c:if test="${c.banner == '1'}">
                                                        <a href="javascript:;" class="btn btn-sm btn-danger product-banner-cancel js-newly-down" title="取消banner" data-id="${c.cid}">
                                                            <i class="fa fa-remove"></i> 取消
                                                        </a>
                                                    </c:if>
                                                </c:if>
                                            </c:if>
                                        </td>
                                    </tr>
                                </c:forEach>
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
    <c:param name="menu" value="products"/>
    <c:param name="subMenu" value="hot"/>
</c:import>
