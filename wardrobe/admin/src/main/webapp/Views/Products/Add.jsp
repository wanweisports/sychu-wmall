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
<%--    <script type="text/javascript" src="Content/js/require.js?v=${static_resource_version}"
            data-main="Content/js/app/products/add.js?v=${static_resource_version}"></script>--%>
    <script src="/Content/lib/jquery.min.js"></script>
    <script src="/Content/lib/jquery-form.js"></script>
    <script src="/Content/lib/formToJson.js"></script>
    <script type="text/javascript">
        //添加或修改商品
        function addSubmit(){
            //验证都必填...


            //封装数据
            var product = $('#product_form').serializeJson();

            //品类
            var $category = $("button[name='categoryBtn']");
            var categorys = "";
            $.each($category, function () {
                if($(this).hasClass("btn-success")){
                    if(categorys) categorys += ",";
                    categorys += $(this).data("id");
                }
            });
            if(!categorys){
                alert("品类必选");
                return;
            }
            product['category'] = "," + categorys + ",";

            //风格
            var $style = $("button[name='styleBtn']");
            var styles = "";
            $.each($style, function () {
                if($(this).hasClass("btn-success")){
                    if(styles) styles += ",";
                    styles += $(this).data("id");
                }
            });
            if(!categorys){
                alert("风格必选");
                return;
            }
            product['style'] = "," + styles + ",";

            //材质
            var $material = $("button[name='materialBtn']");
            var materials = "";
            $.each($material, function () {
                if($(this).hasClass("btn-success")){
                    if(materials) materials += ",";
                    materials += $(this).data("id");
                }
            });
            if(!categorys){
                alert("材质必选");
                return;
            }
            product['material'] = "," + materials + ",";

            //提交数据
            $("#product_form").ajaxSubmit({
                type: "post",
                dataType: "json",
                data: {json: JSON.stringify(product)},
                url: "/commodity/addUpdateCommodity",
                success: function (res) {
                    alert(res.message);
                    if(res.code == 1){
                        
                    }
                },
                error: function (msg) {
                    alert(msg);
                }
            });
        }

        //删除图片
        function delRes(resourceId){
            if(resourceId && window.confirm("确认删除吗？")) {
                $.post("/resource/delRes", {resourceId: resourceId}, function (res) {
                    alert(res.message);
                    if (res.code == 1) {

                    }
                });
            }
        }

        //删除尺码
        function delSize(sid){
            if(sid && window.confirm("确认删除吗？")) {
                $.post("/commodity/delSize", {sid: sid}, function (res) {
                    alert(res.message);
                    if (res.code == 1) {

                    }
                });
            }
        }

        //添加类型
        function addCommTpye(type, dictValue){
            $.post('/admin/products/' + type + '/save', {dictValue: dictValue}, function (res) {
                alert(res.message);
                if (res.code == 1) {

                }
            });
        }

    </script>
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
                            <form id="product_form" method="post" class="form-horizontal" enctype="multipart/form-data" novalidate onsubmit="return false;">
                                <c:if test="${commodity.cid gt 0}">
                                    <input type="hidden" name="cid" value="${commodity.cid}" />
                                    <input type="hidden" name="coid" value="${commodityColor.coid}" />
                                </c:if>
                                <div class="form-group row">
                                    <label class="col-md-2 form-control-label" for="p_commName">
                                        <span class="text-danger">*</span> 商品名称
                                    </label>
                                    <div class="col-md-8">
                                        <input type="text" class="form-control" id="p_commName" placeholder="请输入商品名称" name="commName" value="${commodity.commName}"
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
                                               data-val="true" data-val-required="商品描述不能为空">${commodity.productDesc}</textarea>
                                        <div data-valmsg-for="productDesc" data-valmsg-replace="true"></div>
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <label class="col-md-2 form-control-label" for="p_categorySelect">
                                        <span class="text-danger">*</span> 商品品类
                                    </label>
                                    <div class="col-md-2">
                                        <select class="form-control" id="p_categorySelect" name="categorySelect">
                                            <c:forEach var="c" items="${categoryList}">
                                                <option value="${c.dictId}">${c.dictValue}</option>
                                            </c:forEach>
                                        </select>
                                        <input type="hidden" class="form-control" id="p_category" placeholder="请输入商品品类" name="category"
                                               data-val="true" data-val-required="请至少选择一种商品品类">
                                        <div data-valmsg-for="category" data-valmsg-replace="true"></div>
                                    </div>
                                    <div class="col-md-8 category-list">
                                        <c:forEach var="c" items="${categoryList}">
                                            <c:if test="${fn:contains(commodity.category, dh.concat(c.dictId).concat(dh))}">
                                                <button name="categoryBtn" type="button" class="btn btn-success btn-close category-item" data-id="${c.dictId}">
                                                        ${c.dictValue}<i class="fa fa-remove"></i>
                                                </button>
                                            </c:if>
                                        </c:forEach>
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
                                        <c:forEach var="s" items="${styleList}">
                                            <button name="styleBtn" type="button" class="btn btn-secondary <c:if test="${fn:contains(commodity.style, dh.concat(s.dictId).concat(dh))}">btn-success</c:if>" data-id="${s.dictId}">${s.dictValue}</button>
                                        </c:forEach>
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
                                        <c:forEach var="m" items="${materialList}">
                                            <button name="materialBtn" type="button" class="btn btn-secondary  <c:if test="${fn:contains(commodity.material, dh.concat(m.dictId).concat(dh))}">btn-success</c:if>" data-id="${m.dictId}">${m.dictValue}</button>
                                        </c:forEach>
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
                                        <input type="text" class="form-control" id="p_price" placeholder="请输入商品原价" name="price" value="${commodity.price}"
                                               data-val="true" data-val-required="商品原价不能为空">
                                        <div data-valmsg-for="price" data-valmsg-replace="true"></div>
                                    </div>
                                    <%--<div class="col-md-4">
                                        <input type="text" class="form-control" id="p_couPrice" placeholder="请输入商品优惠价格" name="couPrice" value="${commodity.couPrice}"
                                               data-val="true" data-val-required="商品优惠价格不能为空">
                                        <div data-valmsg-for="couPrice" data-valmsg-replace="true"></div>
                                    </div>--%>
                                </div>
                                <div class="form-group row">
                                    <label class="col-md-2 form-control-label">
                                        <span class="text-danger">*</span> 商品图片
                                    </label>
                                    <div class="col-md-10 text-center">
                                        <div class="pull-left mr-2">
                                            <c:if test="${coverImg != null}">
                                                <img src="${coverImg.resourcePath}" style="width: 100px">
                                                <button type="button" onclick="delRes(${coverImg.resourceId})">删除</button>
                                            </c:if>
                                            <c:if test="${coverImg == null}">
                                                <input type="file" name="file_0" /> <%--此处name不能和其他file的name相同，封面图后缀固定写0--%>
                                            </c:if>
                                            <p>封面图</p>
                                        </div>
                                        <%--这里可以点击一个加号，添加一个轮播图--%>
                                        <c:forEach var="broadImg" items="${broadImgList}" varStatus="status">
                                            <div class="pull-left mr-2">
                                                <img src="${broadImg.resourcePath}" style="width: 100px">
                                                <button type="button" onclick="delRes(${broadImg.resourceId})">删除</button>
                                                <p>轮播图${status.index+1}</p>
                                            </div>
                                        </c:forEach>
                                        <div class="pull-left mr-2">
                                            <input type="file" name="file_1" /> <%--此处name不能和其他file的name相同，后缀1开始，相当于排序--%>
                                            <p>轮播图${fn:length(broadImgList)+1}</p>
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <label class="col-md-2 form-control-label">
                                        <span class="text-danger">*</span> 商品颜色
                                    </label>
                                    <div class="col-md-8">
                                        <input type="text" class="form-control" id="p_colorName" placeholder="请输入商品颜色" name="colorName" value="${commodityColor.colorName}"
                                               data-val="true" data-val-required="请至少输入一种商品颜色">
                                        <div data-valmsg-for="colorName" data-valmsg-replace="true"></div>
                                    </div>
                                </div>
                                <c:set var="show" value="true" />
                                <c:if test="${fn:length(commoditySizeList) == 0}">
                                    <div class="form-group row">
                                        <label class="col-md-2 form-control-label">
                                            <span class="text-danger">*</span> 商品尺码
                                        </label>
                                        <div class="col-md-2">
                                            <select class="form-control" name="size">
                                                <c:forEach var="s" items="${sizeList}">
                                                    <option value="${s.dictValue}" <c:if test="${cs.size==s.dictValue}"></c:if>>${s.dictValue}</option>
                                                </c:forEach>
                                            </select>
                                        </div>
                                        <div class="col-md-2">
                                            <input type="text" class="form-control" name="stock" placeholder="初始库存" value="${cs.stock}">
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
                                    <c:set var="show" value="false" />
                                </c:if>
                                <c:forEach var="cs" items="${commoditySizeList}" varStatus="status">
                                    <div class="form-group row">
                                        <input type="hidden" name="sid" value="${cs.sid}" />
                                        <c:if test="${show}">
                                            <label class="col-md-2 form-control-label">
                                                <span class="text-danger">*</span> 商品尺码
                                            </label>
                                        </c:if>
                                        <div class="<c:if test="${!show}">offset-2</c:if> col-md-2">
                                            <select class="form-control" name="size">
                                                <c:forEach var="s" items="${sizeList}">
                                                    <option value="${s.dictValue}" <c:if test="${cs.size==s.dictValue}">selected</c:if>>${s.dictValue}</option>
                                                </c:forEach>
                                            </select>
                                        </div>
                                        <div class="col-md-2">
                                            <input type="text" class="form-control" name="stock" placeholder="初始库存" value="${cs.stock}">
                                        </div>
                                        <div class="col-md-4">
                                            <button type="button" class="btn btn-primary" <c:if test="${fn:length(commoditySizeList)!=status.index+1}">style="visibility: hidden;"</c:if>>
                                                <i class="fa fa-plus"></i>
                                            </button>
                                            <button type="button" class="btn btn-danger" onclick="delSize(${cs.sid})">
                                                <i class="fa fa-remove"></i>
                                            </button>
                                        </div>
                                    </div>
                                    <c:set var="show" value="false" />
                                </c:forEach>
                            </form>
                        </div>
                        <div class="card-footer text-right">
                            <span class="text-danger pay-note"></span>
                            <button type="button" class="btn btn-primary save-products" onclick="addSubmit()">
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
