package com.wardrobe.platform.service;

import com.wardrobe.common.po.UserCouponInfo;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

/**
 * Created by cxs on 2018/8/12.
 */
public interface IUserCouponService {

    List<Map<String, Object>> getUserCoupons(int userId);

    List<Map<String, Object>> getUserCouponList(int userId);

    List<Map<String, Object>> getUserEffectiveCoupons(int userId);

    UserCouponInfo getUserCouponInfo(int cpid, int uid) throws ParseException;

}
