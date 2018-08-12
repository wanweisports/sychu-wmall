package com.wardrobe.platform.service.impl;

import com.wardrobe.common.constant.IDBConstant;
import com.wardrobe.platform.service.IUserCouponService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by cxs on 2018/8/12.
 */
@Service
public class UserCouponServiceImpl extends BaseService implements IUserCouponService {

    @Override
    public List<Map<String, Object>> getUserCoupons(int userId){
        return baseDao.queryBySql("SELECT sd.dictValue, uci.* FROM user_coupon_info uci, sys_dict sd WHERE uci.serviceType = sd.dictKey AND uci.uid = ? AND sd.dictName = ?", userId, IDBConstant.USER_COUPON);
    }

}
