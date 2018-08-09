<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>

<!-- Breadcrumb -->
<ol class="breadcrumb">
    <li class="breadcrumb-item">当前系统</li>

    <c:choose>
        <c:when test="${param.menu == 'dashboard'}">
            <li class="breadcrumb-item active">工作台</li>
        </c:when>

        <c:when test="${param.menu == 'members'}">
            <li class="breadcrumb-item">会员管理</li>
            <c:choose>
                <c:when test="${param.subMenu == 'list'}">
                    <li class="breadcrumb-item active">会员列表</li>
                </c:when>
                <c:when test="${param.subMenu == 'detail'}">
                    <li class="breadcrumb-item"><a href="/admin/members/list">会员列表</a></li>
                    <li class="breadcrumb-item active">会员详情</li>
                </c:when>
                <c:when test="${param.subMenu == 'balance'}">
                    <li class="breadcrumb-item active">充值金额设置</li>
                </c:when>
                <c:when test="${param.subMenu == 'transactions'}">
                    <li class="breadcrumb-item active">交易记录</li>
                </c:when>
            </c:choose>
        </c:when>

        <c:when test="${param.menu == 'products'}">
            <li class="breadcrumb-item">商品管理</li>
            <c:choose>
                <c:when test="${param.subMenu == 'add'}">
                    <li class="breadcrumb-item active">商品添加</li>
                </c:when>
                <c:when test="${param.subMenu == 'list'}">
                    <li class="breadcrumb-item active">商品列表</li>
                </c:when>
            </c:choose>
        </c:when>

        <c:when test="${param.menu == 'students'}">
            <li class="breadcrumb-item">学员管理</li>
            <c:choose>
                <c:when test="${param.subMenu == 'list'}">
                    <li class="breadcrumb-item active">学员列表</li>
                </c:when>
                <c:when test="${param.subMenu == 'edit'}">
                    <li class="breadcrumb-item"><a href="/admin/students/list">学员列表</a></li>
                    <li class="breadcrumb-item active">学员编辑</li>
                </c:when>
                <c:when test="${param.subMenu == 'add'}">
                    <li class="breadcrumb-item active">添加学员</li>
                </c:when>
                <c:when test="${param.subMenu == 'batch'}">
                    <li class="breadcrumb-item active">批量导入学员</li>
                </c:when>
            </c:choose>
        </c:when>

        <c:when test="${param.menu == 'data'}">
            <li class="breadcrumb-item">数据统计</li>
            <c:choose>
                <c:when test="${param.subMenu == 'orders'}">
                    <li class="breadcrumb-item active">我的订单</li>
                </c:when>
                <c:when test="${param.subMenu == 'detail'}">
                    <li class="breadcrumb-item"><a href="/admin/order/list">我的订单</a></li>
                    <li class="breadcrumb-item active">订单详情</li>
                </c:when>
                <c:when test="${param.subMenu == 'finance'}">
                    <li class="breadcrumb-item active">运营财务</li>
                </c:when>
                <c:when test="${param.subMenu == 'finance_log'}">
                    <li class="breadcrumb-item"><a href="/admin/data/operation/finance">运营财务</a></li>
                    <li class="breadcrumb-item active">详情日志</li>
                </c:when>
                <c:when test="${param.subMenu == 'finance_chart'}">
                    <li class="breadcrumb-item"><a href="/admin/data/operation/finance">运营财务</a></li>
                    <li class="breadcrumb-item active">图表展示</li>
                </c:when>
                <c:when test="${param.subMenu == 'finance_settings'}">
                    <li class="breadcrumb-item"><a href="/admin/data/operation/finance">运营财务</a></li>
                    <li class="breadcrumb-item active">参数设置</li>
                </c:when>
                <c:when test="${param.subMenu == 'finance_edit'}">
                    <li class="breadcrumb-item"><a href="/admin/data/operation/finance">运营财务</a></li>
                    <li class="breadcrumb-item"><a href="/admin/data/operation/finance/log">详情日志</a></li>
                    <li class="breadcrumb-item active">详情编辑</li>
                </c:when>
                <c:when test="${param.subMenu == 'income'}">
                    <li class="breadcrumb-item active">收入流水</li>
                </c:when>
                <c:when test="${param.subMenu == 'expend'}">
                    <li class="breadcrumb-item active">支出流水</li>
                </c:when>
                <c:when test="${param.subMenu == 'summary'}">
                    <li class="breadcrumb-item active">收支统计</li>
                </c:when>
                <c:when test="${param.subMenu == 'settings'}">
                    <li class="breadcrumb-item active">收支类型设置</li>
                </c:when>
            </c:choose>
        </c:when>

        <c:when test="${param.menu == 'finance'}">
            <li class="breadcrumb-item">财务报表</li>
            <c:choose>
                <c:when test="${param.subMenu == 'relationship'}">
                    <li class="breadcrumb-item active">组织管理</li>
                </c:when>
                <c:when test="${param.subMenu == 'settings'}">
                    <li class="breadcrumb-item active">指标设置</li>
                </c:when>
                <c:when test="${param.subMenu == 'log'}">
                    <li class="breadcrumb-item active">完成日志</li>
                </c:when>
                <c:when test="${param.subMenu == 'edit'}">
                    <li class="breadcrumb-item"><a href="/admin/finance/log">完成日志</a></li>
                    <li class="breadcrumb-item active">数据录入</li>
                </c:when>
                <c:when test="${param.subMenu == 'performance'}">
                    <li class="breadcrumb-item active">业绩排名</li>
                </c:when>
                <c:when test="${param.subMenu == 'summary'}">
                    <li class="breadcrumb-item active">数据汇总</li>
                </c:when>
            </c:choose>
        </c:when>

        <c:when test="${param.menu == 'settings'}">
            <li class="breadcrumb-item">系统设置</li>
            <c:choose>
                <c:when test="${param.subMenu == 'admin' || param.subMenu == 'profile'}">
                    <li class="breadcrumb-item active">管理员设置</li>
                </c:when>
                <c:when test="${param.subMenu == 'database'}">
                    <li class="breadcrumb-item active">数据库备份</li>
                </c:when>
                <c:when test="${param.subMenu == 'log'}">
                    <li class="breadcrumb-item active">系统日志</li>
                </c:when>
            </c:choose>
        </c:when>
    </c:choose>

    <!-- Breadcrumb Menu-->
    <li class="breadcrumb-menu d-md-down-none">
        <div class="btn-group" role="group" aria-label="Button group with nested dropdown">
            <a class="btn btn-secondary" href="javascript:;" data-target="#contact_online" data-toggle="modal">
                <i class="icon-speech"></i> &nbsp;联系我们
            </a>
            <a class="btn btn-secondary" href="/admin/dashboard/index">
                <i class="icon-home"></i> &nbsp;商城首页
            </a>
            <a class="btn btn-secondary" href="/admin/settings/log">
                <i class="icon-info"></i> &nbsp;系统日志
            </a>
        </div>
    </li>
</ol>

<div class="modal fade" id="contact_online" tabindex="-1" role="dialog">
    <div class="modal-dialog modal-success modal-sm" role="document">
        <div class="modal-content">
            <div class="modal-body">
                <table class="table table-responsive-sm table-bordered">
                    <tr>
                        <th class="text-center"><i class="fa fa-2x fa-mobile"></i></th><td>12345678901</td>
                    </tr>
                    <tr>
                        <th class="text-center"><i class="fa fa-2x fa-phone"></i></th><td>(010)-1234567</td>
                    </tr>
                    <tr>
                        <th class="text-center"><i class="fa fa-2x fa-at"></i></th><td>test1234@test.com</td>
                    </tr>
                    <tr>
                        <th class="text-center"><i class="fa fa-2x fa-location-arrow"></i></th><td>北京市海淀区某某路100号某某大厦1232室</td>
                    </tr>
                </table>
            </div>
        </div>
    </div>
</div>
