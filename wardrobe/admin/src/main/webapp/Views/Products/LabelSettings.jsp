<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>

<style type="text/css">
    .btn.btn-close {
        position: relative;
        margin-right: 10px;
    }
    .btn.btn-close .fa.fa-remove {
        display: block;
        position: absolute;
        background: #f86c6b;
        color: #ffffff;
        right: -8px;
        top: -10px;
        width: 20px;
        height: 20px;
        line-height: 20px;
        border-radius: 10px;
    }
</style>

<div class="modal fade" id="product_category_add" tabindex="-1" role="dialog">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-body">
                <form id="product_category_form" method="post" class="form-horizontal" novalidate onsubmit="return false;">
                    <div class="form-group row">
                        <label class="col-md-3 form-control-label" for="pc_category">
                            <span class="text-danger">*</span> 商品品类
                        </label>
                        <div class="col-md-8">
                            <input type="text" class="form-control" id="pc_category" placeholder="请输入商品品类" name="dictValue"
                                   data-val="true" data-val-required="商品品类不能为空" autocomplete="off"
                                   data-val-length-max="5" data-val-length-min="2" data-val-length="商品品类必须包含 2~5 个字符">
                            <div data-valmsg-for="category" data-valmsg-replace="true"></div>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary btn-sm" data-dismiss="modal">
                    <i class="fa fa-remove"></i> 取消
                </button>
                <button type="button" class="btn btn-primary btn-sm" id="product_category_save">
                    <i class="fa fa-check"></i> 保存
                </button>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="product_style_add" tabindex="-1" role="dialog">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-body">
                <form id="product_style_form" method="post" class="form-horizontal" novalidate onsubmit="return false;">
                    <div class="form-group row">
                        <label class="col-md-3 form-control-label" for="ps_style">
                            <span class="text-danger">*</span> 商品风格
                        </label>
                        <div class="col-md-8">
                            <input type="text" class="form-control" id="ps_style" placeholder="请输入商品风格" name="dictValue"
                                   data-val="true" data-val-required="商品风格不能为空" autocomplete="off"
                                   data-val-length-max="5" data-val-length-min="2" data-val-length="商品风格必须包含 2~5 个字符">
                            <div data-valmsg-for="style" data-valmsg-replace="true"></div>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary btn-sm" data-dismiss="modal">
                    <i class="fa fa-remove"></i> 取消
                </button>
                <button type="button" class="btn btn-primary btn-sm" id="product_style_save">
                    <i class="fa fa-check"></i> 保存
                </button>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="product_material_add" tabindex="-1" role="dialog">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-body">
                <form id="product_material_form" method="post" class="form-horizontal" novalidate onsubmit="return false;">
                    <div class="form-group row">
                        <label class="col-md-3 form-control-label" for="pm_material">
                            <span class="text-danger">*</span> 商品材质
                        </label>
                        <div class="col-md-8">
                            <input type="text" class="form-control" id="pm_material" placeholder="请输入商品风格" name="dictValue"
                                   data-val="true" data-val-required="商品材质不能为空" autocomplete="off"
                                   data-val-length-max="5" data-val-length-min="2" data-val-length="商品材质必须包含 2~5 个字符">
                            <div data-valmsg-for="style" data-valmsg-replace="true"></div>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary btn-sm" data-dismiss="modal">
                    <i class="fa fa-remove"></i> 取消
                </button>
                <button type="button" class="btn btn-primary btn-sm" id="product_material_save">
                    <i class="fa fa-check"></i> 保存
                </button>
            </div>
        </div>
    </div>
</div>