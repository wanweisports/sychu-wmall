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
                                <c:if test="${type=='hot'}">
                                    <li class="breadcrumb-item">
                                        <strong>热门商品</strong>
                                        <small>Hot Products</small>
                                    </li>
                                    <li class="breadcrumb-item">
                                        <a href="/admin/products/hot/list?type=users">
                                            <strong>人气商品</strong>
                                            <small>Users Products</small>
                                        </a>
                                    </li>
                                </c:if>
                                <c:if test="${type=='users'}">
                                    <li class="breadcrumb-item">
                                        <a href="/admin/products/hot/list?type=hot">
                                            <strong>热门商品</strong>
                                            <small>Hot Products</small>
                                        </a>
                                    </li>
                                    <li class="breadcrumb-item">
                                        <strong>人气商品</strong>
                                        <small>Users Products</small>
                                    </li>
                                </c:if>
                            </ol>
                        </div>
                        <div class="card-block">
                            <table class="table table-striped table-sm products-list">
                                <thead>
                                <tr>
                                    <th>商品图片</th>
                                    <th>商品名称</th>
                                    <th>商品风格</th>
                                    <th>商品原价</th>
                                    <th>优惠价格</th>
                                    <th>已售数量</th>
                                    <th></th>
                                </tr>
                                </thead>
                                <tbody>
                                <tr data-id="">
                                    <td><img src="/Content/images/avatars/1.jpg" alt="商品名称" class="img-rounded"></td>
                                    <td>
                                        <a href="/admin/products/detail?productId=1" class="btn btn-sm btn-link" title="商品名称">骆驼女士优雅风衣</a>
                                    </td>
                                    <td>约会,度假,休闲</td>
                                    <td>￥600</td>
                                    <td>￥399</td>
                                    <td>2012</td>
                                    <td>
                                        <c:if test="${type=='users'}">
                                            <a href="javascript:;" class="btn btn-sm btn-danger product-users-cancel" title="取消人气">
                                                <i class="fa fa-remove"></i> 人气取消
                                            </a>
                                        </c:if>
                                        <c:if test="${type=='hot'}">
                                            <a href="javascript:;" class="btn btn-sm btn-danger product-hot-cancel" title="取消热门">
                                                <i class="fa fa-remove"></i> 热门取消
                                            </a>
                                        </c:if>
                                    </td>
                                </tr>
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
    <c:param name="menu" value="products"/>
    <c:param name="subMenu" value="hot"/>
</c:import>
