package com.wardrobe.controller;

import com.wardrobe.common.annotation.Desc;
import com.wardrobe.common.bean.ResponseBean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * 访问商品相关的接口
 */
@Controller
@RequestMapping("/admin/products")
public class ProductsController extends BaseController {

    @Desc("商品活动设置")
    @RequestMapping(value = "/active/settings", method = RequestMethod.GET)
    public ModelAndView renderProductsActiveSettings() {
        return new ModelAndView("/Products/ActiveSettings");
    }

    @Desc("商品筛选属性设置")
    @RequestMapping(value = "/category/settings", method = RequestMethod.GET)
    public ModelAndView renderProductsCategorySettings() {
        return new ModelAndView("/Products/CategorySettings");
    }

    @Desc("商品管理列表")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ModelAndView renderProductsList() {
        return new ModelAndView("/Products/List");
    }

    @Desc("商品添加")
    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public ModelAndView renderProductsAdd() {
        return new ModelAndView("/Products/Add");
    }

    @Desc("商品订单列表")
    @RequestMapping(value = "/orders/list", method = RequestMethod.GET)
    public ModelAndView renderProductsOrdersList() {
        return new ModelAndView("/Products/OrdersList");
    }

    @Desc("商品订单详情")
    @RequestMapping(value = "/orders/detail", method = RequestMethod.GET)
    public ModelAndView renderProductsOrdersDetail() {
        return new ModelAndView("/Products/OrdersDetail");
    }
}

