package com.wardrobe.platform.service.impl;

import com.wardrobe.common.po.SysDict;
import com.wardrobe.common.po.UserAccount;
import com.wardrobe.common.po.UserInfo;
import com.wardrobe.common.util.Arith;
import com.wardrobe.common.util.StrUtil;
import com.wardrobe.platform.service.IDictService;
import com.wardrobe.platform.service.IUserAccountService;
import com.wardrobe.platform.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Override
    public double getUserAccountBalance(int uid){
        return baseDao.getUniqueResult("SELECT balance FROM user_account WHERE uid = ?1", uid).doubleValue();
    }

    @Override
    public void addRechargePrice(int uid, int dictId){
        UserAccount userAccount = getUserAccount(uid);
        SysDict sysDict = dictService.getDictById(dictId);

        userAccount.setBalance(Arith.conversion(Arith.add(Arith.add(userAccount.getBalance().doubleValue(), StrUtil.objToDouble(sysDict.getDictValue())), StrUtil.objToDouble(sysDict.getDictAdditional()))));
        baseDao.save(userAccount, uid);
    }

}
