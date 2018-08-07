<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ page import="com.wardrobe.layout.Blocks" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> <%-- JSTL表达式（判断，循环，输出） --%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %> <%-- 方法表达式（字符串截取，替换） --%>
<%@ taglib uri="http://www.sychu.com/tags/tag" prefix="layout" %>

<layout:override name="<%=Blocks.BLOCK_HEADER_CSS%>">
    <link href="Content/utils/jqueryAlert/alert/alert.css?v=${static_resource_version}" rel="stylesheet">
    <link href="Content/css/jquery.steps.css?v=${static_resource_version}" rel="stylesheet">
    <style type="text/css">
        .wizard > .content > .body {
            position: relative;
        }
        .form-control-label {
            text-align: right;
            font-weight: bold;
        }
        .icon-sports {}
        .icon-gaoerfu {
            background: url("/Content/images/sports/icon-gaoerfu.png") no-repeat center;
            background-size: 1.5rem;
        }
        .icon-huaxue {
            background: url("/Content/images/sports/icon-huaxue.png") no-repeat center;
            background-size: 1.5rem;
        }
        .icon-lanqiu {
            background: url("/Content/images/sports/icon-lanqiu.png") no-repeat center;
            background-size: 1.5rem;
        }
        .icon-paiqiu {
            background: url("/Content/images/sports/icon-paiqiu.png") no-repeat center;
            background-size: 1.5rem;
        }
        .icon-pingpangqiu {
            background: url("/Content/images/sports/icon-pingpangqiu.png") no-repeat center;
            background-size: 1.5rem;
        }
        .icon-wangqiu {
            background: url("/Content/images/sports/icon-wangqiu.png") no-repeat center;
            background-size: 1.5rem;
        }
        .icon-youyong {
            background: url("/Content/images/sports/icon-youyong.png") no-repeat center;
            background-size: 1.5rem;
        }
        .icon-yumaoqiu {
            background: url("/Content/images/sports/icon-yumaoqiu.png") no-repeat center;
            background-size: 1.5rem;
        }
        .icon-zuqiu {
            background: url("/Content/images/sports/icon-zuqiu.png") no-repeat center;
            background-size: 1.5rem;
        }
        .icon-bangqiu {
            background: url("/Content/images/sports/icon-bangqiu.png") no-repeat center;
            background-size: 1.5rem;
        }
        .sport-item .sport-setting {
            margin-top: -2rem;
        }
        .sport-item a {
            text-decoration: none;
        }
    </style>
</layout:override>

<layout:override name="<%=Blocks.BLOCK_HEADER_SCRIPTS%>">
    <script type="text/javascript" src="Content/js/require.js?v=${static_resource_version}"
            data-main="Content/js/app/init/index.js?v=${static_resource_version}"></script>
</layout:override>

<layout:override name="<%=Blocks.BLOCK_BODY%>">
    <div class="container-fluid">
        <div class="animated fadeIn">
            <div class="row">
                <div class="offset-2 col-md-8">
                    <div id="init-steps">
                        <h3>初始化机构信息</h3>
                        <section>
                            <div class="card">
                                <div class="card-header">
                                    <strong>机构信息</strong>
                                    <small>Information</small>
                                </div>
                                <div class="card-block">
                                    <form id="info_form" method="post" class="form-horizontal row" novalidate onsubmit="return false;">
                                        <div class="col-md-12">
                                            <div class="form-group row">
                                                <label class="col-md-3 form-control-label" for="info_tag_name">
                                                    <span class="text-danger">*</span> 机构名称
                                                </label>
                                                <div class="col-md-9">
                                                    <input type="text" id="info_tag_name" name="orgName" class="form-control" placeholder="请输入机构名称"
                                                           data-val="true" data-val-required="机构名称不能为空"
                                                           data-val-length-max="20" data-val-length-min="2" data-val-length="机构名称必须包含 2~20 个字符">
                                                    <div data-valmsg-for="orgName" data-valmsg-replace="true"></div>
                                                </div>
                                            </div>
                                            <div class="form-group row">
                                                <label class="col-md-3 form-control-label" for="info_org_note">
                                                    <span class="text-danger">*</span> 机构简介
                                                </label>
                                                <div class="col-md-9">
                                                    <textarea rows="9" class="form-control" id="info_org_note" name="orgNote" placeholder="一句话介绍机构简介"
                                                            data-val="true" data-val-required="机构简介不能为空"
                                                            data-val-length-max="200" data-val-length-min="10" data-val-length="机构简介必须包含 10~200 个字符"></textarea>
                                                    <div data-valmsg-for="orgNote" data-valmsg-replace="true"></div>
                                                </div>
                                            </div>
                                            <div class="form-group row">
                                                <label class="col-md-3 form-control-label" for="info_contact_name">
                                                    <span class="text-danger">*</span> 联系人
                                                </label>
                                                <div class="col-md-9">
                                                    <input type="text" class="form-control" id="info_contact_name" name="contactName" placeholder="请输入联系人"
                                                           data-val="true" data-val-required="联系人不能为空"
                                                           data-val-length-max="10" data-val-length-min="2" data-val-length="联系人必须包含 2~10 个字符">
                                                    <div data-valmsg-for="contactName" data-valmsg-replace="true"></div>
                                                </div>
                                            </div>
                                            <div class="form-group row">
                                                <label class="col-md-3 form-control-label" for="info_contact_mobile">
                                                    <span class="text-danger">*</span> 手机号码
                                                </label>
                                                <div class="col-md-9">
                                                    <input type="text" class="form-control" id="info_contact_mobile" name="contactMobile" placeholder="请输入手机号码"
                                                           data-val="true" data-val-required="手机号码不能为空"
                                                           data-val-regex-pattern="^(13[0-9]|15[012356789]|18[02356789]|14[57])[0-9]{8}$" data-val-regex="手机号码格式不正确">
                                                    <div data-valmsg-for="contactMobile" data-valmsg-replace="true"></div>
                                                </div>
                                            </div>
                                        </div>
                                    </form>
                                </div>
                                <div class="card-footer">
                                    <button type="button" class="btn btn-primary save-settings">
                                        <i class="fa fa-check"></i> 保 存
                                    </button>
                                </div>
                            </div>
                        </section>
                        <h3>添加超级管理员</h3>
                        <section>
                            <div class="card">
                                <div class="card-header">
                                    <strong>超级管理员</strong>
                                    <small>Admin</small>
                                </div>
                                <div class="card-block">
                                    <form id="admin_form" method="post" class="form-horizontal" novalidate onsubmit="return false;">
                                        <input type="hidden" id="admin_id" name="orgId">
                                        <div class="form-group row">
                                            <label class="col-md-3 form-control-label" for="admin_userNo">
                                                <span class="text-danger">*</span> 运营编号
                                            </label>
                                            <div class="col-md-6">
                                                <input type="text" class="form-control" id="admin_userNo" name="userNo" placeholder="请输入运营编号"
                                                       data-val="true" data-val-required="运营编号不能为空">
                                                <div data-valmsg-for="userNo" data-valmsg-replace="true"></div>
                                            </div>
                                            <div class="col-md-3">
                                                <button type="button" class="btn btn-sm btn-primary random-operator" title="随机">
                                                    <i class="fa fa-random"></i> 随机
                                                </button>
                                            </div>
                                        </div>
                                        <div class="form-group row">
                                            <label class="col-md-3 form-control-label" for="admin_userName">
                                                <span class="text-danger">*</span> 运营账号
                                            </label>
                                            <div class="col-md-9">
                                                <input type="text" class="form-control" id="admin_userName" name="userName" placeholder="请输入运营账号"
                                                       data-val="true" data-val-required="运营账号不能为空"
                                                       data-val-regex-pattern="^[a-zA-Z][a-zA-Z0-9_]{4,15}$" data-val-regex="运营账号格式不正确">
                                                <div data-valmsg-for="userName" data-valmsg-replace="true"></div>
                                            </div>
                                        </div>
                                        <div class="form-group row">
                                            <label class="col-md-3 form-control-label" for="admin_realName">
                                                <span class="text-danger">*</span> 真实姓名
                                            </label>
                                            <div class="col-md-9">
                                                <input type="text" class="form-control" id="admin_realName" name="realName" placeholder="请输入真实姓名"
                                                       data-val="true" data-val-required="真实姓名不能为空"
                                                       data-val-length-max="10" data-val-length-min="2" data-val-length="真实姓名必须包含 2~10 个字符">
                                                <div data-valmsg-for="realName" data-valmsg-replace="true"></div>
                                            </div>
                                        </div>
                                        <div class="form-group row">
                                            <label class="col-md-3 form-control-label" for="admin_mobile">
                                                <span class="text-danger">*</span> 手机号码
                                            </label>
                                            <div class="col-md-9">
                                                <input type="text" class="form-control" id="admin_mobile" name="mobile" placeholder="请输入手机号码"
                                                       data-val="true" data-val-required="手机号码不能为空"
                                                       data-val-regex-pattern="^(13[0-9]|15[012356789]|166|17[0-9]|18[012356789]|14[57]|19[89])[0-9]{8}$" data-val-regex="手机号码格式不正确">
                                                <div data-valmsg-for="mobile" data-valmsg-replace="true"></div>
                                            </div>
                                        </div>
                                        <div class="form-group row">
                                            <label class="col-md-3 form-control-label" for="admin_idCard">
                                                <span class="text-danger">*</span> 身份证号
                                            </label>
                                            <div class="col-md-9">
                                                <input type="text" class="form-control" id="admin_idCard" name="idCard" placeholder="请输入身份证号"
                                                       data-val="true" data-val-required="身份证号不能为空"
                                                       data-val-regex-pattern="^\d{17}([0-9]|X|x)$" data-val-regex="身份证号格式不正确">
                                                <div data-valmsg-for="idCard" data-valmsg-replace="true"></div>
                                            </div>
                                        </div>
                                    </form>
                                </div>
                                <div class="card-footer">
                                    <button type="button" class="btn btn-primary save-operator">
                                        <i class="fa fa-check"></i> 保 存
                                    </button>
                                </div>
                            </div>
                        </section>
                        <h3>设置开通的权限</h3>
                        <section>
                            <div class="card">
                                <div class="card-header">
                                    <strong>权限设置</strong>
                                    <small>Limits</small>
                                </div>
                                <div class="card-block row sport-list">
                                    <input type="hidden" id="sport_admin_id" name="orgId">
                                    <div class="col-md-6">
                                        <div class="card card-accent-success sport-item"
                                             data-class="icon-lanqiu" data-en="basketball" data-ch="篮球">
                                            <a class="card-body p-0" href="javascript:;">
                                                <div class="p-2 clearfix">
                                                    <i class="icon-sports icon-lanqiu bg-primary p-4 mr-3 float-left"></i>
                                                    <div class="mb-0 pt-1">篮球</div>
                                                    <div class="text-muted text-uppercase font-weight-bold font-xs">basketball</div>
                                                    <i class="fa fa-check-circle-o p-2 float-right sport-setting"></i>
                                                </div>
                                            </a>
                                        </div>
                                    </div>
                                    <div class="col-md-6">
                                        <div class="card card-accent-secondary sport-item"
                                             data-class="icon-zuqiu" data-en="football" data-ch="足球">
                                            <a class="card-body p-0" href="javascript:;">
                                                <div class="p-2 clearfix">
                                                    <i class="icon-sports icon-zuqiu bg-primary p-4 mr-3 float-left"></i>
                                                    <div class="mb-0 pt-1">足球</div>
                                                    <div class="text-muted text-uppercase font-weight-bold font-xs">football</div>
                                                    <i class="fa fa-circle-o p-2 float-right sport-setting"></i>
                                                </div>
                                            </a>
                                        </div>
                                    </div>
                                    <div class="col-md-6">
                                        <div class="card card-accent-secondary sport-item"
                                             data-class="icon-youyong" data-en="swimming" data-ch="游泳">
                                            <a class="card-body p-0" href="javascript:;">
                                                <div class="p-2 clearfix">
                                                    <i class="icon-sports icon-youyong bg-primary p-4 mr-3 float-left"></i>
                                                    <div class="mb-0 pt-1">游泳</div>
                                                    <div class="text-muted text-uppercase font-weight-bold font-xs">swimming</div>
                                                    <i class="fa fa-circle-o p-2 float-right sport-setting"></i>
                                                </div>
                                            </a>
                                        </div>
                                    </div>
                                    <div class="form-group clearfix col-md-12">
                                        <div>
                                            <input type="text" class="form-control" id="admin_number" name="number" placeholder="请输入网点数量（0不限制）" value="0">
                                        </div>
                                    </div>
                                </div>
                                <div class="card-footer">
                                    <button type="button" class="btn btn-primary save-limit">
                                        <i class="fa fa-check"></i> 保 存
                                    </button>
                                </div>
                            </div>
                        </section>
                    </div>
                </div>
            </div>
            <!--/.row-->
        </div>

    </div>
</layout:override>

<c:import url="../Shared/PassportLayout.jsp">
    <c:param name="nav" value="init"/>
</c:import>
