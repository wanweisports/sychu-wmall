package com.wardrobe.controller;

import com.wardrobe.common.annotation.Desc;
import com.wardrobe.common.bean.ResponseBean;
import com.wardrobe.common.constant.IPlatformConstant;
import com.wardrobe.common.po.CommodityColor;
import com.wardrobe.common.po.CommodityInfo;
import com.wardrobe.controller.annotation.CommodityResolver;
import com.wardrobe.platform.service.ICommodityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

/**
 * 访问商品相关的接口
 */
@Controller
@RequestMapping("/admin/products")
public class ProductsController extends BaseController {

    @Autowired
    private ICommodityService commodityService;

    @Desc("商品活动设置")
    @RequestMapping(value = "/active/settings", method = RequestMethod.GET)
    public ModelAndView renderProductsActiveSettings() {
        return new ModelAndView("/Products/ActiveSettings");
    }

    /***********
     * type: "category", "style", "material"
     */

    @Desc("商品筛选属性设置")
    @RequestMapping(value = "/settings", method = RequestMethod.GET)
    public ModelAndView renderProductsSettings() {
        return new ModelAndView("/Products/Settings");
    }

    @Desc("商品筛选属性设置 - 提交")
    @ResponseBody
    @RequestMapping(value = "/{type}/save", method = RequestMethod.POST)
    public ResponseBean saveProductsTypeSettings(@PathVariable String type, ProductsTypeSettingsRequest request) {
        return new ResponseBean(true);
    }

    @Desc("商品筛选属性设置 - 删除")
    @ResponseBody
    @RequestMapping(value = "/{type}/delete", method = RequestMethod.POST)
    public ResponseBean deleteProductsTypeSettings(@PathVariable String type) {
        return new ResponseBean(true);
    }

    @Desc("商品筛选属性设置 - 查询")
    @ResponseBody
    @RequestMapping(value = "/{type}/query", method = RequestMethod.GET)
    public ResponseBean queryProductsTypeSettings() {
        return new ResponseBean(true);
    }

    @Desc("商品管理列表")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ModelAndView renderProductsList() {
        return new ModelAndView("/Products/List");
    }

    @Desc("商品管理列表")
    @RequestMapping(value = "/detail", method = RequestMethod.GET)
    public ModelAndView renderProductsDetail() {
        return new ModelAndView("/Products/Detail");
    }

    @Desc("商品管理列表 -- 人气/热门商品")
    @RequestMapping(value = "/hot/list", method = RequestMethod.GET)
    public ModelAndView renderProductsHotList(String type) {
        ModelAndView modelAndView = new ModelAndView("/Products/HotList");

        if (type == null || type.isEmpty()) {
            type = "hot";
        }
        modelAndView.addObject("type", type);

        return modelAndView;
    }

    @Desc("商品管理列表 -- 人气/热门设定取消")
    @ResponseBody
    @RequestMapping(value = "/hotSetting", method = RequestMethod.POST)
    public ResponseBean setHotSettings(int productId, String type, int status) {
        return new ResponseBean(true);
    }

    @Desc("进入商品添加页面")
    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String renderProductsAdd(Integer cid, Model model) {
        model.addAllAttributes(commodityService.renderProductsAddIn(cid));
        model.addAttribute("dh", IPlatformConstant.DOU_HAO);
        return "Products/Add";
    }

    @Desc("商品添加/修改")
    @ResponseBody
    @RequestMapping("addCommodity")
    public ResponseBean addCommodity(@CommodityResolver CommodityInfo commodityInfo, CommodityColor commodityColor, MultipartFile[] multipartFiles){
        commodityService.addCommodityIn(commodityInfo, commodityColor);
        return new ResponseBean(true);
    }

    @Desc("商品管理列表 -- 库存变更记录")
    @RequestMapping(value = "/sku/list", method = RequestMethod.GET)
    public ModelAndView renderProductsSkuList() {
        ModelAndView modelAndView = new ModelAndView("/Products/SkuList");

        return modelAndView;
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

