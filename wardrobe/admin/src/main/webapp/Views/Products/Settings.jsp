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
    <div class="container-fluid">
        <div class="animated fadeIn">
            <div class="row">
                <div class="col-md-6">
                    <div class="card">
                        <div class="card-header">
                            <strong>商品品类</strong>
                            <small>Category</small>
                            <div class="card-actions">
                                <a href="javascript:;" class="btn-plus"><i class="icon-plus"></i></a>
                            </div>
                        </div>
                        <div class="card-block">
                            <button type="button" class="btn btn-success">连衣裙&nbsp;<i class="fa fa-remove"></i></button>
                            <button type="button" class="btn btn-success">西服&nbsp;<i class="fa fa-remove"></i></button>
                            <button type="button" class="btn btn-success">外套&nbsp;<i class="fa fa-remove"></i></button>
                            <button type="button" class="btn btn-success">下装&nbsp;<i class="fa fa-remove"></i></button>
                            <button type="button" class="btn btn-success">连衣裙&nbsp;<i class="fa fa-remove"></i></button>
                            <button type="button" class="btn btn-success">西服&nbsp;<i class="fa fa-remove"></i></button>
                            <hr>
                            <button type="button" class="btn btn-success">外套&nbsp;<i class="fa fa-remove"></i></button>
                            <button type="button" class="btn btn-success">下装&nbsp;<i class="fa fa-remove"></i></button>
                            <button type="button" class="btn btn-success">连衣裙&nbsp;<i class="fa fa-remove"></i></button>
                            <button type="button" class="btn btn-success">西服&nbsp;<i class="fa fa-remove"></i></button>
                            <button type="button" class="btn btn-success">外套&nbsp;<i class="fa fa-remove"></i></button>
                            <button type="button" class="btn btn-success">下装&nbsp;<i class="fa fa-remove"></i></button>
                        </div>
                    </div>
                </div>
                <div class="col-md-6">
                    <div class="card">
                        <div class="card-header">
                            <strong>商品风格</strong>
                            <small>Style</small>
                            <div class="card-actions">
                                <a href="javascript:;" class="btn-plus"><i class="icon-plus"></i></a>
                            </div>
                        </div>
                        <div class="card-block">
                            <button type="button" class="btn btn-success">职场&nbsp;<i class="fa fa-remove"></i></button>
                            <button type="button" class="btn btn-success">职场&nbsp;<i class="fa fa-remove"></i></button>
                            <button type="button" class="btn btn-success">职场&nbsp;<i class="fa fa-remove"></i></button>
                        </div>
                    </div>
                </div>
                <div class="col-md-6">
                    <div class="card">
                        <div class="card-header">
                            <strong>商品材质</strong>
                            <small>Material</small>
                            <div class="card-actions">
                                <a href="javascript:;" class="btn-plus"><i class="icon-plus"></i></a>
                            </div>
                        </div>
                        <div class="card-block">
                            <button type="button" class="btn btn-success">纯棉&nbsp;<i class="fa fa-remove"></i></button>
                            <button type="button" class="btn btn-success">纯棉&nbsp;<i class="fa fa-remove"></i></button>
                            <button type="button" class="btn btn-success">纯棉&nbsp;<i class="fa fa-remove"></i></button>
                            <button type="button" class="btn btn-success">纯棉&nbsp;<i class="fa fa-remove"></i></button>
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
    <c:param name="subMenu" value="settings"/>
</c:import>
