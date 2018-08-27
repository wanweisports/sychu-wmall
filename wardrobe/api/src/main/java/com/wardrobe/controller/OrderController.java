package com.wardrobe.controller;

import com.wardrobe.common.annotation.Desc;
import com.wardrobe.common.bean.ResponseBean;
import com.wardrobe.common.po.UserInfo;
import com.wardrobe.common.po.UserOrderInfo;
import com.wardrobe.common.view.OrderInputView;
import com.wardrobe.platform.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;

/**
 * Created by cxs on 2018/8/6.
 */
@RequestMapping("order")
@Controller
public class OrderController extends BaseController {

    @Autowired
    private IOrderService orderService;

    @Desc("保存订单，等待支付")
    @RequestMapping("saveOrder")
    public ResponseBean saveOrder(UserOrderInfo userOrderInfo, String scids) throws Exception{
        orderService.saveOrderInfo(userOrderInfo, scids, getUserInfo().getUid());
        return new ResponseBean(true);
    }

    /*
     * 订单支付（1：购物  2：充值）
     */
    @RequestMapping("wxPayPackage")
    public ResponseBean wxPayPackage(OrderInputView orderInputView) throws Exception{
        String finaPackage = orderService.wxPayPackage(orderInputView, getUserInfo().getOpenId());
        return new ResponseBean(new HashMap(){{put("finaPackage", finaPackage);}});
    }

    /*
     * 订单支付回调
     */
    @RequestMapping("asynNotify")
    public ResponseBean asynNotify(HttpServletRequest request, HttpServletResponse response) throws Exception{
        System.out.println("PAY回调~~~");
        response.setContentType("text/xml");
        String msgxml = this.getStreamResult(request, response);//xml数据
        orderService.saveAsynNotify(msgxml, response);
        System.out.println("PAY回调~~~SUCCESS");
        return new ResponseBean(true);
    }

}
