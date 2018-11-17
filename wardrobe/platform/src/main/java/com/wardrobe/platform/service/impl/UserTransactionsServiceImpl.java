package com.wardrobe.platform.service.impl;

import com.wardrobe.common.bean.PageBean;
import com.wardrobe.common.constant.IDBConstant;
import com.wardrobe.common.po.UserOrderInfo;
import com.wardrobe.common.po.UserTransactions;
import com.wardrobe.common.util.*;
import com.wardrobe.common.view.UserTransactionsInputView;
import com.wardrobe.platform.service.IUserTransactionsService;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.HashMap;
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
            if(IDBConstant.TRANSACTIONS_SERVICE_TYPE_CZ.equals(map.get("serviceType"))){ //充值类型：小程序不需要进入详情
                map.remove("serviceId");
                map.put("type", IDBConstant.LOGIC_STATUS_NO); //充值类型归类到支付，供小程序查询
            }
        }
        return pageBean;
    }

    private PageBean getUserTransactions(UserTransactionsInputView userTransactionsInputView){

        Integer uid = userTransactionsInputView.getUid();
        String nickname = userTransactionsInputView.getNickname();
        String mobile = userTransactionsInputView.getMobile();
        String type = userTransactionsInputView.getType();
        boolean wxType = userTransactionsInputView.isWxType();
        String serviceType = userTransactionsInputView.getServiceType();

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
        if(StrUtil.isNotBlank(type)){
            if(IDBConstant.TRANSACTIONS_TYPE_YUE.equals(type)) { //（2：充值与余额  3：薏米）
                whereSql.append(" AND ut.type IN ('").append(IDBConstant.TRANSACTIONS_TYPE_YUE).append("','").append(StrUtil.EMPTY).append("')");
            }else{
                whereSql.append(" AND ut.type = :type");
            }
        }
        if(!wxType) {
            whereSql.append(" AND ut.type != '").append(IDBConstant.TRANSACTIONS_TYPE_WX).append("'"); //小程序用户不查询微信支付的流水
        }
        if(StrUtil.isNotBlank(serviceType)){
            whereSql.append(" AND ut.serviceType = :serviceType");
        }
        whereSql.append(" ORDER BY ut.createTime DESC");
        return super.getPageBean(headSql, bodySql, whereSql, userTransactionsInputView);
    }

    @Override
    public void addOrderUserTransactions(UserOrderInfo userOrderInfo, String serviceType, String type){
        Timestamp nowDate = DateUtil.getNowDate();
        //微信或余额支付
        UserTransactions userTransactions = new UserTransactions();
        userTransactions.setUid(userOrderInfo.getUid());
        userTransactions.setServiceId(userOrderInfo.getOid());
        userTransactions.setServiceType(serviceType);
        userTransactions.setType(type);
        userTransactions.setPrice(userOrderInfo.getPayPrice());
        userTransactions.setCreateTime(nowDate);
        baseDao.save(userTransactions, null);

        if(userOrderInfo.getYcoid() != null){ //订单衣橱币流水记录
            userTransactions = new UserTransactions();
            userTransactions.setUid(userOrderInfo.getUid());
            userTransactions.setServiceId(userOrderInfo.getOid());
            userTransactions.setServiceType(serviceType);
            userTransactions.setType(IDBConstant.TRANSACTIONS_TYPE_YCOID); //衣橱币
            userTransactions.setPrice(Arith.conversion(userOrderInfo.getYcoid()));
            userTransactions.setCreateTime(nowDate);
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

    @Override
    public Map<String, Object> countTransactions(UserTransactionsInputView userTransactionsInputView){
        Integer uid = userTransactionsInputView.getUid();
        String nickname = userTransactionsInputView.getNickname();
        String mobile = userTransactionsInputView.getMobile();
        StringBuilder sql = new StringBuilder("SELECT SUM(ut.price) FROM user_transactions ut, user_info ui WHERE ut.uid = ui.uid AND ut.serviceType = :serviceType");
        if(uid != null){
            sql.append(" AND ut.uid = :uid");
        }
        if(StrUtil.isNotBlank(nickname)){
            sql.append(" AND ui.nickname = :nickname");
        }
        if(StrUtil.isNotBlank(mobile)){
            sql.append(" AND ui.mobile = :mobile");
        }
        userTransactionsInputView.setServiceType(IDBConstant.TRANSACTIONS_SERVICE_TYPE_CZ);
        Map<String, Object> paraMap = JsonUtils.fromJson(userTransactionsInputView);
        Number czPriceSum = baseDao.getUniqueResult(sql.toString(), paraMap);

        sql.append(" AND ut.type != :type");
        userTransactionsInputView.setServiceType(IDBConstant.TRANSACTIONS_SERVICE_TYPE_ZF);
        paraMap.put("type", IDBConstant.TRANSACTIONS_TYPE_YCOID);
        Number zfPriceSum = baseDao.getUniqueResult(sql.toString(), paraMap);


        Map<String, Object> returnMap = new HashMap<>(2, 1);
        returnMap.put("czPriceSum", czPriceSum != null ? czPriceSum.doubleValue() : 0);
        returnMap.put("zfPriceSum", zfPriceSum != null ? zfPriceSum.doubleValue() : 0);
        return returnMap;
    }

}
