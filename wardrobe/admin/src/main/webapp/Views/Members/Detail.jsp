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
            data-main="Content/js/app/members/detail.js?v=${static_resource_version}"></script>
</layout:override>

<layout:override name="<%=Blocks.BLOCK_BODY%>">
    <div class="modal fade" id="member_value_dialog" tabindex="-1" role="dialog">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-body">
                    <div class="alert alert-warning" id="member_value_tip">填写修正金额，请慎重考虑！</div>
                    <form id="member_value_form" method="post" class="form-horizontal" novalidate onsubmit="return false;">
                        <div class="form-group row">
                            <label class="col-md-4 form-control-label" for="ps_num">
                                <span class="text-danger">*</span> 修正值
                            </label>
                            <div class="col-md-8">
                                <input type="text" class="form-control" id="ps_num" placeholder="请输入修正值" name="num"
                                       data-val="true" data-val-required="修正值不能为空" autocomplete="off">
                                <div data-valmsg-for="num" data-valmsg-replace="true"></div>
                            </div>
                        </div>
                        <div class="form-group row">
                            <label class="col-md-4 form-control-label" for="ps_remark">
                                <span class="text-danger">*</span> 修正备注
                            </label>
                            <div class="col-md-8">
                                <textarea class="form-control" id="ps_remark" placeholder="请输入备注" name="remark"
                                          data-val="true" data-val-required="备注不能为空" autocomplete="off"></textarea>
                                <div data-valmsg-for="remark" data-valmsg-replace="true"></div>
                            </div>
                        </div>
                    </form>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary btn-sm" data-dismiss="modal">
                        <i class="fa fa-remove"></i> 取 消
                    </button>
                    <button type="button" class="btn btn-primary btn-sm">
                        <i class="fa fa-check"></i> 确 认
                    </button>
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
                            <strong>会员信息</strong>
                            <small>Members Information</small>
                            <div class="card-header-actions">
                                <a class="card-header-action btn-basket" href="/admin/members/transactions/log?id=${user.uid}">
                                    <i class="icon-basket"></i> 交易记录
                                </a>
                            </div>
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
                        <div class="card-footer">
                            <a href="#member_value_dialog" class="btn btn-danger" data-toggle="modal">
                                <i class="fa fa-bolt"></i> 冲抵余额
                            </a>
                            <a href="#member_value_dialog" class="btn btn-danger" data-toggle="modal">
                                <i class="fa fa-bolt"></i> 冲抵薏米
                            </a>
                            <a href="#member_value_dialog" class="btn btn-danger" data-toggle="modal">
                                <i class="fa fa-bolt"></i> 冲抵积分
                            </a>
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
