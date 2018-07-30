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

    <title>${param.title == null || param.title eq "" ? "北体高科 gaokesport.com - 智慧场馆解决方案的引导者 - TO BE THE GIANT OF WISDOM VENUES SOLUTIONS" : param.title}</title>

    <meta name="keywords" content="${param.keyword == null || param.keyword eq "" ? "北体高科, 智能体育场馆, 物联网, 无线网, 体育场馆, 预订, 收银, gaokesport.com" : param.keyword}"/>
    <meta name="description" content="${param.desc == null || param.desc eq "" ? "北体高科 gaokesport.com - 智慧场馆解决方案的引导者。" : param.desc}"/>
    <meta name="author" content="北体高科（北京）科技有限公司" />

    <!--[if lt IE 9]>
    <meta http-equiv="refresh" content="0;ie.html" />
    <![endif]-->

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

<body class="app flex-row align-items-center">

<!-- content -->
<layout:block name="<%=Blocks.BLOCK_BODY%>"/>


<!-- js -->
<script type="text/javascript" src="Content/js/require.js?v=${static_resource_version}"
        data-main="Content/js/app.js?v=${static_resource_version}"></script>

<layout:block name="<%=Blocks.BLOCK_HEADER_SCRIPTS%>"/>
<layout:block name="<%=Blocks.BLOCK_TRACE_SCRIPTS%>"/>

</body>
</html>
