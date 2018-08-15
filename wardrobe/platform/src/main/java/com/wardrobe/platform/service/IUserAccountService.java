package com.wardrobe.platform.service;

import com.wardrobe.common.po.UserAccount;

import java.sql.Timestamp;

/**
 * Created by cxs on 2018/7/31.
 */
public interface IUserAccountService {

    void addUserScoreAndYcoid(int uid, double priceSum);

    void addUserScore(int uid, double priceSum);

    void initUserAccount(int uid, Timestamp timestamp);

    UserAccount getUserAccount(int uid);

    double getUserAccountBalance(int uid);

    void addRechargePrice(int uid, int dictId);

}
