<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>

<header class="app-header navbar">
    <button class="navbar-toggler mobile-sidebar-toggler d-lg-none" type="button">☰</button>
    <a class="navbar-brand" href="javascript:;"></a>
    <ul class="nav navbar-nav d-md-down-none">
        <li class="nav-item">
            <a class="nav-link navbar-toggler sidebar-toggler" href="javascript:;">☰</a>
        </li>
        <li class="nav-item px-3">
            <a class="nav-link" href="/admin/dashboard/index">商城首页</a>
        </li>
        <li class="nav-item px-3">
            <a class="nav-link" href="/admin/venue/coaches">商品列表</a>
        </li>
        <li class="nav-item px-3">
            <a class="nav-link" href="/admin/class/list">会员列表</a>
        </li>
        <li class="nav-item px-3">
            <a class="nav-link" href="/admin/data/summary">收支统计</a>
        </li>
    </ul>
    <ul class="nav navbar-nav ml-auto">
        <li class="nav-item d-md-down-none" style="display: none">
            <a class="nav-link" href="#" title="未读消息">
                <i class="icon-bell"></i>
                <span class="badge badge-pill badge-danger">5</span>
            </a>
        </li>
        <li class="nav-item d-md-down-none pr-4">
            <a class="nav-link" href="/admin/helper/index">
                <i class="icon-docs"></i> 操作指南
            </a>
        </li>
        <li class="nav-item dropdown pr-4">
            <a class="nav-link dropdown-toggle nav-link" data-toggle="dropdown" href="#" role="button"
                aria-haspopup="true" aria-expanded="false">
                <img src="Content/images/avatars/6.jpg?v=${static_resource_version}" class="img-avatar" alt="${Admin.realName}">
                <span class="d-md-down-none">${Admin.realName}</span>
            </a>
            <div class="dropdown-menu dropdown-menu-right">
                <a class="dropdown-item" href="/admin/settings/profile">
                    <i class="fa fa-pencil"></i> 完善信息
                </a>
                <a class="dropdown-item" href="/admin/settings/profile">
                    <i class="fa fa-key"></i> 修改密码
                </a>
                <a class="dropdown-item" href="/admin/passport/logout">
                    <i class="fa fa-lock"></i> 退出登录
                </a>
            </div>
        </li>
    </ul>
</header>
