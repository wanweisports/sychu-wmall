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
            min-width: 5rem;
            word-break: keep-all;
        }
        .products-list td input.set-seq-value {
            width: 3rem;
            text-align: center;
        }
        .img-rounded {
            height: 2rem;
        }
        .products-list td .products-name {
            overflow: hidden;
            text-overflow: ellipsis;
            white-space: nowrap;
            max-width: 20rem;
        }
    </style>
</layout:override>

<layout:override name="<%=Blocks.BLOCK_HEADER_SCRIPTS%>">
    <script type="text/javascript" src="Content/js/require.js?v=${static_resource_version}"
            data-main="Content/js/app/products/list.js?v=${static_resource_version}"></script>
</layout:override>

<layout:override name="<%=Blocks.BLOCK_BODY%>">
    <div class="modal fade" id="batch_import_products" tabindex="-1" role="dialog">
        <div class="modal-dialog modal-default modal-sm" role="document">
            <div class="modal-content">
                <div class="modal-body">
                    <form id="upload_form" enctype="multipart/form-data" method="post" class="form-horizontal">
                        <div class="form-group row">
                            <div class="col-md-12">
                                <input type="file" class="form-control" id="file1" placeholder="请选择导入文件" name="file1"
                                       data-val="true" data-val-required="请选择导入文件" autocomplete="off">
                                <div data-valmsg-for="file1" data-valmsg-replace="true"></div>
                            </div>
                        </div>
                        <p class="help-block">
                            参考模板：<a href="/commodity/downCommodityTpl" target="_blank">商品Excel模板</a>
                        </p>
                    </form>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-sm btn-secondary" data-dismiss="modal">
                        <i class="fa fa-close"></i> 取 消
                    </button>
                    <button type="button" class="btn btn-sm btn-primary" id="upload_excel">立即上传</button>
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
                            <strong>商品列表</strong>=
                        </div>
                        <div class="card-block">
                            <form id="products_query_form" method="post" class="form-horizontal" action="/admin/products/list">
                                <input type="hidden" name="groupId" value="${groupId}" />
                                <div class="form-group row">
                                    <div class="col-md-2">
                                        <input type="text" name="commNo" class="form-control" placeholder="商品编号" value="${commNo}" autocomplete="off">
                                    </div>
                                    <div class="col-md-2">
                                        <input type="text" name="commName" class="form-control" placeholder="商品名称" value="${commName}" autocomplete="off">
                                    </div>
                                    <div class="col-md-2">
                                        <select class="form-control" name="status">
                                            <option value>全部商品</option>
                                            <option value="1" <c:if test="${status=='1'}">selected</c:if>>已上架</option>
                                            <option value="2" <c:if test="${status=='2'}">selected</c:if>>已下架</option>
                                        </select>
                                    </div>
                                    <div class="col-md-6">
                                        <button type="submit" class="btn btn-primary products-query-btn">
                                            <i class="fa fa-search"></i> 检 索
                                        </button>
                                        <a href="/admin/products/add" class="btn btn-primary">
                                            <i class="fa fa-plus"></i> 添加商品
                                        </a>

                                        <a href="#batch_import_products" class="btn btn-primary pull-right" data-toggle="modal">
                                            <i class="fa fa-file-excel-o"></i> 批量导入
                                        </a>
                                    </div>
                                </div>
                            </form>
                        </div>
                        <div class="card-footer text-right"></div>
                        <div class="card-block">
                            <div class="table-window" style="overflow:auto;">
                                <table class="table table-striped table-sm table-bordered products-list" style="width: 88rem;">
                                    <thead>
                                    <tr>
                                        <th>编号</th>
                                        <th>商品图片</th>
                                        <th>商品名称</th>
                                        <th>商品品类</th>
                                        <th>价格</th>
                                        <th>优惠价</th>
                                        <th>已售</th>
                                        <th>点击量</th>
                                        <th>已收藏</th>
                                        <th>优先级</th>
                                        <th>操作</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <c:forEach var="c" items="${page.list}" varStatus="status">
                                        <tr data-id="">
                                            <td>${c.commNo}</td>
                                            <td><img src="${c.resourcePath}" alt="${c.commName}" class="img-rounded"></td>
                                            <td>
                                                <a href="/admin/products/detail?cid=${c.cid}" class="products-name" title="${c.commName}">${c.commName}</a>
                                            </td>
                                            <td>${c.styleName}</td>
                                            <td>￥${c.price}</td>
                                            <td>￥${c.couPrice}</td>
                                                <%--<td>￥399</td>--%>
                                            <td>${c.saleCount}件</td>
                                            <td>${c.clickRate}次</td>
                                            <td>${c.collectionCount}人</td>
                                            <td>
                                                <input type="text" class="set-seq-value" value="${c.seqNo}" data-id="${c.cid}" />
                                            </td>
                                                <%--<td>--%>
                                                <%--<span class="badge <c:if test="${c.status=='1'}">badge-success</c:if><c:if test="${c.status!='1'}">badge-danger</c:if>">${c.statusName}</span>--%>
                                                <%--</td>--%>
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
                                                <a href="javascript:;" class="btn btn-sm btn-danger product-users-cancel js-status-del" title="删除" data-id="${c.cid}">
                                                    <i class="fa fa-remove"></i> 删除
                                                </a>
                                            </td>
                                        </tr>
                                    </c:forEach>
                                    </tbody>
                                </table>
                            </div>
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
