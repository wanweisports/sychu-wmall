requirejs.config({
    baseUrl: 'Content/',
    paths: {
        "jquery"    : 'bower_components/jquery/dist/jquery',
        "tether"    : 'bower_components/tether/dist/js/tether',
        "bootstrap" : 'bower_components/bootstrap/dist/js/bootstrap',
        "pace"      : 'bower_components/pace/pace',

        "alert"     : 'utils/jqueryAlert/alert/alert',

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
        }
    },  // 依赖关系
    waitSeconds: 0,
    urlArgs: '_=' + new Date().getTime()
});

require(['jquery', 'alert', 'override', 'bootstrap', 'base', 'payment'], function ($, jqueryAlert) {
    'use strict';

    $.postJSON = function(url, data, callback) {
        return $.ajax({
            'type' : 'POST',
            'url' : url,
            'contentType' : 'application/json',
            'data' : JSON.stringify(data),
            'dataType' : 'json',
            'success' : callback
        });
    };

    $(".products-list").on("click", ".product-hot-cancel", function () {
        var productId = $(this).parents("tr").attr("data-id");

        if (window.prompt("您确定要取消此商品的热门设定吗？")) {
            $.postJSON('/admin/products/hotSetting', {productId: productId, type: "hot", status: 0}, function (res) {
                if (res.code == 1) {
                    window.location.reload();
                }
                else {
                    alert("取消设定热门失败！")
                }
            });
        }
    });

    $(".products-list").on("click", ".product-users-cancel", function () {
        var productId = $(this).parents("tr").attr("data-id");

        if (window.prompt("您确定要取消此商品的人气设定吗？")) {
            $.postJSON('/admin/products/hotSetting', {productId: productId, type: "users", status: 0}, function (res) {
                if (res.code == 1) {
                    window.location.reload();
                }
                else {
                    alert("取消设定人气失败！")
                }
            });
        }
    });
});
