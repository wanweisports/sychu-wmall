<%@ page trimDirectiveWhitespaces="true" %>
<%@ page import="com.wardrobe.layout.Blocks" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="layout" uri="http://www.sychu.com/tags/tag"%>

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

    <title>闪衣橱管理系统</title>

    <meta name="keywords"
          content="${param.keyword == null ? "闪衣橱管理系统" : "闪衣橱管理系统"}">
    <meta name="description"
          content="${param.desc == null ? "闪衣橱管理系统" : "闪衣橱管理系统"}">
    <meta name="author" content="北体高科（北京）科技有限公司">

    <!--[if lt IE 9]><meta http-equiv="refresh" content="0;ie.html" /><![endif]-->

    <!-- favicon -->
    <link href="Content/images/favicon.ico?v=${static_resource_version}" rel="shortcut icon" type="image/x-icon">

    <!-- icons -->
    <link href="Content/bower_components/fontawesome/css/font-awesome.min.css?v=${static_resource_version}"
          rel="stylesheet">
    <link href="Content/bower_components/simple-line-icons/css/simple-line-icons.css?v=${static_resource_version}"
          rel="stylesheet">

    <!-- css -->
    <link href="Content/css/base.css?v=${static_resource_version}" rel="stylesheet">
    <layout:block name="<%=Blocks.BLOCK_HEADER_CSS%>"/>
</head>
<body>

<div class="app-body">
    <!-- main -->
    <layout:block name="<%=Blocks.BLOCK_BODY%>"/>
</div>

<!-- js -->
<script type="text/javascript" src="Content/js/require.js?v=${static_resource_version}"
        data-main="Content/js/app.js?v=${static_resource_version}"></script>

<layout:block name="<%=Blocks.BLOCK_HEADER_SCRIPTS%>"/>
<layout:block name="<%=Blocks.BLOCK_TRACE_SCRIPTS%>"/>

</body>
</html>
