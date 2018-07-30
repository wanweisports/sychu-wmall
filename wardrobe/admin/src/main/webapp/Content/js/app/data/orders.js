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
        "payment"   : 'js/widgets/payment',
        "refund"   : 'js/widgets/refundPayment'
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

require(['jquery', 'alert', 'override', 'bootstrap', 'base', 'payment', 'refund'], function ($, jqueryAlert) {
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

    // 去支付
    $(".goto-payment").on("click", function (e) {
        e.preventDefault();

        var orderNo = $(this).parents("tr").attr("data-no");
        var orderAmount = $(this).parents("tr").attr("data-amount");

        var payment = new $.Payment({
            orderNo: orderNo,
            orderAmount: orderAmount,
            failure: function () {
                jqueryAlert({
                    'icon'      : '/Content/images/icon-error.png',
                    'content'   : "学生分班支付失败, 请稍后重试",
                    'closeTime' : 2000,
                    'modal'        : true,
                    'isModalClose' : true
                });
            },
            success: function () {
                jqueryAlert({
                    'icon'      : '/Content/images/icon-ok.png',
                    'content'   : "学员分班支付成功",
                    'closeTime' : 2000,
                    'modal'        : true,
                    'isModalClose' : true
                });

                window.setTimeout(function () {
                    window.location.reload();
                }, 1500);
            }
        });

        payment.showConfirm();
    });

    // 取消
    $(".cancel-payment").on("click", function (e) {
        e.preventDefault();

        var orderNo = $(this).parents("tr").attr("data-no");

        var $alert = jqueryAlert({
            'title' : "提示",
            'content' : "您确定要取消此订单吗？",
            'modal'   : true,
            'contentTextAlign' : 'center',
            'buttons' :{
                '关闭' : function () {
                    $alert.close();
                },
                '确定': function () {
                    $.postJSON("/admin/order/cancel", {orderNo: orderNo}, function (res) {
                        $alert.close();

                        if (res.code == 1) {
                            window.location.reload();
                        } else {
                            jqueryAlert({
                                'icon'      : '/Content/images/icon-error.png',
                                'content'   : "取消订单失败，请稍后重试",
                                'closeTime' : 2000,
                                'modal'        : true,
                                'isModalClose' : true
                            });
                        }
                    });
                }
            }
        })
    });

    // 退款
    $(".back-payment").on("click", function (e) {
        e.preventDefault();

        var orderNo = $(this).parents("tr").attr("data-no");
        var payAmount = $(this).parents("tr").attr("data-pay-amount");

        var refund = new $.RefundPayment({
            orderNo: orderNo,
            payAmount: payAmount,
            failure: function () {
                jqueryAlert({
                    'icon'      : '/Content/images/icon-error.png',
                    'content'   : "订单退款失败, 请稍后重试",
                    'closeTime' : 2000,
                    'modal'        : true,
                    'isModalClose' : true
                });
            },
            success: function () {
                jqueryAlert({
                    'icon'      : '/Content/images/icon-ok.png',
                    'content'   : "订单退款成功",
                    'closeTime' : 2000,
                    'modal'        : true,
                    'isModalClose' : true
                });

                window.setTimeout(function () {
                    window.location.reload();
                }, 1500);
            }
        });

        refund.showConfirm();
    });
});
