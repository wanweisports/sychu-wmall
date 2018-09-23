package com.wardrobe.platform.service;

import com.wardrobe.common.bean.PageBean;
import com.wardrobe.common.po.ReserveOrderInfo;
import com.wardrobe.common.po.SysDict;
import com.wardrobe.common.po.UserOrderInfo;
import com.wardrobe.common.view.OrderInputView;

import javax.servlet.http.HttpServletResponse;
import java.text.ParseException;
import java.util.Map;

/**
 * Created by cxs on 2018/8/6.
 */
public interface IOrderService {

    Integer saveRechargeOrderInfo(UserOrderInfo userOrderInfo, SysDict sysDict, int uid);

    Integer saveOrderInfo(UserOrderInfo userOrderInfo, String scids, int uid) throws ParseException;

    Integer saveReserveOrderInfo(ReserveOrderInfo orderInfo, String scids, int uid) throws ParseException;

    Map<String, Object> getNowReserveOrderInfo(int uid);

    Map<String, Object> getNowReserveOrderDetail(int uid, int roid);

    ReserveOrderInfo getLastReserveOrderInfo(int uid);

    void saveCancelReserveOrder(int uid, int roid);

    Map<Object, Object> wxPayPackage(OrderInputView orderInputView, String openId) throws Exception;

    void saveAsynNotify(String msgxml, HttpServletResponse response) throws Exception;

    PageBean getUserOrderList(OrderInputView orderInputView);

    Map<String, Object> getUserOrderDetail(int oid, int uid);

    PageBean getUserOrderListIn(OrderInputView orderInputView);

    PageBean getReserveOrderInfoList(OrderInputView orderInputView);

}
