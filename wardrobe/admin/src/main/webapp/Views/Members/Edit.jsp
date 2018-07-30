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
        label.radio-inline {
            margin-right: .5rem;
            margin-bottom: 0;
            margin-top: .25rem;
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
                            <strong>学员编辑</strong>
                            <small>Edit</small>
                        </div>
                        <div class="card-block">
                            <form id="students_form" method="post" class="form-horizontal row" novalidate onsubmit="return false;">
                                <input type="hidden" id="stu_studentId" name="id" value="${orgStudents.id}">
                                <div class="col-md-6">
                                    <div class="form-group row">
                                        <label class="col-md-3 form-control-label" for="stu_realName">
                                            <span class="text-danger">*</span> 学员姓名
                                        </label>
                                        <div class="col-md-9">
                                            <input type="text" class="form-control" id="stu_realName" placeholder="请输入学员姓名" name="realName"
                                                   value="${orgStudents.realName}"
                                                   data-val="true" data-val-required="学员姓名不能为空"
                                                   data-val-length-max="10" data-val-length-min="2" data-val-length="学员姓名必须包含 2~10 个字符">
                                            <div data-valmsg-for="realName" data-valmsg-replace="true"></div>
                                        </div>
                                    </div>
                                    <div class="form-group row">
                                        <label class="col-md-3 form-control-label">
                                            <span class="text-danger">*</span> 出生日期
                                        </label>
                                        <div class="col-md-9">
                                            <input type="text" class="form-control datepicker" placeholder="请选择出生日期" name="birthday"
                                                   value="${orgStudents.birthday}"
                                                   data-val="true" data-val-required="出生日期不能为空"
                                                   data-val-regex-pattern="^\d{4}-\d{2}-\d{2}$" data-val-regex="出生日期格式不正确">
                                            <div data-valmsg-for="birthday" data-valmsg-replace="true"></div>
                                        </div>
                                    </div>
                                    <div class="form-group row">
                                        <label class="col-md-3 form-control-label">
                                            <span class="text-danger">*</span> 身高(cm)
                                        </label>
                                        <div class="col-md-9">
                                            <input type="text" class="form-control" placeholder="请输入身高（eg:120）" name="height"
                                                   value="${orgStudents.height}"
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
                                                   value="${orgStudents.mobile}"
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
                                                <input type="radio" id="sex2" name="sex" value="2"
                                                       <c:if test="${orgStudents.sex == 2}">checked</c:if> > 女
                                            </label>
                                        </div>
                                    </div>
                                    <div class="form-group row">
                                        <label class="col-md-3 form-control-label">
                                            <span class="text-danger">*</span> 体重(kg)
                                        </label>
                                        <div class="col-md-9">
                                            <input type="text" class="form-control" placeholder="请输入体重（eg:65）" name="weight"
                                                   value="${orgStudents.weight}"
                                                   data-val="true" data-val-required="体重不能为空"
                                                   data-val-regex-pattern="^([3-9]\d|[1-2]\d{2})$" data-val-regex="体重格式不正确">
                                            <div data-valmsg-for="weight" data-valmsg-replace="true"></div>
                                        </div>
                                    </div>
                                </div>
                            </form>
                        </div>
                        <div class="card-footer">
                            <button type="button" class="btn btn-primary save-students">
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
    <c:param name="menu" value="students"/>
    <c:param name="subMenu" value="edit"/>
</c:import>
