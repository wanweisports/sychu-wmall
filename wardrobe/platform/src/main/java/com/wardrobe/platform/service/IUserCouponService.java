package com.wardrobe.platform.service;

import com.wardrobe.common.po.SysCouponRule;
import com.wardrobe.common.po.UserCouponInfo;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

/**
 * Created by cxs on 2018/8/12.
 */
public interface IUserCouponService {

    int getUserCouponCount(int userId);

    List<Map<String, Object>> getUserCoupons(int userId);

    List<Map<String, Object>> getUserCouponList(int userId);

    List<Map<String, Object>> getUserEffectiveCoupons(int userId, double priceSum);

    UserCouponInfo getUserNotUseCouponInfo(int cpid, int uid) throws ParseException;

    UserCouponInfo getUserUseCouponInfo(int cpid, int uid) throws ParseException;

    List<SysCouponRule> getSysCouponRules(String crType);

    void addUserCoupons(List<SysCouponRule> sysCouponRules, int uid);

    void addUserCoupon(SysCouponRule sysCouponRule, int uid);

    void updateUseUserCouponInfo(Integer cpid, String serviceType, int uid) throws ParseException;

}
