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
                            <strong>库存变更记录</strong>
                            <small>Sku Log</small>
                        </div>
                        <div class="card-block">
                            <form id="products_query_form" method="post" class="form-horizontal" action="/admin/products/sku/list" novalidate onsubmit="return false;">
                                <div class="form-group row">
                                    <div class="col-md-2">
                                        <select class="form-control" name="skuType">
                                            <option>全部类型</option>
                                            <option>商品入库</option>
                                            <option>商品出库</option>
                                            <option>商品销售</option>
                                        </select>
                                    </div>
                                    <div class="col-md-2">
                                        <input type="text" name="nickname" class="form-control" placeholder="开始时间">
                                    </div>
                                    <div class="col-md-2">
                                        <input type="text" name="nickname" class="form-control" placeholder="结束时间">
                                    </div>
                                    <div class="col-md-6">
                                        <button type="submit" class="btn btn-primary products-query-btn">
                                            <i class="fa fa-search"></i> 检 索
                                        </button>
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
                                    <th>商品规格</th>
                                    <th>变更类型</th>
                                    <th>变更数量</th>
                                    <th>变更备注</th>
                                    <th>变更时间</th>
                                </tr>
                                </thead>
                                <tbody>
                                <tr data-id="">
                                    <td><img src="/Content/images/avatars/1.jpg" alt="商品名称" class="img-rounded"></td>
                                    <td>
                                        <a href="/admin/products/detail?productId=1" class="btn btn-sm btn-link" title="商品名称">骆驼女士优雅风衣</a>
                                    </td>
                                    <td>颜色：红色，尺码：XL</td>
                                    <td>商品入库</td>
                                    <td class="text-success">+100</td>
                                    <td>张思思：入库</td>
                                    <td>2018-12-12 11:11:11</td>
                                </tr>
                                <tr data-id="">
                                    <td><img src="/Content/images/avatars/1.jpg" alt="商品名称" class="img-rounded"></td>
                                    <td>
                                        <a href="/admin/products/detail?productId=1" class="btn btn-sm btn-link" title="商品名称">骆驼女士优雅风衣</a>
                                    </td>
                                    <td>颜色：红色，尺码：XL</td>
                                    <td>商品出库</td>
                                    <td class="text-danger">-100</td>
                                    <td>张思思：商品破损，导致损耗数量</td>
                                    <td>2018-12-12 11:11:11</td>
                                </tr>
                                <tr data-id="">
                                    <td><img src="/Content/images/avatars/1.jpg" alt="商品名称" class="img-rounded"></td>
                                    <td>
                                        <a href="/admin/products/detail?productId=1" class="btn btn-sm btn-link" title="商品名称">骆驼女士优雅风衣</a>
                                    </td>
                                    <td>颜色：红色，尺码：XL</td>
                                    <td>商品销售</td>
                                    <td class="text-success">-100</td>
                                    <td>张思思：销售</td>
                                    <td>2018-12-12 11:11:11</td>
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
    <c:param name="subMenu" value="sku"/>
</c:import>
