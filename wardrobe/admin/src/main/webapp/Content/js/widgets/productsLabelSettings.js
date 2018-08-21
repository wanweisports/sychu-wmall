define(["jquery"], function ($) {
    'use strict';

    $.ProductsLabelSettings = function (options) {
        // 显示
        this.show = function (callback) {
            callback = callback || new Function();

            opts.wrapperDiv.modal("show");
            callback();
        };

        // 隐藏
        this.hide = function (callback) {
            callback = callback || new Function();

            opts.wrapperDiv.modal("hide");
            callback();
        };

        // 查询
        this.query = function (conditions, callback) {
            callback = callback || new Function();

            $.getJSON('/admin/products/' + opts.type + '/query', conditions, function (res) {
                if (res.code == 1) {
                    callback(true, res.data);
                } else {
                    callback(false);
                }
            });
        };

        // 删除
        this.delete = function (conditions, callback) {
            callback = callback || new Function();

            $.post('/admin/products/' + opts.type + '/delete', conditions, function (res) {
                if (res.code == 1) {
                    callback(true);
                } else {
                    callback(false);
                }
            });
        };

        // 保存
        this.save = function (conditions, callback) {
            callback = callback || new Function();

            $.post('/admin/products/' + opts.type + '/save', conditions, function (res) {
                if (res.code == 1) {
                    callback(true);
                } else {
                    callback(false);
                }
            });
        };

        options = options || {};
        var __default = {
            cancel: new Function(),
            success: new Function(),
            failure: new Function(),

            type: ""   // 必须
        };

        options.type = (["COMM_CATEGORY", "COMM_STYLE", "COMM_MATERIAL"].indexOf(options.type) > -1) ? options.type : "COMM_CATEGORY";

        var opts = $.extend({}, __default, options);
        
        function __init() {
            if (opts.type === "COMM_CATEGORY") {
                opts.wrapperDiv = $("#product_category_add");
                opts.wrapperForm = $("#product_category_form");
                opts.confirmInput = $("#pc_category");
                opts.confirmBtn = $("#product_category_save");
            }
            else if (opts.type === "COMM_STYLE") {
                opts.wrapperDiv = $("#product_style_add");
                opts.wrapperForm = $("#product_style_form");
                opts.confirmInput = $("#ps_style");
                opts.confirmBtn = $("#product_style_save");
            }
            else if (opts.type === "COMM_MATERIAL") {
                opts.wrapperDiv = $("#product_material_add");
                opts.wrapperForm = $("#product_material_form");
                opts.confirmInput = $("#pm_material");
                opts.confirmBtn = $("#product_material_save");
            }
        }

        if(opts.confirmBtn) {
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

                    if (res.code == 1) {
                        opts.success();
                    } else {
                        opts.failure();
                    }
                });
            });
        }

        __init();
    };
});