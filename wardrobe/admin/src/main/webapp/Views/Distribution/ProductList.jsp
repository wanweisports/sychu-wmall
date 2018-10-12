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
        .app-body {
            margin-top: 15px;
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
                            <form id="products_query_form" method="post" class="form-horizontal" action="/admin/products/list"<%-- novalidate onsubmit="return false;"--%>>
                                <input type="hidden" name="groupId" value="${groupId}" />
                                <div class="form-group row">
                                    <div class="col-md-4">
                                        <input type="text" name="commName" class="form-control" placeholder="商品名称" value="${commName}">
                                    </div>
                                    <div class="col-md-4">
                                        <select class="form-control" name="status">
                                            <option value>全部商品</option>
                                            <option value="1" <c:if test="${status=='1'}">selected</c:if>>已上架</option>
                                            <option value="2" <c:if test="${status=='2'}">selected</c:if>>已下架</option>
                                        </select>
                                    </div>
                                    <div class="col-md-4">
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
                                    <th>商品品类</th>
                                    <th>商品原价</th>
                                    <th></th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach var="c" items="${page.list}" varStatus="status">
                                <tr data-id="">
                                    <td><img src="${c.resourcePath}" alt="${c.commName}" class="img-rounded"></td>
                                    <td>${c.commName}</td>
                                    <td>${c.styleName}</td>
                                    <td>￥${c.price}</td>
                                    <td>
                                        <a href="javascript:;" class="btn btn-sm btn-primary product-enter" title="放入" data-id="${c.cid}" data-name="${c.commName}">
                                            <i class="fa fa-level-down"></i> 放入
                                        </a>
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

<c:import url="../Shared/Layout.jsp">
    <c:param name="menu" value="products"/>
    <c:param name="subMenu" value="list"/>
</c:import>
