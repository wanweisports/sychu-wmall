package com.wardrobe.controller;

import com.wardrobe.common.po.UserInfo;
import com.wardrobe.common.view.OrderInputView;
import com.wardrobe.platform.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by cxs on 2018/8/6.
 */
@RequestMapping("order")
@Controller
public class OrderController extends BaseController {

    @Autowired
    private IOrderService orderService;

    /*
     * 订单支付（1：购物  2：充值）
     */
    @RequestMapping("wxPayPackage")
    public String wxPayPackage(OrderInputView orderInputView, Model model){
        try {
            String finaPackage = orderService.wxPayPackage(orderInputView, getUserInfo().getOpenId());
            model.addAttribute("finaPackage", finaPackage);
        }catch (Exception e){
            e.printStackTrace();
        }
        return "jsapi_pay";
    }

    /*
     * 订单支付回调
     */
    @RequestMapping("asynNotify")
    public void asynNotify(HttpServletRequest request, HttpServletResponse response) throws Exception{
        try{
            System.out.println("网页图腾PAY回调~~~");
            response.setContentType("text/xml");
            String msgxml = this.getStreamResult(request, response);//xml数据
            orderService.saveAsynNotify(msgxml, response);
            System.out.println("网页图腾PAY回调~~~SUCCESS");
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
