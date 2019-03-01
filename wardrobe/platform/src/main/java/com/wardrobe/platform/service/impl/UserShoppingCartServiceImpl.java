package com.wardrobe.platform.service.impl;

import com.wardrobe.common.annotation.Desc;
import com.wardrobe.common.bean.DiscountBean;
import com.wardrobe.common.bean.PageBean;
import com.wardrobe.common.constant.IDBConstant;
import com.wardrobe.common.constant.IPlatformConstant;
import com.wardrobe.common.exception.MessageException;
import com.wardrobe.common.po.*;
import com.wardrobe.common.util.*;
import com.wardrobe.common.view.CommodityInputView;
import com.wardrobe.common.view.UserCouponInputView;
import com.wardrobe.platform.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by cxs on 2018/8/24.
 */
@Service
public class UserShoppingCartServiceImpl extends BaseService implements IUserShoppingCartService {

    @Autowired
    private ICommodityService commodityService;

    @Autowired
    private IUserCouponService userCouponService;

    @Autowired
    private IUserAccountService userAccountService;

    @Autowired
    private ISysRankService rankService;

    @Autowired
    private IOrderService orderService;

    @Override
    public synchronized void saveShoppingCart(UserShoppingCart userShoppingCart){
        Integer sid = userShoppingCart.getSid();
        Timestamp nowDate = DateUtil.getNowDate();
        UserShoppingCart userShoppingCartDB = getUserShoppingCartBySid(sid, userShoppingCart.getShoppingType(), userShoppingCart.getUid());
        CommoditySize commoditySize = commodityService.getCommoditySize(sid);
        if(userShoppingCartDB == null) {
            if(commoditySize.getStock() < 0) throw new MessageException("存在不足,当前库存：" + commoditySize.getStock());

            CommodityColor commodityColor = commodityService.getCommodityColor(commoditySize.getCoid());
            CommodityInfo commodityInfo = commodityService.getCommodityInfo(commodityColor.getCid());
            userShoppingCart.setCoid(commodityColor.getCoid());
            userShoppingCart.setCid(commodityInfo.getCid());
            userShoppingCart.setCreateTime(nowDate);
            baseDao.save(userShoppingCart, null);
        }else{
            userShoppingCartDB.setCount(userShoppingCartDB.getCount()+userShoppingCart.getCount());
            if(commoditySize.getStock() < userShoppingCartDB.getCount()) throw new MessageException("存在不足,当前库存：" + commoditySize.getStock());

            userShoppingCartDB.setUpdateTime(nowDate);
            baseDao.save(userShoppingCartDB, userShoppingCartDB.getScid());
        }
    }

    private UserShoppingCart getUserShoppingCartBySid(int sid, String shoppingType, int uid){
        return baseDao.queryByHqlFirst("FROM UserShoppingCart WHERE sid = ?1 AND shoppingType = ?2 AND uid = ?3", sid, shoppingType, uid);
    }

    @Override
    public Map<String, Object> getUserShoppingCartList(CommodityInputView commodityInputView){
        Map<String, Object> data = new HashMap();

        PageBean pageBean = getUserShoppingCarts(commodityInputView);
        List<Map<String, Object>> list = pageBean.getList();
        double sumPrice = 0;
        for(Map<String, Object> map : list){
            map.put("resourcePath", commodityService.getFmImg(StrUtil.objToInt(map.get("cid"))));
            sumPrice = Arith.add(sumPrice, Arith.mul(StrUtil.objToDouble(map.get("couPrice")), StrUtil.objToInt(map.get("count"))));
        }

        data.put("pageBean", pageBean);
        data.put("sumPrice", sumPrice);
        return data;
    }

    private PageBean getUserShoppingCarts(CommodityInputView commodityInputView){
        Integer uid = commodityInputView.getUid();
        String shoppingType = commodityInputView.getShoppingType();

        StringBuilder headSql = new StringBuilder("SELECT usc.scid, usc.count, ci.commName, ci.price, ci.couPrice, cc.colorName, cs.sid, cs.size, ci.cid");
        StringBuilder bodySql = new StringBuilder(" FROM user_shopping_cart usc, commodity_size cs, commodity_color cc, commodity_info ci");
        StringBuilder whereSql = new StringBuilder(" WHERE usc.sid = cs.sid AND cs.coid = cc.coid AND cc.cid = ci.cid");
        if(uid != null){
            whereSql.append(" AND usc.uid = :uid");
        }
        if(StrUtil.isNotBlank(shoppingType)){
            whereSql.append(" AND usc.shoppingType = :shoppingType");
        }
        return super.getPageBean(headSql, bodySql, whereSql, commodityInputView);
    }

    @Override
    public void deleteUserShoppingCart(String scids, int userId){
        String[] scidArr = scids.split(",");
        for(String scid : scidArr) {
            UserShoppingCart userShoppingCart = getUserShoppingCart(StrUtil.objToInt(scid));
            if (userId == userShoppingCart.getUid()) {
                baseDao.delete(userShoppingCart);
            }
        }
    }

    @Override
    public UserShoppingCart getUserShoppingCart(int scid){
        return baseDao.getToEvict(UserShoppingCart.class, scid);
    }

    @Override
    public Map<String, Object> settlement(String scids, int uid) throws ParseException{
        List<Map<String, Object>> settlement = getSettlement(scids, uid);
        double sumPrice = countSumPrice(settlement);
        UserAccount userAccount = userAccountService.getUserAccount(uid);
        Map<String, Object> data = new HashMap(16, 1);
        data.put("list", settlement);
        //用户总衣橱币
        data.put("ycoid", userAccount.getYcoid());
        //乘以折扣(四舍五入)
        DiscountBean discountBean = concessionalPrice(sumPrice, null, null, uid, orderService.getOrderCommodityCount(settlement));
        //用户总余额
        data.put("balance", userAccount.getBalance().doubleValue());
        //用户优惠券列表
        data.put("coupons", userCouponService.getUserEffectiveCoupons(uid, discountBean.getSumOldDisPrice()));
        data.putAll(JsonUtils.fromJson(discountBean));
        return data;
    }

    @Override
    public double countSumPrice(List<Map<String, Object>> list){
        double sumPrice = 0;
        for(Map<String, Object> map : list){
            map.put("resourcePath", commodityService.getFmImg(StrUtil.objToInt(map.get("cid"))));
            sumPrice = Arith.add(sumPrice, Arith.mul(StrUtil.objToDouble(map.get("couPrice")), StrUtil.objToInt(map.get("count"))));
        }
        return sumPrice;
    }

    private List<Map<String, Object>> getSettlement(final String scids, final int uid) {
        StringBuilder sql = new StringBuilder("SELECT usc.scid, usc.count, ci.commName, ci.price, ci.couPrice, cc.colorName, cs.size, ci.cid");
        sql.append(" FROM user_shopping_cart usc, commodity_size cs, commodity_color cc, commodity_info ci");
        sql.append(" WHERE usc.sid = cs.sid AND cs.coid = cc.coid AND cc.cid = ci.cid AND usc.uid = :uid");
        sql.append(" AND usc.scid IN(:scids)");
        return baseDao.queryBySql(sql.toString(), new HashMap() {{
            putAll(SQLUtil.getInToSQL("scids", scids));
            put("uid", uid);
        }});
    }

    @Override
    public Map<String, Object> settlementCount(UserCouponInputView userCouponInputView, int uid) throws ParseException {
        List<Map<String, Object>> settlement = getSettlement(userCouponInputView.getScids(), uid);
        double sumPrice = 0;
        for(Map<String, Object> map : settlement){
            sumPrice = Arith.add(sumPrice, Arith.mul(StrUtil.objToDouble(map.get("couPrice")), StrUtil.objToInt(map.get("count"))));
        }

        Map<String, Object> data = new HashMap<>(16, 1);
        //乘以折扣(四舍五入)
        DiscountBean discountBean = concessionalPrice(sumPrice, userCouponInputView.getServiceType(), userCouponInputView.getCpid(), uid,  orderService.getOrderCommodityCount(settlement));
        data.putAll(JsonUtils.fromJson(discountBean));
        return data;
    }

    @Override
    public Map<String, Object> settlementRfidCount(UserCouponInputView userCouponInputView, int uid) throws ParseException{
        String dbids = userCouponInputView.getDbids();
        if(StrUtil.isNotBlank(dbids)) {
            StringBuilder sql = new StringBuilder("SELECT ci.cid, ci.commName, ci.price, ci.couPrice, sdc.`name`, 1 count FROM sys_commodity_distribution cd, sys_device_control sdc, commodity_info ci");
            sql.append(" WHERE cd.dcid = sdc.dcid AND cd.cid = ci.cid AND cd.dbid IN(:dbids)");
            List<Map<String, Object>> settlement = baseDao.queryBySql(sql.toString(), new HashMap<String, Object>() {{
                putAll(SQLUtil.getInToSQL("dbids", dbids));
            }});
            double sumPrice = 0;
            for (Map<String, Object> map : settlement) {
                sumPrice = Arith.add(sumPrice, Arith.mul(StrUtil.objToDouble(map.get("couPrice")), StrUtil.objToInt(map.get("count"))));
            }

            Map<String, Object> data = new HashMap<>(10, 1);
            DiscountBean discountBean = concessionalPrice(sumPrice, userCouponInputView.getServiceType(), userCouponInputView.getCpid(), uid, null);
            data.putAll(JsonUtils.fromJson(discountBean));
        }
        return new HashMap<>(1);
    }

    @Desc("计算优惠")
    @Override
    public DiscountBean concessionalPrice(double sumPrice, String serviceType, Integer cpid, int uid, Integer commodityCount) throws ParseException{
        DiscountBean discountBean = new DiscountBean();
        UserAccount userAccount = userAccountService.getUserAccount(uid);

        int freight = commodityCount != null && commodityCount < 2 ? IPlatformConstant.FREIGHT : 0;//运费（满两件包邮，只买一件商品的运费写￥13）
        discountBean.setFreight(freight);
        discountBean.setSumOldPrice(StrUtil.roundKeepTwo(sumPrice));

        SysRankInfo rankInfoByRank = rankService.getRankInfoByRank(userAccount.getRank());
        double discount = rankInfoByRank.getRankDiscount().doubleValue();
        discountBean.setDiscount(discount);
        sumPrice = StrUtil.roundKeepTwo(Arith.mul(sumPrice > 0 ? sumPrice : 0, discount)); //先计算用户折扣
        discountBean.setSumOldDisPrice(sumPrice);

        //再使用衣橱币或者优惠券
        if(IDBConstant.LOGIC_STATUS_YES.equals(serviceType)){ //使用优惠券
            UserCouponInfo userCouponInfo = userCouponService.getUserNotUseCouponInfo(cpid, uid, sumPrice);
            if(userCouponInfo != null) {
                double couponPrice = userCouponInfo.getCouponPrice().doubleValue();
                discountBean.setCouponPrice(couponPrice);
                discountBean.setCpid(userCouponInfo.getCpid());
                sumPrice = Arith.sub(sumPrice, couponPrice);
            }
        }else if(IDBConstant.LOGIC_STATUS_NO.equals(serviceType)){ //使用衣橱币
            int ycoid = userAccount.getYcoid();
            if(ycoid >= sumPrice) {  //衣橱币超过总金额
                discountBean.setUseYcoid((int)(sumPrice));
                sumPrice = (sumPrice) -  (int)(sumPrice); //处理衣橱币整数，还要支付小数
            }else{
                discountBean.setUseYcoid(ycoid);
                sumPrice = Arith.sub(sumPrice, ycoid);
            }
        }
        sumPrice = sumPrice > 0 ? sumPrice : 0;
        discountBean.setSumPrice(StrUtil.roundKeepTwo(sumPrice + freight)); //支付总金额

        discountBean.countUserDiscountSubPrice(); //计算用户等级折扣减去多少金额

        //能否使用余额支付（余额需要大于支付金额，包括运费）：1：是  2：否
        discountBean.setUseBalance(userAccount.getBalance().doubleValue() >= discountBean.getSumPrice() ? IDBConstant.LOGIC_STATUS_YES : IDBConstant.LOGIC_STATUS_NO);
        return discountBean;
    }

}
