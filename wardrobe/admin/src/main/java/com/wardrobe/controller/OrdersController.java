package com.wardrobe.controller;

import com.wardrobe.common.annotation.Desc;
import com.wardrobe.common.bean.ResponseBean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 订单管理
 */
@Controller
@RequestMapping("/admin/orders")
public class OrdersController extends BaseController {

    @Desc("订单列表")
    @RequestMapping(value = "/list")
    public String renderOrdersList() {
        return "Orders/List";
    }

    @Desc("订单详情")
    @RequestMapping(value = "/detail")
    public String renderOrdersDetail() {
        return "Orders/Detail";
    }

    @Desc("预约订单列表")
    @RequestMapping(value = "/reservation")
    public String renderOrdersReservation() {
        return "Orders/Reservation";
    }

    @Desc("退款订单列表")
    @RequestMapping(value = "/refund")
    public String renderOrdersRefund() {
        return "Orders/Refund";
    }

    @Desc("同意/不同意退款")
    @ResponseBody
    @RequestMapping("/refund/apply")
    public ResponseBean applyRefundOrders() {
        return new ResponseBean(true);
    }

}
