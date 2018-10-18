package com.wardrobe.controller;

import com.wardrobe.common.annotation.Desc;
import com.wardrobe.common.bean.ResponseBean;
import com.wardrobe.common.constant.IDBConstant;
import com.wardrobe.common.util.JsonUtils;
import com.wardrobe.common.util.StrUtil;
import com.wardrobe.common.view.OrderInputView;
import com.wardrobe.platform.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 订单管理
 */
@Controller
@RequestMapping("/admin/orders")
public class OrdersController extends BaseController {

    @Autowired
    private IOrderService orderService;

    @Desc("订单列表")
    @RequestMapping(value = "/list")
    public String renderOrdersList(OrderInputView orderInputView, Model model) {
        model.addAllAttributes(JsonUtils.fromJsonDF(orderInputView));
        setPageInfo(model, orderService.getUserOrderListIn(orderInputView), "/admin/orders/list", orderInputView);
        return "Orders/List";
    }

    @Desc("订单详情")
    @RequestMapping(value = "/detail")
    public String renderOrdersDetail() {
        return "Orders/Detail";
    }

    @Desc("预约订单列表")
    @RequestMapping(value = "/reservation")
    public String renderOrdersReservation(OrderInputView orderInputView, Model model) {
        model.addAllAttributes(JsonUtils.fromJsonDF(orderInputView));
        setPageInfo(model, orderService.getReserveOrderInfoList(orderInputView), "/admin/orders/reservation", orderInputView);
        return "Orders/Reservation";
    }

    @Desc("删除预约订单")
    @ResponseBody
    @RequestMapping("/reservation/delete")
    public ResponseBean deleteReservation(int roid) {
        orderService.deleteReservation(roid);
        return new ResponseBean(true);
    }

    @Desc("退款订单列表")
    @RequestMapping(value = "/refund")
    public String renderOrdersRefund(OrderInputView orderInputView, Model model) {
        model.addAllAttributes(JsonUtils.fromJsonDF(orderInputView));
        orderInputView.setStatus(StrUtil.join(new String[]{IDBConstant.ORDER_STATUS_RETURN_ING, IDBConstant.ORDER_STATUS_REFUSE, IDBConstant.ORDER_STATUS_RETURN}, ","));
        setPageInfo(model, orderService.getUserOrderListIn(orderInputView), "/admin/orders/refund", orderInputView);
        return "Orders/Refund";
    }

    @Desc("同意/不同意退款")
    @ResponseBody
    @RequestMapping("/refund/apply")
    public ResponseBean applyRefundOrders() {
        return new ResponseBean(true);
    }

}
