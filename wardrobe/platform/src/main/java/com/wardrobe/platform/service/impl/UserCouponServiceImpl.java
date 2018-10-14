package com.wardrobe.platform.service.impl;

import com.wardrobe.common.constant.IDBConstant;
import com.wardrobe.common.constant.IPlatformConstant;
import com.wardrobe.common.po.SysCouponRule;
import com.wardrobe.common.po.UserCouponInfo;
import com.wardrobe.common.util.DateUtil;
import com.wardrobe.common.util.StrUtil;
import com.wardrobe.platform.service.IUserCouponService;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.sql.Timestamp;
import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by cxs on 2018/8/12.
 */
@Service
public class UserCouponServiceImpl extends BaseService implements IUserCouponService {

    @Override
    public int getUserCouponCount(int userId){
        return baseDao.getUniqueResult("SELECT COUNT(1) FROM user_coupon_info uci WHERE uci.uid = ?1 AND uci.status = ?2", userId, IDBConstant.LOGIC_STATUS_NO).intValue();
    }

    @Override
    public List<Map<String, Object>> getUserCoupons(int userId){
        return baseDao.queryBySql("SELECT sd.dictValue, uci.* FROM user_coupon_info uci, sys_dict sd WHERE uci.serviceType = sd.dictId AND uci.uid = ?1 AND sd.dictName = ?2 ORDER BY uci.createTime DESC", userId, IDBConstant.USER_COUPON);
    }

    @Override
    public List<Map<String, Object>> getUserCouponList(int userId){
        return baseDao.queryBySql("SELECT sd.dictValue, uci.* FROM user_coupon_info uci, sys_dict sd WHERE uci.serviceType = sd.dictId AND uci.uid = ?1 AND sd.dictName = ?2 AND uci.status = ?3 ORDER BY uci.dueTime DESC", userId, IDBConstant.USER_COUPON, IDBConstant.LOGIC_STATUS_NO);
    }

    @Override
    public List<Map<String, Object>> getUserEffectiveCoupons(int userId, double priceSum){
        List<Map<String, Object>> list = baseDao.queryBySql("SELECT sd.dictValue, uci.* FROM user_coupon_info uci, sys_dict sd WHERE uci.serviceType = sd.dictId AND uci.uid = ?1 AND sd.dictName = ?2 AND uci.status = ?3 AND uci.dueTime >= ?4 ORDER BY uci.fullPrice, uci.dueTime", userId, IDBConstant.USER_COUPON, IDBConstant.LOGIC_STATUS_NO, DateUtil.dateToString(new Date(), null) + IPlatformConstant.time00);
        if(!CollectionUtils.isEmpty(list)){
            list.stream().forEach(map -> { //计算满减优惠是否能使用
                if(StrUtil.objToDouble(map.get("fullPrice")) <= priceSum){ //金额大于满减优惠，则可以使用
                    map.put("status", IDBConstant.LOGIC_STATUS_YES);
                }else{
                    map.put("status", IDBConstant.LOGIC_STATUS_NO);
                }
            });
        }
        return list;
    }

    @Override
    public UserCouponInfo getUserCouponInfo(int cpid, int uid) throws ParseException{
        return baseDao.queryByHqlFirst("FROM UserCouponInfo WHERE cpid = ?1 AND uid = ?2 AND status = ?3 AND dueTime >= ?4", cpid, uid, IDBConstant.LOGIC_STATUS_NO, DateUtil.dateToDate(new Date()));
    }

    @Override
    public List<SysCouponRule> getSysCouponRules(String crType){
        return baseDao.queryByHql("FROM SysCouponRule WHERE crType = ?1 AND (crTime IS NULL OR crTime >= ?2)", crType, new Timestamp(System.currentTimeMillis()));
    }

    @Override
    public void addUserCoupons(List<SysCouponRule> sysCouponRules, int uid){
        if(!CollectionUtils.isEmpty(sysCouponRules)){
            sysCouponRules.stream().forEach(couponRule -> {
                addUserCoupon(couponRule, uid);
            });
        }
    }

    @Override
    public void addUserCoupon(SysCouponRule sysCouponRule, int uid){
        Date date = new Date();
        Timestamp nowDate = new Timestamp(date.getTime());
        UserCouponInfo userCouponInfo = new UserCouponInfo();
        userCouponInfo.setCreateTime(nowDate);
        userCouponInfo.setStatus(IDBConstant.LOGIC_STATUS_NO);
        userCouponInfo.setCouponPrice(sysCouponRule.getCouponPrice());
        userCouponInfo.setFullPrice(sysCouponRule.getFullPrice());
        userCouponInfo.setServiceType(sysCouponRule.getServiceType());
        userCouponInfo.setUid(uid);
        if(IDBConstant.LOGIC_STATUS_YES.equals(sysCouponRule.getRule())) { //这里需要处理以后的优惠券到期规则，目前都是按到期时间算
            userCouponInfo.setDueTime(new Timestamp(DateUtil.addDate(date, sysCouponRule.getDueNum()).getTime()));
        }
        baseDao.save(userCouponInfo, null);
    }

    @Override
    public void updateUseUserCouponInfo(Integer cpid, String serviceType, int uid) throws ParseException{
        if(IDBConstant.LOGIC_STATUS_YES.equals(serviceType)){
            UserCouponInfo userCouponInfo = getUserCouponInfo(cpid, uid);
            userCouponInfo.setStatus(IDBConstant.LOGIC_STATUS_YES);
            baseDao.save(userCouponInfo, userCouponInfo.getStatus());
        }
    }

}
