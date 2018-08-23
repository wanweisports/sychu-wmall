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
        "payment"   : 'js/widgets/payment'
    },
    shim: {
        "bootstrap": {
            deps: ["jquery", "override"]
        },
        "alert": {
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

require(['jquery', 'alert', 'override', 'bootstrap', 'base', 'jquery.validate', 'jquery.validate.unobtrusive'], function ($, jqueryAlert) {
    'use strict';

    // 表单校验配置
    $(document).ready(function () {
        $('#product_size_form, #product_sku_form').validate({
            ignore: ":hidden"
        });
    });

    // 设置下架
    $(".js-status-down").on("click", function (e) {
        e.preventDefault();

        var $this = $(this);

        if ($this.attr("working") == "working") {
            return false;
        }
        $this.attr("working", "working");

        var cid = $(this).data('id');

        $.post("/commodity/updateStatus", {cid: cid, status: 2}, function (res) {
            $this.attr("working", "");

            if (res.code == 1) {
                window.location.reload();
            } else {
                jqueryAlert({
                    'icon'      : '/Content/images/icon-error.png',
                    'content'   : "商品下架失败, 请稍后重试",
                    'closeTime' : 2000,
                    'modal'        : true,
                    'isModalClose' : true
                });
            }
        });
    });

    // 设置上架
    $(".js-status-top").on("click", function (e) {
        e.preventDefault();

        var $this = $(this);

        if ($this.attr("working") == "working") {
            return false;
        }
        $this.attr("working", "working");

        var cid = $(this).data('id');

        $.post("/commodity/updateStatus", {cid: cid, status: 1}, function (res) {
            $this.attr("working", "");

            if (res.code == 1) {
                window.location.reload();
            } else {
                jqueryAlert({
                    'icon'      : '/Content/images/icon-error.png',
                    'content'   : "商品上架失败, 请稍后重试",
                    'closeTime' : 2000,
                    'modal'        : true,
                    'isModalClose' : true
                });
            }
        });
    });

    // 取消最热
    $(".js-hot-down").on("click", function (e) {
        e.preventDefault();

        var $this = $(this);

        if ($this.attr("working") == "working") {
            return false;
        }
        $this.attr("working", "working");

        var cid = $(this).data('id');

        $.post("/commodity/updateHot", {cid: cid, hot: 2}, function (res) {
            $this.attr("working", "");

            if (res.code == 1) {
                window.location.reload();
            } else {
                jqueryAlert({
                    'icon'      : '/Content/images/icon-error.png',
                    'content'   : "取消最热商品失败, 请稍后重试",
                    'closeTime' : 2000,
                    'modal'        : true,
                    'isModalClose' : true
                });
            }
        });
    });

    // 设置最热
    $(".js-hot-top").on("click", function (e) {
        e.preventDefault();

        var $this = $(this);

        if ($this.attr("working") == "working") {
            return false;
        }
        $this.attr("working", "working");

        var cid = $(this).data('id');

        $.post("/commodity/updateHot", {cid: cid, hot: 1}, function (res) {
            $this.attr("working", "");

            if (res.code == 1) {
                window.location.reload();
            } else {
                jqueryAlert({
                    'icon'      : '/Content/images/icon-error.png',
                    'content'   : "设置最热商品失败, 请稍后重试",
                    'closeTime' : 2000,
                    'modal'        : true,
                    'isModalClose' : true
                });
            }
        });
    });

    // 取消最新
    $(".js-newly-down").on("click", function (e) {
        e.preventDefault();

        var $this = $(this);

        if ($this.attr("working") == "working") {
            return false;
        }
        $this.attr("working", "working");

        var cid = $(this).data('id');

        $.post("/commodity/updateNewly", {cid: cid, newly: 2}, function (res) {
            $this.attr("working", "");

            if (res.code == 1) {
                window.location.reload();
            } else {
                jqueryAlert({
                    'icon'      : '/Content/images/icon-error.png',
                    'content'   : "取消最新商品失败, 请稍后重试",
                    'closeTime' : 2000,
                    'modal'        : true,
                    'isModalClose' : true
                });
            }
        });
    });

    // 设置最新
    $(".js-newly-top").on("click", function (e) {
        e.preventDefault();

        var $this = $(this);

        if ($this.attr("working") == "working") {
            return false;
        }
        $this.attr("working", "working");

        var cid = $this.data('id');

        $.post("/commodity/updateNewly", {cid: cid, newly: 1}, function (res) {
            $this.attr("working", "");

            if (res.code == 1) {
                window.location.reload();
            } else {
                jqueryAlert({
                    'icon'      : '/Content/images/icon-error.png',
                    'content'   : "设置最新商品失败, 请稍后重试",
                    'closeTime' : 2000,
                    'modal'        : true,
                    'isModalClose' : true
                });
            }
        });
    });

    // 增加库存
    $(".size-list").on("click", ".sku-add", function () {
        var $this = $(this);
        var sid = $this.parents("tr").attr('data-id');

        $("#product_sku_tip").html('增加商品库存吗？');
        $("#product_sku_sid").val(sid);
        $("#product_sku_type").val('10');
    });

    // 减少库存
    $(".size-list").on("click", ".sku-minus", function () {
        var $this = $(this);
        var sid = $this.parents("tr").attr('data-id');

        $("#product_sku_tip").html('减少商品库存吗？');
        $("#product_sku_sid").val(sid);
        $("#product_sku_type").val('20');
    });

    // 保存库存
    $("#product_sku_save").on("click", function (e) {
        e.preventDefault();

        var $form = $("#product_sku_form");
        var conditions = $form.serialize();

        if ($form.attr("working") == "working" || !$form.valid()) {
            return false;
        }
        $form.attr("working", "working");

        $.post("/admin/products/saveStock", conditions, function (res) {
            $form.attr("working", "");

            if (res.code == 1) {
                window.location.reload();
            } else {
                jqueryAlert({
                    'icon'      : '/Content/images/icon-error.png',
                    'content'   : "保存商品库存失败, 请稍后重试",
                    'closeTime' : 2000,
                    'modal'        : true,
                    'isModalClose' : true
                });
            }
        });
    });

    // 删除尺码
    $(".size-list").on("click", ".sku-delete", function (e) {
        e.preventDefault();

        var $this = $(this);

        var $dialog = jqueryAlert({
            'title'   : '提示',
            'content' : '您确定要删除此尺码？',
            'modal'   : true,
            'buttons' :{
                '确定' : function () {
                    $dialog.close();

                    if ($this.attr("working") == "working") {
                        return false;
                    }
                    $this.attr("working", "working");

                    $.post("/commodity/delSize", {sid: $this.parents("tr").attr("data-id")}, function (res) {
                        $this.attr("working", "");

                        if (res.code == 1) {
                            window.location.reload();
                        } else {
                            jqueryAlert({
                                'icon'      : '/Content/images/icon-error.png',
                                'content'   : "商品尺码删除失败, 请稍后重试",
                                'closeTime' : 2000,
                                'modal'        : true,
                                'isModalClose' : true
                            });
                        }
                    });
                },
                '取消' : function(){
                    $dialog.close();
                }
            }
        });
    });

    // 保存尺码
    $("#product_size_save").on("click", function (e) {
        e.preventDefault();

        var $form = $("#product_size_form");
        var conditions = $form.serialize();

        if ($form.attr("working") == "working" || !$form.valid()) {
            return false;
        }
        $form.attr("working", "working");

        $.post("/admin/products/saveEdit", conditions, function (res) {
            $form.attr("working", "");

            if (res.code == 1) {
                window.location.reload();
            } else {
                jqueryAlert({
                    'icon'      : '/Content/images/icon-error.png',
                    'content'   : "保存商品尺码失败, 请稍后重试",
                    'closeTime' : 2000,
                    'modal'        : true,
                    'isModalClose' : true
                });
            }
        });
    });

});
