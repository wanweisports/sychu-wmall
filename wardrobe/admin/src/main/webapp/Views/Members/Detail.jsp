<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ page import="com.wardrobe.layout.Blocks" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> <%-- JSTL表达式（判断，循环，输出） --%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %> <%-- 方法表达式（字符串截取，替换） --%>
<%@ taglib uri="http://www.sychu.com/tags/tag" prefix="layout" %>

<layout:override name="<%=Blocks.BLOCK_HEADER_CSS%>">
    <link href="Content/bower_components/bootstrap-datepicker/dist/css/bootstrap-datepicker3.css?v=${static_resource_version}" rel="stylesheet">
    <style type="text/css">
        .member-info th, .member-account th {
            width: 10%;
        }
        .member-info td, .member-account td {
            width: 25%;
        }
    </style>
</layout:override>

<layout:override name="<%=Blocks.BLOCK_HEADER_SCRIPTS%>">
    <script type="text/javascript" src="Content/js/require.js?v=${static_resource_version}"
            data-main="Content/js/app/students/edit.js?v=${static_resource_version}"></script>
</layout:override>

<layout:override name="<%=Blocks.BLOCK_BODY%>">
    <div class="container-fluid">
        <div class="animated fadeIn">
            <div class="row">
                <div class="col-md-12">
                    <div class="card">
                        <div class="card-header">
                            <strong>会员信息</strong>
                            <small>Members Information</small>
                        </div>
                        <div class="card-block">
                            <table class="table table-responsive-sm table-bordered member-info">
                                <tbody>
                                <tr>
                                    <th>用户id：</th>
                                    <td>${user.uid}</td>
                                    <th>微信openId：</th>
                                    <td>${user.openId}</td>
                                    <th rowspan="3">用户头像：</th>
                                    <td rowspan="3">
                                        <img src="${user.headImg}" class="img-rounded" style="height: 7rem;">
                                    </td>
                                </tr>
                                <tr>
                                    <th>用户昵称：</th>
                                    <td>${user.nickname}</td>
                                    <th>手机号码：</th>
                                    <td>${user.mobile}</td>
                                </tr>
                                <tr>
                                    <th>性别：</th>
                                    <td>${user.sexName}</td>
                                    <th>年龄：</th>
                                    <td>${user.age}</td>
                                </tr>
                                <tr>
                                    <th>用户等级：</th>
                                    <td>${userAccount.rankName}</td>
                                    <th>尺码偏好：</th>
                                    <td>${user.usualSizeName}</td>
                                    <th>风格偏好：</th>
                                    <td>${user.dressStyleName}</td>
                                </tr>
                                <tr>
                                    <th>账户余额：</th>
                                    <td>￥${userAccount.balance}</td>
                                    <th>衣橱币：</th>
                                    <td>${userAccount.ycoid}</td>
                                    <th>积分：</th>
                                    <td>${userAccount.score}</td>
                                </tr>
                                <tr>
                                    <th>用户邀请码：</th>
                                    <td>${user.inviteCode}</td>
                                    <th>邀请人</th>
                                    <td>${user.invitedByUserName}</td>
                                    <th>注册时间：</th>
                                    <td>${user.registerTime}</td>
                                </tr>
                                </tbody>
                            </table>
                        </div>
                    </div>
                    <div class="card">
                        <div class="card-header">
                            <strong>优惠券列表</strong>
                            <small>Members Coupons</small>
                        </div>
                        <div class="card-block">
                            <table class="table table-responsive-sm table-bordered member-address">
                                <thead>
                                <tr>
                                    <th>##</th>
                                    <th>优惠券ID</th>
                                    <th>优惠券业务类型</th>
                                    <th>优惠券面值</th>
                                    <th>优惠券状态</th>
                                    <th>优惠券到期时间</th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach var="c" items="${userCoupon}" varStatus="status">
                                <tr>
                                    <td>${status.index+1}</td>
                                    <td>${c.cpid}</td>
                                    <td>${c.dictValue}</td>
                                    <td>￥${c.couponPrice}</td>
                                    <td>
                                        <c:if test="${c.status == '1'}">已使用</c:if>
                                        <c:if test="${c.status != '1'}">未使用</c:if>
                                    </td>
                                    <td>${c.dueTime}</td>
                                </tr>
                                </c:forEach>
                                </tbody>
                            </table>
                        </div>
                    </div>
                    <%--<div class="card">
                        <div class="card-header">
                            <strong>收货地址</strong>
                            <small>Members Express Address</small>
                        </div>
                        <div class="card-block">
                            <table class="table table-responsive-sm table-bordered member-address">
                                <thead>
                                <tr>
                                    <th>##</th>
                                    <th>地址编号</th>
                                    <th>省市区</th>
                                    <th>详细地址</th>
                                    <th>邮编</th>
                                    <th>收货人姓名</th>
                                    <th>收货人手机</th>
                                    <th>是否默认</th>
                                    <th>设置时间</th>
                                </tr>
                                </thead>
                                <tbody>
                                <tr>
                                    <td>1</td>
                                    <td>U20180612101155</td>
                                    <td>山东省-潍坊市-坊子区</td>
                                    <td>舜王街道程戈庄村100号</td>
                                    <td>262202</td>
                                    <td>唐四毛</td>
                                    <td>158****3167</td>
                                    <td>是</td>
                                    <td>2018-07-24 14:46:33</td>
                                </tr>
                                <tr>
                                    <td>2</td>
                                    <td>U20180612101155</td>
                                    <td>山东省-潍坊市-坊子区</td>
                                    <td>舜王街道程戈庄村100号</td>
                                    <td>262202</td>
                                    <td>唐四毛</td>
                                    <td>158****3167</td>
                                    <td>是</td>
                                    <td>2018-07-24 14:46:33</td>
                                </tr>
                                <tr>
                                    <td>3</td>
                                    <td>U20180612101155</td>
                                    <td>山东省-潍坊市-坊子区</td>
                                    <td>舜王街道程戈庄村100号</td>
                                    <td>262202</td>
                                    <td>唐四毛</td>
                                    <td>158****3167</td>
                                    <td>是</td>
                                    <td>2018-07-24 14:46:33</td>
                                </tr>
                                </tbody>
                            </table>
                        </div>
                    </div>--%>
                </div>
            </div>
            <!--/.row-->
        </div>

    </div>
</layout:override>

<c:import url="../Shared/GeneralLayout.jsp">
    <c:param name="menu" value="members"/>
    <c:param name="subMenu" value="detail"/>
</c:import>
