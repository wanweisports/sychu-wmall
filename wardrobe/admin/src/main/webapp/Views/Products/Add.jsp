<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ page import="com.wardrobe.layout.Blocks" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> <%-- JSTL表达式（判断，循环，输出） --%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %> <%-- 方法表达式（字符串截取，替换） --%>
<%@ taglib uri="http://www.sychu.com/tags/tag" prefix="layout" %>

<layout:override name="<%=Blocks.BLOCK_HEADER_CSS%>">
    <link href="Content/bower_components/bootstrap-datepicker/dist/css/bootstrap-datepicker3.css?v=${static_resource_version}" rel="stylesheet">
    <style type="text/css">
        .form-control-label {
            text-align: right;
            font-weight: bold;
        }
        .product-image-remove {
            position: absolute;
            right: -.75rem;
            top: -.75rem;
            border-radius: 1rem;
            z-index: 100;
            display: none;
        }
        .product-image-file {
            position: absolute;
            left: 0;
            top: 0;
            width: 100%;
            height: 100%;
            opacity: 0;
        }
        .product-image-show {
            width: 100px;
            height: 100px;
        }
    </style>
</layout:override>

<layout:override name="<%=Blocks.BLOCK_HEADER_SCRIPTS%>">
    <script type="text/javascript" src="Content/js/require.js?v=${static_resource_version}"
            data-main="Content/js/app/products/add.js?v=${static_resource_version}11"></script>
</layout:override>

<layout:override name="<%=Blocks.BLOCK_BODY%>">

    <%@ include file="./LabelSettings.jsp" %>

    <div class="container-fluid">
        <div class="animated fadeIn">
            <div class="row">
                <div class="col-md-12">
                    <div class="card">
                        <div class="card-header">
                            <strong>商品添加</strong>
                            <small>Product Add</small>
                        </div>
                        <div class="card-block">
                            <iframe name="product_form_result" id="product_form_result" style="position: absolute; left: -9999px;"></iframe>
                            <form id="product_form" method="post" class="form-horizontal" action="/admin/products/saveAdd"
                                  enctype="multipart/form-data" novalidat target="product_form_result">
                                <input type="hidden" name="groupId" value="${groupId}" />
                                <div class="form-group row">
                                    <label class="col-md-2 form-control-label" for="p_commNo">
                                        <span class="text-danger">*</span> 商品编号
                                    </label>
                                    <div class="col-md-8">
                                        <input type="text" class="form-control" id="p_commNo" placeholder="请输入商品编号" name="commNo"
                                               data-val="true" data-val-required="商品编号不能为空" autocomplete="off">
                                        <div data-valmsg-for="commNo" data-valmsg-replace="true"></div>
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <label class="col-md-2 form-control-label" for="p_commName">
                                        <span class="text-danger">*</span> 商品名称
                                    </label>
                                    <div class="col-md-8">
                                        <input type="text" class="form-control" id="p_commName" placeholder="请输入商品名称" name="commName"
                                               data-val="true" data-val-required="商品名称不能为空" autocomplete="off"
                                               data-val-length-max="30" data-val-length-min="2" data-val-length="商品名称必须包含 2~30 个字符">
                                        <div data-valmsg-for="commName" data-valmsg-replace="true"></div>
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <label class="col-md-2 form-control-label" for="p_productDesc">
                                        <span class="text-danger">*</span> 商品描述
                                    </label>
                                    <div class="col-md-8">
                                        <textarea class="form-control" id="p_productDesc" placeholder="请输入商品描述" name="productDesc"
                                               data-val="true" data-val-required="商品描述不能为空" autocomplete="off"></textarea>
                                        <div data-valmsg-for="productDesc" data-valmsg-replace="true"></div>
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <label class="col-md-2 form-control-label" for="p_brandName">
                                        <span class="text-danger">*</span> 商品品牌
                                    </label>
                                    <div class="col-md-8">
                                        <input type="text" class="form-control" id="p_brandName" placeholder="请输入商品品牌" name="brandName"
                                               data-val="true" data-val-required="商品品牌不能为空" autocomplete="off">
                                        <div data-valmsg-for="brandName" data-valmsg-replace="true"></div>
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <label class="col-md-2 form-control-label" for="p_categorySelect">
                                        <span class="text-danger">*</span> 商品品类
                                    </label>
                                    <div class="col-md-2">
                                        <select class="form-control" id="p_categorySelect" name="categorySelect">
                                            <option value="">请选择</option>
                                            <c:forEach var="c" items="${categoryList}">
                                                <option value="${c.dictId}">${c.dictValue}</option>
                                            </c:forEach>
                                        </select>
                                        <input type="text" class="form-control" id="p_category" placeholder="请输入商品品类" name="category"
                                               data-val="true" data-val-required="请至少选择一种商品品类" style="position: absolute; left: -9999px">
                                        <div data-valmsg-for="category" data-valmsg-replace="true"></div>
                                    </div>
                                    <div class="col-md-8 category-list">
                                        <button type="button" class="btn btn-primary category-add" data-toggle="modal" data-target="#product_category_add">
                                            <i class="fa fa-plus"></i>
                                        </button>
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <label class="col-md-2 form-control-label">
                                        <span class="text-danger">*</span> 商品风格
                                    </label>
                                    <div class="col-md-10 style-list">
                                        <c:forEach var="s" items="${styleList}">
                                            <button type="button" class="btn btn-secondary style-item mr-2" data-id="${s.dictId}">${s.dictValue != '' ? s.dictValue : '无'}</button>
                                        </c:forEach>
                                        <button type="button" class="btn btn-primary style-add" data-toggle="modal" data-target="#product_style_add">
                                            <i class="fa fa-plus"></i>
                                        </button>
                                        <div data-valmsg-for="style" data-valmsg-replace="true"></div>
                                        <input type="text" class="form-control" id="p_style" placeholder="请选择商品风格" name="style"
                                               data-val="true" data-val-required="请至少选择一种商品风格" style="position: absolute; left: -9999px">
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <label class="col-md-2 form-control-label">
                                        <span class="text-danger">*</span> 商品材质
                                    </label>
                                    <div class="col-md-10 material-list">
                                        <c:forEach var="m" items="${materialList}">
                                            <button type="button" class="btn btn-secondary material-item mr-2" data-id="${m.dictId}">${m.dictValue != '' ? m.dictValue : '无'}</button>
                                        </c:forEach>
                                        <button type="button" class="btn btn-primary material-add" data-toggle="modal" data-target="#product_material_add">
                                            <i class="fa fa-plus"></i>
                                        </button>
                                        <div data-valmsg-for="material" data-valmsg-replace="true"></div>
                                        <input type="text" class="form-control" id="p_material" placeholder="请选择商品材质" name="material"
                                               data-val="true" data-val-required="请至少选择一种商品材质" style="position: absolute; left: -9999px">
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <label class="col-md-2 form-control-label">
                                        <span class="text-danger">*</span> 商品价格
                                    </label>
                                    <div class="col-md-4">
                                        <input type="text" class="form-control" id="p_price" placeholder="请输入商品原价" name="price"
                                               data-val="true" data-val-required="商品原价不能为空" autocomplete="off">
                                        <div data-valmsg-for="price" data-valmsg-replace="true"></div>
                                    </div>
                                    <div class="col-md-4">
                                        <input type="text" class="form-control" id="p_couPrice" placeholder="请输入商品优惠价格" name="couPrice"
                                               data-val="true" data-val-required="商品优惠价格不能为空" autocomplete="off">
                                        <div data-valmsg-for="couPrice" data-valmsg-replace="true"></div>
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <label class="col-md-2 form-control-label">
                                        <span class="text-danger">*</span> 商品图片
                                    </label>
                                    <div class="col-md-10 text-center">
                                        <div class="pull-left mr-4">
                                            <div style="width: 100%; position: relative">
                                                <button class="btn btn-danger btn-sm product-image-remove">
                                                    <i class="fa fa-remove"></i>
                                                </button>
                                                <img class="product-image-show" src="/Content/images/upload.png">
                                                <input type="file" class="product-image-file" name="file_0"> <%--此处name不能和其他file的name相同，封面图后缀固定写0--%>
                                            </div>
                                            <p>封面图</p>
                                        </div>
                                        <%--这里可以点击一个加号，添加一个轮播图--%>
                                        <div class="pull-left mr-4">
                                            <div style="width: 100%; position: relative">
                                                <button class="btn btn-danger btn-sm product-image-remove">
                                                    <i class="fa fa-remove"></i>
                                                </button>
                                                <img class="product-image-show" src="/Content/images/upload.png">
                                                <input type="file" class="product-image-file" name="file_1"> <%--此处name不能和其他file的name相同，封面图后缀固定写0--%>
                                            </div>
                                            <p>轮播图1</p>
                                        </div>
                                        <div class="pull-left mr-4">
                                            <div style="width: 100%; position: relative">
                                                <button class="btn btn-danger btn-sm product-image-remove">
                                                    <i class="fa fa-remove"></i>
                                                </button>
                                                <img class="product-image-show" src="/Content/images/upload.png">
                                                <input type="file" class="product-image-file" name="file_2"> <%--此处name不能和其他file的name相同，封面图后缀固定写0--%>
                                            </div>
                                            <p>轮播图2</p>
                                        </div>
                                        <div class="pull-left mr-4">
                                            <div style="width: 100%; position: relative">
                                                <button class="btn btn-danger btn-sm product-image-remove">
                                                    <i class="fa fa-remove"></i>
                                                </button>
                                                <img class="product-image-show" src="/Content/images/upload.png">
                                                <input type="file" class="product-image-file" name="file_3"> <%--此处name不能和其他file的name相同，封面图后缀固定写0--%>
                                            </div>
                                            <p>轮播图3</p>
                                        </div>
                                        <div class="pull-left mr-4">
                                            <div style="width: 100%; position: relative">
                                                <button class="btn btn-danger btn-sm product-image-remove">
                                                    <i class="fa fa-remove"></i>
                                                </button>
                                                <img class="product-image-show" src="/Content/images/upload.png">
                                                <input type="file" class="product-image-file" name="file_4"> <%--此处name不能和其他file的name相同，封面图后缀固定写0--%>
                                            </div>
                                            <p>轮播图4</p>
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <label class="col-md-2 form-control-label">
                                        <span class="text-danger">*</span> 商品颜色
                                    </label>
                                    <div class="col-md-8">
                                        <input type="text" class="form-control" id="p_colorName" placeholder="请输入商品颜色" name="colorName"
                                               data-val="true" data-val-required="商品颜色不能为空" autocomplete="off">
                                        <div data-valmsg-for="colorName" data-valmsg-replace="true"></div>
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <label class="col-md-2 form-control-label">
                                        <span class="text-danger">*</span> 商品排序
                                    </label>
                                    <div class="col-md-8">
                                        <input type="text" class="form-control" id="p_seqNo" placeholder="请输入商品排序(值越大越靠前)" name="seqNo"
                                               data-val="true" data-val-required="商品排序不能为空" autocomplete="off">
                                        <div data-valmsg-for="seqNo" data-valmsg-replace="true"></div>
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <label class="col-md-2 form-control-label">
                                        <span class="text-danger">*</span> 商品尺码
                                    </label>
                                    <div class="col-md-8 size-list">
                                        <div class="size-item row mb-2">
                                            <div class="col-md-2">
                                                <select class="form-control" name="initSize">
                                                    <c:forEach var="s" items="${sizeList}">
                                                        <option value="${s.dictValue}">${s.dictValue}</option>
                                                    </c:forEach>
                                                </select>
                                            </div>
                                            <div class="col-md-3">
                                                <input type="text" class="form-control" name="initStock" placeholder="初始库存" autocomplete="off">
                                            </div>
                                            <div class="col-md-4">
                                                <button type="button" class="btn btn-primary size-add">
                                                    <i class="fa fa-plus"></i>
                                                </button>
                                                <button type="button" class="btn btn-danger size-remove">
                                                    <i class="fa fa-remove"></i>
                                                </button>
                                            </div>
                                        </div>
                                        <input type="text" class="form-control" id="p_size" name="size" style="position: absolute; left: -9999px">
                                        <input type="text" class="form-control" id="p_stock" placeholder="请输入尺码" name="stock"
                                               data-val="true" data-val-required="请至少输入一种商品尺码" style="position: absolute; left: -9999px">
                                        <div data-valmsg-for="stock" data-valmsg-replace="true"></div>
                                    </div>
                                </div>
                            </form>
                        </div>
                        <div class="card-footer text-right">
                            <span class="text-danger pay-note"></span>
                            <button type="button" class="btn btn-primary save-products">
                                <i class="fa fa-check"></i> 保 存
                            </button>
                        </div>
                    </div>
                </div>
            </div>
            <!--/.row-->
        </div>

    </div>
</layout:override>

<c:import url="../Shared/GeneralLayout.jsp">
    <c:param name="menu" value="products"/>
    <c:param name="subMenu" value="add"/>
</c:import>
