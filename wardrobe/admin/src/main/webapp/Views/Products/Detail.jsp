<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ page import="com.wardrobe.layout.Blocks" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> <%-- JSTL表达式（判断，循环，输出） --%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %> <%-- 方法表达式（字符串截取，替换） --%>
<%@ taglib uri="http://www.sychu.com/tags/tag" prefix="layout" %>

<layout:override name="<%=Blocks.BLOCK_HEADER_CSS%>">
    <style type="text/css">
        .product-image-file {
            position: absolute;
            left: 0;
            top: 0;
            width: 100%;
            height: 100%;
            opacity: 0;
        }
        .size-list th {
            padding: 0.75rem;
        }
        .size-list td {
            padding: 0.3rem 0.75rem;
        }
        .product-info th {
            width: 9rem;
        }
    </style>
</layout:override>

<layout:override name="<%=Blocks.BLOCK_HEADER_SCRIPTS%>">
    <script type="text/javascript" src="Content/js/require.js?v=${static_resource_version}"
            data-main="Content/js/app/products/detail.js?v=${static_resource_version}"></script>
    <script type="text/javascript">
        function saveCommodityBanner(){
            $("#js-banner").ajaxSubmit({
                type: "post",
                dataType: "json",
                data: {seqNo: $("#seqNo").val()},
                url: "/commodity/saveCommodityBanner",
                success: function (res) {
                    if(res.code == 1){
                        window.location.reload();
                    }else{
                        alert(res.message);
                    }
                }
            });
        }

        function delCommodityBanner(cid){
            $.post("/commodity/delCommodityBanner", {cid: cid}, function(res){
                if(res.code == 1){
                    window.location.reload();
                }else{
                    alert(res.message);
                }
            });
        }
    </script>
</layout:override>

<layout:override name="<%=Blocks.BLOCK_BODY%>">
    <div class="modal fade" id="banner_settings" tabindex="-1" role="dialog">
        <div class="modal-dialog modal-default" role="document">
            <form class="modal-content" id="js-banner">
                <div class="modal-body">
                    <form id="banner_form" method="post" class="form-horizontal" novalidate onsubmit="return false;">
                        <input type="hidden" name="cid" value="${product.cid}">
                        <div class="form-group row">
                            <label class="col-md-3 form-control-label">
                                <span class="text-danger">*</span> 上传banner
                            </label>
                            <div class="col-md-9 col-form-label">
                                <div style="width: 100%; position: relative">
                                    <img class="product-image-show" src="/Content/images/upload.png">
                                    <input type="file" class="product-image-file" name="file">
                                </div>
                            </div>
                        </div>
                        <div class="form-group row">
                            <label class="col-md-3 form-control-label">
                                <span class="text-danger">*</span> 排序值
                            </label>
                            <div class="col-md-9 col-form-label">
                                <div style="width: 100%; position: relative">
                                    <input type="text" class="form-control" id="seqNo" name="seqNo" placeholder="值越大，越靠前">
                                </div>
                            </div>
                        </div>
                    </form>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-sm btn-secondary" data-dismiss="modal">
                        <i class="fa fa-close"></i> 取 消
                    </button>
                    <button type="button" class="btn btn-sm btn-primary save-upload" onclick="saveCommodityBanner()">
                        <i class="fa fa-check"></i> 保 存
                    </button>
                </div>
            </form>
        </div>
    </div>

    <div class="container-fluid">
        <div class="animated fadeIn">
            <div class="row">
                <div class="col-md-12">
                    <div class="card">
                        <div class="card-header">
                            <strong>商品详情</strong>
                        </div>
                        <div class="card-block">
                            <table class="table table-responsive-sm table-bordered product-info">
                                <tbody>
                                <tr>
                                    <th>商品名称：</th>
                                    <td>${product.commName}</td>
                                    <th>商品品牌：</th>
                                    <td>${product.brandName}</td>
                                    <th>商品标签：</th>
                                    <td>
                                        <c:if test="${product.hot=='1'}"><span class="badge badge-danger mr-1">热门</span></c:if>
                                        <c:if test="${product.newly=='1'}"><span class="badge badge-danger mr-1">最新</span></c:if>
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
                                    <td>${product.price}元</td>
                                    <th>商品优惠价：</th>
                                    <td>
                                        <span class="badge badge-danger">${product.couPrice}元</span>
                                    </td>
                                    <th>销售数量：</th>
                                    <td>
                                        <a href="/admin/products/sku/list?cid=${product.cid}">${product.saleCount}</a>
                                    </td>
                                </tr>
                                <tr>
                                    <th>浏览量：</th>
                                    <td>
                                        <span class="badge badge-danger">1000人次</span>
                                    </td>
                                    <th>其他颜色：</th>
                                    <td colspan="3">
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
                                    <th>轮播图片：</th>
                                    <td colspan="5">
                                        <img src="${coverImg.resourcePath}" style="width: 100px; height: 100px;" alt="封面图">
                                        <c:forEach var="b" items="${broadImgList}" varStatus="status">
                                            <img src="${b.resourcePath}" style="width: 100px; height: 100px;" alt="轮播图${status.index+1}">
                                        </c:forEach>
                                    </td>
                                </tr>
                                <c:if test="${commodityBanner != null}">
                                    <tr>
                                        <th>banner图片：</th>
                                        <td>
                                            <img src="${bannerImg.resourcePath}" style="width: 100px; height: 100px;" alt="banner图">
                                        </td>
                                        <th>banner排序：</th>
                                        <td colspan="3">${commodityBanner.seqNo}</td>
                                    </tr>
                                </c:if>
                                <tr>
                                    <th>详情图片：</th>
                                    <td colspan="5">
                                        <c:forEach var="b" items="${detailImgList}" varStatus="status">
                                            <img src="${b.resourcePath}" style="width: 100px; height: 100px;" alt="详情图${status.index+1}">
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
                                    <i class="fa fa-plus"></i> 添加同类商品
                                </a>
                            </c:if>
                            <c:if test="${product.groupId == null}">
                                <a href="/admin/products/add?groupId=${product.cid}" class="btn btn-primary">
                                    <i class="fa fa-plus"></i> 添加同类商品
                                </a>
                            </c:if>

                            <c:if test="${product.status=='1'}">
                                <a href="javascript:;" class="btn btn-danger js-status-down" title="下架" data-id="${product.cid}">
                                    <i class="fa fa-level-down"></i> 商品下架
                                </a>
                            </c:if>
                            <c:if test="${product.status!='1'}">
                                <a href="javascript:;" class="btn btn-primary js-status-top" title="上架" data-id="${product.cid}">
                                    <i class="fa fa-level-up"></i> 商品上架
                                </a>
                            </c:if>

                            <c:if test="${product.hot == '1'}">
                                <a href="javascript:;" class="btn btn-danger product-hot-cancel js-hot-down" title="取消热门" data-id="${product.cid}">
                                    <i class="fa fa-remove"></i> 取消热门
                                </a>
                            </c:if>
                            <c:if test="${product.hot != '1'}">
                                <a href="javascript:;" class="btn btn-primary js-hot-top" title="添加到热门" data-id="${product.cid}">
                                    <i class="fa fa-heart"></i> 设置热门
                                </a>
                            </c:if>
                            <c:if test="${product.newly == '1'}">
                                <a href="javascript:;" class="btn btn-danger product-users-cancel js-newly-down" title="取消最新" data-id="${product.cid}">
                                    <i class="fa fa-remove"></i> 取消最新
                                </a>
                            </c:if>
                            <c:if test="${product.newly != '1'}">
                                <a href="javascript:;" class="btn btn-primary js-newly-top" title="添加到最新" data-id="${product.cid}">
                                    <i class="fa fa-bolt"></i> 设置最新
                                </a>
                            </c:if>

                            <%--<c:if test="${commodityBanner == null}">--%>
                                <%--<a href="#banner_settings" class="btn btn-primary" title="设置banner" data-id="${product.cid}" data-toggle="modal">--%>
                                    <%--<i class="fa fa-photo"></i> 设置banner--%>
                                <%--</a>--%>
                            <%--</c:if>--%>
                            <%--<c:if test="${commodityBanner != null}">--%>
                                <%--<a href="javascript:;" class="btn btn-danger" title="取消banner" data-id="${product.cid}" onclick="delCommodityBanner('${product.cid}')">--%>
                                    <%--<i class="fa fa-photo"></i> 取消banner--%>
                                <%--</a>--%>
                            <%--</c:if>--%>
                        </div>
                    </div>
                    <div class="card">
                        <div class="card-header">
                            <strong>尺码列表</strong>
                        </div>
                        <div class="card-block">
                            <table class="table table-striped table-sm table-bordered size-list">
                                <thead>
                                <tr>
                                    <th>序号</th>
                                    <th>尺码大小</th>
                                    <%--<th>初始库存</th>--%>
                                    <th>当前库存</th>
                                    <th>操作</th>
                                </tr>
                                </thead>
                                <tbody id="tbody">
                                    <c:forEach var="s" items="${productSizeList}" varStatus="status">
                                    <tr data-id="${s.sid}">
                                        <td>${status.index+1}</td>
                                        <td>${s.size}</td>
                                        <%--<td>${s.stock}件</td>--%>
                                        <td>${s.stock}件</td>
                                        <td>
                                            <a href="#product_sku_dialog" class="btn btn-sm btn-primary sku-add" data-toggle="modal">
                                                <i class="fa fa-plus"></i> 增加库存
                                            </a>
                                            <a href="#product_sku_dialog" class="btn btn-sm btn-danger sku-minus" data-toggle="modal">
                                                <i class="fa fa-minus"></i> 减少库存
                                            </a>
                                            <a href="javascript:;" class="btn btn-sm btn-danger sku-delete">
                                                <i class="fa fa-remove"></i> 删除尺码
                                            </a>
                                        </td>
                                    </tr>
                                    </c:forEach>
                                    <tr data-id="">
                                        <td>--</td>
                                        <td>合计</td>
                                        <%--<td>11件</td>--%>
                                        <td>${stockSum}件</td>
                                        <td>--</td>
                                    </tr>
                                </tbody>
                            </table>
                        </div>
                        <div class="card-footer">
                            <a href="#product_size_dialog" class="btn btn-primary size-add" data-toggle="modal">
                                <i class="fa fa-plus"></i> 增加尺码
                            </a>
                        </div>
                    </div>
                    <div class="card">
                        <div class="card-header">
                            <strong>推荐商品</strong>
                        </div>
                        <div class="card-block">
                            <table class="table table-striped table-sm table-bordered size-list">
                                <thead>
                                <tr>
                                    <th>商品编号</th>
                                    <th>商品图片</th>
                                    <th>商品名称</th>
                                    <th>商品品类</th>
                                    <th>商品材质</th>
                                    <th>商品价格</th>
                                    <th>操作</th>
                                </tr>
                                </thead>
                                <tbody>
                                </tbody>
                            </table>
                        </div>
                        <div class="card-footer">
                            <a href="#product_relation_dialog" class="btn btn-primary" data-toggle="modal">
                                <i class="fa fa-star"></i> 设置推荐
                            </a>
                        </div>
                    </div>
                </div>
                <!--/.col-->
            </div>
            <!--/.row-->
        </div>
    </div>

    <div class="modal fade" id="product_size_dialog" tabindex="-1" role="dialog">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-body">
                    <form id="product_size_form" method="post" class="form-horizontal" novalidate onsubmit="return false;">
                        <input type="hidden" id="product_size_cid" name="cid" value="${product.cid}" />
                        <div class="form-group row">
                            <label class="col-md-4 form-control-label" for="product_size_size">
                                <span class="text-danger">*</span> 商品尺码
                            </label>
                            <div class="col-md-8">
                                <select class="form-control" name="size" id="product_size_size" data-val="true" data-val-required="请选择尺码大小">
                                    <c:forEach var="s" items="${sizeList}">
                                        <option value="${s.dictValue}">${s.dictValue}</option>
                                    </c:forEach>
                                </select>
                                <div data-valmsg-for="size" data-valmsg-replace="true"></div>
                            </div>
                        </div>
                        <div class="form-group row">
                            <label class="col-md-4 form-control-label" for="product_size_stock">
                                <span class="text-danger">*</span> 初始库存
                            </label>
                            <div class="col-md-8">
                                <input type="text" class="form-control" id="product_size_stock" placeholder="请输入初始库存" name="stock"
                                       data-val="true" data-val-required="初始库存不能为空" autocomplete="off">
                                <div data-valmsg-for="stock" data-valmsg-replace="true"></div>
                            </div>
                        </div>
                    </form>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary btn-sm" data-dismiss="modal">
                        <i class="fa fa-remove"></i> 取消
                    </button>
                    <button type="button" class="btn btn-primary btn-sm" id="product_size_save">
                        <i class="fa fa-check"></i> 保存
                    </button>
                </div>
            </div>
        </div>
    </div>

    <div class="modal fade" id="product_sku_dialog" tabindex="-1" role="dialog">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-body">
                    <div class="alert alert-warning" id="product_sku_tip"></div>
                    <form id="product_sku_form" method="post" class="form-horizontal" novalidate onsubmit="return false;">
                        <input type="hidden" id="product_sku_sid" name="sid" />
                        <input type="hidden" id="product_sku_type" name="type" />
                        <div class="form-group row">
                            <label class="col-md-4 form-control-label" for="ps_num">
                                <span class="text-danger">*</span> 变更数量
                            </label>
                            <div class="col-md-8">
                                <input type="text" class="form-control" id="ps_num" placeholder="请输入库存数量" name="num"
                                       data-val="true" data-val-required="库存数量不能为空" autocomplete="off">
                                <div data-valmsg-for="num" data-valmsg-replace="true"></div>
                            </div>
                        </div>
                        <div class="form-group row">
                            <label class="col-md-4 form-control-label" for="ps_remark">
                                <span class="text-danger">*</span> 变更备注
                            </label>
                            <div class="col-md-8">
                                <textarea class="form-control" id="ps_remark" placeholder="请输入备注" name="remark"
                                          data-val="true" data-val-required="备注不能为空" autocomplete="off"></textarea>
                                <div data-valmsg-for="remark" data-valmsg-replace="true"></div>
                            </div>
                        </div>
                    </form>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary btn-sm" data-dismiss="modal">
                        <i class="fa fa-remove"></i> 取消
                    </button>
                    <button type="button" class="btn btn-primary btn-sm" id="product_sku_save">
                        <i class="fa fa-check"></i> 保存
                    </button>
                </div>
            </div>
        </div>
    </div>

    <div class="modal fade" id="product_relation_dialog" tabindex="-1" role="dialog">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-body">
                    <form id="product_relation_form" method="post" class="form-horizontal" novalidate onsubmit="return false;">
                        <div class="form-group row">
                            <label class="col-md-4 form-control-label" for="ps_num">
                                <span class="text-danger">*</span> 关联商品
                            </label>
                            <div class="col-md-8">
                                <input type="text" class="form-control" id="cid" placeholder="请输入关联商品" name="cid"
                                       data-val="true" data-val-required="关联商品不能为空" autocomplete="off">
                                <div data-valmsg-for="cid" data-valmsg-replace="true"></div>
                            </div>
                        </div>
                    </form>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary btn-sm" data-dismiss="modal">
                        <i class="fa fa-remove"></i> 取消
                    </button>
                    <button type="button" class="btn btn-primary btn-sm" id="product_relation_save">
                        <i class="fa fa-check"></i> 保存
                    </button>
                </div>
            </div>
        </div>
    </div>

</layout:override>

<c:import url="../Shared/GeneralLayout.jsp">
    <c:param name="menu" value="products"/>
    <c:param name="subMenu" value="detail"/>
</c:import>
