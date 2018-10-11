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

    <script type="text/javascript">
        function showCommoditys(dcid){
            $.post("/admin/distribution/lockCommoditys", {dcid: dcid}, function (res) {
                var list = res.list;
                var commoditys = '';
                $.each(list, function (index, item) {
                    commoditys += '<tr data-id=""><td>'+item.name+'</td><td>'+item.size+'</td><td>'+item.count+'件</td><td><a href="#" class="btn btn-danger btn-sm"><i class="fa fa-remove"></i> 移除 </a></td></tr>';
                });
                $("#commoditys").html(commoditys);
            });
        }
    </script>
</layout:override>

<layout:override name="<%=Blocks.BLOCK_BODY%>">
    <div class="modal fade" id="distribution_enter" tabindex="-1" role="dialog">
        <div class="modal-dialog modal-default" role="document">
            <div class="modal-content">
                <div class="modal-body">
                    <form id="distribution_form" method="post" class="form-horizontal" novalidate onsubmit="return false;">
                        <div class="form-group row">
                            <label class="col-md-3 form-control-label" for="wardrobe_address">
                                <span class="text-danger">*</span> 选择衣服
                            </label>
                            <div class="col-md-9">
                                <input type="text" class="form-control" id="wardrobe_address" placeholder="请输入所在地址" name="address"
                                       data-val="true" data-val-required="所在地址不能为空" autocomplete="off"
                                       data-val-length-max="30" data-val-length-min="2" data-val-length="所在地址必须包含 2~30 个字符">
                                <div data-valmsg-for="address" data-valmsg-replace="true"></div>
                            </div>
                        </div>
                        <div class="form-group row">
                            <label class="col-md-3 form-control-label" for="wardrobe_doorIp">
                                <span class="text-danger">*</span> 射频编码
                            </label>
                            <div class="col-md-9">
                                <input type="text" class="form-control" id="wardrobe_doorIp" placeholder="大门IP地址" name="doorIp"
                                       data-val="true" data-val-required="大门IP地址不能为空" autocomplete="off">
                                <div data-valmsg-for="doorIp" data-valmsg-replace="true"></div>
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

    <div class="modal fade" id="distribution_list" tabindex="-1" role="dialog">
        <div class="modal-dialog modal-default" role="document">
            <div class="modal-content">
                <div class="modal-body">
                    <table class="table table-striped table-sm distribution-list">
                        <thead>
                        <tr>
                            <th>衣服</th>
                            <th>尺码</th>
                            <th>数量</th>
                            <th></th>
                        </tr>
                        </thead>
                        <tbody id="commoditys">
                            <%--<tr data-id=""><td>女装女装女装女装女装</td><td>XL</td><td>1件</td><td><a href="#" class="btn btn-danger btn-sm"><i class="fa fa-remove"></i> 移除 </a></td></tr>--%>
                        </tbody>
                    </table>
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
                            <form id="distribution_query_form" method="post" class="form-horizontal" action="/admin/distribution/settings" novalidate <%--onsubmit="return false;"--%>>
                                <div class="form-group row">
                                    <%--<div class="col-md-2">
                                        <input type="text" class="form-control" placeholder="配送日期" value="2018-10-11">
                                    </div>--%>
                                    <div class="col-md-3">
                                        <select class="form-control" name="did">
                                            <option value="">全部</option>
                                            <c:forEach var="device" items="${deviceList}">
                                                <option value="${device.did}" <c:if test="${device.did == did}">selected</c:if>>${device.name}</option>
                                            </c:forEach>
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
                        <div class="card-block">
                            <table class="table table-striped table-sm distribution-list">
                                <thead>
                                <tr>
                                    <th>衣橱名称</th>
                                    <th>柜子编号</th>
                                    <th>衣服数量</th>
                                    <th></th>
                                </tr>
                                </thead>
                                <tbody>
                                    <c:forEach var="deviceControl" items="${deviceControlList}">
                                        <tr data-id="">
                                            <td>${deviceControl.deviceName}</td>
                                            <td>${deviceControl.name}</td>
                                            <td>
                                                <a href="#distribution_list" class="btn btn-link" data-toggle="modal" onclick="showCommoditys('${deviceControl.dcid}')">${deviceControl.cdCount}件</a>
                                            </td>
                                            <td>
                                                <a href="#distribution_enter" class="btn btn-primary btn-sm distribution-enter" data-toggle="modal">
                                                    <i class="fa fa-crosshairs"></i> 放入
                                                </a>
                                            </td>
                                        </tr>
                                    </c:forEach>
                                </tbody>
                            </table>
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
