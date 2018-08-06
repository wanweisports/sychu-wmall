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
            data-main="Content/js/app/students/list.js?v=${static_resource_version}"></script>
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
                            <form id="members_query_form" method="post" class="form-horizontal" novalidate onsubmit="return false;">
                                <div class="form-group row">
                                    <div class="col-md-2">
                                        <input type="text" name="" class="form-control" placeholder="会员编号">
                                    </div>
                                    <div class="col-md-2">
                                        <input type="text" name="" class="form-control" placeholder="会员手机号">
                                    </div>
                                    <div class="col-md-8">
                                        <button type="button" class="btn btn-primary members-query-btn">
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
                                    <th>用户头像</th>
                                    <th>会员昵称</th>
                                    <th>手机号码</th>
                                    <th>性别</th>
                                    <th>年龄</th>
                                    <th>余额（元）</th>
                                    <th>衣橱币</th>
                                    <th>积分</th>
                                    <th>等级</th>
                                    <th>邀请人</th>
                                    <th>注册时间</th>
                                </tr>
                                </thead>
                                <tbody>
                                <tr data-id="">
                                    <td><img src="/Content/images/avatars/1.jpg" alt="用户头像" class="img-rounded"></td>
                                    <td>
                                        <a href="/admin/members/detail?memberId=" class="btn btn-sm btn-link" title="会员详情">
                                            <i class="fa fa-user"></i> 王军
                                        </a>
                                    </td>
                                    <td>158****3167</td>
                                    <td>帅哥</td>
                                    <td>85后</td>
                                    <td>
                                        <a href="/admin/members/transactions/log?memberId=" class="btn btn-sm btn-link" title="会员详情">
                                            <i class="fa fa-money"></i> 1000.00
                                        </a>
                                    </td>
                                    <td>9999</td>
                                    <td>9999</td>
                                    <td>VIP5</td>
                                    <td>U20180724142512</td>
                                    <td>2018-12-12 11:11:11</td>
                                </tr>
                                <tr data-id="">
                                    <td><img src="/Content/images/avatars/2.jpg" alt="用户头像" class="img-rounded"></td>
                                    <td>
                                        <a href="/admin/members/detail?memberId=" class="btn btn-sm btn-link" title="会员详情">
                                            <i class="fa fa-user"></i> 程晓松
                                        </a>
                                    </td>
                                    <td>158****3167</td>
                                    <td>帅哥</td>
                                    <td>85后</td>
                                    <td>
                                        <a href="/admin/members/transactions/log?memberId=" class="btn btn-sm btn-link" title="会员详情">
                                            <i class="fa fa-money"></i> 1000.00
                                        </a>
                                    </td>
                                    <td>9999</td>
                                    <td>9999</td>
                                    <td>VIP5</td>
                                    <td>U20180724142512</td>
                                    <td>2018-12-12 11:11:11</td>
                                </tr>
                                <tr data-id="">
                                    <td><img src="/Content/images/avatars/3.jpg" alt="用户头像" class="img-rounded"></td>
                                    <td>
                                        <a href="/admin/members/detail?memberId=" class="btn btn-sm btn-link" title="会员详情">
                                            <i class="fa fa-user"></i> 孙延琦
                                        </a>
                                    </td>
                                    <td>158****3167</td>
                                    <td>帅哥</td>
                                    <td>85后</td>
                                    <td>
                                        <a href="/admin/members/transactions/log?memberId=" class="btn btn-sm btn-link" title="会员详情">
                                            <i class="fa fa-money"></i> 1000.00
                                        </a>
                                    </td>
                                    <td>9999</td>
                                    <td>9999</td>
                                    <td>VIP5</td>
                                    <td>U20180724142512</td>
                                    <td>2018-12-12 11:11:11</td>
                                </tr>
                                <tr data-id="">
                                    <td><img src="/Content/images/avatars/4.jpg" alt="用户头像" class="img-rounded"></td>
                                    <td>
                                        <a href="/admin/members/detail?memberId=" class="btn btn-sm btn-link" title="会员详情">
                                            <i class="fa fa-user"></i> 张自忠
                                        </a>
                                    </td>
                                    <td>158****3167</td>
                                    <td>帅哥</td>
                                    <td>85后</td>
                                    <td>
                                        <a href="/admin/members/transactions/log?memberId=" class="btn btn-sm btn-link" title="会员详情">
                                            <i class="fa fa-money"></i> 1000.00
                                        </a>
                                    </td>
                                    <td>9999</td>
                                    <td>9999</td>
                                    <td>VIP5</td>
                                    <td>U20180724142512</td>
                                    <td>2018-12-12 11:11:11</td>
                                </tr>
                                <tr data-id="">
                                    <td><img src="/Content/images/avatars/5.jpg" alt="用户头像" class="img-rounded"></td>
                                    <td>
                                        <a href="/admin/members/detail?memberId=" class="btn btn-sm btn-link" title="会员详情">
                                            <i class="fa fa-user"></i> 刘心怡
                                        </a>
                                    </td>
                                    <td>158****3167</td>
                                    <td>帅哥</td>
                                    <td>85后</td>
                                    <td>
                                        <a href="/admin/members/transactions/log?memberId=" class="btn btn-sm btn-link" title="会员详情">
                                            <i class="fa fa-money"></i> 1000.00
                                        </a>
                                    </td>
                                    <td>9999</td>
                                    <td>9999</td>
                                    <td>VIP5</td>
                                    <td>U20180724142512</td>
                                    <td>2018-12-12 11:11:11</td>
                                </tr>
                                <tr data-id="">
                                    <td><img src="/Content/images/avatars/6.jpg" alt="用户头像" class="img-rounded"></td>
                                    <td>
                                        <a href="/admin/members/detail?memberId=" class="btn btn-sm btn-link" title="会员详情">
                                            <i class="fa fa-user"></i> 王佳玉
                                        </a>
                                    </td>
                                    <td>158****3167</td>
                                    <td>帅哥</td>
                                    <td>85后</td>
                                    <td>
                                        <a href="/admin/members/transactions/log?memberId=" class="btn btn-sm btn-link" title="会员详情">
                                            <i class="fa fa-money"></i> 1000.00
                                        </a>
                                    </td>
                                    <td>9999</td>
                                    <td>9999</td>
                                    <td>VIP5</td>
                                    <td>U20180724142512</td>
                                    <td>2018-12-12 11:11:11</td>
                                </tr>
                                <tr data-id="">
                                    <td><img src="/Content/images/avatars/7.jpg" alt="用户头像" class="img-rounded"></td>
                                    <td>
                                        <a href="/admin/members/detail?memberId=" class="btn btn-sm btn-link" title="会员详情">
                                            <i class="fa fa-user"></i> 李依依
                                        </a>
                                    </td>
                                    <td>158****3167</td>
                                    <td>帅哥</td>
                                    <td>85后</td>
                                    <td>
                                        <a href="/admin/members/transactions/log?memberId=" class="btn btn-sm btn-link" title="会员详情">
                                            <i class="fa fa-money"></i> 1000.00
                                        </a>
                                    </td>
                                    <td>9999</td>
                                    <td>9999</td>
                                    <td>VIP5</td>
                                    <td>U20180724142512</td>
                                    <td>2018-12-12 11:11:11</td>
                                </tr>
                                <tr data-id="">
                                    <td><img src="/Content/images/avatars/8.jpg" alt="用户头像" class="img-rounded"></td>
                                    <td>
                                        <a href="/admin/members/detail?memberId=" class="btn btn-sm btn-link" title="会员详情">
                                            <i class="fa fa-user"></i> 赵蕊蕊
                                        </a>
                                    </td>
                                    <td>158****3167</td>
                                    <td>帅哥</td>
                                    <td>85后</td>
                                    <td>
                                        <a href="/admin/members/transactions/log?memberId=" class="btn btn-sm btn-link" title="会员详情">
                                            <i class="fa fa-money"></i> 1000.00
                                        </a>
                                    </td>
                                    <td>9999</td>
                                    <td>9999</td>
                                    <td>VIP5</td>
                                    <td>U20180724142512</td>
                                    <td>2018-12-12 11:11:11</td>
                                </tr>
                                <tr data-id="">
                                    <td><img src="/Content/images/avatars/6.jpg" alt="用户头像" class="img-rounded"></td>
                                    <td>
                                        <a href="/admin/members/detail?memberId=" class="btn btn-sm btn-link" title="会员详情">
                                            <i class="fa fa-user"></i> 钱思茂
                                        </a>
                                    </td>
                                    <td>158****3167</td>
                                    <td>帅哥</td>
                                    <td>85后</td>
                                    <td>
                                        <a href="/admin/members/transactions/log?memberId=" class="btn btn-sm btn-link" title="会员详情">
                                            <i class="fa fa-money"></i> 1000.00
                                        </a>
                                    </td>
                                    <td>9999</td>
                                    <td>9999</td>
                                    <td>VIP5</td>
                                    <td>U20180724142512</td>
                                    <td>2018-12-12 11:11:11</td>
                                </tr>
                                <tr data-id="">
                                    <td><img src="/Content/images/avatars/2.jpg" alt="用户头像" class="img-rounded"></td>
                                    <td>
                                        <a href="/admin/members/detail?memberId=" class="btn btn-sm btn-link" title="会员详情">
                                            <i class="fa fa-user"></i> 诸葛宜
                                        </a>
                                    </td>
                                    <td>158****3167</td>
                                    <td>帅哥</td>
                                    <td>85后</td>
                                    <td>
                                        <a href="/admin/members/transactions/log?memberId=" class="btn btn-sm btn-link" title="会员详情">
                                            <i class="fa fa-money"></i> 1000.00
                                        </a>
                                    </td>
                                    <td>9999</td>
                                    <td>9999</td>
                                    <td>VIP5</td>
                                    <td>U20180724142512</td>
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
    <c:param name="menu" value="members"/>
    <c:param name="subMenu" value="list"/>
</c:import>
