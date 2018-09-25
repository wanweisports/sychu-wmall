package com.wardrobe.controller;

import com.wardrobe.common.annotation.Desc;
import com.wardrobe.common.bean.PageBean;
import com.wardrobe.common.bean.ResponseBean;
import com.wardrobe.common.constant.IDBConstant;
import com.wardrobe.common.po.ReserveOrderInfo;
import com.wardrobe.common.po.UserInfo;
import com.wardrobe.common.po.UserOrderInfo;
import com.wardrobe.common.view.CommodityInputView;
import com.wardrobe.common.view.OrderInputView;
import com.wardrobe.platform.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by cxs on 2018/8/6.
 */
@RequestMapping("order")
@Controller
public class OrderController extends BaseController {

    @Autowired
    private IOrderService orderService;

    @Desc("保存订单，等待支付")
    @ResponseBody
    @RequestMapping("saveOrder")
    public ResponseBean saveOrder(UserOrderInfo userOrderInfo, String scids) throws Exception{
        Integer oid = orderService.saveOrderInfo(userOrderInfo, scids, getUserInfo().getUid());
        return new ResponseBean(new HashMap(){{put("oid", oid);}});
    }

    @Desc("保存预约订单")
    @ResponseBody
    @RequestMapping("saveReserveOrder")
    public ResponseBean saveOrder(ReserveOrderInfo orderInfo, String scids) throws Exception{
        Integer oid = orderService.saveReserveOrderInfo(orderInfo, scids, getUserInfo().getUid());
        return new ResponseBean(new HashMap(){{put("oid", oid);}});
    }

    @Desc("试衣间详情-结算")
    @ResponseBody
    @RequestMapping("reserveOrderInfo")
    public ResponseBean reserveOrderInfo() throws Exception{
        return new ResponseBean(orderService.getNowReserveOrderInfo(getUserInfo().getUid()));
    }

    @Desc("试衣间详情-结算-详情")
    @ResponseBody
    @RequestMapping("reserveOrderDetail")
    public ResponseBean reserveOrderDetail(int roid) throws Exception{
        return new ResponseBean(orderService.getNowReserveOrderDetail(getUserInfo().getUid(), roid));
    }

    @Desc("取消预约")
    @ResponseBody
    @RequestMapping("cancelReserveOrder")
    public ResponseBean cancelReserveOrder(int roid) throws Exception{
        orderService.saveCancelReserveOrder(roid, getUserInfo().getUid());
        return new ResponseBean(true);
    }

    /*
     * 订单支付（1：购物  2：充值）
     */
    @ResponseBody
    @RequestMapping("wxPayPackage")
    public ResponseBean wxPayPackage(OrderInputView orderInputView) throws Exception{
        Map<Object, Object> payPackage = orderService.wxPayPackage(orderInputView, getUserInfo().getOpenId());
        if(IDBConstant.LOGIC_STATUS_YES.equals(payPackage.get("ok"))){
            return new ResponseBean(IDBConstant.LOGIC_STATUS_NO, null);
        }else {
            return new ResponseBean(payPackage);
        }
    }

    /*
     * 订单支付回调
     */
    @ResponseBody
    @RequestMapping("asynNotify")
    public ResponseBean asynNotify(HttpServletRequest request, HttpServletResponse response) throws Exception{
        System.out.println("PAY回调~~~");
        response.setContentType("text/xml");
        String msgxml = this.getStreamResult(request, response);//xml数据
        orderService.saveAsynNotify(msgxml, response);
        System.out.println("PAY回调~~~SUCCESS");
        return new ResponseBean(true);
    }

    @Desc("用户订单列表")
    @ResponseBody
    @RequestMapping("userOrders")
    public ResponseBean userOrders(OrderInputView orderInputView){
        orderInputView.setUid(getUserInfo().getUid());
        return new ResponseBean(setPageInfo(orderService.getUserOrderList(orderInputView)));
    }

    @Desc("用户订单详情")
    @ResponseBody
    @RequestMapping("userOrderDetail")
    public ResponseBean userOrderDetail(int oid){
        return new ResponseBean(orderService.getUserOrderDetail(oid, getUserInfo().getUid()));
    }

}
