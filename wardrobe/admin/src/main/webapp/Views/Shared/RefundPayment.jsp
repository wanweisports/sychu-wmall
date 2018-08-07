<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>

<style type="text/css">
    .icon-payment {
        display: block;
    }
    .refund-payment-money {
        border: 1px solid #f8cb00;
        cursor: pointer;
    }
    .refund-payment-alipay {
        border: 1px solid #5b9edd;
        cursor: pointer;
    }
    .refund-payment-alipay .icon-alipay {
        background: url("/Content/images/payment/icon-alipay.png") no-repeat center;
        background-size: 2.0rem;
        background-color: #5b9edd !important;
    }
    .refund-payment-alipay .h6 {
        color: #5b9edd !important;
    }
    .refund-payment-weixin {
        border: 1px solid #00c800;
        cursor: pointer;
    }
    .refund-payment-weixin .icon-weixin {
        background: url("/Content/images/payment/icon-weixin.png") no-repeat center;
        background-size: 2.0rem;
        border-right: 1px solid #00c800;
    }
    .refund-payment-weixin .h6 {
        color: #00c800 !important;
    }
    .refund-payment-card {
        border: 1px solid #20a8d8;
        cursor: pointer;
    }
    .refund-payment-money:hover, .refund-payment-money:focus, .refund-payment-money.active,
    .refund-payment-alipay:hover, .refund-payment-alipay:focus, .refund-payment-alipay.active,
    .refund-payment-weixin:hover, .refund-payment-weixin:focus, .refund-payment-weixin.active,
    .refund-payment-card:hover, .refund-payment-card:focus, .refund-payment-card.active {
        border-color: #f86c6b;
        box-shadow: 0 0 0 2px rgba(248, 108, 107, 0.5);
    }
</style>

<div class="modal fade" id="user_refund_payment" tabindex="-1" role="dialog">
    <div class="modal-dialog modal-primary" role="document">
        <div class="modal-content">
            <div class="modal-body">
                <p class="help-block">请确认支付金额，并选择相应的方式退款。</p>
                <form id="user_refund_payment_form" method="post" class="form-horizontal" novalidate onsubmit="return false;">
                    <input type="hidden" id="order_id" name="orderId">
                    <div class="refund-payment-type-list row">
                        <div class="col-md-4">
                            <div class="card refund-payment-type refund-payment-money" data-pay="1">
                                <div class="card-body p-0 clearfix">
                                    <i class="fa fa-money bg-warning p-2 font-4xl mr-3 float-left"></i>
                                    <div class="h6 text-warning mb-0 pt-2">现 金</div>
                                    <div class="text-muted text-uppercase font-weight-bold font-xs">Cash</div>
                                </div>
                            </div>
                        </div>
                        <div class="col-md-4">
                            <div class="card refund-payment-type refund-payment-weixin" data-pay="2">
                                <div class="card-body p-0 clearfix">
                                    <i class="icon-payment icon-weixin p-4 mr-3 float-left"></i>
                                    <div class="h6 text-primary mb-0 pt-2">微 信</div>
                                    <div class="text-muted text-uppercase font-weight-bold font-xs">Weixin</div>
                                </div>
                            </div>
                        </div>
                        <div class="col-md-4">
                            <div class="card refund-payment-type refund-payment-alipay" data-pay="3">
                                <div class="card-body p-0 clearfix">
                                    <i class="icon-payment icon-alipay p-4 mr-3 float-left"></i>
                                    <div class="h6 mb-0 pt-2">支付宝</div>
                                    <div class="text-muted text-uppercase font-weight-bold font-xs">Alipay</div>
                                </div>
                            </div>
                        </div>
                        <div class="col-md-4">
                            <div class="card refund-payment-type refund-payment-card" data-pay="4">
                                <div class="card-body p-0 clearfix">
                                    <i class="fa fa-credit-card bg-primary p-2 font-4xl mr-3 float-left"></i>
                                    <div class="h6 text-primary mb-0 pt-2">银 联</div>
                                    <div class="text-muted text-uppercase font-weight-bold font-xs">Bank</div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-4">
                            <label>支付额度：</label>
                            <input type="hidden" name="orderNo" class="form-control">
                            <input type="hidden" name="refundType" class="form-control">
                            <input type="text" name="payAmount" class="form-control" disabled title="支付额度">
                        </div>
                        <div class="col-md-8">
                            <label>退款额度：</label>
                            <input type="text" name="refundAmount" class="form-control" placeholder="请输入退款额度..." title="退款额度">
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-sm btn-secondary save-refund-payment-cancel" data-dismiss="modal">
                    <i class="fa fa-close"></i> 取 消
                </button>
                <button type="button" class="btn btn-sm btn-primary save-refund-payment-done">
                    <i class="fa fa-check"></i> 退 款
                </button>
            </div>
        </div>
    </div>
</div>
