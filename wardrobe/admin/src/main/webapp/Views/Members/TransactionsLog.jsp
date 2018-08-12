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
            padding: 0.75rem;
        }
    </style>
</layout:override>

<layout:override name="<%=Blocks.BLOCK_HEADER_SCRIPTS%>">
    <script type="text/javascript" src="Content/js/require.js?v=${static_resource_version}"
            data-main="Content/js/app/students/list.js?v=${static_resource_version}"></script>
</layout:override>

<layout:override name="<%=Blocks.BLOCK_BODY%>">
    <div class="container-fluid">
        <div class="animated fadeIn">
            <div class="row">
                <div class="col-md-12">
                    <div class="card">
                        <div class="card-header">
                            <strong>会员交易记录</strong>
                            <small>Members Transactions Log</small>
                        </div>
                        <div class="card-block">
                            <form id="members_query_form" method="post" class="form-horizontal" action="/admin/members/transactions/log" <%--novalidate onsubmit="return false;"--%>>
                                <div class="form-group row">
                                    <div class="col-md-2">
                                        <input type="text" name="nickname" class="form-control" placeholder="会员昵称" value="${nickname}">
                                    </div>
                                    <div class="col-md-2">
                                        <input type="text" name="mobile" class="form-control" placeholder="会员手机号" value="${mobile}">
                                    </div>
                                    <div class="col-md-8">
                                        <button type="submit" class="btn btn-primary members-query-btn">
                                            <i class="fa fa-search"></i> 检 索
                                        </button>
                                    </div>
                                </div>
                            </form>
                        </div>
                        <div class="card-footer text-right"></div>
                        <div class="card-block">
                            <table class="table table-striped table-sm user-list">
                                <thead>
                                <tr>
                                    <th>交易流水号</th>
                                    <th>交易业务类型</th>
                                    <th>交易业务ID</th>
                                    <th>交易余额（元）</th>
                                    <th>会员昵称</th>
                                    <th>手机号码</th>
                                    <th>交易时间</th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach var="t" items="${list}">
                                <tr data-id="">
                                    <td>${t.tid}</td>
                                    <td>${t.serviceTypeName}</td>
                                    <td>${t.serviceId}</td>
                                    <td>${t.price}</td>
                                    <td>${t.nickname}</td>
                                    <td>${t.mobile}</td>
                                    <td>${t.createTime}</td>
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
    <c:param name="menu" value="members"/>
    <c:param name="subMenu" value="transactions"/>
</c:import>
