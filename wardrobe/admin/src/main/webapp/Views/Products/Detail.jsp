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
    <script type="text/javascript" src="Content/lib/jquery.min.js"></script>
    <script type="text/javascript">
        function deleteSize(obj){
            if(window.confirm('删除后不可恢复，确认删除吗？')) {
                var $obj = $(obj);
                var $tr = $obj.parent().parent();
                var sid = $tr.data('id');
                $.post("/commodity/delSize", {sid: sid}, function (res) {
                    if (res.code == 1) {
                        window.location.reload();
                    } else {
                        alert(res.message);
                    }
                });
            }
        }

        function showEdit(obj){
            var $obj = $(obj);
            $obj.hide().next().show();
            var $tr = $obj.parent().parent();
            var $td_size = $tr.find("td").eq(1);
            $td_size.html($('#sizeSed').clone().show().val($td_size.html()));

            var $td_stock = $tr.find("td").eq(2);
            $td_stock.html("<input class='form-control' type='text' name='size' value='"+$td_stock.html()+"' />");
        }

        function editSize(obj){
            var $obj = $(obj);
            var $tr = $obj.parent().parent();
            var $td_size = $tr.find("td").eq(1).find("select");
            var $td_stock = $tr.find("td").eq(2).find("input");

            var sid = $tr.data('id');
            $.post("/commodity/updateSize", {size: $td_size.val(), stock: $td_stock.val(), cid: ${product.cid}, sid: sid}, function (res) {
                if(res.code == 1){
                    window.location.reload();
                }else{
                    alert(res.message);
                }
            });
        }

        function addSizeTpl(){
            var index = $("#tbody").find("tr").length+1;
            $("#tbody").append('<tr><td>'+index+'</td><td></td><td></td><td><a href="javascript:;" class="btn btn-sm btn btn-primary" onclick="showEdit(this)"><i class="fa fa-edit"></i> 编辑</a><a href="javascript:;" class="btn btn-sm btn btn-primary" style="display: none" onclick="editSize(this)"><i class="fa fa-check"></i> 保存</a><a href="javascript:;" class="btn btn-sm btn-danger" onclick="deleteSize(this)"><i class="fa fa-remove"></i> 删除</a></td></tr>');
        }
    </script>
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
                                    <td><c:if test="${product.hot=='1'}"><span class="badge badge-danger">热门</span></c:if><c:if test="${product.newly=='1'}"><span class="badge badge-danger">最新</span></c:if></td>
                                    <th>销售数量：</th>
                                    <td>
                                        <a href="/admin/products/sku/list?cid=${product.cid}">${product.saleCount}</a>
                                    </td>
                                </tr>
                                <tr>
                                    <th>商品品类：</th>
                                    <td>${product.categoryName}</td>
                                    <th>商品风格：</th>
                                    <td>${product.styleName}</td>
                                    <th>商品材质：</th>
                                    <td>${product.materialName}</td>
                                </tr>
                                <tr>
                                    <th>商品原价：</th>
                                    <td>${product.price}</td>
                                    <th>商品优惠价：</th>
                                    <td>${product.couPrice}</td>
                                    <th>其他颜色：</th>
                                    <td>
                                        <a href="/admin/products/list?groupId=${product.groupId}">
                                            <c:forEach var="gs" items="${groupCommodityColorList}" varStatus="status">
                                                <c:if test="${status.index gt 0}">、</c:if>${gs.colorName}
                                            </c:forEach>
                                        </a>
                                    </td>
                                </tr>
                                <tr>
                                    <th>商品描述：</th>
                                    <td colspan="5">${product.productDesc}</td>
                                </tr>
                                <tr>
                                    <th>商品图片：</th>
                                    <td colspan="5">
                                        <img src="${coverImg.resourcePath}" style="width: 100px; height: 100px;" alt="封面图">
                                        <c:forEach var="b" items="${broadImgList}" varStatus="status">
                                            <img src="${b.resourcePath}" style="width: 100px; height: 100px;" alt="轮播图${status.index+1}">
                                        </c:forEach>
                                    </td>
                                </tr>
                                </tbody>
                            </table>
                        </div>
                        <div class="card-footer">
                            <a href="/admin/products/edit?cid=${product.cid}" class="btn btn-primary">
                                <i class="fa fa-pencil"></i> 编辑商品
                            </a>
                            <c:if test="${product.groupId != null}">
                                <a href="/admin/products/add?groupId=${product.groupId}" class="btn btn-primary">
                            </c:if>
                            <c:if test="${product.groupId == null}">
                                <a href="/admin/products/add?groupId=${product.cid}" class="btn btn-primary">
                            </c:if>
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
                                <tbody id="tbody">
                                    <c:forEach var="s" items="${productSizeList}" varStatus="status">
                                    <tr data-id="${s.sid}" <c:if test="${status.index==0}">id="size_tpl"</c:if>>
                                        <td>${status.index+1}</td>
                                        <td>${s.size}</td>
                                        <td>${s.stock}</td>
                                        <td>
                                            <a href="javascript:;" class="btn btn-sm btn btn-primary" onclick="showEdit(this)">
                                                <i class="fa fa-edit"></i> 编辑
                                            </a>
                                            <a href="javascript:;" class="btn btn-sm btn btn-primary" style="display: none" onclick="editSize(this)">
                                                <i class="fa fa-check"></i> 保存
                                            </a>
                                            <a href="javascript:;" class="btn btn-sm btn-danger" onclick="deleteSize(this)">
                                                <i class="fa fa-remove"></i> 删除
                                            </a>
                                        </td>
                                    </tr>
                                    </c:forEach>
                                </tbody>
                            </table>
                        </div>
                        <div class="card-footer">
                            <a href="javascript:;" class="btn btn-primary" onclick="addSizeTpl()">
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
    <select class="form-control" name="size" style="display: none" id="sizeSed">
        <c:forEach var="s" items="${sizeList}">
            <option value="${s.dictValue}">${s.dictValue}</option>
        </c:forEach>
    </select>
</layout:override>

<c:import url="../Shared/GeneralLayout.jsp">
    <c:param name="menu" value="products"/>
    <c:param name="subMenu" value="detail"/>
</c:import>
