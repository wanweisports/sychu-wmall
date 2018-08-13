package com.wardrobe.controller;

import com.wardrobe.common.annotation.Desc;
import com.wardrobe.common.bean.ResponseBean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 访问商品相关的接口
 */
@Controller
@RequestMapping("products")
public class ProductsController extends BaseController {

    @Desc("商品Banner列表")
    @ResponseBody
    @RequestMapping(value = "/banner/list", method = RequestMethod.GET)
    public ResponseBean getProductsBannerList() {
        return new ResponseBean(true);
    }
}
