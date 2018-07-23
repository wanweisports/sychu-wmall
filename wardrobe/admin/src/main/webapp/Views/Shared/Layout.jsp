<%@ page trimDirectiveWhitespaces="true" %>
<%@ page import="com.wardrobe.layout.Blocks" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="layout" uri="http://www.wanwei.com/tags/tag"%>

<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <base href="<%= basePath %>">

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="renderer" content="webkit|ie-comp|ie-stand">

    <title>${param.title == null ? "" : param.title}</title>

    <meta name="keywords"
          content="${param.keyword == null ? "" : param.keyword}">
    <meta name="description"
          content="${param.desc == null ? "" : param.desc}">
    <meta name="author" content="">

    <!--[if lt IE 9]>
    <meta http-equiv="refresh" content="0;ie.html" />
    <![endif]-->

    <link href="Content/favicon.ico?v=${static_resource_version}" rel="shortcut icon" type="image/x-icon">

    <link href="Content/lib/bootstrap/bootstrap.min.css?v=${static_resource_version}" rel="stylesheet" type="text/css">
    <link href="Content/lib/font-awesome/font-awesome.min.css?v=${static_resource_version}" rel="stylesheet" type="text/css">
    <link href="Content/lib/animate/animate.min.css?v=${static_resource_version}" rel="stylesheet" type="text/css">
    <link href="Content/lib/hplus.css?v=${static_resource_version}" rel="stylesheet" type="text/css">
    <layout:block name="<%=Blocks.BLOCK_HEADER_CSS%>"/>
</head>
<body class="gray-bg">
    <div class="wrapper wrapper-content animated fadeInRight">
        <layout:block name="<%=Blocks.BLOCK_BODY%>"/>
    </div>

    <div class="modal fade" id="tips_failure_modal" tabindex="-1" role="dialog" aria-labelledby="tips_failure_modal_label">
        <div class="modal-dialog modal-sm">
            <div class="modal-content">
                <div class="modal-body">
                    <div class="alert alert-danger tips-content" role="alert"></div>
                </div>
            </div>
        </div>
    </div>

    <div class="modal fade" id="tips_success_modal" tabindex="-1" role="dialog" aria-labelledby="tips_success_modal_label">
        <div class="modal-dialog modal-sm">
            <div class="modal-content">
                <div class="modal-body">
                    <div class="alert alert-success tips-content" role="alert"></div>
                </div>
            </div>
        </div>
    </div>

    <div class="modal fade" id="tips_confirm_modal" tabindex="-1" role="dialog" aria-labelledby="tips_confirm_modal_label" aria-hidden="true">
        <div class="modal-dialog modal-sm">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                    <h5 class="modal-title" id="tips_confirm_modal_label">确认提示</h5>
                </div>
                <div class="modal-body">
                    <div class="alert alert-warning tips-content" role="alert"></div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-success" data-dismiss="modal">
                        <span class="glyphicon glyphicon-ok"></span> 确 定
                    </button>
                </div>
            </div>
        </div>
    </div>

    <!-- 全局js -->
    <script src="Content/lib/jquery/jquery-1.12.3.min.js?v=${static_resource_version}"></script>
    <script src="Content/lib/bootstrap/bootstrap.min.js?v=${static_resource_version}"></script>
   <script src="Content/lib/moment/moment.min.js?v=${static_resource_version}"></script>
    <script src="Content/lib/jquery/jquery.cookie/jquery.cookie-1.4.1.min.js?v=${static_resource_version}"></script>
    <script src="Content/app/common/text_magnifier.js?v=${static_resource_version}"></script>
    <script src="Content/app/common/base_new.js?v=${static_resource_version}"></script>

    <layout:block name="<%=Blocks.BLOCK_HEADER_SCRIPTS%>"/>
    <layout:block name="<%=Blocks.BLOCK_TRACE_SCRIPTS%>"/>

    <div class="gohome" style="display: none;">
        <a class="animated bounceInUp" href="/" title="工作面板">
            <i class="fa fa-home"></i>
        </a>
    </div>

</body>
</html>
