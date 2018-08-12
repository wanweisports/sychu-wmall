package com.wardrobe.platform.service;

import java.util.List;
import java.util.Map;

/**
 * Created by cxs on 2018/8/12.
 */
public interface IUserCouponService {

    List<Map<String, Object>> getUserCoupons(int userId);

}
