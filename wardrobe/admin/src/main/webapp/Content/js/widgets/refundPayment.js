define(["jquery"], function ($) {
    'use strict';

    $.RefundPayment = function (options) {
        // 显示支付弹窗
        this.showConfirm = function (callback) {
            callback = callback || new Function();

            $("#user_refund_payment").modal("show");
            callback();
        };
        // 隐藏支付弹窗
        this.hideConfirm = function (callback) {
            callback = callback || new Function();

            $("#user_refund_payment").modal("show");
            callback();
        };

        options = options || {};
        var __default = {
            cancel: new Function(),
            success: new Function(),
            failure: new Function(),

            orderNo: "",   // 必须
            refundType: 0,
            payAmount: 0,  // 必须
            refundAmount: 0
        };

        var opts = $.extend({}, __default, options);
        
        function __init() {
            $("#user_refund_payment [name='orderNo']").val(opts.orderNo);
            $("#user_refund_payment [name='refundAmount']").val(opts.payAmount);
            $("#user_refund_payment [name='payAmount']").val(opts.payAmount);
        }

        function __isValid() {
            var orderNo = $("#user_refund_payment [name='orderNo']").val();
            var refundAmount = $("#user_refund_payment [name='refundAmount']").val();
            var payAmount = $("#user_refund_payment [name='payAmount']").val();
            var refundType = $("#user_refund_payment [name='refundType']").val();

            if (!parseInt(refundAmount) || !refundType) {
                return false
            }

            return true;
        }

        $("#user_refund_payment .save-refund-payment-done").on("click", function (e) {
            e.preventDefault();

            var $form = $("#user_refund_payment_form");
            var conditions = $form.serialize();

            if ($form.attr("submitting") == "submitting") {
                return false;
            }

            if (!__isValid()) {
                return opts.failure();
            }

            $form.attr("submitting", "submitting");

            $.post('/admin/order/refund', conditions, function (res) {
                $form.attr("submitting", "");

                if (res.code == 1) {
                    opts.success();
                } else {
                    opts.failure();
                }
            });
        });

        $("#user_refund_payment .save-payment-cancel").on("click", function (e) {
            e.preventDefault();

            opts.cancel();
        });

        $("#user_refund_payment .refund-payment-type-list ").on("click", ".refund-payment-type", function (e) {
            e.preventDefault();

            $("#user_refund_payment .refund-payment-type.active").removeClass("active");
            $(this).addClass("active");

            $("#user_refund_payment [name='refundType']").val($(this).attr("data-pay"));
        });

        __init();
    };
});