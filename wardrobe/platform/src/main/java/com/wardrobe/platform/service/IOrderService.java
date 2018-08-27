package com.wardrobe.platform.service;

import com.wardrobe.common.po.UserOrderInfo;
import com.wardrobe.common.view.OrderInputView;

import javax.servlet.http.HttpServletResponse;

/**
 * Created by cxs on 2018/8/6.
 */
public interface IOrderService {

    void saveOrderInfo(UserOrderInfo userOrderInfo, String scids, int uid);

    String wxPayPackage(OrderInputView orderInputView, String openId) throws Exception;

    void saveAsynNotify(String msgxml, HttpServletResponse response) throws Exception;

}
