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
            data-main="Content/js/app/members/rechargeSettings.js?v=${static_resource_version}"></script>
</layout:override>

<layout:override name="<%=Blocks.BLOCK_BODY%>">
    <div class="container-fluid">
        <div class="animated fadeIn">
            <div class="row">
                <div class="col-md-12">
                    <div class="card">
                        <div class="card-header">
                            <strong>充值额度设置</strong>
                            <small>Balance Settings</small>
                        </div>
                        <div class="card-block">
                            <form id="balance_settings_form" method="post" class="form-horizontal row" novalidate onsubmit="return false;">
                                <div class="col-md-3">
                                    <div class="form-group row">
                                        <label class="col-md-4 form-control-label" for="bs_balance">
                                            <span class="text-danger">*</span> 充值金额
                                        </label>
                                        <div class="col-md-8">
                                            <input type="text" class="form-control" id="bs_balance" placeholder="请输入充值金额" name="dictValue"
                                                   data-val="true" data-val-required="充值金额不能为空" autocomplete="off">
                                            <div data-valmsg-for="dictValue" data-valmsg-replace="true"></div>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-md-3">
                                    <div class="form-group row">
                                        <label class="col-md-4 form-control-label" for="bs_balance_addi">
                                            <span class="text-danger">*</span> 赠送金额
                                        </label>
                                        <div class="col-md-8">
                                            <input type="text" class="form-control" id="bs_balance_addi" placeholder="请输入赠送金额" name="dictAdditional"
                                                   data-val="true" data-val-required="赠送金额不能为空" autocomplete="off">
                                            <div data-valmsg-for="dictAdditional" data-valmsg-replace="true"></div>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-md-6">
                                    <div class="form-group row">
                                        <div class="col-md-12">
                                            <button type="submit" class="btn btn-primary" id="bs_settings">
                                                <i class="fa fa-check"></i> 设 定
                                            </button>
                                        </div>
                                    </div>
                                </div>
                            </form>
                        </div>
                        <div class="card-footer"></div>
                        <div class="card-block">
                            <table class="table table-striped table-sm balance-list">
                                <thead>
                                <tr>
                                    <th>##</th>
                                    <th>充值金额</th>
                                    <th>赠送金额</th>
                                    <th>创建时间</th>
                                    <th></th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach var="dict" items="${dicts}" varStatus="status">
                                    <tr data-id="">
                                        <td>${status.index+1}</td>
                                        <td>￥${dict.dictValue}</td>
                                        <td>￥${dict.dictAdditional}</td>
                                        <td>${dict.createTime}</td>
                                        <td>
                                            <a href="javascript:;" class="btn btn-sm btn-danger balance-delete" title="删除" data-id="${dict.dictId}">
                                                <i class="fa fa-remove"></i> 删除
                                            </a>
                                        </td>
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
    <c:param name="subMenu" value="balance"/>
</c:import>
