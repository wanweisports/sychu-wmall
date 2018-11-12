<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ page import="com.wardrobe.layout.Blocks" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> <%-- JSTL表达式（判断，循环，输出） --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %> <%-- 方法表达式（字符串截取，替换） --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %> <%-- 方法表达式（字符串截取，替换） --%>
<%@ taglib uri="http://www.sychu.com/tags/tag" prefix="layout" %>

<layout:override name="<%=Blocks.BLOCK_HEADER_CSS%>">
    <style type="text/css">
        .orders-list th {
            padding: 0.75rem;
        }
        .orders-list td {
            padding: 0.3rem 0.75rem;
        }
    </style>
</layout:override>

<layout:override name="<%=Blocks.BLOCK_HEADER_SCRIPTS%>">
    <script type="text/javascript" src="Content/js/require.js?v=${static_resource_version}"
            data-main="Content/js/app/orders/list.js?v=${static_resource_version}"></script>
</layout:override>

<layout:override name="<%=Blocks.BLOCK_BODY%>">
    <div class="container-fluid">
        <div class="animated fadeIn">
            <div class="row">
                <div class="col-md-12">
                    <div class="card">
                        <div class="card-header">
                            <strong>预约订单列表</strong>
                            <small>Orders List</small>
                        </div>
                        <div class="card-block">
                            <form id="members_query_form" method="post" class="form-horizontal" action="/admin/orders/reservation" novalidate <%--onsubmit="return false;"--%>>
                                <div class="form-group row">
                                    <div class="col-md-3">
                                        <input type="text" name="ono" class="form-control" placeholder="预约单编号" value="${ono}">
                                    </div>
                                    <div class="col-md-9">
                                        <button type="submit" class="btn btn-primary members-query-btn">
                                            <i class="fa fa-search"></i> 检 索
                                        </button>
                                    </div>
                                </div>
                            </form>
                        </div>
                        <div class="card-footer text-right"></div>
                        <div class="card-block">
                            <table class="table table-striped table-bordered table-sm orders-list">
                                <thead>
                                <tr>
                                    <th>预约单ID</th>
                                    <th>预约单编号</th>
                                    <th>预约试衣间</th>
                                    <th>试衣开始时间</th>
                                    <th>试衣结束时间</th>
                                    <th>会员姓名</th>
                                    <th>预约状态</th>
                                    <th>预约日期</th>
                                    <th>操作</th>
                                </tr>
                                </thead>
                                    <c:forEach var="r" items="${page.list}">
                                        <tr>
                                            <td>${r.roid}</td>
                                            <td>
                                                <a href="/admin/orders/reservation/detail?roid=${r.roid}">${r.rno}</a>
                                            </td>
                                            <td>${r.deviceName}（${r.lockName}）</td>
                                            <td><fmt:formatDate value="${r.reserveStartTime}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
                                            <td><fmt:formatDate value="${r.reserveEndTime}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
                                            <td>${r.nickname}</td>
                                            <td>
                                                <c:if test="${r.status=='1'}">
                                                    <span class="badge badge-success">正常</span>
                                                </c:if>
                                                <c:if test="${r.status=='2'}">
                                                    <span class="badge badge-danger">已取消</span>
                                                </c:if>
                                            </td>
                                            <td><fmt:formatDate value="${r.createTime}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
                                            <td>
                                                <a href="javascript:;" class="btn btn-sm btn-danger js-status-del" title="删除" data-id="${r.roid}">
                                                    <i class="fa fa-remove"></i> 删除
                                                </a>
                                            </td>
                                        </tr>
                                    </c:forEach>
                                <tbody>
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
    <c:param name="menu" value="orders"/>
    <c:param name="subMenu" value="reservation"/>
</c:import>
