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
    </style>
</layout:override>

<layout:override name="<%=Blocks.BLOCK_HEADER_SCRIPTS%>">
    <script type="text/javascript" src="Content/js/require.js?v=${static_resource_version}"
            data-main="Content/js/app/products/add.js?v=${static_resource_version}"></script>
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
                            <form id="product_form" method="post" class="form-horizontal" novalidate onsubmit="return false;">
                                <div class="form-group row">
                                    <label class="col-md-2 form-control-label" for="p_commName">
                                        <span class="text-danger">*</span> 商品名称
                                    </label>
                                    <div class="col-md-8">
                                        <input type="text" class="form-control" id="p_commName" placeholder="请输入商品名称" name="commName"
                                               data-val="true" data-val-required="商品名称不能为空"
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
                                               data-val="true" data-val-required="商品描述不能为空"></textarea>
                                        <div data-valmsg-for="productDesc" data-valmsg-replace="true"></div>
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <label class="col-md-2 form-control-label" for="p_categorySelect">
                                        <span class="text-danger">*</span> 商品品类
                                    </label>
                                    <div class="col-md-2">
                                        <select class="form-control" id="p_categorySelect" name="categorySelect">
                                            <option>连衣裙</option>
                                        </select>
                                        <input type="hidden" class="form-control" id="p_category" placeholder="请输入商品品类" name="category"
                                               data-val="true" data-val-required="请至少选择一种商品品类">
                                        <div data-valmsg-for="category" data-valmsg-replace="true"></div>
                                    </div>
                                    <div class="col-md-8 category-list">
                                        <button type="button" class="btn btn-success btn-close category-item">
                                            连衣裙<i class="fa fa-remove"></i>
                                        </button>
                                        <button type="button" class="btn btn-success  btn-close category-item">
                                            西服<i class="fa fa-remove"></i>
                                        </button>
                                        <button type="button" class="btn btn-success btn-close category-item">
                                            连衣裙<i class="fa fa-remove"></i>
                                        </button>
                                        <button type="button" class="btn btn-success btn-close category-item">
                                            连衣裙<i class="fa fa-remove"></i>
                                        </button>
                                        <button type="button" class="btn btn-success  btn-close category-item">
                                            西服<i class="fa fa-remove"></i>
                                        </button>
                                        <button type="button" class="btn btn-success btn-close category-item">
                                            连衣裙<i class="fa fa-remove"></i>
                                        </button>
                                        <button type="button" class="btn btn-primary category-add" data-toggle="modal" data-target="#product_category_add">
                                            <i class="fa fa-plus"></i>
                                        </button>
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <label class="col-md-2 form-control-label">
                                        <span class="text-danger">*</span> 商品风格
                                    </label>
                                    <div class="col-md-10">
                                        <input type="hidden" class="form-control" id="p_style" placeholder="请选择商品风格" name="style"
                                               data-val="true" data-val-required="请至少选择一种商品风格">
                                        <div data-valmsg-for="category" data-valmsg-replace="true"></div>
                                        <button type="button" class="btn btn-success">职 场</button>
                                        <button type="button" class="btn btn-success">约 会</button>
                                        <button type="button" class="btn btn-secondary">职 场</button>
                                        <button type="button" class="btn btn-secondary">职 场</button>
                                        <button type="button" class="btn btn-success">职 场</button>
                                        <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#product_style_add">
                                            <i class="fa fa-plus"></i>
                                        </button>
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <label class="col-md-2 form-control-label">
                                        <span class="text-danger">*</span> 商品材质
                                    </label>
                                    <div class="col-md-10">
                                        <input type="hidden" class="form-control" id="p_material" placeholder="请选择商品材质" name="material"
                                               data-val="true" data-val-required="请至少选择一种商品材质">
                                        <button type="button" class="btn btn-secondary">棉 麻</button>
                                        <button type="button" class="btn btn-success">纯 棉</button>
                                        <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#product_material_add">
                                            <i class="fa fa-plus"></i>
                                        </button>
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <label class="col-md-2 form-control-label">
                                        <span class="text-danger">*</span> 商品价格
                                    </label>
                                    <div class="col-md-4">
                                        <input type="text" class="form-control" id="p_price" placeholder="请输入商品原价" name="price"
                                               data-val="true" data-val-required="商品原价不能为空">
                                        <div data-valmsg-for="price" data-valmsg-replace="true"></div>
                                    </div>
                                    <div class="col-md-4">
                                        <input type="text" class="form-control" id="p_couPrice" placeholder="请输入商品优惠价格" name="couPrice"
                                               data-val="true" data-val-required="商品优惠价格不能为空">
                                        <div data-valmsg-for="couPrice" data-valmsg-replace="true"></div>
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <label class="col-md-2 form-control-label">
                                        <span class="text-danger">*</span> 商品图片
                                    </label>
                                    <div class="col-md-10 text-center">
                                        <div class="pull-left mr-2">
                                            <img src="/Content/images/upload.png" style="width: 100px">
                                            <p>封面图</p>
                                        </div>
                                        <div class="pull-left mr-2">
                                            <img src="/Content/images/upload.png" style="width: 100px">
                                            <p>轮播图1</p>
                                        </div>
                                        <div class="pull-left mr-2">
                                            <img src="/Content/images/upload.png" style="width: 100px">
                                            <p>轮播图2</p>
                                        </div>
                                        <div class="pull-left mr-2">
                                            <img src="/Content/images/upload.png" style="width: 100px">
                                            <p>轮播图3</p>
                                        </div>
                                        <div class="pull-left">
                                            <img src="/Content/images/upload.png" style="width: 100px">
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
                                               data-val="true" data-val-required="请至少输入一种商品颜色">
                                        <div data-valmsg-for="colorName" data-valmsg-replace="true"></div>
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <label class="col-md-2 form-control-label">
                                        <span class="text-danger">*</span> 商品尺码
                                    </label>
                                    <div class="col-md-2">
                                        <select class="form-control">
                                            <option selected>XL</option>
                                            <option>XXL</option>
                                        </select>
                                    </div>
                                    <div class="col-md-2">
                                        <input type="text" class="form-control" placeholder="初始库存">
                                    </div>
                                    <div class="col-md-4">
                                        <button type="button" class="btn btn-primary">
                                            <i class="fa fa-plus"></i>
                                        </button>
                                        <button type="button" class="btn btn-danger">
                                            <i class="fa fa-remove"></i>
                                        </button>
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <div class="offset-2 col-md-2">
                                        <select class="form-control">
                                            <option selected>XL</option>
                                            <option>XXL</option>
                                        </select>
                                    </div>
                                    <div class="col-md-2">
                                        <input type="text" class="form-control" placeholder="初始库存">
                                    </div>
                                    <div class="col-md-4">
                                        <button type="button" class="btn btn-primary">
                                            <i class="fa fa-plus"></i>
                                        </button>
                                        <button type="button" class="btn btn-danger">
                                            <i class="fa fa-remove"></i>
                                        </button>
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <div class="offset-2 col-md-2">
                                        <select class="form-control">
                                            <option selected>XL</option>
                                            <option>XXL</option>
                                        </select>
                                    </div>
                                    <div class="col-md-2">
                                        <input type="text" class="form-control" placeholder="初始库存">
                                    </div>
                                    <div class="col-md-4">
                                        <button type="button" class="btn btn-primary">
                                            <i class="fa fa-plus"></i>
                                        </button>
                                        <button type="button" class="btn btn-danger">
                                            <i class="fa fa-remove"></i>
                                        </button>
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
