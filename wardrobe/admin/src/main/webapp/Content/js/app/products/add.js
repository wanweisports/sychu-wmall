requirejs.config({
    baseUrl: 'Content/',
    paths: {
        "jquery"    : 'bower_components/jquery/dist/jquery',
        "tether"    : 'bower_components/tether/dist/js/tether',
        "bootstrap" : 'bower_components/bootstrap/dist/js/bootstrap',
        "pace"      : 'bower_components/pace/pace',

        "alert"     : 'utils/jqueryAlert/alert/alert',

        "jquery.validate"              : 'bower_components/jquery.validation/dist/jquery.validate',
        "jquery.validate.unobtrusive"  : 'bower_components/Microsoft.jQuery.Unobtrusive.Validation/jquery.validate.unobtrusive',

        "base"      : 'js/widgets/base',
        "override"  : 'js/widgets/override',
        "fromToJson" : 'lib/formToJson',
        "productsLabelSettings" : 'js/widgets/productsLabelSettings'
    },
    shim: {
        "bootstrap": {
            deps: ["jquery", "override"]
        },
        "alert": {
            deps: ["jquery"]
        },
        "fromToJson": {
            deps: ["jquery"]
        },
        "jquery.validate": {
            deps: ["jquery", "override"]
        },
        "jquery.validate.unobtrusive": {
            deps: ["jquery", "jquery.validate"]
        }
    },  // 依赖关系
    waitSeconds: 0,
    urlArgs: '_=' + new Date().getTime()
});

require(['jquery', 'alert', 'override', 'bootstrap', 'base', 'fromToJson', 'jquery.validate', 'jquery.validate.unobtrusive', 'productsLabelSettings'], function ($, jqueryAlert) {
    'use strict';

    /* load *************************************************/
    var $categorySelect = $("#p_categorySelect");
    var $categorySelectedList = $(".category-list");
    var $categoryAdd = $(".category-add");
    var $categorySelected = $("#p_category");

    var $styleSelectList = $(".style-list");
    var $styleAdd = $(".style-add");
    var $styleSelected = $("#p_style");

    var $materialSelectList = $(".material-list");
    var $materialAdd = $(".material-add");
    var $materialSelected = $("#p_material");

    var $sizeList = $(".size-list");

    function __setProductSettingsList(type) {
        $.getJSON("/admin/products/getProductSettingsList", {}, function (res) {
            var data = res.data;

            if (res.code == 1) {
                var i;

                if (type === "COMM_CATEGORY") {
                    var categoryOptHtml = '';
                    var categoryData = data.COMM_CATEGORY;
                    for (i = 0; i < categoryData.length; i++) {
                        categoryOptHtml += '<option value="' + categoryData[i].dictId + '">' + categoryData[i].dictValue + '</option>'
                    }
                    $categorySelect.html(categoryOptHtml);
                    $categoryAdd.before('<button type="button" class="btn btn-success btn-close category-item mr-2" data-id="' + categoryData[categoryData.length - 1].dictId + '">' +
                        categoryData[categoryData.length - 1].dictValue + '<i class="fa fa-remove"></i></button>');
                }

                if (type === "COMM_STYLE") {
                    var styleData = data.COMM_STYLE;
                    $styleAdd.before('<button type="button" class="btn btn-success style-item mr-2" data-id="' + styleData[styleData.length - 1].dictId + '">' +
                        styleData[styleData.length - 1].dictValue + '</button>');
                }

                if (type === "COMM_MATERIAL") {
                    var materialData = data.COMM_MATERIAL;
                    $materialAdd.before('<button type="button" class="btn btn-success material-item mr-2" data-id="' + materialData[materialData.length - 1].dictId + '">' +
                        materialData[materialData.length - 1].dictValue + '</button>');
                }
            }
        });
    }

    /* category *************************************************/
    var $category = new $.ProductsLabelSettings({
        type: "COMM_CATEGORY",
        load: function (opts) {
            opts.confirmBtn.on("click", function (e) {
                e.preventDefault();

                var $form = opts.wrapperForm;
                var conditions = $form.serialize();

                if ($form.attr("submitting") == "submitting") {
                    return false;
                }

                $form.attr("submitting", "submitting");

                $.post('/admin/products/' + opts.type + '/save', conditions, function (res) {
                    $form.attr("submitting", "");
                    $category.hide();

                    if (res.code == 1) {
                        __setProductSettingsList("COMM_CATEGORY");
                        __setCategorySelected();
                    } else {
                        jqueryAlert({
                            'icon'      : '/Content/images/icon-error.png',
                            'content'   : "商品品类保存失败, 请稍后重试",
                            'closeTime' : 2000,
                            'modal'        : true,
                            'isModalClose' : true
                        });
                    }
                });
            });
        }
    });

    function __setCategorySelected() {
        var selected = $categorySelectedList.find(".category-item");

        var values = [];
        for (var i = 0; i < selected.length; i++) {
            values.push(selected.eq(i).attr("data-id"));
        }

        $categorySelected.val("," + values.join(",") + ",");
    }

    $categorySelect.on("change", function (e) {
        e.preventDefault();

        var $this = $(this);
        if(!$this.val()) return false;

        var exist = $categorySelectedList.find(".category-item[data-id='" + $this.val() + "']");
        if (exist && exist.length > 0) {
            return false;
        }
        else {
            $categoryAdd.before('<button type="button" class="btn btn-success btn-close category-item" data-id="' + $this.val() + '">' +
                $this.find("option:selected").text() + '<i class="fa fa-remove"></i></button>');
            __setCategorySelected();
        }
    });

    $categorySelectedList.on("click", ".category-item .fa-remove", function (e) {
        e.preventDefault();

        var $this = $(this);
        $this.parent().remove();
        __setCategorySelected();
    });

    /* style *************************************************/
    var $style = new $.ProductsLabelSettings({
        type: "COMM_STYLE",
        load: function (opts) {
            opts.confirmBtn.on("click", function (e) {
                e.preventDefault();

                var $form = opts.wrapperForm;
                var conditions = $form.serialize();

                if ($form.attr("submitting") == "submitting") {
                    return false;
                }

                $form.attr("submitting", "submitting");

                $.post('/admin/products/' + opts.type + '/save', conditions, function (res) {
                    $form.attr("submitting", "");
                    $style.hide();

                    if (res.code == 1) {
                        __setProductSettingsList("COMM_STYLE");
                        __setStyleSelected();
                    } else {
                        jqueryAlert({
                            'icon'      : '/Content/images/icon-error.png',
                            'content'   : "商品风格保存失败, 请稍后重试",
                            'closeTime' : 2000,
                            'modal'        : true,
                            'isModalClose' : true
                        });
                    }
                });
            });
        }
    });

    function __setStyleSelected() {
        var selected = $styleSelectList.find(".style-item.btn-success");

        var values = [];
        for (var i = 0; i < selected.length; i++) {
            values.push(selected.eq(i).attr("data-id"));
        }

        $styleSelected.val("," + values.join(",") + ",");
    }

    $styleSelectList.on("click", ".style-item", function (e) {
        e.preventDefault();

        var $this = $(this);

        if ($this.hasClass("btn-success")) {
            $this.removeClass("btn-success").addClass("btn-secondary");
        }
        else {
            $this.removeClass("btn-secondary").addClass("btn-success");
        }
        __setStyleSelected();
    });

    /* material *************************************************/
    var $material = new $.ProductsLabelSettings({
        type: "COMM_MATERIAL",
        load: function (opts) {
            opts.confirmBtn.on("click", function (e) {
                e.preventDefault();

                var $form = opts.wrapperForm;
                var conditions = $form.serialize();

                if ($form.attr("submitting") == "submitting") {
                    return false;
                }

                $form.attr("submitting", "submitting");

                $.post('/admin/products/' + opts.type + '/save', conditions, function (res) {
                    $form.attr("submitting", "");
                    $material.hide();

                    if (res.code == 1) {
                        __setProductSettingsList("COMM_MATERIAL");
                        __setMaterialSelected();
                    } else {
                        jqueryAlert({
                            'icon'      : '/Content/images/icon-error.png',
                            'content'   : "商品材质保存失败, 请稍后重试",
                            'closeTime' : 2000,
                            'modal'        : true,
                            'isModalClose' : true
                        });
                    }
                });
            });
        }
    });

    function __setMaterialSelected() {
        var selected = $materialSelectList.find(".material-item.btn-success");

        var values = [];
        for (var i = 0; i < selected.length; i++) {
            values.push(selected.eq(i).attr("data-id"));
        }

        $materialSelected.val("," + values.join(",") + ",");
    }

    $materialSelectList.on("click", ".material-item", function (e) {
        e.preventDefault();

        var $this = $(this);

        if ($this.hasClass("btn-success")) {
            $this.removeClass("btn-success").addClass("btn-secondary");
        }
        else {
            $this.removeClass("btn-secondary").addClass("btn-success");
        }
        __setMaterialSelected();
    });

    /* size *************************************************/
    function __setSizeList() {
        var $items = $sizeList.find(".size-item");
        var sizeList = [], stockList = [];

        for (var i = 0; i < $items.length; i++) {
            var size = $items.eq(i).find("[name='initSize']").val();
            var stock = $items.eq(i).find("[name='initStock']").val();
            if (!stock) {
                continue;
            }

            sizeList.push(size);
            stockList.push(stock);
        }

        $("#p_size").val(sizeList.join(","));
        $("#p_stock").val(stockList.join(","));
    }

    $sizeList.on("click", ".size-add", function (e) {
        e.preventDefault();

        var $item = $(this).parents(".size-item");
        var $clone = $item.clone();

        $item.find(".size-add").hide();

        $sizeList.append($clone);
    });

    $sizeList.on("click", ".size-remove", function (e) {
        e.preventDefault();

        if ($sizeList.find(".size-item").length <= 1) {
            jqueryAlert({
                'icon'      : '/Content/images/icon-error.png',
                'content'   : "已经是最后一个尺码了",
                'closeTime' : 2000,
                'modal'        : true,
                'isModalClose' : true
            });
            return false;
        }

        var $item = $(this).parents(".size-item");
        $item.remove();

        $sizeList.find(".size-item:last-child .size-add").show();
    });

    /* img *************************************************/
    var $fileImages = $(".product-image-file");
    $fileImages.on("change", function (e) {
        e.preventDefault();

        var $this = $(this);
        var file = $this[0].files[0];
        var fileReader = new FileReader();
        fileReader.onloadend = function () {
            if (fileReader.readyState == fileReader.DONE) {
                $this.prevAll(".product-image-show").attr('src', fileReader.result);
                $this.prevAll(".product-image-remove").show();
            }
        };
        fileReader.readAsDataURL(file);
    });

    var $fileImagesRemove = $(".product-image-remove");
    $fileImagesRemove.on("click", function (e) {
        e.preventDefault();

        var $this = $(this);
        $this.hide();
        $this.nextAll(".product-image-show").attr('src', "/Content/images/upload.png");
        $this.nextAll(".product-image-file").val("");
    });

    /* detail img *************************************************/
    var $fileDetailImages = $(".product-detail-file");
    $fileDetailImages.on("change", function (e) {
        e.preventDefault();

        var $this = $(this);
        var file = $this[0].files[0];
        var fileReader = new FileReader();
        fileReader.onloadend = function () {
            if (fileReader.readyState == fileReader.DONE) {
                $this.prevAll(".product-detail-show").attr('src', fileReader.result);
                $this.prevAll(".product-detail-remove").show();
            }
        };
        fileReader.readAsDataURL(file);
    });

    var $fileDetailImagesRemove = $(".product-detail-remove");
    $fileDetailImagesRemove.on("click", function (e) {
        e.preventDefault();

        var $this = $(this);
        $this.hide();
        $this.nextAll(".product-detail-show").attr('src', "/Content/images/upload.png");
        $this.nextAll(".product-detail-file").val("");
    });

    /* save *************************************************/
    // 表单校验配置
    $(document).ready(function () {
        $('#product_form').validate({
            ignore: ":hidden"
        });
    });

    $("#product_form_result").on("load", function () {
        var doc = $(this)[0].contentWindow.document;
        var html = doc.body.textContent;
        try{
            var result = JSON.parse(html);
            // 识别登录结果
            if (result.code == 1) {
                jqueryAlert({
                    'icon'      : '/Content/images/icon-ok.png',
                    'content'   : "保存商品成功",
                    'closeTime' : 2000,
                    'modal'        : true,
                    'isModalClose' : true
                });

                setTimeout(function () {
                    window.location.reload();
                }, 1500);
            }
            else {
                jqueryAlert({
                    'icon'      : '/Content/images/icon-error.png',
                    'content'   : "保存商品失败, 请稍后重试",
                    'closeTime' : 2000,
                    'modal'        : true,
                    'isModalClose' : true
                });
                $(".save-products").prop("disabled", false).html('<i class="fa fa-check"></i> 保 存');
            }
        } catch(er) {
            jqueryAlert({
                'icon'      : '/Content/images/icon-error.png',
                'content'   : "保存商品失败, 请稍后重试",
                'closeTime' : 2000,
                'modal'        : true,
                'isModalClose' : true
            });
            $(".save-products").prop("disabled", false).html('<i class="fa fa-check"></i> 保 存');
        }
    });

    $(".save-products").on("click", function (e) {
        e.preventDefault();

        __setSizeList();

        var $form = $("#product_form");
        var conditions = $form.serializeArray();

        console.log(conditions);

        if ($form.attr("submitting") == "submitting" || !$form.valid()) {
            return false;
        }
        $form.attr("submitting", "submitting");
        $(this).prop("disabled", true).html('<i class="fa fa-check"></i> 正在上传...');

        $("#product_form").submit();
        $form.attr("submitting", "");
        return false;
    });
});
