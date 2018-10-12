package com.wardrobe.controller;

import com.wardrobe.common.annotation.Desc;
import com.wardrobe.common.annotation.NotProtected;
import com.wardrobe.common.bean.PageBean;
import com.wardrobe.common.util.JsonUtils;
import com.wardrobe.common.view.CommodityInputView;
import com.wardrobe.common.view.OrderInputView;
import com.wardrobe.platform.service.ICommodityService;
import com.wardrobe.platform.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 配送管理
 */
@Controller
@RequestMapping("/admin/distribution")
public class DistributionController1 extends BaseController {

    @Autowired
    private ICommodityService commodityService;

    @Autowired
    private IOrderService orderService;

    @Desc("柜子列表")
    @NotProtected
    @RequestMapping(value = "/settings")
    public String renderDistributionSettings() {
        return "Distribution/Settings";
    }

    @Desc("衣服列表")
    @NotProtected
    @RequestMapping(value = "/products")
    public String renderDistributionProducts(CommodityInputView commodityInputView, Model model) {
        PageBean pageBean = commodityService.getCommodityListIn(commodityInputView);
        setPageInfo(model, pageBean, "/admin/products/list", commodityInputView);
        model.addAllAttributes(JsonUtils.fromJsonDF(commodityInputView));

        return "Distribution/ProductList";
    }

    @Desc("预约单列表")
    @NotProtected
    @RequestMapping(value = "/orders")
    public String renderDistributionOrders(OrderInputView orderInputView, Model model) {
        model.addAllAttributes(JsonUtils.fromJsonDF(orderInputView));
        setPageInfo(model, orderService.getReserveOrderInfoList(orderInputView), "/admin/orders/reservation", orderInputView);

        return "Distribution/OrderList";
    }

}
