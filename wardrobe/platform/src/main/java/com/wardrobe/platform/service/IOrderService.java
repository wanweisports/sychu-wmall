package com.wardrobe.platform.service;

import com.wardrobe.common.bean.PageBean;
import com.wardrobe.common.po.ReserveOrderInfo;
import com.wardrobe.common.po.SysDict;
import com.wardrobe.common.po.UserOrderDetail;
import com.wardrobe.common.po.UserOrderInfo;
import com.wardrobe.common.view.OrderInputView;

import javax.servlet.http.HttpServletResponse;
import java.text.ParseException;
import java.util.List;
import java.util.Map;

/**
 * Created by cxs on 2018/8/6.
 */
public interface IOrderService {

    Integer saveRechargeOrderInfo(UserOrderInfo userOrderInfo, SysDict sysDict, int uid);

    Integer saveOrderInfo(UserOrderInfo userOrderInfo, String scids, int uid) throws ParseException;

    List<UserOrderDetail> getUserOrderDetails(int oid);

    Map<String, Object> getRfidSettlement(Map<String, Object> data, int uid) throws ParseException;

    Integer saveRfidOrderInfo(UserOrderInfo userOrderInfo, String dbids, int uid) throws ParseException;

    Integer saveReserveOrderInfo(ReserveOrderInfo orderInfo, String scids, int uid) throws ParseException;

    Map<String, Object> getNowReserveOrderInfo(int uid);

    Map<String, Object> getNowReserveOrderDetail(int uid, int roid);

    ReserveOrderInfo getLastReserveOrderInfo(int uid);

    void saveCancelOrder(int oid, int uid) throws Exception;

    void saveCancelReserveOrder(int roid, int uid);

    Map<Object, Object> saveWxPayPackage(OrderInputView orderInputView, String openId) throws Exception;

    void saveAsynNotify(String msgxml, HttpServletResponse response) throws Exception;

    PageBean getUserOrderList(OrderInputView orderInputView);

    Map<String, Object> getUserOrderDetail(int oid, int uid);

    PageBean getUserOrderListIn(OrderInputView orderInputView);

    Map<String, Object> getUserOrderDetailIn(int oid);

    Map<String, Object> getUserOrderReservationDetailIn(int roid);

    PageBean getReserveOrderInfoList(OrderInputView orderInputView);

    List<Map<String, Object>> getReserveOrderByTime(String time);

    List<UserOrderInfo> getOvertimeOrders();

    void updateOvertimeOrders(List<UserOrderInfo> userOrderInfos) throws ParseException;

    Map<String, Object> getNowCanOpenLock(int did, int uid);

    void deleteReservation(int roid);

    int getOrderCommodityCount(List<Map<String, Object>> settments);

    void saveOrderExpressFH(int oid, String expressCompany);

}
