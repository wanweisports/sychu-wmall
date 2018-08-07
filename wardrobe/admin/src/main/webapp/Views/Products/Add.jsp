<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ page import="com.wardrobe.layout.Blocks" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> <%-- JSTL表达式（判断，循环，输出） --%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %> <%-- 方法表达式（字符串截取，替换） --%>
<%@ taglib uri="http://www.sychu.com/tags/tag" prefix="layout" %>

<layout:override name="<%=Blocks.BLOCK_HEADER_CSS%>">
    <link href="Content/bower_components/bootstrap-datepicker/dist/css/bootstrap-datepicker3.css?v=${static_resource_version}" rel="stylesheet">
    <style type="text/css">
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
        .class-item {
            cursor: pointer;
        }
        .class-item.class-item-selected {
            border: 2px solid #4dbd74;
        }
        label.radio-inline {
            margin-right: .5rem;
            margin-bottom: 0;
            margin-top: .25rem;
        }
    </style>
</layout:override>

<layout:override name="<%=Blocks.BLOCK_HEADER_SCRIPTS%>">
    <script type="text/javascript" src="Content/js/require.js?v=${static_resource_version}"
            data-main="Content/js/app/students/add.js?v=${static_resource_version}"></script>
</layout:override>

<layout:override name="<%=Blocks.BLOCK_BODY%>">

    <%@ include file="../Shared/Payment.jsp" %>

    <div class="container-fluid">
        <div class="animated fadeIn">
            <div class="row">
                <div class="col-md-12">
                    <div class="card">
                        <div class="card-header">
                            <strong>商品添加</strong>
                            <small>Product Add</small>
                        </div>
                        <div class="card-block">
                            <form id="product_form" method="post" class="form-horizontal row" novalidate onsubmit="return false;">
                                <div class="col-md-6">
                                    <div class="form-group row">
                                        <label class="col-md-3 form-control-label" for="p_commName">
                                            <span class="text-danger">*</span> 商品名称
                                        </label>
                                        <div class="col-md-9">
                                            <input type="text" class="form-control" id="p_commName" placeholder="请输入商品名称" name="commName"
                                                   data-val="true" data-val-required="商品名称不能为空"
                                                   data-val-length-max="20" data-val-length-min="2" data-val-length="商品名称必须包含 2~20 个字符">
                                            <div data-valmsg-for="commName" data-valmsg-replace="true"></div>
                                        </div>
                                    </div>
                                    <div class="form-group row">
                                        <label class="col-md-3 form-control-label" for="stu_birthday">
                                            <span class="text-danger">*</span> 出生日期
                                        </label>
                                        <div class="col-md-9">
                                            <input type="text" class="form-control datepicker" id="stu_birthday" placeholder="请选择出生日期" name="birthday"
                                                   data-val="true" data-val-required="出生日期不能为空"
                                                   data-val-regex-pattern="^\d{4}-\d{2}-\d{2}$" data-val-regex="出生日期格式不正确">
                                            <div data-valmsg-for="birthday" data-valmsg-replace="true"></div>
                                        </div>
                                    </div>
                                    <div class="form-group row">
                                        <label class="col-md-3 form-control-label" for="stu_height">
                                            <span class="text-danger">*</span> 身高(cm)
                                        </label>
                                        <div class="col-md-9">
                                            <input type="text" class="form-control" id="stu_height" placeholder="请输入身高（eg:120）" name="height"
                                                   data-val="true" data-val-required="身高不能为空"
                                                   data-val-regex-pattern="^([3-9]\d|[1-2]\d{2})$" data-val-regex="身高格式不正确">
                                            <div data-valmsg-for="height" data-valmsg-replace="true"></div>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-md-6">
                                    <div class="form-group row">
                                        <label class="col-md-3 form-control-label" for="stu_mobile">
                                            <span class="text-danger">*</span> 手机号码
                                        </label>
                                        <div class="col-md-9">
                                            <input type="text" class="form-control" id="stu_mobile" placeholder="请输入手机号码" name="mobile"
                                                   data-val="true" data-val-required="手机号码不能为空"
                                                   data-val-regex-pattern="^(13[0-9]|15[012356789]|166|17[0-9]|18[012356789]|14[57]|19[89])[0-9]{8}$" data-val-regex="手机号码格式不正确">
                                            <div data-valmsg-for="mobile" data-valmsg-replace="true"></div>
                                        </div>
                                    </div>
                                    <div class="form-group row">
                                        <label class="col-md-3 form-control-label">
                                            <span class="text-danger">*</span> 性别
                                        </label>
                                        <div class="col-md-9">
                                            <label class="radio-inline" for="sex1">
                                                <input type="radio" id="sex1" name="sex" value="1" checked> 男
                                            </label>
                                            <label class="radio-inline" for="sex2">
                                                <input type="radio" id="sex2" name="sex" value="2"> 女
                                            </label>
                                        </div>
                                    </div>
                                    <div class="form-group row">
                                        <label class="col-md-3 form-control-label" for="stu_weight">
                                            <span class="text-danger">*</span> 体重(kg)
                                        </label>
                                        <div class="col-md-9">
                                            <input type="text" class="form-control" id="stu_weight" placeholder="请输入体重（eg:65）" name="weight"
                                                   data-val="true" data-val-required="体重不能为空"
                                                   data-val-regex-pattern="^([3-9]\d|[1-2]\d{2})$" data-val-regex="体重格式不正确">
                                            <div data-valmsg-for="weight" data-valmsg-replace="true"></div>
                                        </div>
                                    </div>
                                </div>
                            </form>
                        </div>
                        <div class="card-footer"></div>
                        <div class="card-block">
                            <form id="class_form" method="post" class="form-horizontal" novalidate onsubmit="return false;">
                                <input type="hidden" id="class_student_id" name="studentId">
                                <input type="hidden" id="class_id" name="classId">
                                <input type="hidden" id="class_ids" name="classIds">
                                <div class="row class-list">
                                    <c:forEach var="item" items="${orgClassList}" varStatus="loop">
                                        <c:if test="${item.orgClass.status != 3}">
                                            <div class="col-md-3 pull-left">
                                                <div class="card">
                                                    <div class="card-body p-3 clearfix class-item"
                                                         data-id="${item.orgClass.id}"
                                                         data-name="${item.orgClass.className}"
                                                         data-price="${item.orgClass.classPrice}">
                                                        <i class="icon-sports ${item.orgSports.sportIcon} bg-primary p-4 mr-3 float-left"></i>
                                                        <div class="mb-0 pt-1"> ${item.orgClass.className}</div>
                                                        <div class="text-muted text-uppercase font-weight-bold font-xs">
                                                            <c:if test="${item.orgClass.status == 1}"><i class="fa fa-battery-empty" title="已开班"></i></c:if>
                                                            <c:if test="${item.orgClass.status == 2}"><i class="fa fa-battery-half" title="上课中"></i></c:if>
                                                            &nbsp;${item.orgClass.classPrice}元
                                                        </div>
                                                    </div>
                                                    <div class="card-footer px-3 py-2">
                                                        <a class="font-weight-bold font-xs btn-block text-muted" href="/admin/class/list">
                                                            班级列表 <i class="fa fa-angle-right float-right font-lg"></i>
                                                        </a>
                                                    </div>
                                                </div>
                                            </div>
                                        </c:if>
                                    </c:forEach>
                                </div>
                            </form>
                        </div>
                        <div class="card-footer text-right">
                            <span class="text-danger pay-note"></span>
                            <button type="button" class="btn btn-primary save-class">
                                <i class="fa fa-check"></i> 保 存
                            </button>
                        </div>
                    </div>
                </div>
            </div>
            <!--/.row-->
        </div>

    </div>
</layout:override>

<c:import url="../Shared/GeneralLayout.jsp">
    <c:param name="menu" value="products"/>
    <c:param name="subMenu" value="add"/>
</c:import>
