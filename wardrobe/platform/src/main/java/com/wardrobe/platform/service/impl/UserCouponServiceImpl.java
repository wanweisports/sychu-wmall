package com.wardrobe.platform.service.impl;

import com.wardrobe.common.constant.IDBConstant;
import com.wardrobe.common.constant.IPlatformConstant;
import com.wardrobe.common.po.SysCouponRule;
import com.wardrobe.common.po.UserCouponInfo;
import com.wardrobe.common.util.DateUtil;
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
    public List<Map<String, Object>> getUserCoupons(int userId){
        return baseDao.queryBySql("SELECT sd.dictValue, uci.* FROM user_coupon_info uci, sys_dict sd WHERE uci.serviceType = sd.dictId AND uci.uid = ?1 AND sd.dictName = ?2 ORDER BY uci.createTime DESC", userId, IDBConstant.USER_COUPON);
    }

    @Override
    public List<Map<String, Object>> getUserCouponList(int userId){
        return baseDao.queryBySql("SELECT sd.dictValue, uci.* FROM user_coupon_info uci, sys_dict sd WHERE uci.serviceType = sd.dictId AND uci.uid = ?1 AND sd.dictName = ?2 ORDER BY uci.status DESC, uci.dueTime DESC", userId, IDBConstant.USER_COUPON);
    }

    @Override
    public List<Map<String, Object>> getUserEffectiveCoupons(int userId){
        return baseDao.queryBySql("SELECT sd.dictValue, uci.* FROM user_coupon_info uci, sys_dict sd WHERE uci.serviceType = sd.dictId AND uci.uid = ?1 AND sd.dictName = ?2 AND uci.status = ?3 AND uci.dueTime >= ?4 ORDER BY uci.dueTime", userId, IDBConstant.USER_COUPON, IDBConstant.LOGIC_STATUS_YES, DateUtil.dateToString(new Date(), null) + IPlatformConstant.time00);
    }

    @Override
    public UserCouponInfo getUserCouponInfo(int cpid, int uid) throws ParseException{
        return baseDao.queryByHqlFirst("FROM UserCouponInfo WHERE cpid = ?1 AND uid = ?2 AND status = ?3 AND dueTime >= ?4", cpid, uid, IDBConstant.LOGIC_STATUS_YES, DateUtil.dateToDate(new Date()));
    }

    @Override
    public List<SysCouponRule> getSysCouponRules(String crType){
        return baseDao.queryByHql("FROM SysCouponRule WHERE crType = ?1 AND (crTime IS NULL OR crTime >= NOW())", crType);
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
        userCouponInfo.setStatus(IDBConstant.LOGIC_STATUS_YES);
        userCouponInfo.setCouponPrice(sysCouponRule.getCouponPrice());
        userCouponInfo.setFullPrice(sysCouponRule.getFullPrice());
        userCouponInfo.setServiceType(sysCouponRule.getServiceType());
        userCouponInfo.setUid(uid);
        userCouponInfo.setDueTime(new Timestamp(DateUtil.addDate(date, sysCouponRule.getDueNum()).getTime()));
        baseDao.save(userCouponInfo, null);
    }

}
