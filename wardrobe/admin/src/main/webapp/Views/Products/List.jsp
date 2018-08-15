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
        .img-rounded {
            width: 2rem;
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
                            <strong>商品列表</strong>
                            <small>Products List</small>
                        </div>
                        <div class="card-block">
                            <form id="products_query_form" method="post" class="form-horizontal" action="/admin/products/list" novalidate onsubmit="return false;">
                                <div class="form-group row">
                                    <div class="col-md-2">
                                        <input type="text" name="name" class="form-control" placeholder="商品名称">
                                    </div>
                                    <div class="col-md-2">
                                        <select class="form-control" name="status">
                                            <option>全部商品</option>
                                            <option>已上架</option>
                                            <option>已下架</option>
                                        </select>
                                    </div>
                                    <div class="col-md-8">
                                        <button type="submit" class="btn btn-primary products-query-btn">
                                            <i class="fa fa-search"></i> 检 索
                                        </button>
                                        <a href="/admin/products/add" class="btn btn-primary">
                                            <i class="fa fa-plus"></i> 添加商品
                                        </a>
                                    </div>
                                </div>
                            </form>
                        </div>
                        <div class="card-footer text-right"></div>
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
                                    <th>商品状态</th>
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
                                    <td>
                                        <a href="/admin/products/transaction/records?productId=1" class="btn btn-sm btn-link">2012件</a>
                                    </td>
                                    <td><span class="badge badge-success">已上架</span></td>
                                    <td>
                                        <a href="javascript:;" class="btn btn-sm btn-danger" title="下架">
                                            <i class="fa fa-level-down"></i> 下架
                                        </a>
                                        <a href="javascript:;" class="btn btn-sm btn-primary" title="人气">
                                            <i class="fa fa-heart"></i> 人气
                                        </a>
                                        <a href="javascript:;" class="btn btn-sm btn-primary" title="热门">
                                            <i class="fa fa-bolt"></i> 热门
                                        </a>
                                    </td>
                                </tr>
                                <tr data-id="">
                                    <td><img src="/Content/images/avatars/1.jpg" alt="商品名称" class="img-rounded"></td>
                                    <td>
                                        <a href="/admin/products/detail?productId=1" class="btn btn-sm btn-link" title="商品名称">骆驼女士优雅风衣</a>
                                    </td>
                                    <td>约会,度假,休闲</td>
                                    <td>￥600</td>
                                    <td>￥399</td>
                                    <td>
                                        <a href="/admin/products/transaction/records?productId=1" class="btn btn-sm btn-link">2012件</a>
                                    </td>
                                    <td><span class="badge badge-danger">已下架</span></td>
                                    <td>
                                        <a href="javascript:;" class="btn btn-sm btn-primary" title="上架">
                                            <i class="fa fa-level-up"></i> 上架
                                        </a>
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
    <c:param name="subMenu" value="list"/>
</c:import>
