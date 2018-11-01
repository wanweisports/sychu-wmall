package com.wardrobe.platform.service.impl;

import com.wardrobe.common.bean.PageBean;
import com.wardrobe.common.constant.IDBConstant;
import com.wardrobe.common.po.UserOrderInfo;
import com.wardrobe.common.po.UserTransactions;
import com.wardrobe.common.util.Arith;
import com.wardrobe.common.util.DateUtil;
import com.wardrobe.common.util.StrUtil;
import com.wardrobe.common.view.UserTransactionsInputView;
import com.wardrobe.platform.service.IUserTransactionsService;
import org.springframework.stereotype.Service;

import java.util.ListIterator;
import java.util.Map;

/**
 * Created by cxs on 2018/8/1.
 */
@Service
public class UserTransactionsServiceImpl extends BaseService implements IUserTransactionsService {

    @Override
    public PageBean getUserTransactionsList(UserTransactionsInputView userTransactionsInputView){
        PageBean pageBean = getUserTransactions(userTransactionsInputView);
        ListIterator<Map<String, Object>> listIterator = pageBean.getList().listIterator();
        while (listIterator.hasNext()){
            Map<String, Object> map = listIterator.next();
            String serviceType = dictService.getDictValue(IDBConstant.TRANSACTIONS_SERVICE_TYPE, StrUtil.objToStr(map.get("serviceType")));
            String type = dictService.getDictValue(IDBConstant.TRANSACTIONS_TYPE, StrUtil.objToStr(map.get("type")));
            map.put("serviceTypeName",  StrUtil.objToStrDefEmpty(type) + serviceType);
        }
        return pageBean;
    }

    private PageBean getUserTransactions(UserTransactionsInputView userTransactionsInputView){

        Integer uid = userTransactionsInputView.getUid();
        String nickname = userTransactionsInputView.getNickname();
        String mobile = userTransactionsInputView.getMobile();

        StringBuilder headSql = new StringBuilder("SELECT ut.*, ui.nickname, ui.mobile");
        StringBuilder bodySql = new StringBuilder(" FROM user_transactions ut, user_info ui");
        StringBuilder whereSql = new StringBuilder(" WHERE ut.uid = ui.uid");
        if(uid != null){
            whereSql.append(" AND ut.uid = :uid");
        }
        if(StrUtil.isNotBlank(nickname)){
            whereSql.append(" AND ui.nickname = :nickname");
        }
        if(StrUtil.isNotBlank(mobile)){
            whereSql.append(" AND ui.mobile = :mobile");
        }
        whereSql.append(" AND ut.serviceType != '").append(IDBConstant.TRANSACTIONS_SERVICE_TYPE_ZF).append("'"); //小程序用户不查询微信支付的流水
        whereSql.append(" AND ut.type != '").append(IDBConstant.TRANSACTIONS_TYPE_WX).append("'");                //同时满足
        return super.getPageBean(headSql, bodySql, whereSql, userTransactionsInputView);
    }

    @Override
    public void addOrderUserTransactions(UserOrderInfo userOrderInfo, String serviceType, String type){
        //微信或余额支付
        UserTransactions userTransactions = new UserTransactions();
        userTransactions.setUid(userOrderInfo.getUid());
        userTransactions.setServiceId(userOrderInfo.getOid());
        userTransactions.setServiceType(serviceType);
        userTransactions.setType(type);
        userTransactions.setPrice(userOrderInfo.getPayPrice());
        userTransactions.setCreateTime(DateUtil.getNowDate());
        baseDao.save(userTransactions, null);

        if(userOrderInfo.getYcoid() != null){ //订单衣橱币流水记录
            userTransactions = new UserTransactions();
            userTransactions.setUid(userOrderInfo.getUid());
            userTransactions.setServiceId(userOrderInfo.getOid());
            userTransactions.setServiceType(serviceType);
            userTransactions.setType(IDBConstant.TRANSACTIONS_TYPE_YCOID); //衣橱币
            userTransactions.setPrice(Arith.conversion(userOrderInfo.getYcoid()));
            userTransactions.setCreateTime(DateUtil.getNowDate());
            baseDao.save(userTransactions, null);
        }
    }

    @Override
    public void addUserTransactions(int uid, double price, String serviceType, String type){
        //微信或余额支付
        UserTransactions userTransactions = new UserTransactions();
        userTransactions.setUid(uid);
        userTransactions.setServiceId(uid);
        userTransactions.setServiceType(serviceType);
        userTransactions.setType(type);
        userTransactions.setPrice(Arith.conversion(price));
        userTransactions.setCreateTime(DateUtil.getNowDate());
        baseDao.save(userTransactions, null);
    }

    @Override
    public PageBean getUserTransactionsListIn(UserTransactionsInputView userTransactionsInputView){
        return getUserTransactionsList(userTransactionsInputView);
    }

}
