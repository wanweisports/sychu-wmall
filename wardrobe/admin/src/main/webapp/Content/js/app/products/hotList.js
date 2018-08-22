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

    $(".products-list").on("click", ".product-hot-cancel", function () {
        var productId = $(this).attr("data-id");

        if (window.confirm("您确定要取消此商品的热门设定吗？")) {
            $.post('/commodity/updateHot', {cid: productId, hot: 2}, function (res) {
                if (res.code == 1) {
                    window.location.reload();
                }
                else {
                    jqueryAlert({
                        'icon'      : '/Content/images/icon-error.png',
                        'content'   : "取消设定热门失败, 请稍后重试",
                        'closeTime' : 2000,
                        'modal'        : true,
                        'isModalClose' : true
                    });
                }
            });
        }
    });

    $(".products-list").on("click", ".product-users-cancel", function () {
        var productId = $(this).attr("data-id");

        if (window.confirm("您确定要取消此商品的人气设定吗？")) {
            $.post('/commodity/updateNewly', {cid: productId, newly: 2}, function (res) {
                if (res.code == 1) {
                    window.location.reload();
                }
                else {
                    jqueryAlert({
                        'icon'      : '/Content/images/icon-error.png',
                        'content'   : "取消设定人气失败, 请稍后重试",
                        'closeTime' : 2000,
                        'modal'        : true,
                        'isModalClose' : true
                    });
                }
            });
        }
    });
});
