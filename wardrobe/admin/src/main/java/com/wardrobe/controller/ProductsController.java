package com.wardrobe.controller;

import com.wardrobe.common.annotation.Desc;
import com.wardrobe.common.bean.PageBean;
import com.wardrobe.common.bean.ResponseBean;
import com.wardrobe.common.constant.IDBConstant;
import com.wardrobe.common.constant.IPlatformConstant;
import com.wardrobe.common.po.SysDict;
import com.wardrobe.common.util.JsonUtils;
import com.wardrobe.common.view.CommodityInputView;
import com.wardrobe.platform.service.ICommodityService;
import com.wardrobe.platform.service.IDictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * 访问商品相关的接口
 */
@Controller
@RequestMapping("/admin/products")
public class ProductsController extends BaseController {

    @Autowired
    private ICommodityService commodityService;

    @Autowired
    private IDictService dictService;

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
    public ResponseBean saveProductsTypeSettings(@PathVariable String type, String dictValue, ProductsTypeSettingsRequest request) {
        dictService.addDict(new SysDict(type, dictValue));
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
    @RequestMapping(value = "/list")
    public String renderProductsList(CommodityInputView commodityInputView, Model model) {
        PageBean pageBean = commodityService.getCommodityListIn(commodityInputView);
        setPageInfo(model, pageBean, "/admin/products/list", commodityInputView);
        model.addAllAttributes(JsonUtils.fromJsonDF(commodityInputView));
        return "Products/List";
    }

    @Desc("商品管理列表")
    @RequestMapping(value = "/detail", method = RequestMethod.GET)
    public ModelAndView renderProductsDetail() {
        return new ModelAndView("/Products/Detail");
    }

    @Desc("商品管理列表 -- 人气/热门商品")
    @RequestMapping(value = "/hot/list", method = RequestMethod.GET)
    public String renderProductsHotList(CommodityInputView commodityInputView, Model model) {
        commodityInputView.setStatus(IDBConstant.LOGIC_STATUS_YES);
        PageBean pageBean = commodityService.getCommodityListIn(commodityInputView);
        setPageInfo(model, pageBean, "/admin/products/list", commodityInputView);
        model.addAllAttributes(JsonUtils.fromJsonDF(commodityInputView));
        return "Products/HotList";
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

