<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ page import="com.wardrobe.layout.Blocks" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> <%-- JSTL表达式（判断，循环，输出） --%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %> <%-- 方法表达式（字符串截取，替换） --%>
<%@ taglib uri="http://www.sychu.com/tags/tag" prefix="layout" %>

<layout:override name="<%=Blocks.BLOCK_HEADER_CSS%>">
    <style type="text/css">
        .user-list th {
            padding: 0.75rem;
        }
        .user-list td {
            padding: 0.3rem 0.75rem;
        }
    </style>
</layout:override>

<layout:override name="<%=Blocks.BLOCK_HEADER_SCRIPTS%>">
    <script type="text/javascript" src="Content/js/require.js?v=${static_resource_version}"
            data-main="Content/js/app/products/list.js?v=${static_resource_version}"></script>
</layout:override>

<layout:override name="<%=Blocks.BLOCK_BODY%>">
    <div class="container-fluid">
        <div class="animated fadeIn">
            <div class="row">
                <div class="col-md-12">
                    <div class="card">
                        <div class="card-header">
                            <ol class="breadcrumb" style="background: inherit;padding: 0;margin: 0;">
                                <li class="breadcrumb-item">
                                    <strong>商品详情</strong>
                                    <small>Products Info</small>
                                </li>
                                <li class="breadcrumb-item">
                                    <a href="/admin/products/hot/list?type=users">
                                        <strong>人气商品</strong>
                                        <small>Users Products</small>
                                    </a>
                                </li>
                            </ol>
                        </div>
                        <div class="card-block">
                            <form id="members_query_form" method="post" class="form-horizontal" novalidate onsubmit="return false;">
                                <div class="form-group row">
                                    <div class="col-md-3">
                                        <input type="text" name="" class="form-control" placeholder="会员编号">
                                    </div>
                                    <div class="col-md-3">
                                        <input type="text" name="" class="form-control" placeholder="会员手机号">
                                    </div>
                                    <div class="col-md-6">
                                        <button type="button" class="btn btn-primary members-query-btn">
                                            <i class="fa fa-search"></i> 检 索
                                        </button>
                                        <a href="/admin/members/add" class="btn btn-primary pull-right" title="会员添加">
                                            <i class="fa fa-user-plus"></i> 会员添加
                                        </a>
                                    </div>
                                </div>
                            </form>
                        </div>
                        <div class="card-footer text-right"></div>
                        <div class="card-block">
                            <table class="table table-striped table-sm user-list">
                                <thead>
                                <tr>
                                    <th>##</th>
                                    <th>会员编号</th>
                                    <th>手机号码</th>
                                    <th>性别</th>
                                    <th>年龄</th>
                                    <th>风格偏好</th>
                                    <th>账户余额</th>
                                    <th>衣橱币</th>
                                    <th>积分</th>
                                    <th>邀请人</th>
                                    <th></th>
                                </tr>
                                </thead>
                                <tbody>
                                <tr data-id="">
                                    <td>1</td>
                                    <td>
                                        <a href="/admin/members/detail?memberId=" class="btn btn-sm btn-link" title="会员详情">
                                            <i class="fa fa-user"></i> U20180724142512
                                        </a>
                                    </td>
                                    <td>158****3167</td>
                                    <td>帅哥</td>
                                    <td>85后</td>
                                    <td>职场、约会、休闲、度假</td>
                                    <td>￥1000.00</td>
                                    <td>9999</td>
                                    <td>65431</td>
                                    <td>U20180724142512</td>
                                    <td>
                                        <a href="/admin/members/edit?memberId=" class="btn btn-sm btn-primary user-refresh" title="重新编辑">
                                            <i class="fa fa-pencil"></i> 修改
                                        </a>
                                    </td>
                                </tr>
                                <tr data-id="">
                                    <td>2</td>
                                    <td>
                                        <a href="/admin/members/detail?memberId=" class="btn btn-sm btn-link" title="会员详情">
                                            <i class="fa fa-user"></i> U20180612101155
                                        </a>
                                    </td>
                                    <td>158****3167</td>
                                    <td>美女</td>
                                    <td>90后</td>
                                    <td>职场、约会、休闲、度假</td>
                                    <td>￥1000.00</td>
                                    <td>9999</td>
                                    <td>65431</td>
                                    <td>U20180724142512</td>
                                    <td>
                                        <a href="/admin/members/edit?memberId=" class="btn btn-sm btn-primary user-refresh" title="重新编辑">
                                            <i class="fa fa-pencil"></i> 修改
                                        </a>
                                    </td>
                                </tr>
                                </tbody>
                            </table>
                            <div>
                                <%@ include file="../Shared/Pagination.jsp" %>
                            </div>
                        </div>
                    </div>
                </div>
                <!--/.col-->
            </div>
            <!--/.row-->
        </div>

    </div>
</layout:override>

<c:import url="../Shared/GeneralLayout.jsp">
    <c:param name="menu" value="products"/>
    <c:param name="subMenu" value="list"/>
</c:import>
