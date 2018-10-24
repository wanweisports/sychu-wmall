package com.wardrobe.platform.service;

import com.wardrobe.common.po.UserAccount;
import com.wardrobe.common.po.UserInfo;
import com.wardrobe.common.po.UserOrderInfo;

import java.sql.Timestamp;
import java.util.Map;

/**
 * Created by cxs on 2018/7/31.
 */
public interface IUserAccountService {

    void addUserScoreAndYcoid(int uid, double priceSum);

    void addUserScore(int uid, double priceSum);

    void updateRank(int uid);

    void initUserAccount(UserInfo userInfo);

    UserAccount getUserAccount(int uid);

    double getUserAccountBalance(int uid);

    int addRechargeOrderInfo(int uid, int dictId, double price);

    void addRechargePrice(UserOrderInfo userOrderInfo);

    Map<String, Object> getScore(int uid);

    void updateUserYcoid(int useYcoidCount, int uid);

}
