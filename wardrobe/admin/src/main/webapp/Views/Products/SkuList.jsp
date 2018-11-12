<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ page import="com.wardrobe.layout.Blocks" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> <%-- JSTL表达式（判断，循环，输出） --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %> <%-- 方法表达式（字符串截取，替换） --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %> <%-- 方法表达式（字符串截取，替换） --%>
<%@ taglib uri="http://www.sychu.com/tags/tag" prefix="layout" %>

<layout:override name="<%=Blocks.BLOCK_HEADER_CSS%>">
    <link rel="stylesheet" href="/Content/bower_components/datetimepicker/build/jquery.datetimepicker.min.css">
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
            data-main="Content/js/app/products/skuTotal.js?v=${static_resource_version}"></script>
</layout:override>

<layout:override name="<%=Blocks.BLOCK_BODY%>">
    <div class="container-fluid">
        <div class="animated fadeIn">
            <div class="row">
                <div class="col-md-12">
                    <div class="card">
                        <div class="card-header">
                            <strong>商品库存日志</strong>
                        </div>
                        <div class="card-block">
                            <form id="products_query_form" method="get" class="form-horizontal" action="/admin/products/sku/list" <%--novalidate onsubmit="return false;"--%>>
                                <div class="form-group row">
                                    <div class="col-md-2">
                                        <select class="form-control" name="type">
                                            <option value="">全部类型</option>
                                            <c:forEach var="d" items="${types}">
                                                <option value="${d.dictKey}" <c:if test="${d.dictKey == type}">selected</c:if>>${d.dictValue}</option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                    <div class="col-md-2">
                                        <!--年-月-日 小时:分钟:秒-->
                                        <input type="text" id="startTime" name="startTime" class="form-control" placeholder="开始时间" value="${startTime}" autocomplete="off">
                                    </div>
                                    <div class="col-md-2">
                                        <!--年-月-日 小时:分钟:秒-->
                                        <input type="text" id="endTime" name="endTime" class="form-control" placeholder="结束时间" value="${endTime}" autocomplete="off">
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
                            <table class="table table-striped table-bordered table-sm products-list">
                                <thead>
                                <tr>
                                    <th>商品图片</th>
                                    <th>商品名称</th>
                                    <th>商品规格</th>
                                    <th>变更类型</th>
                                    <th>变更数量</th>
                                    <th>变更备注</th>
                                    <th>变更人</th>
                                    <th>变更时间</th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach var="c" items="${page.list}">
                                <tr data-id="">
                                    <td><img src="${c.resourcePath}" alt="${c.commName}" class="img-rounded"></td>
                                    <td>
                                        <a href="/admin/products/detail?cid=${c.cid}" class="btn btn-sm btn-link" title="${c.commName}">${c.commName}</a>
                                    </td>
                                    <td>颜色：${c.colorName}，尺码：${c.size}</td>
                                    <td>${c.typeName}</td>
                                    <td>
                                        <c:if test="${c.type=='10'}">
                                            <span class="badge badge-success">+ ${c.num}件</span>
                                        </c:if>
                                        <c:if test="${c.type=='20'}">
                                            <span class="badge badge-warning">- ${c.num}件</span>
                                        </c:if>
                                        <c:if test="${c.type=='30'}">
                                            <span class="badge badge-danger">- ${c.num}件</span>
                                        </c:if>
                                    </td>
                                    <td>${c.remark}</td>
                                    <td>${c.operatorId}</td>
                                    <td><fmt:formatDate value="${c.createTime}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
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
    <c:param name="subMenu" value="sku"/>
</c:import>
