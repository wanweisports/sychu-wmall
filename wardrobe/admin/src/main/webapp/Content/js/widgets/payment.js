define(["jquery"], function ($) {
    'use strict';

    $.Payment = function (options) {
        // 显示支付弹窗
        this.showConfirm = function (callback) {
            callback = callback || new Function();

            $("#user_payment").modal("show");
            callback();
        };
        // 隐藏支付弹窗
        this.hideConfirm = function (callback) {
            callback = callback || new Function();

            $("#user_payment").modal("show");
            callback();
        };

        options = options || {};
        var __default = {
            cancel: new Function(),
            success: new Function(),
            failure: new Function(),

            orderNo: "",   // 必须
            payType: 0,
            orderAmount: 0,  // 必须
            payAmount: 0
        };

        var opts = $.extend({}, __default, options);
        
        function __init() {
            $("#user_payment [name='orderNo']").val(opts.orderNo);
            $("#user_payment [name='orderAmount']").val(opts.orderAmount);
            $("#user_payment [name='payAmount']").val(opts.orderAmount);
        }

        function __isValid() {
            var orderNo = $("#user_payment [name='orderNo']").val();
            var orderAmount = $("#user_payment [name='orderAmount']").val();
            var payAmount = $("#user_payment [name='payAmount']").val();
            var payType = $("#user_payment [name='payType']").val();

            if (!parseInt(payAmount) || !payType) {
                return false
            }

            return true;
        }

        $("#user_payment .save-payment-done").on("click", function (e) {
            e.preventDefault();

            var $form = $("#user_payment_form");
            var conditions = $form.serialize();

            if ($form.attr("submitting") == "submitting") {
                return false;
            }

            if (!__isValid()) {
                return opts.failure();
            }

            $form.attr("submitting", "submitting");

            $.post('/admin/order/pay', conditions, function (res) {
                $form.attr("submitting", "");

                if (res.code == 1) {
                    opts.success();
                } else {
                    opts.failure();
                }
            });
        });

        $("#user_payment .save-payment-cancel").on("click", function (e) {
            e.preventDefault();

            opts.cancel();
        });

        $("#user_payment .payment-type-list ").on("click", ".payment-type", function (e) {
            e.preventDefault();

            $("#user_payment .payment-type.active").removeClass("active");
            $(this).addClass("active");

            $("#user_payment [name='payType']").val($(this).attr("data-pay"));
        });

        __init();
    };
});