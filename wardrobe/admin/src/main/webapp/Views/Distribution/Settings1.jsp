<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ page import="com.wardrobe.layout.Blocks" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> <%-- JSTL表达式（判断，循环，输出） --%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %> <%-- 方法表达式（字符串截取，替换） --%>
<%@ taglib uri="http://www.sychu.com/tags/tag" prefix="layout" %>

<layout:override name="<%=Blocks.BLOCK_HEADER_CSS%>">
    <style type="text/css">
        .distribution-list th {
            padding: 0.75rem;
        }
        .distribution-list td {
            padding: 0.3rem 0.75rem;
        }
    </style>
</layout:override>

<layout:override name="<%=Blocks.BLOCK_HEADER_SCRIPTS%>">
    <script type="text/javascript" src="Content/js/require.js?v=${static_resource_version}"
            data-main="Content/js/app/distribution/settings.js?v=${static_resource_version}"></script>
</layout:override>

<layout:override name="<%=Blocks.BLOCK_BODY%>">
    <div class="modal fade" id="distribution_enter" tabindex="-1" role="dialog">
        <div class="modal-dialog modal-default" role="document">
            <div class="modal-content">
                <div class="modal-body">
                    <form id="distribution_form" method="post" class="form-horizontal" novalidate onsubmit="return false;">
                        <input type="hidden" class="form-control" id="distribution_cid" name="cid">
                        <div class="form-group row">
                            <label class="col-md-3 form-control-label" for="distribution_product">
                                <span class="text-danger">*</span> 选择商品
                            </label>
                            <div class="col-md-6">
                                <input type="text" class="form-control" id="distribution_product" placeholder="输入商品名称" name="commName"
                                       data-val="true" data-val-required="请输入商品名称" autocomplete="off" readonly>
                                <div data-valmsg-for="commName" data-valmsg-replace="true"></div>
                            </div>
                            <div class="col-md-2">
                                <button type="button" class="btn btn-sm btn-primary" data-toggle="modal" data-target="#distribution_query_list">
                                    <i class="fa fa-search"></i> 检 索
                                </button>
                            </div>
                        </div>
                        <div class="form-group row">
                            <label class="col-md-3 form-control-label" for="distribution_size">
                                <span class="text-danger">*</span> 选择尺码
                            </label>
                            <div class="col-md-9">
                                <select class="form-control" id="distribution_size" name="size" data-val="true" data-val-required="请选择尺码">
                                    <option value="">选择尺码</option>
                                </select>
                                <div data-valmsg-for="size" data-valmsg-replace="true"></div>
                            </div>
                        </div>
                        <div class="form-group row">
                            <label class="col-md-3 form-control-label" for="distribution_code">
                                <span class="text-danger">*</span> 射频编码
                            </label>
                            <div class="col-md-9">
                                <input type="text" class="form-control" id="distribution_code" placeholder="射频编码" name="code"
                                       data-val="true" data-val-required="射频编码不能为空" autocomplete="off">
                                <div data-valmsg-for="code" data-valmsg-replace="true"></div>
                            </div>
                        </div>
                    </form>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-sm btn-secondary" data-dismiss="modal">
                        <i class="fa fa-close"></i> 取 消
                    </button>
                    <button type="button" class="btn btn-sm btn-primary save-wardrobe">
                        <i class="fa fa-check"></i> 保 存
                    </button>
                </div>
            </div>
        </div>
    </div>

    <div class="modal fade" id="distribution_query_list" tabindex="-1" role="dialog">
        <div class="modal-dialog modal-default modal-lg" role="document" style="max-width: 1024px !important;">
            <div class="modal-content">
                <div class="modal-body">
                    <iframe src="/admin/distribution/products" style="width: 100%; height: 500px" id="distribution_iframe"></iframe>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-sm btn-secondary" data-dismiss="modal">
                        <i class="fa fa-close"></i> 取 消
                    </button>
                </div>
            </div>
        </div>
    </div>

    <div class="container-fluid">
        <div class="animated fadeIn">
            <div class="row">
                <div class="col-md-12">
                    <div class="card">
                        <div class="card-header">
                            <strong>配送设置</strong>
                            <small>Distribution Settings</small>
                        </div>
                        <div class="card-block">
                            <form id="distribution_query_form" method="post" class="form-horizontal" action="/admin/distribution/settings" novalidate onsubmit="return false;">
                                <div class="form-group row">
                                    <div class="col-md-2">
                                        <input type="text" class="form-control" placeholder="配送日期" value="2018-10-11">
                                    </div>
                                    <div class="col-md-3">
                                        <select class="form-control" name="">
                                            <option>新中关购物中心</option>
                                        </select>
                                    </div>
                                    <div class="col-md-7">
                                        <button type="submit" class="btn btn-primary distribution-query-btn">
                                            <i class="fa fa-search"></i> 检 索
                                        </button>
                                    </div>
                                </div>
                            </form>
                        </div>
                        <div class="card-footer text-right"></div>
                        <div class="card-block row">
                            <div class="col-sm-6 col-md-4">
                                <div class="card">
                                    <div class="card-header">
                                        柜子A
                                        <span class="badge badge-success float-right">已放入2件</span>
                                    </div>
                                    <div class="card-body">
                                        <table class="table table-striped table-sm distribution-list">
                                            <thead>
                                            <tr>
                                                <th>衣服</th>
                                                <th>尺码</th>
                                                <th>数量</th>
                                                <th></th>
                                            </tr>
                                            </thead>
                                            <tbody>
                                            <tr data-id="">
                                                <td>女装女装女装女装女装</td>
                                                <td>XL</td>
                                                <td>1件</td>
                                                <td>
                                                    <a href="#" class="btn btn-sm btn-danger">
                                                        <i class="fa fa-remove"></i> 移 除
                                                    </a>
                                                </td>
                                            </tr>
                                            <tr data-id="">
                                                <td>女装女装女装女装女装</td>
                                                <td>XL</td>
                                                <td>1件</td>
                                                <td>
                                                    <a href="#" class="btn btn-sm btn-danger">
                                                        <i class="fa fa-remove"></i> 移 除
                                                    </a>
                                                </td>
                                            </tr>
                                            </tbody>
                                        </table>
                                    </div>
                                    <div class="card-footer">
                                        <button type="button" class="btn btn-sm btn-primary" data-target="#distribution_enter" data-toggle="modal">
                                            <i class="fa fa-plus"></i> 放入衣服
                                        </button>
                                    </div>
                                </div>
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
    <c:param name="menu" value="distribution"/>
    <c:param name="subMenu" value="settings"/>
</c:import>
