<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ page import="com.wardrobe.layout.Blocks" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> <%-- JSTL表达式（判断，循环，输出） --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %> <%-- 方法表达式（字符串截取，替换） --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %> <%-- 方法表达式（字符串截取，替换） --%>
<%@ taglib uri="http://www.sychu.com/tags/tag" prefix="layout" %>

<layout:override name="<%=Blocks.BLOCK_HEADER_CSS%>">
    <style type="text/css">
        .members-list th {
            padding: 0.75rem;
        }
        .members-list td {
            padding: 0.3rem 0.75rem;
        }
        .img-rounded {
            height: 2rem;
        }
    </style>
</layout:override>

<layout:override name="<%=Blocks.BLOCK_HEADER_SCRIPTS%>">
    <script type="text/javascript" src="Content/js/require.js?v=${static_resource_version}"
            data-main="Content/js/app/members/list.js?v=${static_resource_version}"></script>
</layout:override>

<layout:override name="<%=Blocks.BLOCK_BODY%>">
    <div class="container-fluid">
        <div class="animated fadeIn">
            <div class="row">
                <div class="col-md-12">
                    <div class="card">
                        <div class="card-header">
                            <strong>会员列表</strong>
                            <small>Members List</small>
                        </div>
                        <div class="card-block">
                            <form id="members_query_form" method="post" class="form-horizontal" action="/admin/members/list"<%-- novalidate onsubmit="return false;"--%>>
                                <div class="form-group row">
                                    <div class="col-md-2">
                                        <input type="text" name="nickname" class="form-control" placeholder="会员昵称" value="${nickname}" autocomplete="off">
                                    </div>
                                    <div class="col-md-2">
                                        <input type="text" name="mobile" class="form-control" placeholder="会员手机号" value="${mobile}" autocomplete="off">
                                    </div>
                                    <div class="col-md-2">
                                        <input type="text" name="invitedBy" class="form-control" placeholder="邀请人" value="${invitedBy}" autocomplete="off">
                                    </div>
                                    <div class="col-md-6">
                                        <button type="submit" class="btn btn-primary members-query-btn">
                                            <i class="fa fa-search"></i> 检 索
                                        </button>
                                    </div>
                                </div>
                            </form>
                        </div>
                        <div class="card-footer text-right"></div>
                        <div class="card-block">
                            <table class="table table-striped table-bordered table-sm members-list">
                                <thead>
                                <tr>
                                    <th>用户头像</th>
                                    <th>会员昵称</th>
                                    <th>手机号码</th>
                                    <th>出生日期</th>
                                    <th>账户余额</th>
                                    <th>薏米</th>
                                    <th>积分</th>
                                    <th>等级</th>
                                    <th>邀请人</th>
                                    <th>注册时间</th>
                                    <th>操作</th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach var="user" items="${page.list}">
                                    <tr data-id="${user.id}">
                                        <td><img src="${user.headImg}" alt="用户头像" class="img-rounded"></td>
                                        <td>
                                            <a href="/admin/members/detail?userId=${user.uid}" class="btn btn-sm btn-link" title="会员详情">
                                                <i class="fa fa-user"></i> ${user.nickname}
                                            </a>
                                        </td>
                                        <td>${user.mobile}</td>
                                        <td>${user.age}</td>
                                        <td>
                                            <a href="/admin/members/transactions/log?uid=${user.uid}" class="btn btn-sm btn-link" title="账户余额">
                                                ￥${user.balance}
                                            </a>
                                        </td>
                                        <td>${user.ycoid}</td>
                                        <td>${user.score}</td>
                                        <td>${user.rankName}</td>
                                        <td>${user.invitedByUserName}</td>
                                        <td><fmt:formatDate value="${user.registerTime}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
                                        <td>
                                            <a href="javascript:;" class="btn btn-danger btn-sm member-stop">
                                                <i class="fa fa-stop-circle-o"></i> 停用
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

<c:import url="../Shared/GeneralLayout.jsp">
    <c:param name="menu" value="members"/>
    <c:param name="subMenu" value="list"/>
</c:import>
