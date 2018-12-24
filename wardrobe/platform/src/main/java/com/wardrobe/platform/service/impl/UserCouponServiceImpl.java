package com.wardrobe.platform.service.impl;

import com.wardrobe.common.constant.IDBConstant;
import com.wardrobe.common.constant.IPlatformConstant;
import com.wardrobe.common.po.SysCouponRule;
import com.wardrobe.common.po.SysDict;
import com.wardrobe.common.po.UserCouponInfo;
import com.wardrobe.common.util.DateUtil;
import com.wardrobe.common.util.StrUtil;
import com.wardrobe.platform.service.IUserCouponService;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
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
    public void updateExpiredCoupons(){
        List<UserCouponInfo> userCouponInfos = baseDao.queryByHql("FROM UserCouponInfo WHERE status = ?1 AND dueTime < ?2", IDBConstant.LOGIC_STATUS_NO, new java.sql.Date(System.currentTimeMillis()));
        if(userCouponInfos != null && userCouponInfos.size() > 0){
            userCouponInfos.stream().forEach(userCouponInfo -> {
                userCouponInfo.setStatus(IDBConstant.LOGIC_STATUS_OTHER); //已过期
                baseDao.save(userCouponInfo, userCouponInfo.getCpid());
            });
        }
    }

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
        List<Map<String, Object>> list = baseDao.queryBySql("SELECT sd.dictValue, uci.* FROM user_coupon_info uci, sys_dict sd WHERE uci.serviceType = sd.dictId AND uci.uid = ?1 AND sd.dictName = ?2 AND uci.status = ?3 ORDER BY uci.dueTime DESC", userId, IDBConstant.USER_COUPON, IDBConstant.LOGIC_STATUS_NO);
        return descCoupons(list);
    }

    @Override
    public List<Map<String, Object>> getUserEffectiveCoupons(int userId, double priceSum){
        List<Map<String, Object>> list = baseDao.queryBySql("SELECT sd.dictValue, uci.* FROM user_coupon_info uci, sys_dict sd WHERE uci.serviceType = sd.dictId AND uci.uid = ?1 AND sd.dictName = ?2 AND uci.status = ?3 AND DATE(uci.dueTime) >= ?4 ORDER BY uci.fullPrice, uci.dueTime", userId, IDBConstant.USER_COUPON, IDBConstant.LOGIC_STATUS_NO, DateUtil.dateToString(new Date(), null));
        if(!CollectionUtils.isEmpty(list)){
            list.stream().forEach(map -> { //计算满减优惠是否能使用
                if(StrUtil.objToDouble(map.get("fullPrice")) <= priceSum){ //金额大于满减优惠，则可以使用
                    map.put("status", IDBConstant.LOGIC_STATUS_YES);
                }else{
                    map.put("status", IDBConstant.LOGIC_STATUS_NO);
                }
            });
        }
        return descCoupons(list);
    }

    private List<Map<String, Object>> descCoupons(List<Map<String, Object>> list){
        if(list != null && list.size() > 0){
            StringBuilder desc = new StringBuilder();
            list.stream().forEach(map -> {
                desc.setLength(0);
                Object dueTime = map.get("dueTime");
                map.put("dueTime", dueTime != null ? dueTime.toString().substring(0, dueTime.toString().lastIndexOf(" ")) : StrUtil.EMPTY);
                if (IDBConstant.COUPON_SERVICE_TYPE.equals(map.get("serviceType").toString())) {
                    map.put("dictValue", desc.append("满").append(StrUtil.objToInt(map.get("fullPrice"))).append("元可使用").toString());
                    desc.setLength(0);
                    map.put("dictValue2", desc.append("满").append(StrUtil.objToInt(map.get("fullPrice"))).append("减").append(StrUtil.objToInt(map.get("couponPrice"))).toString());
                }
            });
        }
        return list;
    }

    @Override
    public UserCouponInfo getUserNotUseCouponInfo(int cpid, int uid, double fullPrice) throws ParseException{
        return baseDao.queryByHqlFirst("FROM UserCouponInfo WHERE cpid = ?1 AND uid = ?2 AND status = ?3 AND dueTime >= ?4 AND fullPrice <= ?5", cpid, uid, IDBConstant.LOGIC_STATUS_NO, DateUtil.dateToDate(new Date()), new BigDecimal(fullPrice));
    }

    @Override
    public UserCouponInfo getUserUseCouponInfo(int cpid, int uid) throws ParseException{
        return baseDao.queryByHqlFirst("FROM UserCouponInfo WHERE cpid = ?1 AND uid = ?2 AND status = ?3 AND dueTime >= ?4", cpid, uid, IDBConstant.LOGIC_STATUS_YES, DateUtil.dateToDate(new Date()));
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
    public void updateUseUserCouponInfo(int cpid) throws ParseException {
        if (cpid > 0) {
            UserCouponInfo userCouponInfo = getUserCouponInfo(cpid);
            userCouponInfo.setStatus(IDBConstant.LOGIC_STATUS_YES);
            baseDao.save(userCouponInfo, cpid);
        }
    }

    private UserCouponInfo getUserCouponInfo(int cpid){
        return baseDao.getToEvict(UserCouponInfo.class, cpid);
    }

    @Override
    public String getUserCouponDescIn(Integer cpid){
        if(cpid != null) {
            UserCouponInfo userCouponInfo = getUserCouponInfo(cpid);
            if (userCouponInfo != null) {
                if (IDBConstant.COUPON_SERVICE_TYPE.equals(userCouponInfo.getServiceType().toString())) {
                    return new StringBuilder().append("满").append(StrUtil.objToInt(userCouponInfo.getFullPrice())).append("减").append(StrUtil.objToInt(userCouponInfo.getCouponPrice())).toString();
                }
            }
        }
        return StrUtil.EMPTY;
    }

}
