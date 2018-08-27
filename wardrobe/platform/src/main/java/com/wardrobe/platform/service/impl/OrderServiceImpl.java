package com.wardrobe.platform.service.impl;

import com.wardrobe.common.constant.IDBConstant;
import com.wardrobe.common.po.*;
import com.wardrobe.common.util.Arith;
import com.wardrobe.common.util.DateUtil;
import com.wardrobe.common.util.StrUtil;
import com.wardrobe.common.view.OrderInputView;
import com.wardrobe.platform.service.*;
import com.wardrobe.wx.WeiXinConnector;
import com.wardrobe.wx.http.HttpConnect;
import com.wardrobe.wx.util.ConfigUtil;
import com.wardrobe.wx.util.PayCommonUtil;
import com.wardrobe.wx.util.SignUtil;
import com.wardrobe.wx.util.XMLUtil;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.sql.Timestamp;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * Created by cxs on 2018/8/6.
 */
@Service
public class OrderServiceImpl extends BaseService implements IOrderService {

    private Logger logger = Logger.getLogger(OrderServiceImpl.class);

    @Autowired
    private IUserTransactionsService userTransactionsService;

    @Autowired
    private IUserAccountService userAccountService;

    @Autowired
    private IUserShoppingCartService userShoppingCartService;

    @Autowired
    private ICommodityService commodityService;

    private UserOrderInfo getUserOrderInfo(int oid){
        return baseDao.getToEvict(UserOrderInfo.class, oid);
    }

    private UserOrderInfo getUserOrderInfoAndDetails(int oid){
        UserOrderInfo userOrderInfo = getUserOrderInfo(oid);
        userOrderInfo.setUserOrderDetails(getUserOrderDetails(oid));
        return userOrderInfo;
    }

    private List<UserOrderDetail> getUserOrderDetails(int oid){
        return baseDao.queryByHql("FROM UserOrderDetail WHERE oid = ?", oid);
    }

    @Override
    public synchronized void saveOrderInfo(UserOrderInfo userOrderInfo, String scids, int uid){ //购物车ids，多个逗号分隔
        Timestamp nowDate = DateUtil.getNowDate();
        userOrderInfo.setUid(uid);
        userOrderInfo.setCreateTime(nowDate);
        userOrderInfo.setPayStatus(IDBConstant.LOGIC_STATUS_NO);
        userOrderInfo.setStatus(IDBConstant.LOGIC_STATUS_YES);
        baseDao.save(userOrderInfo, null);

        int oid = userOrderInfo.getOid();
        String[] scidArr = scids.split(",");
        double priceSum = 0;
        for(String scid : scidArr) { //待处理积分与优惠券问题
            UserShoppingCart userShoppingCart = userShoppingCartService.getUserShoppingCart(StrUtil.objToInt(scid));
            Integer cid = userShoppingCart.getCid();
            UserOrderDetail userOrderDetail = new UserOrderDetail();
            userOrderDetail.setCreateTime(nowDate);
            userOrderDetail.setCid(cid);

            CommodityInfo commodityInfo = commodityService.getCommodityInfo(cid);
            userOrderDetail.setItemCount(userShoppingCart.getCount());
            userOrderDetail.setItemName(commodityInfo.getCommName());
            userOrderDetail.setItemPrice(commodityInfo.getPrice());

            CommodityColor commodityColor = commodityService.getCommodityColorByCid(cid);
            userOrderDetail.setItemColor(commodityColor.getColorName());

            CommoditySize commoditySize = commodityService.getCommoditySize(userShoppingCart.getSid());
            userOrderDetail.setItemSize(commoditySize.getSize());

            userOrderDetail.setItemPriceSum(Arith.conversion(Arith.div(commodityInfo.getPrice().doubleValue(), userShoppingCart.getCount())));
            userOrderDetail.setOid(oid);
            userOrderDetail.setItemImg(commodityService.getFmImg(cid));
            baseDao.save(userOrderDetail, null);

            priceSum = Arith.add(priceSum, userOrderDetail.getItemPriceSum().doubleValue());
        }

        userOrderInfo.setPriceSum(Arith.conversion(priceSum));
        baseDao.save(userOrderInfo, oid);
    }

    @Override
    public String wxPayPackage(OrderInputView orderInputView, String openId) throws Exception {
        Integer oId = orderInputView.getOrderId();

        UserOrderInfo orderInfo = getUserOrderInfo(oId); //openId = "oTK5utzXi_A2q8aus80Y60__LzY0";
        // 1 参数
        // 订单号
        String orderId = orderInfo.getOid() + "_" + System.currentTimeMillis();
        // 附加数据 原样返回
        String attach = "cxs";
        // 总金额以分为单位，不带小数点
        String totalFee = StrUtil.getMoney(StrUtil.objToStr(orderInfo.getPriceSum().doubleValue()));

        // 订单生成的机器 IP
        String spbill_create_ip = "127.0.0.1";
        // 这里notify_url是 支付完成后微信发给该链接信息，可以判断会员是否支付成功，改变订单状态等。
        String notify_url = "http://47.94.196.103/order/asynNotify";
        String trade_type = "JSAPI";

        // ---必须参数
        // 商户号
        String mch_id = WeiXinConnector.MCH_ID;
        // 随机字符串
        String nonce_str = StrUtil.getNonceStr();

        // 商品描述根据情况修改
        String body = IDBConstant.TRANSACTIONS_TYPE_ZF.equals(orderInfo.getOrderType()) ? "商品购买" : "充值";

        // 商户订单号
        String out_trade_no = orderId;

        SortedMap<Object, Object> packageParams = new TreeMap<Object, Object>();
        packageParams.put("appid", WeiXinConnector.APP_ID);
        packageParams.put("mch_id", mch_id);
        packageParams.put("nonce_str", nonce_str);
        packageParams.put("body", body);
        packageParams.put("attach", attach);
        packageParams.put("out_trade_no", out_trade_no);

        // 这里写的金额为1 分到时修改
        packageParams.put("total_fee", totalFee);
        packageParams.put("spbill_create_ip", spbill_create_ip);
        packageParams.put("notify_url", notify_url);

        packageParams.put("trade_type", trade_type);
        packageParams.put("openid", openId);
        String sign = PayCommonUtil.createSign("UTF-8", packageParams);
        packageParams.put("sign", sign);

        String requestXML = PayCommonUtil.getRequestXml(packageParams);
        logger.info("requestXML：" + requestXML);
        Map map = XMLUtil.doXMLParse(HttpConnect.httpsRequestStr(ConfigUtil.UNIFIED_ORDER_URL, "POST", requestXML));

        String prepay_id = map.get("prepay_id").toString();
        logger.info("获取到的预支付ID：" + prepay_id);

        //获取prepay_id后，拼接最后请求支付所需要的package

        SortedMap<Object, Object> finalpackage = new TreeMap<Object, Object>();
        String timestamp = SignUtil.getTimeStamp();
        String packages = "prepay_id="+prepay_id;
        finalpackage.put("appId", WeiXinConnector.APP_ID);
        finalpackage.put("timeStamp", timestamp);
        finalpackage.put("nonceStr", nonce_str);
        finalpackage.put("package", packages);
        finalpackage.put("signType", "MD5");
        //要签名
        String finalsign = PayCommonUtil.createSign("UTF-8", finalpackage);

        String finaPackage = "\"appId\":\"" + WeiXinConnector.APP_ID + "\",\"timeStamp\":\"" + timestamp
                + "\",\"nonceStr\":\"" + nonce_str + "\",\"package\":\""
                + packages + "\",\"signType\" : \"MD5" + "\",\"paySign\":\""
                + finalsign + "\"";

        logger.info("V3 jsApi package:"+finaPackage);
        return finaPackage;
    }

    @Override
    public void saveAsynNotify(String msgxml, HttpServletResponse response) throws Exception{
        Map map = XMLUtil.doXMLParse(msgxml);

        String result_code=(String) map.get("result_code");
        String out_trade_no  = (String) map.get("out_trade_no");
        String total_fee  = (String) map.get("total_fee");
        String sign  = (String) map.get("sign");
        Double amount = new Double(total_fee) / 100;//获取订单金额
        System.out.println("qianming");
        if(result_code.equals("SUCCESS")){
            System.out.println("qianming_success");
            //验证签名
            String fee_type  = (String) map.get("fee_type");
            String bank_type  = (String) map.get("bank_type");
            String cash_fee  = (String) map.get("cash_fee");
            String is_subscribe  = (String) map.get("is_subscribe");
            String nonce_str  = (String) map.get("nonce_str");
            String openid  = (String) map.get("openid");
            String return_code  = (String) map.get("return_code");
            String sub_mch_id  = (String) map.get("sub_mch_id");
            String time_end  = (String) map.get("time_end");
            String trade_type  = (String) map.get("trade_type");
            String transaction_id  = (String) map.get("transaction_id");
            String attach  = (String) map.get("attach");

            //需要对以下字段进行签名
            SortedMap<Object, Object> packageParams = new TreeMap<Object, Object>();
            packageParams.put("appid", WeiXinConnector.APP_ID);
            packageParams.put("bank_type", bank_type);
            packageParams.put("cash_fee", cash_fee);
            packageParams.put("fee_type", fee_type);
            packageParams.put("is_subscribe", is_subscribe);
            packageParams.put("mch_id", WeiXinConnector.MCH_ID);
            packageParams.put("nonce_str", nonce_str);
            packageParams.put("openid", openid);
            packageParams.put("out_trade_no", out_trade_no);
            packageParams.put("result_code", result_code);
            packageParams.put("return_code", return_code);
            packageParams.put("sub_mch_id", sub_mch_id);
            packageParams.put("time_end", time_end);
            packageParams.put("total_fee", total_fee); //用自己系统的数据：订单金额
            packageParams.put("trade_type", trade_type);
            packageParams.put("transaction_id", transaction_id);
            packageParams.put("attach", attach);

            String endsign = PayCommonUtil.createSign("UTF-8", packageParams);
            System.out.println("qianming_1");
            if(sign.equals(endsign)){//验证签名是否正确  官方签名工具地址：https://pay.weixin.qq.com/wiki/tools/signverify/
                System.out.println("qianming_1_success");
                //处理自己的业务逻辑
                logger.info("支付成功!");
                Integer oId = StrUtil.objToInt(out_trade_no.substring(0, out_trade_no.indexOf("_")));
                UserOrderInfo userOrderInfo = getUserOrderInfoAndDetails(oId);
                if(!IDBConstant.LOGIC_STATUS_YES.equals(userOrderInfo.getPayStatus())){ //未支付状态
                    userOrderInfo.setPayStatus(IDBConstant.LOGIC_STATUS_YES);
                    userOrderInfo.setPayPrice(userOrderInfo.getPriceSum());
                    //充值类型需要处理用户账户金额
                    synchronized (OrderServiceImpl.class) {
                        if (IDBConstant.TRANSACTIONS_TYPE_ZF.equals(userOrderInfo.getOrderType())) {
                            userAccountService.addRechargePrice(userOrderInfo.getUid(), userOrderInfo.getUserOrderDetails().get(0).getCid());
                        }
                        //交易流水
                        userTransactionsService.addUserTransactions(userOrderInfo.getUid(), oId, userOrderInfo.getOrderType(), userOrderInfo.getPayPrice());
                    }
                }
            }else{
                System.out.println("qianming_1_fail");
                logger.info("sign===>" + sign);
                logger.info("endsign===>" + endsign);
                logger.info("验证签名不正确");
            }
        }else{
            System.out.println("qianming_1_null");
            response.getWriter().write(setXML("FAIL", "报文为空"));
        }
    }

    private String setXML(String return_code, String return_msg) {
        return "<xml><return_code><![CDATA[" + return_code + "]]></return_code><return_msg><![CDATA[" + return_msg + "]]></return_msg></xml>";
    }


}
