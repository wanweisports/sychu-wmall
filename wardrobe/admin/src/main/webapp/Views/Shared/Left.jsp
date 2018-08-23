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
                    <i class="icon-home"></i> 商城首页
                </a>
            </li>

            <li class="nav-item nav-dropdown <c:if test="${param.menu == 'products'}">open</c:if>">
                <a class="nav-link nav-dropdown-toggle" href="javascript:;">
                    <i class="icon-basket"></i> 商品管理
                </a>
                <ul class="nav-dropdown-items">
                    <li class="nav-item <c:if test="${param.menu == 'products' && (param.subMenu == 'list' || param.subMenu == 'detail' || param.subMenu == 'edit')}">open</c:if>">
                        <a class="nav-link <c:if test="${param.menu == 'products' && (param.subMenu == 'list' || param.subMenu == 'detail' || param.subMenu == 'edit')}">active</c:if>" href="/admin/products/list">
                            <i class="icon-arrow-right"></i> 商品列表
                        </a>
                    </li>
                    <li class="nav-item <c:if test="${param.menu == 'products' && (param.subMenu == 'hot')}">open</c:if>">
                        <a class="nav-link <c:if test="${param.menu == 'products' && (param.subMenu == 'hot')}">active</c:if>" href="/admin/products/hot/list?hot=1">
                            <i class="icon-arrow-right"></i> 人气/热门商品
                        </a>
                    </li>
                    <li class="nav-item <c:if test="${param.menu == 'products' && (param.subMenu == 'add')}">open</c:if>">
                        <a class="nav-link <c:if test="${param.menu == 'products' && (param.subMenu == 'add')}">active</c:if>" href="/admin/products/add">
                            <i class="icon-arrow-right"></i> 商品添加
                        </a>
                    </li>
                    <li class="nav-item <c:if test="${param.menu == 'products' && (param.subMenu == 'sku')}">open</c:if>">
                        <a class="nav-link <c:if test="${param.menu == 'products' && (param.subMenu == 'sku')}">active</c:if>" href="/admin/products/sku/list">
                            <i class="icon-arrow-right"></i> 库存变更记录
                        </a>
                    </li>
                    <li class="nav-item <c:if test="${param.menu == 'products' && (param.subMenu == 'settings')}">open</c:if>">
                        <a class="nav-link <c:if test="${param.menu == 'products' && (param.subMenu == 'settings')}">active</c:if>" href="/admin/products/settings">
                            <i class="icon-arrow-right"></i> 商品标签设置
                        </a>
                    </li>
                </ul>
            </li>

            <li class="nav-item nav-dropdown <c:if test="${param.menu == 'wardrobe'}">open</c:if>">
                <a class="nav-link nav-dropdown-toggle" href="javascript:;">
                    <i class="icon-directions"></i> 试衣间管理
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
                    <li class="nav-item <c:if test="${param.subMenu == 'log'}">open</c:if>">
                        <a class="nav-link <c:if test="${param.subMenu == 'log'}">active</c:if>" href="/admin/settings/log">
                            <i class="icon-arrow-right"></i> 接口设置
                        </a>
                    </li>
                </ul>
            </li>

            <li class="nav-item nav-dropdown <c:if test="${param.menu == 'orders'}">open</c:if>">
                <a class="nav-link nav-dropdown-toggle" href="javascript:;">
                    <i class="icon-list"></i> 订单管理
                </a>
                <ul class="nav-dropdown-items">
                    <li class="nav-item <c:if test="${param.subMenu == 'list' || param.subMenu == 'detail'}">open</c:if>">
                        <a class="nav-link <c:if test="${param.subMenu == 'list' || param.subMenu == 'detail'}">active</c:if>" href="/admin/order/list">
                            <i class="icon-arrow-right"></i> 商品订单
                        </a>
                    </li>
                    <li class="nav-item <c:if test="${param.subMenu == 'list' || param.subMenu == 'detail'}">open</c:if>">
                        <a class="nav-link <c:if test="${param.subMenu == 'list' || param.subMenu == 'detail'}">active</c:if>" href="/admin/order/list">
                            <i class="icon-arrow-right"></i> 配送订单
                        </a>
                    </li>
                    <li class="nav-item <c:if test="${param.subMenu == 'refund'}">open</c:if>">
                        <a class="nav-link <c:if test="${param.subMenu == 'refund'}">active</c:if>" href="/admin/order/list">
                            <i class="icon-arrow-right"></i> 退款订单
                        </a>
                    </li>
                </ul>
            </li>

            <li class="nav-item nav-dropdown <c:if test="${param.menu == 'members'}">open</c:if>">
                <a class="nav-link nav-dropdown-toggle" href="javascript:;">
                    <i class="icon-user"></i> 客户管理
                </a>
                <ul class="nav-dropdown-items">
                    <li class="nav-item <c:if test="${param.menu == 'members' && (param.subMenu == 'list' || param.subMenu == 'detail')}">open</c:if>">
                        <a class="nav-link <c:if test="${param.menu == 'members' && (param.subMenu == 'list' || param.subMenu == 'detail')}">active</c:if>" href="/admin/members/list">
                            <i class="icon-arrow-right"></i> 会员列表
                        </a>
                    </li>
                    <li class="nav-item <c:if test="${param.menu == 'members' && (param.subMenu == 'balance')}">open</c:if>">
                        <a class="nav-link <c:if test="${param.menu == 'members' && (param.subMenu == 'balance')}">active</c:if>" href="/admin/members/recharge/settings">
                            <i class="icon-arrow-right"></i> 充值金额设置
                        </a>
                    </li>
                    <li class="nav-item <c:if test="${param.menu == 'members' && (param.subMenu == 'transactions')}">open</c:if>">
                        <a class="nav-link <c:if test="${param.menu == 'members' && (param.subMenu == 'transactions')}">active</c:if>" href="/admin/members/transactions/log">
                            <i class="icon-arrow-right"></i> 交易记录
                        </a>
                    </li>
                </ul>
            </li>

            <li class="nav-item nav-dropdown <c:if test="${param.menu == 'active'}">open</c:if>">
                <a class="nav-link nav-dropdown-toggle" href="javascript:;">
                    <i class="icon-basket"></i> 销售管理
                </a>
                <ul class="nav-dropdown-items">
                    <li class="nav-item <c:if test="${param.subMenu == 'active'}">open</c:if>">
                        <a class="nav-link <c:if test="${param.subMenu == 'active'}">active</c:if>" href="/admin/products/active/settings">
                            <i class="icon-arrow-right"></i> 促销管理
                        </a>
                    </li>
                    <li class="nav-item <c:if test="${param.subMenu == 'coupon'}">open</c:if>">
                        <a class="nav-link <c:if test="${param.subMenu == 'coupon'}">active</c:if>" href="/admin/products/list">
                            <i class="icon-arrow-right"></i> 礼券设置
                        </a>
                    </li>
                </ul>
            </li>

            <li class="nav-item nav-dropdown <c:if test="${param.menu == 'data'}">open</c:if>">
                <a class="nav-link nav-dropdown-toggle" href="javascript:;">
                    <i class="icon-chart"></i> 财务统计
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
                    <i class="icon-settings"></i> 系统设置
                </a>
                <ul class="nav-dropdown-items">
                    <li class="nav-item <c:if test="${param.subMenu == 'admin'}">open</c:if>">
                        <a class="nav-link <c:if test="${param.subMenu == 'admin'}">active</c:if>" href="/admin/settings/admin">
                            <i class="icon-arrow-right"></i> 权限设置
                        </a>
                    </li>
                    <li class="nav-item <c:if test="${param.subMenu == 'admin'}">open</c:if>">
                        <a class="nav-link <c:if test="${param.subMenu == 'admin'}">active</c:if>" href="/admin/settings/admin">
                            <i class="icon-arrow-right"></i> 管理员列表
                        </a>
                    </li>
                    <li class="nav-item <c:if test="${param.subMenu == 'admin'}">open</c:if>">
                        <a class="nav-link <c:if test="${param.subMenu == 'admin'}">active</c:if>" href="/admin/settings/admin">
                            <i class="icon-arrow-right"></i> 个人信息
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
