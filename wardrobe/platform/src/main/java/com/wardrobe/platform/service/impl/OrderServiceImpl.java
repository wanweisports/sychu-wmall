package com.wardrobe.platform.service.impl;

import com.wardrobe.common.annotation.Desc;
import com.wardrobe.common.bean.DiscountBean;
import com.wardrobe.common.bean.PageBean;
import com.wardrobe.common.constant.IDBConstant;
import com.wardrobe.common.constant.IPlatformConstant;
import com.wardrobe.common.exception.MessageException;
import com.wardrobe.common.po.*;
import com.wardrobe.common.util.*;
import com.wardrobe.common.view.OrderInputView;
import com.wardrobe.platform.service.*;
import com.wardrobe.wx.http.HttpConnect;
import com.wardrobe.wx.util.ConfigUtil;
import com.wardrobe.wx.util.PayCommonUtil;
import com.wardrobe.wx.util.SignUtil;
import com.wardrobe.wx.util.XMLUtil;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.sql.*;
import java.text.ParseException;
import java.util.*;
import java.util.Date;

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
    private IUserService userService;

    @Autowired
    private IUserShoppingCartService userShoppingCartService;

    @Autowired
    private ICommodityService commodityService;

    @Autowired
    private ISysDeviceService sysDeviceService;

    @Autowired
    private IUserCouponService userCouponService;

    @Autowired
    private IResourceService resourceService;

    private UserOrderInfo getUserOrderInfo(int oid){
        return baseDao.getToEvict(UserOrderInfo.class, oid);
    }

    private ReserveOrderInfo getReserveOrderInfo(int roid){
        return baseDao.getToEvict(ReserveOrderInfo.class, roid);
    }

    private List<ReserveOrderDetail> getReserveOrderDetails(int roid){
        return baseDao.queryByHql("FROM ReserveOrderDetail WHERE roid = ?1", roid);
    }

    private UserOrderInfo getUserOrderInfoAndDetails(int oid){
        UserOrderInfo userOrderInfo = getUserOrderInfo(oid);
        userOrderInfo.setUserOrderDetails(getUserOrderDetails(oid));
        return userOrderInfo;
    }

    private ReserveOrderInfo getUserOrderReserveInfoAndDetails(int roid){
        ReserveOrderInfo userOrderInfo = getReserveOrderInfo(roid);
        userOrderInfo.setReserveOrderDetails(getReserveOrderDetails(roid));
        return userOrderInfo;
    }

    @Override
    public List<UserOrderDetail> getUserOrderDetails(int oid){
        return baseDao.queryByHql("FROM UserOrderDetail WHERE oid = ?1", oid);
    }

    @Override
    public synchronized Integer saveRechargeOrderInfo(UserOrderInfo userOrderInfo, SysDict sysDict, int uid) {
        Timestamp nowDate = DateUtil.getNowDate();
        userOrderInfo.setUid(uid);
        userOrderInfo.setCreateTime(nowDate);
        userOrderInfo.setOrderType(IDBConstant.LOGIC_STATUS_NO); //充值
        userOrderInfo.setPayStatus(IDBConstant.LOGIC_STATUS_NO);
        userOrderInfo.setStatus(IDBConstant.LOGIC_STATUS_YES);
        userOrderInfo.setOno(getOno());
        baseDao.save(userOrderInfo, null);

        int oid = userOrderInfo.getOid();

        //充值金额
        UserOrderDetail userOrderDetail = new UserOrderDetail();
        userOrderDetail.setCreateTime(nowDate);
        userOrderDetail.setItemCount(1);
        userOrderDetail.setItemName("充值金额");
        userOrderDetail.setItemPrice(userOrderInfo.getPriceSum());
        userOrderDetail.setItemPriceSum(userOrderDetail.getItemPrice());
        userOrderDetail.setOid(oid);
        baseDao.save(userOrderDetail, null);

        //赠送金额
        userOrderDetail = new UserOrderDetail();
        userOrderDetail.setItemType(IDBConstant.LOGIC_STATUS_NO); //充值赠送类型
        userOrderDetail.setCreateTime(nowDate);
        userOrderDetail.setItemCount(1);
        userOrderDetail.setItemName("赠送金额");
        userOrderDetail.setItemPrice(Arith.conversion(StrUtil.objToDoubleDef0(sysDict.getDictAdditional())));
        userOrderDetail.setItemPriceSum(userOrderDetail.getItemPrice());
        userOrderDetail.setOid(oid);
        baseDao.save(userOrderDetail, null);

        return oid;
    }

    private boolean existOrderByNo(String ono){
        return baseDao.queryBySqlFirst("SELECT 1 FROM user_order_info WHERE ono = ?1", ono) != null;
    }

    @Desc("生成不重复的订单编号")
    private String getOno(){
        //生成重复的订单编号
        String ono = IPlatformConstant.ONO + StrUtil.objToStr(StrUtil.initCode(6));
        while (existOrderByNo(ono)){
            ono = IPlatformConstant.ONO + StrUtil.objToStr(StrUtil.initCode(6));
        }
        return ono;
    }

    @Override
    public synchronized Integer saveOrderInfo(UserOrderInfo userOrderInfo, String scids, int uid) throws ParseException{ //购物车ids，多个逗号分隔
        Timestamp nowDate = DateUtil.getNowDate();
        userOrderInfo.setUid(uid);
        userOrderInfo.setCreateTime(nowDate);
        userOrderInfo.setOrderType(IDBConstant.LOGIC_STATUS_YES);
        userOrderInfo.setPayStatus(IDBConstant.ORDER_PAY_STATUS_NO);
        userOrderInfo.setStatus(IDBConstant.ORDER_STATUS_NORMAL);
        userOrderInfo.setOno(getOno()); //订单编号
        baseDao.save(userOrderInfo, null);

        int oid = userOrderInfo.getOid();
        String[] scidArr = scids.split(",");
        double priceSum = 0;    //总价
        int commodityCount = 0; //订单商品总数量
        for(String scid : scidArr) { //待处理积分与优惠券问题【已处理】
            UserShoppingCart userShoppingCart = userShoppingCartService.getUserShoppingCart(StrUtil.objToInt(scid));
            Integer cid = userShoppingCart.getCid();
            UserOrderDetail userOrderDetail = new UserOrderDetail();
            userOrderDetail.setCreateTime(nowDate);
            userOrderDetail.setCid(cid);

            CommodityInfo commodityInfo = commodityService.getCommodityInfo(cid);
            userOrderDetail.setItemCount(userShoppingCart.getCount());
            userOrderDetail.setItemName(commodityInfo.getCommName());
            userOrderDetail.setItemPrice(commodityInfo.getCouPrice());

            CommodityColor commodityColor = commodityService.getCommodityColorByCid(cid);
            userOrderDetail.setItemColor(commodityColor.getColorName());

            CommoditySize commoditySize = commodityService.getCommoditySize(userShoppingCart.getSid());
            userOrderDetail.setSid(commoditySize.getSid());
            userOrderDetail.setItemSize(commoditySize.getSize());

            userOrderDetail.setItemPriceSum(Arith.conversion(Arith.mul(commodityInfo.getCouPrice().doubleValue(), userShoppingCart.getCount())));
            userOrderDetail.setOid(oid);
            userOrderDetail.setItemImg(commodityService.getFmImg(cid, false));
            baseDao.save(userOrderDetail, null);

            priceSum = Arith.add(priceSum, userOrderDetail.getItemPriceSum().doubleValue());

            //删除购物车
            baseDao.delete(userShoppingCart);

            //减少库存
            Integer stock = commoditySize.getStock();
            commoditySize.setStock(stock - userOrderDetail.getItemCount());
            if(stock <= 0) throw new MessageException("存在不足,当前库存：" + stock);
            baseDao.save(commoditySize, commoditySize.getSid());

            commodityCount += userOrderDetail.getItemCount();
        }

        userOrderInfo.setPriceSum(Arith.conversion(priceSum));  //商品原总价
        //支付价格：之后减去优惠部分
        double sumPrice = userOrderInfo.getPriceSum().doubleValue();
        String serviceType = userOrderInfo.getServiceType();
        DiscountBean discountBean = userShoppingCartService.concessionalPrice(sumPrice, serviceType, userOrderInfo.getCpid(), uid, commodityCount);

        userOrderInfo.setFreight(Arith.conversion(discountBean.getFreight()));
        userOrderInfo.setPayPrice(Arith.conversion(discountBean.getSumPrice())); //支付金额
        baseDao.save(userOrderInfo, oid);

        updateUserBelongings(userOrderInfo, discountBean); //下单时，处理用户衣橱币，优惠券，余额等信息

        return oid;
    }

    @Override
    public Map<String, Object> getRfidSettlement(Map<String, Object> data, int uid) throws ParseException{
        Double sumPrice = StrUtil.objToDouble(data.get("sumPrice"));
        DiscountBean discountBean = userShoppingCartService.concessionalPrice(sumPrice, null, null, uid, null);
        data.putAll(JsonUtils.fromJson(discountBean));
        UserAccount userAccount = userAccountService.getUserAccount(uid);
        data.put("ycoid", userAccount.getYcoid());
        data.put("coupons", userCouponService.getUserEffectiveCoupons(uid, discountBean.getSumOldDisPrice()));
        return data;
    }

    @Override
    public synchronized Integer saveRfidOrderInfo(UserOrderInfo userOrderInfo, String dbids, int uid) throws ParseException{ //配送ids，多个逗号分隔
        Timestamp nowDate = DateUtil.getNowDate();
        userOrderInfo.setUid(uid);
        userOrderInfo.setCreateTime(nowDate);
        userOrderInfo.setOrderType(IDBConstant.LOGIC_STATUS_OTHER); //射频订单
        userOrderInfo.setPayStatus(IDBConstant.ORDER_PAY_STATUS_NO);
        userOrderInfo.setStatus(IDBConstant.ORDER_STATUS_NORMAL);
        userOrderInfo.setOno(getOno()); //订单编号
        baseDao.save(userOrderInfo, null);

        int oid = userOrderInfo.getOid();
        String[] cidArr = dbids.split(",");
        double priceSum = 0;
        for(String dbid : cidArr) { //待处理积分与优惠券问题【已处理】
            SysCommodityDistribution sysCommodityDistribution = baseDao.getToEvict(SysCommodityDistribution.class, StrUtil.objToInt(dbid));
            Integer cid = sysCommodityDistribution.getCid();
            UserOrderDetail userOrderDetail = new UserOrderDetail();
            userOrderDetail.setCreateTime(nowDate);
            userOrderDetail.setCid(cid);
            userOrderDetail.setDbid(StrUtil.objToInt(dbid));

            CommodityInfo commodityInfo = commodityService.getCommodityInfo(cid);
            userOrderDetail.setItemCount(1);
            userOrderDetail.setItemName(commodityInfo.getCommName());
            userOrderDetail.setItemPrice(commodityInfo.getCouPrice());

            CommodityColor commodityColor = commodityService.getCommodityColorByCid(cid);
            userOrderDetail.setItemColor(commodityColor.getColorName());

            CommoditySize commoditySize = commodityService.getCommoditySize(sysCommodityDistribution.getSid());
            userOrderDetail.setSid(commoditySize.getSid());
            userOrderDetail.setItemSize(commoditySize.getSize());

            userOrderDetail.setItemPriceSum(Arith.conversion(Arith.mul(commodityInfo.getCouPrice().doubleValue(), 1)));
            userOrderDetail.setOid(oid);
            userOrderDetail.setItemImg(commodityService.getFmImg(cid, false));
            baseDao.save(userOrderDetail, null);

            priceSum = Arith.add(priceSum, userOrderDetail.getItemPriceSum().doubleValue());

            //减少库存
            Integer stock = commoditySize.getStock();
            commoditySize.setStock(stock - userOrderDetail.getItemCount());
            if(stock <= 0) throw new MessageException("存在不足,当前库存：" + stock);
            baseDao.save(commoditySize, commoditySize.getSid());
        }

        userOrderInfo.setPriceSum(Arith.conversion(priceSum));  //商品原总价
        //支付价格：之后减去优惠部分
        double sumPrice = userOrderInfo.getPriceSum().doubleValue();

        String serviceType = userOrderInfo.getServiceType();
        DiscountBean discountBean = userShoppingCartService.concessionalPrice(sumPrice, serviceType, userOrderInfo.getCpid(), uid, null);
        userOrderInfo.setPayPrice(Arith.conversion(discountBean.getSumPrice()));
        baseDao.save(userOrderInfo, oid);

        updateUserBelongings(userOrderInfo, discountBean); //下单时，处理用户衣橱币，优惠券，余额等信息


        return oid;
    }

    @Desc("下单时，处理用户衣橱币，优惠券，余额等信息")
    private void updateUserBelongings(UserOrderInfo userOrderInfo, DiscountBean discountBean) throws ParseException {
        //优惠券置为使用状态
        userCouponService.updateUseUserCouponInfo(discountBean.getCpid());

        //衣橱币减去
        int userYcoid = discountBean.getUseYcoid();
        //减去用户衣橱币
        userAccountService.updateUserYcoid(userYcoid, userOrderInfo.getUid());
        if (discountBean.getUseYcoid() > 0) {
            userOrderInfo.setYcoid(userYcoid);
            baseDao.save(userOrderInfo, userOrderInfo.getOid());
        }
        if (IDBConstant.LOGIC_STATUS_NO.equals(userOrderInfo.getPayType())) { //余额支付：//再次判断余额是否足够
            UserAccount userAccount = userAccountService.getUserAccount(userOrderInfo.getUid());
            if (userAccount.getBalance().doubleValue() < userOrderInfo.getPayPrice().doubleValue())
                throw new MessageException("余额不足，请选择微信支付或充值足够后再试~");
        }
    }

    private boolean existReserveOrderByNo(String rno){
        return baseDao.queryBySqlFirst("SELECT 1 FROM reserve_order_info WHERE rno = ?1", rno) != null;
    }

    @Desc("生成不重复的预约单编号")
    private String getRno(){
        //生成重复的订单编号
        String rno = IPlatformConstant.RNO + StrUtil.objToStr(StrUtil.initCode(6));
        while (existReserveOrderByNo(rno)){
            rno = IPlatformConstant.RNO + StrUtil.objToStr(StrUtil.initCode(6));
        }
        return rno;
    }

    @Desc("规则是每天17点之前下单，可以约次日以后，17点以后下单可以约隔日以后")
    @Override
    public synchronized Integer saveReserveOrderInfo(ReserveOrderInfo orderInfo, String scids, int uid) throws ParseException{ //购物车ids，多个逗号分隔
        if(StrUtil.isBlank(scids)) throw new MessageException("操作错误!");
        Integer did = orderInfo.getDid();

        SysDeviceInfo sysDeviceInfo = sysDeviceService.getDeviceInfo(did);
        if(sysDeviceInfo == null) throw new MessageException("当前试衣间不存在，请重新选择！");

        if(!IDBConstant.LOGIC_STATUS_YES.equals(sysDeviceInfo.getStatus())) throw new MessageException("当前试衣间未开启，请稍候再试！");

        Timestamp reserveStartTime = orderInfo.getReserveStartTime();
        Timestamp reserveEndTime = orderInfo.getReserveEndTime();

        Timestamp nowDate = DateUtil.getNowDate();
        Date date = new Date(nowDate.getTime());
        //只能预约第二天，每天只能预约一单
        String startDateStr = DateUtil.dateToString(new Date(reserveStartTime.getTime()), DateUtil.YYYYMMDD);
        int startDateHouse = StrUtil.objToInt(DateUtil.dateToString(new Date(reserveStartTime.getTime()), DateUtil.HH));
        Date rd = DateUtil.initDateByDay(new Date(reserveStartTime.getTime()));

        if(startDateHouse < 17) { //每天17点之前下单，可以约次日以后
            Date tomorrow = DateUtil.initDateByDay(DateUtil.addDate(date, 1)); //明天0点数据
            Date after15 = DateUtil.initDateByDay(DateUtil.addDate(date, 15)); //之后15天
            if (rd.getTime() < tomorrow.getTime() || rd.getTime() > after15.getTime()) throw new MessageException("预约17点之前的只能预约明天及之后的14天！");
        }else{ //17点以后下单可以约隔日以后
            Date afterTomorrow = DateUtil.initDateByDay(DateUtil.addDate(date, 2)); //后天0点数据
            Date after16 = DateUtil.initDateByDay(DateUtil.addDate(date, 16)); //之后16天
            if (rd.getTime() < afterTomorrow.getTime() || rd.getTime() > after16.getTime()) throw new MessageException("预约17点以后的只能预约后天及之后的15天！");
        }
        ReserveOrderInfo existNowReserve = isExistNowReserve(startDateStr, uid);
        if(existNowReserve != null) throw new MessageException("您已经预约了" + DateUtil.dateToString(new Date(existNowReserve.getReserveStartTime().getTime()), DateUtil.YYYYMMDDHHMM) + "到" +  DateUtil.dateToString(new Date(existNowReserve.getReserveEndTime().getTime()), DateUtil.YYYYMMDDHHMM) + "，不能重复预约");

        String startTime = sysDeviceInfo.getStartTime();
        String endTime = sysDeviceInfo.getEndTime();
        Date startDTime = DateUtil.getHHMM(startTime);
        Date endDTime = DateUtil.getHHMM(endTime);
        if(DateUtil.getHHMMSS(reserveStartTime).getTime() < startDTime.getTime() || DateUtil.getHHMMSS(reserveEndTime).getTime() > endDTime.getTime()) throw new MessageException("试衣间开启时间为：" + startTime + "到" + endTime);

        ReserveOrderInfo reserveOrderInfo = isReserveByDate(reserveStartTime, reserveEndTime);
        if(reserveOrderInfo != null) throw new MessageException(DateUtil.dateToString(reserveOrderInfo.getReserveStartTime(), DateUtil.YYYYMMDDHHMM)+"至"+DateUtil.dateToString(reserveOrderInfo.getReserveEndTime(), DateUtil.YYYYMMDDHHMM)+"已有其他用户预约使用该配衣间，请选择其他时间！");

        SysDeviceControl sysDeviceControl = sysDeviceService.getDistributionDeviceControl(did, reserveStartTime, reserveEndTime);
        if(sysDeviceControl == null) throw new MessageException("当前预约已满，请稍候再试！");

        orderInfo.setDcid(sysDeviceControl.getDcid());
        orderInfo.setUid(uid);
        orderInfo.setCreateTime(nowDate);
        orderInfo.setPayStatus(IDBConstant.ORDER_PAY_STATUS_NO);
        orderInfo.setStatus(IDBConstant.ORDER_STATUS_NORMAL);
        orderInfo.setRno(getRno()); //预约单编号
        baseDao.save(orderInfo, null);

        java.sql.Date dbDate = new java.sql.Date(DateUtil.initDateByDay(new Date(reserveStartTime.getTime())).getTime());
        int oid = orderInfo.getRoid();
        String[] scidArr = scids.split(",");
        double priceSum = 0;
        for(String scid : scidArr) { //待处理积分与优惠券问题
            UserShoppingCart userShoppingCart = userShoppingCartService.getUserShoppingCart(StrUtil.objToInt(scid));
            Integer cid = userShoppingCart.getCid();
            ReserveOrderDetail orderDetail = new ReserveOrderDetail();
            orderDetail.setCreateTime(nowDate);
            orderDetail.setCid(cid);

            CommodityInfo commodityInfo = commodityService.getCommodityInfo(cid);
            orderDetail.setResItemCount(userShoppingCart.getCount());
            orderDetail.setResItemName(commodityInfo.getCommName());
            orderDetail.setResItemPrice(commodityInfo.getCouPrice());

            CommodityColor commodityColor = commodityService.getCommodityColorByCid(cid);
            orderDetail.setResItemColor(commodityColor.getColorName());

            CommoditySize commoditySize = commodityService.getCommoditySize(userShoppingCart.getSid());
            orderDetail.setSid(commoditySize.getSid());
            orderDetail.setResItemSize(commoditySize.getSize());

            orderDetail.setResItemPriceSum(Arith.conversion(Arith.mul(commodityInfo.getCouPrice().doubleValue(), userShoppingCart.getCount())));
            orderDetail.setRoid(oid);
            orderDetail.setResItemImg(commodityService.getFmImg(cid, false));
            baseDao.save(orderDetail, null);

            priceSum = Arith.add(priceSum, orderDetail.getResItemPriceSum().doubleValue());

            //预约购物车商品变为普通购物车商品
            //userShoppingCart.setShoppingType(IDBConstant.LOGIC_STATUS_YES);
            //删除购物车
            baseDao.delete(userShoppingCart);

            //放入到配送表
            SysCommodityDistribution commodityDistribution = new SysCommodityDistribution();
            commodityDistribution.setCid(cid);
            commodityDistribution.setSid(commoditySize.getSid());
            commodityDistribution.setDcid(orderInfo.getDcid());
            commodityDistribution.setDbTime(dbDate);
            commodityDistribution.setRoid(oid);
            commodityDistribution.setStatus(IDBConstant.LOGIC_STATUS_YES);
            commodityDistribution.setCreateTime(nowDate);
            baseDao.save(commodityDistribution, null);
        }

        orderInfo.setPriceSum(Arith.conversion(priceSum));  //商品原总价
        orderInfo.setPayPrice(orderInfo.getPriceSum()); //支付价格：之后减去优惠部分
        baseDao.save(orderInfo, oid);
        return oid;
    }

    @Desc("预约时间是否有交集")
    private ReserveOrderInfo isReserveByDate(Timestamp startDate, Timestamp endDate){
        ReserveOrderInfo reserveOrderInfo = baseDao.queryByHqlFirst("FROM ReserveOrderInfo r WHERE NOT (r.reserveEndTime <= ?1 OR r.reserveStartTime >= ?2) AND r.status = ?3", startDate, endDate, IDBConstant.LOGIC_STATUS_YES);
        return reserveOrderInfo;
    }

    @Desc("存在当前时间之前的预约，则不能进行预约")
    private ReserveOrderInfo isExistNowReserve(String reserveStartTime, int uid) throws ParseException{
        return baseDao.queryByHqlFirst("SELECT r FROM ReserveOrderInfo r WHERE DATE(r.reserveStartTime) = ?1 AND r.uid = ?2 AND r.status = ?3 ORDER BY r.roid DESC", DateUtil.stringToDate(reserveStartTime, DateUtil.YYYYMMDD), uid, IDBConstant.LOGIC_STATUS_YES);
    }

    @Desc("获得未支付并在当前时间之前的最后一个未支付的订单（一个时间段只能预约一次）")
    @Override
    public Map<String, Object> getNowReserveOrderInfo(int uid){
        StringBuilder sql = new StringBuilder("SELECT sd.did, sdc.dcid, r.roid, DATE(r.reserveStartTime) resDate, TIME(r.reserveStartTime) resStartTime, TIME(r.reserveEndTime) resEndTime, sd.name sdName, sd.address, a.areaNameFull, sdc.name sdcName FROM reserve_order_info r, sys_device_control sdc, sys_device_info sd, sys_area a");
        sql.append(" WHERE r.dcid = sdc.dcid AND sdc.did = sd.did AND sd.areaId = a.areaId AND r.reserveEndTime >= NOW() AND r.uid = ?1 AND r.status = ?2 AND r.payStatus = ?3 ORDER BY r.roid DESC");
        List<Map<String, Object>> list = baseDao.queryBySql(sql.toString(), uid, IDBConstant.LOGIC_STATUS_YES, IDBConstant.LOGIC_STATUS_NO);
        list.parallelStream().forEach(map -> {
            String startTime = StrUtil.objToStr(map.get("resStartTime"));
            String endTime = StrUtil.objToStr(map.get("resEndTime"));
            map.put("resStartTime", startTime.substring(0, startTime.lastIndexOf(":")));
            map.put("resEndTime", endTime.substring(0, endTime.lastIndexOf(":")));
            map.put("areaNameFull", StrUtil.objToStr(map.get("areaNameFull")).replace("->", " "));
        });
        Map<String, Object> data = new HashMap();
        data.put("list", list);
        return data;
    }

    @Override
    public Map<String, Object> getNowReserveOrderDetail(int uid, int roid){
        Map<String, Object> data = new HashMap();
        List<Map<String, Object>> list = baseDao.queryBySql("SELECT rod.* FROM reserve_order_detail rod, reserve_order_info r  WHERE rod.roid = r.roid AND r.uid = ?1 AND rod.roid = ?2", new Integer[]{uid, roid});
        double sumPrice = 0;
        for(Map<String, Object> map : list){
            map.put("resItemImg", resourceService.parseImgPath(StrUtil.objToStr(map.get("resItemImg"))));
            sumPrice = Arith.add(sumPrice, StrUtil.objToDouble(map.get("resItemPriceSum")));
        }
        data.put("sumPrice", sumPrice);
        data.put("list", list);
        return data;
    }

    @Override
    public ReserveOrderInfo getLastReserveOrderInfo(int uid){
        return baseDao.queryByHqlFirst("FROM ReserveOrderInfo r WHERE r.uid = ?1 ORDER BY r.roid DESC LIMIT 1", uid);
    }

    @Override
    public void saveCancelOrder(int oid, int uid) throws Exception{
        UserOrderInfo userOrderInfo = getUserOrderInfoAndDetails(oid);
        if(userOrderInfo.getUid() != uid) throw new MessageException("错误");
        userOrderInfo.setPayStatus(IDBConstant.LOGIC_STATUS_OTHER); //取消订单
        userOrderInfo.setUpdateTime(DateUtil.getNowDate());
        baseDao.save(userOrderInfo, oid);
        //取消订单恢复---》优惠券返还，衣橱币返还，库存还原
        updateOrderHander(userOrderInfo);
    }

    @Override
    public void saveCancelReserveOrder(int roid, int uid){
        ReserveOrderInfo reserveOrderInfo = getReserveOrderInfo(roid);
        if(reserveOrderInfo.getUid() != uid) throw new MessageException("错误");
        reserveOrderInfo.setStatus(IDBConstant.LOGIC_STATUS_NO); //取消预约
        reserveOrderInfo.setUpdateTime(DateUtil.getNowDate());
        baseDao.save(reserveOrderInfo, roid);

        //移除此预约柜子
        deleteRemoveCommodityDistribution(roid);
    }

    private List<SysCommodityDistribution> getCommodityDistributionsByRoid(int roid){
        return baseDao.queryByHql("FROM SysCommodityDistribution WHERE roid = ?1", roid);
    }

    @Desc("移除此预约柜子")
    private void deleteRemoveCommodityDistribution(int roid){
        //移除柜子
        List<SysCommodityDistribution> commodityDistributions = getCommodityDistributionsByRoid(roid);
        if(commodityDistributions != null && commodityDistributions.size() > 0){
            commodityDistributions.stream().forEach(commodityDistribution -> {
                baseDao.delete(commodityDistribution);
            });
        }
    }

    @Override
    public Map<Object, Object> saveWxPayPackage(OrderInputView orderInputView, String openId) throws Exception {
        Integer oId = orderInputView.getOrderId();

        UserOrderInfo orderInfo = getUserOrderInfo(oId); //openId = "oTK5utzXi_A2q8aus80Y60__LzY0";
        if(orderInfo == null || IDBConstant.LOGIC_STATUS_YES.equals(orderInfo.getPayStatus())) return null; //订单不存在或者已经支付的状态，则直接返回

        Integer uid = orderInfo.getUid();
        synchronized (orderInfo.getOno().intern()) {
            // 1 参数
            // 订单号
            String orderId = orderInfo.getOid() + "_" + System.currentTimeMillis();
            // 附加数据 原样返回
            String attach = "cxs";
            double payPrice = orderInfo.getPayPrice().doubleValue();
            // 总金额以分为单位，不带小数点
            if (payPrice <= 0 || IDBConstant.LOGIC_STATUS_NO.equals(orderInfo.getPayType())) { //无需支付
                if (IDBConstant.LOGIC_STATUS_NO.equals(orderInfo.getPayType())) { //余额支付
                    //再次判断余额是否足够
                    UserAccount userAccount = userAccountService.getUserAccount(uid);
                    if(userAccount.getBalance().doubleValue() < payPrice) throw new MessageException("余额不足，请选择微信支付或充值足够后再试。");
                    userAccount.setBalance(Arith.conversion(Arith.sub(userAccount.getBalance().doubleValue(), payPrice)));
                    baseDao.save(userAccount, userAccount.getUid());
                    //增加支付金额的衣橱币与积分
                    userAccountService.addUserScoreAndYcoid(uid, payPrice);
                    //交易流水
                    userTransactionsService.addOrderUserTransactions(orderInfo, IDBConstant.TRANSACTIONS_SERVICE_TYPE_ZF, IDBConstant.LOGIC_STATUS_NO); //余额支付
                }
                orderInfo.setPayStatus(IDBConstant.LOGIC_STATUS_YES);
                orderInfo.setPayTime(DateUtil.getNowDate());
                baseDao.save(orderInfo, oId);
                return new HashMap() {{
                    put("ok", IDBConstant.LOGIC_STATUS_YES);
                }};
            }

            String totalFee = StrUtil.getMoney(StrUtil.objToStr(payPrice)); //这里是支付金额

            // 订单生成的机器 IP
            String spbill_create_ip = "127.0.0.1";
            // 这里notify_url是 支付完成后微信发给该链接信息，可以判断会员是否支付成功，改变订单状态等。
            String notify_url = ConfigUtil.NOTIFY_URL;
            String trade_type = "JSAPI";

            // ---必须参数
            // 随机字符串
            String nonce_str = StrUtil.getNonceStr();

            // 商品描述根据情况修改
            String body = IDBConstant.TRANSACTIONS_SERVICE_TYPE_ZF.equals(orderInfo.getOrderType()) ? "商品购买" : "充值";

            // 商户订单号
            String out_trade_no = orderId;

            SortedMap<Object, Object> packageParams = new TreeMap<Object, Object>();
            packageParams.put("appid", ConfigUtil.APPID);
            packageParams.put("mch_id", ConfigUtil.MCH_ID);  //商户号
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
            String packages = "prepay_id=" + prepay_id;
            finalpackage.put("appId", ConfigUtil.APPID);
            finalpackage.put("timeStamp", timestamp);
            finalpackage.put("nonceStr", nonce_str);
            finalpackage.put("package", packages);
            finalpackage.put("signType", ConfigUtil.SIGN_TYPE);

            //要签名
            String finalsign = PayCommonUtil.createSign("UTF-8", finalpackage);
            finalpackage.put("paySign", finalsign);

            String finaPackage = "\"appId\":\"" + ConfigUtil.APPID + "\",\"timeStamp\":\"" + timestamp
                    + "\",\"nonceStr\":\"" + nonce_str + "\",\"package\":\""
                    + packages + "\",\"signType\" : \"MD5" + "\",\"paySign\":\""
                    + finalsign + "\"";
       /* timeStamp: param.data.timeStamp,//记住，这边的timeStamp一定要是字符串类型的，不然会报错，我这边在java后端包装成了字符串类型了
                nonceStr: param.data.nonceStr,
        package: param.data.package,
        signType: 'MD5',
                paySign: param.data.paySign,*/
            logger.info("V3 jsApi package:" + finaPackage);
            finalpackage.put("finaPackage", finaPackage);
            return finalpackage;
            //logger.info("V3 jsApi package:"+finaPackage);
            //return finaPackage;
        }
    }

    @Override
    public synchronized void saveAsynNotify(String msgxml, HttpServletResponse response) throws Exception{
        Map map = XMLUtil.doXMLParse(msgxml);

        String result_code=(String) map.get("result_code");
        String out_trade_no  = (String) map.get("out_trade_no");
        String total_fee  = (String) map.get("total_fee");
        String sign  = (String) map.get("sign");
        Double amount = new Double(total_fee) / 100;//获取订单金额
        System.out.println("saveAsynNotify===>" + result_code);
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
            packageParams.put("appid", ConfigUtil.APPID);
            packageParams.put("bank_type", bank_type);
            packageParams.put("cash_fee", cash_fee);
            packageParams.put("fee_type", fee_type);
            packageParams.put("is_subscribe", is_subscribe);
            packageParams.put("mch_id", ConfigUtil.MCH_ID);
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
            System.out.println("packageParams===>" + packageParams);
            String endsign = PayCommonUtil.createSign("UTF-8", packageParams);
            System.out.println("qianming_1");
            if(sign.equals(endsign)){//验证签名是否正确  官方签名工具地址：https://pay.weixin.qq.com/wiki/tools/signverify/
                System.out.println("qianming_1_success");
                //处理自己的业务逻辑
                logger.info("支付成功!");
                Integer oId = StrUtil.objToInt(out_trade_no.substring(0, out_trade_no.indexOf("_")));
                UserOrderInfo userOrderInfo = getUserOrderInfoAndDetails(oId);
                Date nowDate = new Date();
                if(!IDBConstant.LOGIC_STATUS_YES.equals(userOrderInfo.getPayStatus())){ //未支付状态
                    userOrderInfo.setPayStatus(IDBConstant.LOGIC_STATUS_YES);
                    userOrderInfo.setPayTime(DateUtil.getNowDate());
                    baseDao.save(userOrderInfo, userOrderInfo.getOid());

                    Integer uid = userOrderInfo.getUid();
                    String orderType = userOrderInfo.getOrderType();
                    //普通订单或射频订单
                    if(IDBConstant.LOGIC_STATUS_YES.equals(orderType) || IDBConstant.LOGIC_STATUS_OTHER.equals(orderType)) {
                        //累加积分与衣橱币(衣米)
                        //每消费或充值1元，获得1积分。消费金额按照商品订单实际支付金额（仅微信支付）计算，舍弃订单金额小数位。
                        //每消费100元，获得1衣米。消费金额按照商品订单实际支付金额（仅微信支付）计算，按照订单金额向下取整，如：支付199元，获得1衣米。
                        //1衣米 = 1元。使用衣米支付时，订单元以下金额 = 1衣米。
                        double payPrice = userOrderInfo.getPayPrice().doubleValue();
                        userAccountService.addUserScoreAndYcoid(uid, payPrice);

                        //写入库存日志
                        commodityService.saveOrderSubStock(userOrderInfo);

                        //写入商品已售多少件
                        commodityService.saveCommoditySaleCount(userOrderInfo);

                        //交易流水(支付普通)
                        userTransactionsService.addOrderUserTransactions(userOrderInfo, IDBConstant.TRANSACTIONS_SERVICE_TYPE_ZF, userOrderInfo.getPayType());

                        //邀请好友注册一个月以内第一笔消费，给推荐人赠送1%薏米（消费1000元，送推荐人10薏米）
                        UserInfo userInfo = userService.getUserInfo(uid);
                        Date dateBefore30 = DateUtil.addDate(nowDate, -30);
                        if(userInfo.getInvitedBy() != null && userInfo.getRegisterTime().getTime() >= dateBefore30.getTime() && getUserPayOrderCount(uid) == 1){ //有邀请人，并且申请时间在30天之内
                            userAccountService.setUserYcoid(userInfo.getInvitedBy(), payPrice);
                        }

                        //处理配送改为已售出
                        if(IDBConstant.LOGIC_STATUS_OTHER.equals(orderType)){
                            List<UserOrderDetail> userOrderDetails = userOrderInfo.getUserOrderDetails();
                            if(userOrderDetails != null) {
                                for (UserOrderDetail ud : userOrderDetails) {
                                    if (ud != null) {
                                        SysCommodityDistribution commodityDistribution = baseDao.getToEvict(SysCommodityDistribution.class, ud.getDbid());
                                        if (commodityDistribution != null) {
                                            commodityDistribution.setStatus(IDBConstant.LOGIC_STATUS_NO);
                                            baseDao.save(commodityDistribution, commodityDistribution.getDcid());
                                        }
                                    }
                                }
                            }
                        }
                    }else if(IDBConstant.LOGIC_STATUS_NO.equals(orderType)){ //充值订单
                        //增加用户账号余额
                        userAccountService.updateRechargePrice(userOrderInfo);
                        //交易流水(充值)
                        userTransactionsService.addOrderUserTransactions(userOrderInfo, IDBConstant.TRANSACTIONS_SERVICE_TYPE_CZ, null); //充值不拼接type
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

    @Desc("用户已支付订单数量")
    private int getUserPayOrderCount(int uid){
        return baseDao.getUniqueResult("SELECT COUNT(1) FROM user_order_info WHERE uid = ? AND payStatus = ?", uid, IDBConstant.LOGIC_STATUS_YES).intValue();
    }

    @Override
    public PageBean getUserOrderList(OrderInputView orderInputView){
        PageBean pageBean = getUserOrders(orderInputView);
        List<Map<String, Object>> list = pageBean.getList();
        for(Map<String, Object> map : list){
            List<UserOrderDetail> userOrderDetails = getUserOrderDetails(StrUtil.objToInt(map.get("oid")));
            userOrderDetails.parallelStream().forEach(userOrderDetail -> {
                userOrderDetail.setItemImg(resourceService.parseImgPath(userOrderDetail.getItemImg()));
            });
            map.put("orderDetails", userOrderDetails);
        }
        return pageBean;
    }

    @Override
    public Map<String, Object> getUserOrderDetail(int oid, int uid){
        UserOrderInfo userOrderInfo = getUserOrderInfo(oid);
        if(userOrderInfo.getUid() != uid) throw new MessageException("操作错误!!!");
        List<UserOrderDetail> userOrderDetails = getUserOrderDetails(oid);
        userOrderDetails.parallelStream().forEach(userOrderDetail -> {
            userOrderDetail.setItemImg(resourceService.parseImgPath(userOrderDetail.getItemImg()));
        });

        Map<String, Object> data = new HashMap<>(2, 1);
        data.put("order", userOrderInfo);
        data.put("orderDetails", userOrderDetails);
        return data;
    }

    private PageBean getUserOrders(OrderInputView orderInputView){
        Integer uid = orderInputView.getUid();
        StringBuilder headSql = new StringBuilder("SELECT *");
        StringBuilder bodySql = new StringBuilder(" FROM user_order_info uoi");
        StringBuilder whereSql = new StringBuilder(" WHERE 1=1");
        if(uid != null){
            whereSql.append(" AND uoi.uid = :uid");
        }
        whereSql.append(" AND uoi.orderType != '").append(IDBConstant.LOGIC_STATUS_NO).append("'"); //只查询购物的订单(1：普通订单，2：充值，3：射频订单)
        whereSql.append(" ORDER BY uoi.createTime DESC");
        return super.getPageBean(headSql, bodySql, whereSql, orderInputView);
    }

    private List<Map<String, Object>> getUserOrderDetail(int oid){
        return baseDao.queryBySql("SELECT * FROM user_order_detail WHERE oid = ?1", oid);
    }

    @Override
    public PageBean getUserOrderListIn(OrderInputView orderInputView){
        PageBean pageBean = getUserOrdersIn(orderInputView);
        List<Map<String, Object>> list = pageBean.getList();
        list.parallelStream().forEach(map -> {
            map.put("payStatusName", dictService.getDictValue(IDBConstant.ORDER_PAY_STATUS, StrUtil.objToStr(map.get("payStatus"))));
            map.put("statusName", dictService.getDictValue(IDBConstant.ORDER_STATUS, StrUtil.objToStr(map.get("status"))));
        });
        return pageBean;
    }

    @Override
    public Map<String, Object> getUserOrderDetailIn(int oid){
        UserOrderInfo userOrderInfo = getUserOrderInfoAndDetails(oid);
        List<UserOrderDetail> userOrderDetails = userOrderInfo.getUserOrderDetails();
        userOrderDetails.parallelStream().forEach(userOrderDetail -> {
            userOrderDetail.setItemImg(resourceService.parseImgPath(userOrderDetail.getItemImg()));
        });
        userOrderInfo.setPayStatus(dictService.getDictValue(IDBConstant.ORDER_PAY_STATUS, userOrderInfo.getPayStatus()));
        userOrderInfo.setStatus(dictService.getDictValue(IDBConstant.ORDER_STATUS, userOrderInfo.getStatus()));

        Map<String, Object> data = new HashMap<>(2, 1);
        data.put("order", userOrderInfo);
        data.put("couponDesc", userCouponService.getUserCouponDescIn(userOrderInfo.getCpid()));
        return data;
    }

    @Override
    public Map<String, Object> getUserOrderReservationDetailIn(int roid){
        ReserveOrderInfo userOrderInfo = getUserOrderReserveInfoAndDetails(roid);
        List<ReserveOrderDetail> userOrderDetails = userOrderInfo.getReserveOrderDetails();
        userOrderDetails.parallelStream().forEach(userOrderDetail -> {
            userOrderDetail.setResItemImg(resourceService.parseImgPath(userOrderDetail.getResItemImg()));
        });
        userOrderInfo.setStatus(dictService.getDictValue(IDBConstant.RESERVE_ORDER_STATUS, userOrderInfo.getStatus()));

        Map<String, Object> data = new HashMap<>(1, 1);
        data.put("order", userOrderInfo);
        return data;
    }

    private PageBean getUserOrdersIn(OrderInputView orderInputView){
        String status = orderInputView.getStatus();
        String ono = orderInputView.getOno();
        StringBuilder headSql = new StringBuilder("SELECT uoi.*, ui.nickname");
        StringBuilder bodySql = new StringBuilder(" FROM user_order_info uoi, user_info ui");
        StringBuilder whereSql = new StringBuilder(" WHERE uoi.uid = ui.uid");
        Map inMap = new HashMap<>();
        if(StrUtil.isNotBlank(status)){
            whereSql.append(" AND uoi.status IN(:statusArr)");
            inMap.putAll(SQLUtil.getInToSQL("statusArr", status));
        }
        if(StrUtil.isNotBlank(ono)){
            whereSql.append(" AND uoi.ono = :ono");
        }
        whereSql.append(" AND uoi.orderType != '").append(IDBConstant.LOGIC_STATUS_NO).append("'");
        whereSql.append(" ORDER BY uoi.createTime DESC");
        return super.getPageBean(headSql, bodySql, whereSql, orderInputView, inMap);
    }

    @Override
    public PageBean getReserveOrderInfoList(OrderInputView orderInputView){
        PageBean pageBean = getReserveOrderInfos(orderInputView);
        return pageBean;
    }

    private PageBean getReserveOrderInfos(OrderInputView orderInputView){
        String ono = orderInputView.getOno();
        StringBuilder headSql = new StringBuilder("SELECT roi.*, ui.nickname, sdc.name lockName, sdi.name deviceName");
        StringBuilder bodySql = new StringBuilder(" FROM reserve_order_info roi, user_info ui, sys_device_control sdc, sys_device_info sdi");
        StringBuilder whereSql = new StringBuilder(" WHERE roi.uid = ui.uid AND roi.dcid = sdc.dcid AND sdc.did = sdi.did");

        if(StrUtil.isNotBlank(ono)){
            whereSql.append(" AND roi.rno = :ono");
        }

        whereSql.append(" ORDER BY roi.reserveStartTime DESC, roi.createTime DESC, roi.roid");
        return super.getPageBean(headSql, bodySql, whereSql, orderInputView);
    }

    @Override
    public List<Map<String, Object>> getReserveOrderByTime(String time){
        return baseDao.queryBySql("SELECT roi.roid, roi.rno FROM reserve_order_info roi WHERE reserveStartTime BETWEEN ?1 AND ?2 AND  roi.status = ?3", time + IPlatformConstant.time00, time + IPlatformConstant.time24, IDBConstant.LOGIC_STATUS_YES);
    }

    @Override
    public List<UserOrderInfo> getOvertimeOrders(){
        Date minute30Before = DateUtil.addDateByType(new Date(), Calendar.MINUTE, -30);
        List<UserOrderInfo> userOrderInfos = baseDao.queryByHql("FROM UserOrderInfo oi WHERE oi.orderType != ?1 AND oi.payStatus = ?2 AND oi.createTime <= ?3", IDBConstant.LOGIC_STATUS_NO, IDBConstant.LOGIC_STATUS_NO, minute30Before);
        userOrderInfos.stream().forEach(userOrderInfo -> {
            userOrderInfo.setUserOrderDetails(getUserOrderDetails(userOrderInfo.getOid()));
        });
        return userOrderInfos;
    }

    @Desc("超时订单变为已取消，优惠券返还，衣橱币返还，库存还原")
    @Override
    public void updateOvertimeOrders(List<UserOrderInfo> userOrderInfos) throws ParseException{
        for(UserOrderInfo userOrderInfo : userOrderInfos){
            userOrderInfo.setPayStatus(IDBConstant.LOGIC_STATUS_OTHER); //已取消
            baseDao.save(userOrderInfo, userOrderInfo.getOid());
            updateOrderHander(userOrderInfo);
        }
    }

    @Desc("取消订单处理方法--->优惠券返还，衣橱币返还，库存还原")
    private void updateOrderHander(UserOrderInfo userOrderInfo) throws ParseException {
        Integer uid = userOrderInfo.getUid();
        if(userOrderInfo.getCpid() != null){ //处理优惠券
            UserCouponInfo userCouponInfo = userCouponService.getUserUseCouponInfo(userOrderInfo.getCpid(), uid);
            if(userCouponInfo != null){
                userCouponInfo.setStatus(IDBConstant.LOGIC_STATUS_NO);
                baseDao.save(userCouponInfo, userCouponInfo.getCpid());
            }
        }
        if(userOrderInfo.getYcoid() != null){ //处理衣橱币
            UserAccount userAccount = userAccountService.getUserAccount(uid);
            userAccount.setYcoid(userAccount.getYcoid() + userOrderInfo.getYcoid());
            baseDao.save(userAccount, uid);
        }
        //处理库存
        List<UserOrderDetail> userOrderDetails = userOrderInfo.getUserOrderDetails();
        userOrderDetails.stream().forEach(userOrderDetail -> {
            CommoditySize commoditySize = commodityService.getCommoditySize(userOrderDetail.getSid());
            if(commoditySize != null) {
                commoditySize.setStock(commoditySize.getStock() + userOrderDetail.getItemCount());
                baseDao.save(commoditySize, commoditySize.getSid());
            }
        });
    }

    @Override
    public Map<String, Object> getNowCanOpenLock(int did, int uid){
        StringBuilder sql = new StringBuilder("SELECT sdc.did, sdc.dcid, sdc.`name` FROM sys_device_control sdc ");
        sql.append(" WHERE sdc.did = ?1 AND type = ?2 AND NOT EXISTS(SELECT 1 FROM reserve_order_info roi WHERE roi.dcid = sdc.dcid AND roi.uid != ?3 AND roi.reserveEndTime >= NOW())");
        List<Map<String, Object>> locks = baseDao.queryBySql(sql.toString(), new Object[]{did, IDBConstant.LOGIC_STATUS_NO, uid});
        Map<String, Object> data = new HashMap<>(2, 1);
        data.put("locks", locks);
        return data;
    }

    @Override
    public void deleteReservation(int roid){
        ReserveOrderInfo reserveOrderInfo = getReserveOrderInfo(roid);
        if(reserveOrderInfo != null){
            List<ReserveOrderDetail> reserveOrderDetails = getReserveOrderDetails(roid);
            if(reserveOrderDetails != null && reserveOrderDetails.size() > 0){
                reserveOrderDetails.stream().forEach(reserveOrderDetail -> {
                    baseDao.delete(reserveOrderDetail);
                });
            }
            baseDao.delete(reserveOrderInfo);
        }
        //移除此预约柜子
        deleteRemoveCommodityDistribution(roid);
    }

    @Override
    public int getOrderCommodityCount(List<Map<String, Object>> settments){
        int count = 0;
        for(Map map : settments){
            count += StrUtil.objToInt(map.get("count"));
        }
        return count;
    }

}
