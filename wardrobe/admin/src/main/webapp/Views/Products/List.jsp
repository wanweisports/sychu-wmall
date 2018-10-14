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
    <script type="text/javascript">
        function uoloadExcel(btn){
            $(btn).prop("disabled", true).text("正在上传..");
            $("#upload_form").ajaxSubmit({
                type: "post",
                dataType: "json",
                data: {},
                url: "/commodity/excelCommoditys",
                success: function (res) {
                    if(res.code == 1){
                        window.location.reload();
                    }else{
                        alert(res.message);
                        $(btn).prop("disabled", false).text("上传商品Excel");
                    }
                }
            });
        }
    </script>
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
                                    <div class="col-md-2">
                                        <input type="text" name="commName" class="form-control" placeholder="商品名称" value="${commName}">
                                    </div>
                                    <div class="col-md-2">
                                        <select class="form-control" name="status">
                                            <option value>全部商品</option>
                                            <option value="1" <c:if test="${status=='1'}">selected</c:if>>已上架</option>
                                            <option value="2" <c:if test="${status=='2'}">selected</c:if>>已下架</option>
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
                        <div class="card-footer text-right">
                            <form id="upload_form" enctype="multipart/form-data">
                                <input type="file" name="file1"><br/>
                                <button type="button" onclick="uoloadExcel(this)">上传商品Excel</button>
                                <a href="/commodity/downCommodityTpl" target="_blank">下载商品Excel模板</a>
                            </form>
                        </div>
                        <div class="card-block">
                            <table class="table table-striped table-sm products-list">
                                <thead>
                                <tr>
                                    <th>商品图片</th>
                                    <th>商品名称</th>
                                    <th>商品品类</th>
                                    <th>商品材质</th>
                                    <th>商品原价</th>
                                    <%--<th>优惠价格</th>--%>
                                    <th>已售数量</th>
                                    <th>商品状态</th>
                                    <th></th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach var="c" items="${page.list}" varStatus="status">
                                <tr data-id="">
                                    <td><img src="${c.resourcePath}" alt="${c.commName}" class="img-rounded"></td>
                                    <td>
                                        <a href="/admin/products/detail?cid=${c.cid}" class="btn btn-sm btn-link" title="${c.commName}">${c.commName}</a>
                                    </td>
                                    <td>${c.styleName}</td>
                                    <td>${c.materialName}</td>
                                    <td>￥${c.price}</td>
                                    <%--<td>￥399</td>--%>
                                    <td>
                                        <a href="/admin/products/transaction/records?productId=1" class="btn btn-sm btn-link">${c.saleCount}件</a>
                                    </td>
                                    <td>
                                        <span class="badge <c:if test="${c.status=='1'}">badge-success</c:if><c:if test="${c.status!='1'}">badge-danger</c:if>">${c.statusName}</span>
                                    </td>
                                    <td>
                                        <c:if test="${c.status=='1'}">
                                            <a href="javascript:;" class="btn btn-sm btn-danger js-status-down" title="下架" data-id="${c.cid}">
                                                <i class="fa fa-level-down"></i> 下架
                                            </a>
                                        </c:if>
                                        <c:if test="${c.status!='1'}">
                                            <a href="javascript:;" class="btn btn-sm btn-primary js-status-top" title="上架" data-id="${c.cid}">
                                                <i class="fa fa-level-up"></i> 上架
                                            </a>
                                        </c:if>
                                            <%--<c:if test="${c.hot == '1'}">--%>
                                                <%--<a href="javascript:;" class="btn btn-sm btn-danger product-hot-cancel js-hot-down" title="取消热门" data-id="${c.cid}">--%>
                                                    <%--<i class="fa fa-remove"></i> 取消热门--%>
                                                <%--</a>--%>
                                            <%--</c:if>--%>
                                            <%--<c:if test="${c.hot != '1'}">--%>
                                                <%--<a href="javascript:;" class="btn btn-sm btn-primary js-hot-top" title="添加到热门" data-id="${c.cid}">--%>
                                                    <%--<i class="fa fa-heart"></i> 热门--%>
                                                <%--</a>--%>
                                            <%--</c:if>--%>
                                            <%--<c:if test="${c.newly == '1'}">--%>
                                                <%--<a href="javascript:;" class="btn btn-sm btn-danger product-users-cancel js-newly-down" title="取消最新" data-id="${c.cid}">--%>
                                                    <%--<i class="fa fa-remove"></i> 取消最新--%>
                                                <%--</a>--%>
                                            <%--</c:if>--%>
                                            <%--<c:if test="${c.newly != '1'}">--%>
                                                <%--<a href="javascript:;" class="btn btn-sm btn-primary js-newly-top" title="添加到最新" data-id="${c.cid}">--%>
                                                    <%--<i class="fa fa-bolt"></i> 最新--%>
                                                <%--</a>--%>
                                            <%--</c:if>--%>
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
    <c:param name="menu" value="products"/>
    <c:param name="subMenu" value="list"/>
</c:import>
