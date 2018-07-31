package com.wardrobe.platform.service.impl;

import com.wardrobe.common.po.UserAccount;
import com.wardrobe.common.util.Arith;
import com.wardrobe.platform.service.IUserAccountService;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;

/**
 * Created by cxs on 2018/7/31.
 */
@Service
public class UserAccountServiceImpl extends BaseService implements IUserAccountService {

    @Override
    public void initUserAccount(int uid, Timestamp timestamp){
        UserAccount userAccount = new UserAccount();
        userAccount.setUid(uid);
        userAccount.setBalance(Arith.conversion(0));
        userAccount.setYcoid(Arith.conversion(0));
        userAccount.setScore(0);
        userAccount.setCreateTime(timestamp);
        baseDao.save(userAccount, null);
    }

    @Override
    public UserAccount getUserAccount(int uid){
        return baseDao.getToEvict(UserAccount.class, uid);
    }

}
