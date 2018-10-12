package com.wardrobe.controller;

import com.wardrobe.common.annotation.Desc;
import com.wardrobe.common.annotation.NotProtected;
import com.wardrobe.common.bean.PageBean;
import com.wardrobe.common.bean.ResponseBean;
import com.wardrobe.common.constant.IDBConstant;
import com.wardrobe.common.po.SysCommodityDistribution;
import com.wardrobe.common.po.SysCouponRule;
import com.wardrobe.common.util.JsonUtils;
import com.wardrobe.common.view.CommodityInputView;
import com.wardrobe.common.view.DeviceInputView;
import com.wardrobe.common.view.OrderInputView;
import com.wardrobe.platform.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 配送管理
 */
@Controller
@RequestMapping("/admin/distribution")
public class DistributionController extends BaseController {

    @Autowired
    private IOrderService orderService;

    @Autowired
    private ISysDeviceService deviceService;

    @Autowired
    private ICommodityService commodityService;

    @Desc("柜子列表")
    @NotProtected
    @RequestMapping(value = "/settings")
    public String renderDistributionSettings(Model model, DeviceInputView deviceInputView) {
        model.addAllAttributes(JsonUtils.fromJson(deviceInputView));
        deviceInputView.setPageSize(null);
        deviceInputView.setType(IDBConstant.LOGIC_STATUS_NO);
        model.addAttribute("deviceList", deviceService.getSysDeviceInfoList(deviceInputView).getList());
        model.addAllAttributes(deviceService.getDistributionSetting(deviceInputView));
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

/*    @Desc("获取某个衣柜的所有衣服")
    @ResponseBody
    @RequestMapping(value = "/lockCommoditys")
    public ResponseBean lockCommoditys(int dcid){
        List<Map<String, Object>> list = deviceService.getSysDeviceControlCommoditys(dcid);
        Map map = new HashMap<>(1, 1);
        map.put("list", list);
        return new ResponseBean(map);
    }*/

    @Desc("移除某个衣柜下的衣服")
    @ResponseBody
    @RequestMapping(value = "/delLockCommodity")
    public ResponseBean delLockCommodity(int dbid){
        deviceService.deleteSysDeviceControlCommodity(dbid);
        return new ResponseBean(true);
    }

    @Desc("添加衣服到某个衣柜")
    @ResponseBody
    @RequestMapping(value = "/saveLockCommodity")
    public ResponseBean saveLockCommodity(SysCommodityDistribution commodityDistribution){
        deviceService.saveSysDeviceControlCommodity(commodityDistribution);
        return new ResponseBean(true);
    }

}
