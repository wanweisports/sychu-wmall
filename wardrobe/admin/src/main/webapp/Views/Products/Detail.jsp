<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ page import="com.wardrobe.layout.Blocks" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> <%-- JSTL表达式（判断，循环，输出） --%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %> <%-- 方法表达式（字符串截取，替换） --%>
<%@ taglib uri="http://www.sychu.com/tags/tag" prefix="layout" %>

<layout:override name="<%=Blocks.BLOCK_HEADER_CSS%>">
    <style type="text/css">
        .size-list th {
            padding: 0.75rem;
        }
        .size-list td {
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
                            <strong>商品详情</strong>
                            <small>Products Information</small>
                        </div>
                        <div class="card-block">
                            <table class="table table-responsive-sm table-bordered member-info">
                                <tbody>
                                <tr>
                                    <th>商品名称：</th>
                                    <td>${product.commName}</td>
                                    <th>商品标签：</th>
                                    <td><span class="badge badge-danger">热门</span><span class="badge badge-danger">人气</span></td>
                                    <th>销售数量：</th>
                                    <td>
                                        <a href="/admin/products/sku/list?cid=${product.cid}">${product.saleCount}</a>
                                    </td>
                                </tr>
                                <tr>
                                    <th>商品品类：</th>
                                    <td>${product.category}</td>
                                    <th>商品风格：</th>
                                    <td>${product.style}</td>
                                    <th>商品材质：</th>
                                    <td>${product.material}</td>
                                </tr>
                                <tr>
                                    <th>商品原价：</th>
                                    <td>${product.price}</td>
                                    <th>商品优惠价：</th>
                                    <td>${product.couPrice}</td>
                                    <th>其他颜色：</th>
                                    <td>
                                        <a href="/admin/products/list?groupId=${product.groupId}">黄色</a>
                                    </td>
                                </tr>
                                <tr>
                                    <th>商品描述：</th>
                                    <td colspan="5">${product.productDesc}</td>
                                </tr>
                                <tr>
                                    <th>商品图片：</th>
                                    <td colspan="5">
                                        <img src="/Content/images/upload.png" style="width: 100px; height: 100px;" alt="封面图">
                                        <img src="/Content/images/upload.png" style="width: 100px; height: 100px;" alt="轮播图1">
                                        <img src="/Content/images/upload.png" style="width: 100px; height: 100px;" alt="轮播图2">
                                        <img src="/Content/images/upload.png" style="width: 100px; height: 100px;" alt="轮播图3">
                                        <img src="/Content/images/upload.png" style="width: 100px; height: 100px;" alt="轮播图4">
                                    </td>
                                </tr>
                                </tbody>
                            </table>
                        </div>
                        <div class="card-footer">
                            <a href="/admin/products/edit?cid=${product.cid}" class="btn btn-primary">
                                <i class="fa fa-pencil"></i> 编辑商品
                            </a>
                            <a href="/admin/products/add?groupId=${product.groupId}" class="btn btn-primary">
                                <i class="fa fa-plus"></i> 添加同类商品
                            </a>
                        </div>
                    </div>
                    <div class="card">
                        <div class="card-header">
                            <strong>尺码列表</strong>
                            <small>Size List</small>
                        </div>
                        <div class="card-block">
                            <table class="table table-responsive-sm table-bordered size-list">
                                <thead>
                                <tr>
                                    <th>##</th>
                                    <th>尺码大小</th>
                                    <th>初始库存</th>
                                    <th>操作</th>
                                </tr>
                                </thead>
                                <tbody>
                                <tr data-id="">
                                    <td>1</td>
                                    <td>XL</td>
                                    <td>1000</td>
                                    <td>
                                        <a href="javascript:;" class="btn btn-sm btn-danger">
                                            <i class="fa fa-remove"></i> 删除
                                        </a>
                                    </td>
                                </tbody>
                            </table>
                        </div>
                        <div class="card-footer">
                            <a href="javascript:;" class="btn btn-primary">
                                <i class="fa fa-plus"></i> 增加尺码
                            </a>
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
    <c:param name="subMenu" value="detail"/>
</c:import>
