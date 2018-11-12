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
            data-main="Content/js/app/members/transactionsLog.js?v=${static_resource_version}"></script>
</layout:override>

<layout:override name="<%=Blocks.BLOCK_BODY%>">
    <div class="modal fade" id="distribution_query_list" tabindex="-1" role="dialog">
        <div class="modal-dialog modal-default modal-lg" role="document" style="max-width: 1024px !important;">
            <div class="modal-content">
                <div class="modal-body">
                    <iframe src="/admin/against/members" style="width: 100%; height: 500px" id="distribution_iframe"></iframe>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-sm btn-secondary" id="distribution_query_list_hide" data-dismiss="modal">
                        <i class="fa fa-close"></i> 取 消
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
                            <strong>额度冲抵</strong>
                        </div>
                        <div class="card-block">
                            <form id="recharge_against_form" method="post" class="form-horizontal" novalidate onsubmit="return false;">
                                <div class="form-group row">
                                    <label class="col-md-3 form-control-label" for="distribution_product">
                                        <span class="text-danger">*</span> 选择会员
                                    </label>
                                    <div class="col-md-6">
                                        <input type="text" class="form-control" id="distribution_product" placeholder="请选择选择会员" name="commName"
                                               data-val="true" data-val-required="请选择选择会员" autocomplete="off" readonly>
                                        <div data-valmsg-for="commName" data-valmsg-replace="true"></div>
                                    </div>
                                    <div class="col-md-2">
                                        <button type="button" class="btn btn-sm btn-primary" id="distribution_product_query" data-toggle="modal" data-target="#distribution_query_list">
                                            <i class="fa fa-search"></i> 检 索
                                        </button>
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <label class="col-md-3 form-control-label" for="distribution_roi">
                                        <span class="text-danger">*</span> 冲抵额度
                                    </label>
                                    <div class="col-md-3">
                                        <select class="form-control" id="distribution_roi" name="roid" data-val="true" data-val-required="请选择预约单">
                                            <option value="">余额冲抵</option>
                                            <option value="">薏米冲抵</option>
                                            <option value="">积分冲抵</option>
                                        </select>
                                        <div data-valmsg-for="size" data-valmsg-replace="true"></div>
                                    </div>
                                    <div class="col-md-6">
                                        <input type="text" class="form-control" id="distribution_code" placeholder="冲抵额度" name="rfidEpc"
                                               data-val="true" data-val-required="冲抵额度不能为空" autocomplete="off" <%--readonly--%>>
                                        <div data-valmsg-for="code" data-valmsg-replace="true"></div>
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <label class="col-md-3 form-control-label" for="distribution_code">
                                        <span class="text-danger">*</span> 冲抵备注
                                    </label>
                                    <div class="col-md-9">
                                        <textarea class="form-control"></textarea>
                                        <div data-valmsg-for="code" data-valmsg-replace="true"></div>
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <div class="offset-3 col-md-9">
                                        <button type="submit" class="btn btn-primary col-md-3">
                                            <i class="fa fa-check"></i> 确 认
                                        </button>
                                    </div>
                                </div>
                            </form>
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
    <c:param name="subMenu" value="against"/>
</c:import>
