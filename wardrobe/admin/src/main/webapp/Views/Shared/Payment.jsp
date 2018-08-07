<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>

<style type="text/css">
    .icon-payment {
        display: block;
    }
    .payment-money {
        border: 1px solid #f8cb00;
        cursor: pointer;
    }
    .payment-alipay {
        border: 1px solid #5b9edd;
        cursor: pointer;
    }
    .payment-alipay .icon-alipay {
        background: url("/Content/images/payment/icon-alipay.png") no-repeat center;
        background-size: 2.0rem;
        background-color: #5b9edd !important;
    }
    .payment-alipay .h6 {
        color: #5b9edd !important;
    }
    .payment-weixin {
        border: 1px solid #00c800;
        cursor: pointer;
    }
    .payment-weixin .icon-weixin {
        background: url("/Content/images/payment/icon-weixin.png") no-repeat center;
        background-size: 2.0rem;
        border-right: 1px solid #00c800;
    }
    .payment-weixin .h6 {
        color: #00c800 !important;
    }
    .payment-card {
        border: 1px solid #20a8d8;
        cursor: pointer;
    }
    .payment-money:hover, .payment-money:focus, .payment-money.active,
    .payment-alipay:hover, .payment-alipay:focus, .payment-alipay.active,
    .payment-weixin:hover, .payment-weixin:focus, .payment-weixin.active,
    .payment-card:hover, .payment-card:focus, .payment-card.active {
        border-color: #f86c6b;
        box-shadow: 0 0 0 2px rgba(248, 108, 107, 0.5);
    }
</style>

<div class="modal fade" id="user_payment" tabindex="-1" role="dialog">
    <div class="modal-dialog modal-primary" role="document">
        <div class="modal-content">
            <div class="modal-body">
                <p class="help-block">请确认订单金额，并选择相应的方式支付。</p>
                <form id="user_payment_form" method="post" class="form-horizontal" novalidate onsubmit="return false;">
                    <input type="hidden" id="order_id" name="orderId">
                    <div class="payment-type-list row">
                        <div class="col-md-4">
                            <div class="card payment-type payment-money" data-pay="1">
                                <div class="card-body p-0 clearfix">
                                    <i class="fa fa-money bg-warning p-2 font-4xl mr-3 float-left"></i>
                                    <div class="h6 text-warning mb-0 pt-2">现 金</div>
                                    <div class="text-muted text-uppercase font-weight-bold font-xs">Cash</div>
                                </div>
                            </div>
                        </div>
                        <div class="col-md-4">
                            <div class="card payment-type payment-weixin" data-pay="2">
                                <div class="card-body p-0 clearfix">
                                    <i class="icon-payment icon-weixin p-4 mr-3 float-left"></i>
                                    <div class="h6 text-primary mb-0 pt-2">微 信</div>
                                    <div class="text-muted text-uppercase font-weight-bold font-xs">Weixin</div>
                                </div>
                            </div>
                        </div>
                        <div class="col-md-4">
                            <div class="card payment-type payment-alipay" data-pay="3">
                                <div class="card-body p-0 clearfix">
                                    <i class="icon-payment icon-alipay p-4 mr-3 float-left"></i>
                                    <div class="h6 mb-0 pt-2">支付宝</div>
                                    <div class="text-muted text-uppercase font-weight-bold font-xs">Alipay</div>
                                </div>
                            </div>
                        </div>
                        <div class="col-md-4">
                            <div class="card payment-type payment-card" data-pay="4">
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
                            <label>预计额度：</label>
                            <input type="hidden" name="orderNo" class="form-control">
                            <input type="hidden" name="payType" class="form-control">
                            <input type="text" name="orderAmount" class="form-control" disabled title="预计额度">
                        </div>
                        <div class="col-md-8">
                            <label>实际额度：</label>
                            <input type="text" name="payAmount" class="form-control" placeholder="请输入实际金额..." title="实际额度">
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-sm btn-secondary save-payment-cancel" data-dismiss="modal">
                    <i class="fa fa-close"></i> 取 消
                </button>
                <button type="button" class="btn btn-sm btn-primary save-payment-done">
                    <i class="fa fa-check"></i> 支 付
                </button>
            </div>
        </div>
    </div>
</div>
