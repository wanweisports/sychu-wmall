package com.wardrobe.platform.service.impl;

import com.wardrobe.common.bean.PageBean;
import com.wardrobe.common.constant.IDBConstant;
import com.wardrobe.common.po.UserTransactions;
import com.wardrobe.common.util.Arith;
import com.wardrobe.common.util.DateUtil;
import com.wardrobe.common.util.StrUtil;
import com.wardrobe.common.view.UserTransactionsInputView;
import com.wardrobe.platform.service.IDictService;
import com.wardrobe.platform.service.IUserTransactionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
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
            map.put("serviceTypeName",  dictService.getDictValue(IDBConstant.TRANSACTIONS_TYPE, StrUtil.objToStr(map.get("serviceType"))));
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
        return super.getPageBean(headSql, bodySql, whereSql, userTransactionsInputView);
    }

    @Override
    public void addUserTransactions(int uid, int serviceId, String serviceType, BigDecimal price){
        UserTransactions userTransactions = new UserTransactions();
        userTransactions.setUid(uid);
        userTransactions.setServiceId(serviceId);
        userTransactions.setServiceType(serviceType);
        userTransactions.setPrice(price);
        userTransactions.setCreateTime(DateUtil.getNowDate());
        baseDao.save(userTransactions, null);
    }

    @Override
    public PageBean getUserTransactionsListIn(UserTransactionsInputView userTransactionsInputView){
        return getUserTransactionsList(userTransactionsInputView);
    }

}
