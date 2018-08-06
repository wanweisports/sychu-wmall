<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> <%-- JSTL表达式（判断，循环，输出） --%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %> <%-- 方法表达式（字符串截取，替换） --%>
<%@ taglib uri="http://www.sychu.com/tags/tag" prefix="layout" %>

<div class="sidebar">
    <nav class="sidebar-nav">
        <ul class="nav">
            <li class="nav-item">
                <a class="nav-link" href="/admin/dashboard/index">
                    <i class="icon-graph"></i> 工作面板
                </a>
            </li>
            <li class="nav-item nav-dropdown <c:if test="${param.menu == 'members'}">open</c:if>">
                <a class="nav-link nav-dropdown-toggle" href="javascript:;">
                    <i class="icon-user"></i> 会员管理
                </a>
                <ul class="nav-dropdown-items">
                    <li class="nav-item <c:if test="${param.subMenu == 'list' || param.subMenu == 'detail'}">open</c:if>">
                        <a class="nav-link <c:if test="${param.subMenu == 'list' || param.subMenu == 'detail'}">active</c:if>" href="/admin/members/list">
                            <i class="icon-arrow-right"></i> 会员列表
                        </a>
                    </li>
                    <li class="nav-item <c:if test="${param.subMenu == 'transactions'}">open</c:if>">
                        <a class="nav-link <c:if test="${param.subMenu == 'transactions'}">active</c:if>" href="/admin/members/recharge/log">
                            <i class="icon-arrow-right"></i> 交易记录
                        </a>
                    </li>
                </ul>
            </li>

            <li class="nav-item nav-dropdown <c:if test="${param.menu == 'products'}">open</c:if>">
                <a class="nav-link nav-dropdown-toggle" href="javascript:;">
                    <i class="icon-home"></i> 商品管理
                </a>
                <ul class="nav-dropdown-items">
                    <li class="nav-item <c:if test="${param.subMenu == 'active'}">open</c:if>">
                        <a class="nav-link <c:if test="${param.subMenu == 'active'}">active</c:if>" href="/admin/products/active/settings">
                            <i class="icon-arrow-right"></i> 活动设置
                        </a>
                    </li>
                    <li class="nav-item <c:if test="${param.subMenu == 'list'}">open</c:if>">
                        <a class="nav-link <c:if test="${param.subMenu == 'list'}">active</c:if>" href="/admin/products/list">
                            <i class="icon-arrow-right"></i> 商品列表
                        </a>
                    </li>
                    <li class="nav-item <c:if test="${param.subMenu == 'orders' || param.subMenu == 'detail'}">open</c:if>">
                        <a class="nav-link <c:if test="${param.subMenu == 'orders' || param.subMenu == 'detail'}">active</c:if>" href="/admin/order/list">
                            <i class="icon-arrow-right"></i> 商品订单
                        </a>
                    </li>
                    <li class="nav-item <c:if test="${param.subMenu == 'add'}">open</c:if>">
                        <a class="nav-link <c:if test="${param.subMenu == 'add'}">active</c:if>" href="/admin/products/add">
                            <i class="icon-arrow-right"></i> 商品添加
                        </a>
                    </li>
                    <li class="nav-item <c:if test="${param.subMenu == 'add'}">open</c:if>">
                        <a class="nav-link <c:if test="${param.subMenu == 'add'}">active</c:if>" href="/admin/products/add">
                            <i class="icon-arrow-right"></i> 筛选管理
                        </a>
                    </li>
                    <li class="nav-item <c:if test="${param.subMenu == 'list'}">open</c:if>">
                        <a class="nav-link <c:if test="${param.subMenu == 'list'}">active</c:if>" href="/admin/products/list">
                            <i class="icon-arrow-right"></i> 库存变更日志
                        </a>
                    </li>
                </ul>
            </li>

            <li class="nav-item nav-dropdown <c:if test="${param.menu == 'wardrobe'}">open</c:if>">
                <a class="nav-link nav-dropdown-toggle" href="javascript:;">
                    <i class="icon-home"></i> 试衣间管理
                </a>
                <ul class="nav-dropdown-items">
                    <li class="nav-item <c:if test="${param.subMenu == 'list'}">open</c:if>">
                        <a class="nav-link <c:if test="${param.subMenu == 'list'}">active</c:if>" href="/admin/settings/wardrobe">
                            <i class="icon-arrow-right"></i> 试衣间列表
                        </a>
                    </li>
                    <li class="nav-item <c:if test="${param.subMenu == 'orders'}">open</c:if>">
                        <a class="nav-link <c:if test="${param.subMenu == 'orders'}">active</c:if>" href="/admin/settings/wardrobe">
                            <i class="icon-arrow-right"></i> 预约面板
                        </a>
                    </li>
                    <li class="nav-item <c:if test="${param.subMenu == 'orders'}">open</c:if>">
                        <a class="nav-link <c:if test="${param.subMenu == 'orders'}">active</c:if>" href="/admin/settings/wardrobe">
                            <i class="icon-arrow-right"></i> 预约订单
                        </a>
                    </li>
                    <li class="nav-item <c:if test="${param.subMenu == 'orders'}">open</c:if>">
                        <a class="nav-link <c:if test="${param.subMenu == 'orders'}">active</c:if>" href="/admin/settings/wardrobe">
                            <i class="icon-arrow-right"></i> 预约订单详情
                        </a>
                    </li>
                    <li class="nav-item <c:if test="${param.subMenu == 'log'}">open</c:if>">
                        <a class="nav-link <c:if test="${param.subMenu == 'log'}">active</c:if>" href="/admin/settings/log">
                            <i class="icon-arrow-right"></i> 试衣间设置
                        </a>
                    </li>
                </ul>
            </li>

            <li class="nav-item nav-dropdown <c:if test="${param.menu == 'data'}">open</c:if>">
                <a class="nav-link nav-dropdown-toggle" href="javascript:;">
                    <i class="icon-chart"></i> 数据统计
                </a>
                <ul class="nav-dropdown-items">
                    <li class="nav-item <c:if test="${param.subMenu == 'income'}">open</c:if>">
                        <a class="nav-link <c:if test="${param.subMenu == 'income'}">active</c:if>" href="/admin/data/income">
                            <i class="icon-arrow-right"></i> 收入流水
                        </a>
                    </li>
                    <li class="nav-item <c:if test="${param.subMenu == 'expend'}">open</c:if>">
                        <a class="nav-link <c:if test="${param.subMenu == 'expend'}">active</c:if>" href="/admin/data/expend">
                            <i class="icon-arrow-right"></i> 支出流水
                        </a>
                    </li>
                    <li class="nav-item <c:if test="${param.subMenu == 'summary'}">open</c:if>">
                        <a class="nav-link <c:if test="${param.subMenu == 'summary'}">active</c:if>" href="/admin/data/summary">
                            <i class="icon-arrow-right"></i> 收支统计
                        </a>
                    </li>
                    <li class="nav-item <c:if test="${param.subMenu == 'setting'}">open</c:if>" style="display: none">
                        <a class="nav-link <c:if test="${param.subMenu == 'setting'}">active</c:if>" href="/admin/data/settings">
                            <i class="icon-arrow-right"></i> 收支类型设置
                        </a>
                    </li>
                </ul>
            </li>

            <li class="nav-item nav-dropdown <c:if test="${param.menu == 'settings'}">open</c:if>">
                <a class="nav-link nav-dropdown-toggle" href="javascript:;">
                    <i class="icon-home"></i> 系统设置
                </a>
                <ul class="nav-dropdown-items">
                    <li class="nav-item <c:if test="${param.subMenu == 'wardrobe'}">open</c:if>">
                        <a class="nav-link <c:if test="${param.subMenu == 'wardrobe'}">active</c:if>" href="/admin/settings/wardrobe">
                            <i class="icon-arrow-right"></i> 充值金额设置
                        </a>
                    </li>
                    <li class="nav-item <c:if test="${param.subMenu == 'admin'}">open</c:if>">
                        <a class="nav-link <c:if test="${param.subMenu == 'admin'}">active</c:if>" href="/admin/settings/admin">
                            <i class="icon-arrow-right"></i> 管理员管理
                        </a>
                    </li>
                    <li class="nav-item <c:if test="${param.subMenu == 'database'}">open</c:if>">
                        <a class="nav-link <c:if test="${param.subMenu == 'database'}">active</c:if>" href="/admin/settings/database">
                            <i class="icon-arrow-right"></i> 数据库备份
                        </a>
                    </li>
                    <li class="nav-item <c:if test="${param.subMenu == 'log'}">open</c:if>">
                        <a class="nav-link <c:if test="${param.subMenu == 'log'}">active</c:if>" href="/admin/settings/log">
                            <i class="icon-arrow-right"></i> 系统日志
                        </a>
                    </li>
                </ul>
            </li>
        </ul>
    </nav>
</div>
