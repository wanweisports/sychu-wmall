package com.wardrobe.platform.service.impl;

import com.wardrobe.common.annotation.Desc;
import com.wardrobe.common.constant.IDBConstant;
import com.wardrobe.common.constant.IPlatformConstant;
import com.wardrobe.common.exception.MessageException;
import com.wardrobe.common.po.SysDict;
import com.wardrobe.common.po.UserAccount;
import com.wardrobe.common.po.UserInfo;
import com.wardrobe.common.po.UserOrderInfo;
import com.wardrobe.common.util.Arith;
import com.wardrobe.common.util.StrUtil;
import com.wardrobe.platform.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

/**
 * 直接结账：每消费1元送1闪币和1积分
 充值：充5000元送100元，充1w元送300元，充3w元送1000。每充值1元送1积分。

 消费结算时，可选择是否用闪币抵扣或下次再用，每100闪币可抵扣1元钱。闪币和优惠券不可以同时用
 */
@Service
public class UserAccountServiceImpl extends BaseService implements IUserAccountService {

    @Autowired
    private ISysRankService sysRankService;

    @Autowired
    private IUserTransactionsService userTransactionsService;

    @Autowired
    private IOrderService orderService;

    @Override
    public synchronized void addUserScoreAndYcoid(int uid, double priceSum){
        setUserYcoid(uid, priceSum);
        setUserScore(uid, priceSum);
        updateRank(uid);
    }

    @Override
    public synchronized void addUserScore(int uid, double priceSum){
        setUserScore(uid, priceSum);
        updateRank(uid);
    }

    //每消费100元，获得1衣米。消费金额按照商品订单实际支付金额（仅微信支付）计算，按照订单金额向下取整，如：支付199元，获得1衣米。
    private synchronized void setUserYcoid(int uid, double priceSum){
        UserAccount userAccount = getUserAccount(uid);
        if(userAccount != null) {
            int ycoid = (int) priceSum / 100;
            if(ycoid > 0) {
                userAccount.setYcoid((int) priceSum * IPlatformConstant.ADD_USER_YCOID);
                baseDao.save(userAccount, uid);
            }
        }
    }

    private synchronized void setUserScore(int uid, double priceSum){
        UserAccount userAccount = getUserAccount(uid);
        if(userAccount != null) {
            userAccount.setScore(userAccount.getScore() + (int) priceSum * IPlatformConstant.ADD_USER_SCORE);
            baseDao.save(userAccount, uid);
        }
    }

    private synchronized void updateRank(int uid){
        UserAccount userAccount = getUserAccount(uid);
        Integer rank = sysRankService.getRank(userAccount.getScore(), userAccount.getRank());
        if(rank != null){
            userAccount.setRank(rank);
            baseDao.save(userAccount, uid);
        }
    }

    @Override
    public synchronized void initUserAccount(UserInfo userInfo){
        UserAccount userAccount = new UserAccount();
        userAccount.setUid(userInfo.getUid());
        userAccount.setBalance(Arith.conversion(0));
        userAccount.setYcoid(IPlatformConstant.INIT_USER_SCORE);
        userAccount.setRank(1);

        userAccount.setScore(0);
        userAccount.setCreateTime(userInfo.getRegisterTime());
        baseDao.save(userAccount, null);
    }

    @Override
    public synchronized UserAccount getUserAccount(int uid){
        return baseDao.getToEvict(UserAccount.class, uid);
    }

    @Override
    public synchronized double getUserAccountBalance(int uid){
        return baseDao.getUniqueResult("SELECT balance FROM user_account WHERE uid = ?1", uid).doubleValue();
    }

    @Desc("充值下单")
    @Override
    public synchronized int addRechargeOrderInfo(int uid, int dictId, double price){
        SysDict sysDict = dictService.getDictById(dictId);
        if(sysDict == null || !IDBConstant.RECHARGE_TYPE.equals(sysDict.getDictName())) throw new MessageException("操作失败，请刷新页面重试！");

        UserAccount userAccount = getUserAccount(uid);
        Double rechargePrice = StrUtil.objToDouble(sysDict.getDictValue());
        if(rechargePrice.doubleValue() != price) throw new MessageException("操作失败，请刷新页面重试！");

        UserOrderInfo userOrderInfo = new UserOrderInfo();
        userOrderInfo.setPriceSum(Arith.conversion(rechargePrice));
        return orderService.saveRechargeOrderInfo(userOrderInfo, sysDict, uid);
    }

    @Desc("充值成功后，回调累计金额")
    @Override
    public synchronized void addRechargePrice(UserOrderInfo userOrderInfo){

        /*Double additionalPrice = StrUtil.objToDouble(sysDict.getDictAdditional());

        //累计用户金额
        BigDecimal rechargePriceSum = Arith.conversion(Arith.add(rechargePrice, additionalPrice));
        userAccount.setBalance(rechargePriceSum);
        baseDao.save(userAccount, uid);

        //保存流水
        userTransactionsService.addUserTransactions(uid, 0, IDBConstant.TRANSACTIONS_TYPE_CZ, rechargePriceSum);

        //积分累计
        this.addUserScore(uid, rechargePrice);*/
    }

    @Override
    public synchronized Map<String, Object> getScore(int uid){
        Map<String, Object> data = new HashMap(1, 1);
        data.put("score", baseDao.getUniqueResult("SELECT score FROM user_account WHERE uid = ?1", uid).intValue());
        return data;
    }

}
