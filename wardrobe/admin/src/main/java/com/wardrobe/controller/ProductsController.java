package com.wardrobe.controller;

import com.wardrobe.common.annotation.Desc;
import com.wardrobe.common.bean.PageBean;
import com.wardrobe.common.bean.ResponseBean;
import com.wardrobe.common.constant.IDBConstant;
import com.wardrobe.common.constant.IPlatformConstant;
import com.wardrobe.common.po.*;
import com.wardrobe.common.util.JsonUtils;
import com.wardrobe.common.view.CommodityInputView;
import com.wardrobe.controller.request.ProductRequest;
import com.wardrobe.controller.request.SysDictRequest;
import com.wardrobe.platform.service.ICommodityService;
import com.wardrobe.platform.service.IDictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    /***********
     * type: "category", "style", "material"
     */
    @Desc("商品筛选属性设置")
    @RequestMapping(value = "/settings", method = RequestMethod.GET)
    public String renderProductsSettings(Model model) {
        Map<String, Object> data = new HashMap<>(3, 1);
        data.put("categoryList", dictService.getDicts(IDBConstant.COMM_CATEGORY));
        data.put("styleList", dictService.getDicts(IDBConstant.COMM_STYLE));
        data.put("materialList", dictService.getDicts(IDBConstant.COMM_MATERIAL));
        model.addAllAttributes(data);
        return "Products/Settings";

    }

    @Desc("商品筛选属性设置 - 提交")
    @ResponseBody
    @RequestMapping(value = "/{type}/save", method = RequestMethod.POST)
    public ResponseBean saveProductsTypeSettings(@PathVariable String type, SysDictRequest request) {
        dictService.addDict(new SysDict(type, request.getDictValue()));
        return new ResponseBean(true);
    }

    @Desc("商品筛选属性设置 - 删除")
    @ResponseBody
    // 被替换 DictController.delDict
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

    @Desc("商品管理详情")
    @RequestMapping(value = "/detail", method = RequestMethod.GET)
    public String renderProductsDetail(int cid, Model model) {
        model.addAllAttributes(commodityService.renderCommodityDetailIn(cid));
        return "/Products/Detail";
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
    public String renderProductsAdd(Integer groupId, Model model) {
        model.addAllAttributes(commodityService.renderProductsAddIn(null));
        model.addAttribute("groupId", groupId);
        return "Products/Add";
    }

    @Desc("进入商品编辑页面")
    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public String renderProductsEdit(Integer cid, Model model) {
        model.addAllAttributes(commodityService.renderProductsAddIn(cid));
        model.addAttribute("dh", IPlatformConstant.DOU_HAO);

        return "Products/Edit";
    }

    @Desc("进入商品各种设置字典")
    @ResponseBody
    @RequestMapping(value = "/getProductSettingsList", method = RequestMethod.GET)
    public ResponseBean getProductCategoryList() {
        Map map = new HashMap<>();

        map.put("COMM_CATEGORY", dictService.getDicts(IDBConstant.COMM_CATEGORY));
        map.put("COMM_STYLE", dictService.getDicts(IDBConstant.COMM_STYLE));
        map.put("COMM_MATERIAL", dictService.getDicts(IDBConstant.COMM_MATERIAL));
        map.put("USER_SIZE", dictService.getDicts(IDBConstant.USER_SIZE));

        return new ResponseBean(map);
    }

    @Desc("商品提交")
    @ResponseBody
    @RequestMapping(value = "/saveAdd", method = RequestMethod.POST)
    public ResponseBean saveProductsAdd(ProductRequest productRequest, MultipartHttpServletRequest multipartHttpServletRequest) throws IOException {

        CommodityInfo commodityInfo = new CommodityInfo();
        commodityInfo.setCommName(productRequest.getCommName());
        commodityInfo.setProductDesc(productRequest.getProductDesc());
        commodityInfo.setCategory(productRequest.getCategory());
        commodityInfo.setStyle(productRequest.getStyle());
        commodityInfo.setMaterial(productRequest.getMaterial());
        commodityInfo.setPrice(productRequest.getPrice());
        commodityInfo.setCouPrice(productRequest.getCouPrice());
        commodityInfo.setGroupId(productRequest.getGroupId());

        CommodityColor commodityColor = new CommodityColor();
        commodityColor.setColorName(productRequest.getColorName());

        List<CommoditySize> commoditySizeList = new ArrayList<>();
        String[] sizeArr = productRequest.getSize().split(",");
        String[] stockArr = productRequest.getStock().split(",");
        for (int i = 0; i < sizeArr.length; i++) {
            CommoditySize commoditySize = new CommoditySize();
            commoditySize.setSize(sizeArr[i]);
            commoditySize.setStock(Integer.parseInt(stockArr[i]));

            commoditySizeList.add(commoditySize);
        }
        commodityColor.setCommoditySizes(commoditySizeList);
        commodityInfo.setCommodityColor(commodityColor);

        commodityService.addUpdateCommodityIn(commodityInfo, multipartHttpServletRequest);

        return new ResponseBean(true);
    }

    @Desc("商品提交")
    @ResponseBody
    @RequestMapping(value = "/saveEdit", method = RequestMethod.POST)
    public ResponseBean saveProductsEdit(ProductRequest productRequest, MultipartHttpServletRequest multipartHttpServletRequest) throws IOException {

        CommodityInfo commodityInfo = new CommodityInfo();
        commodityInfo.setCid(productRequest.getCid());
        commodityInfo.setCommName(productRequest.getCommName());
        commodityInfo.setProductDesc(productRequest.getProductDesc());
        commodityInfo.setCategory(productRequest.getCategory());
        commodityInfo.setStyle(productRequest.getStyle());
        commodityInfo.setMaterial(productRequest.getMaterial());
        commodityInfo.setPrice(productRequest.getPrice());
        commodityInfo.setCouPrice(productRequest.getCouPrice());
        commodityInfo.setGroupId(productRequest.getGroupId());

        CommodityColor commodityColor = new CommodityColor();
        commodityColor.setCid(productRequest.getCid());
        commodityColor.setCoid(productRequest.getCoid());
        commodityColor.setColorName(productRequest.getColorName());

        commodityInfo.setCommodityColor(commodityColor);

        commodityService.addUpdateCommodityIn(commodityInfo, multipartHttpServletRequest);

        return new ResponseBean(true);
    }

    @ResponseBody
    @RequestMapping("saveStock")
    public ResponseBean saveStock(CommodityStock commodityStock){
        commodityService.saveStock(commodityStock, true);
        return new ResponseBean(true);
    }

    @Desc("商品管理列表 -- 库存变更记录")
    @RequestMapping(value = "/sku/list")
    public String renderProductsSkuList(CommodityInputView commodityInputView, Model model) {
        PageBean pageBean = commodityService.getStockListIn(commodityInputView);
        setPageInfo(model, pageBean, "/admin/products/sku/list", commodityInputView);
        model.addAllAttributes(JsonUtils.fromJsonDF(commodityInputView));
        model.addAttribute("types", dictService.getDicts(IDBConstant.COMM_STOCK_TYPE));
        return "Products/SkuList";
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

