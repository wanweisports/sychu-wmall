package com.wardrobe.platform.service.impl;

import com.wardrobe.common.bean.PageBean;
import com.wardrobe.common.constant.IDBConstant;
import com.wardrobe.common.util.StrUtil;
import com.wardrobe.common.view.UserTransactionsInputView;
import com.wardrobe.platform.service.IDictService;
import com.wardrobe.platform.service.IUserTransactionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ListIterator;
import java.util.Map;

/**
 * Created by cxs on 2018/8/1.
 */
@Service
public class UserTransactionsServiceImpl extends BaseService implements IUserTransactionsService {

    @Autowired
    private IDictService dictService;

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
        StringBuilder headSql = new StringBuilder("SELECT *");
        StringBuilder bodySql = new StringBuilder(" FROM user_transactions");
        StringBuilder whereSql = new StringBuilder(" WHERE 1=1");

        return super.getPageBean(headSql, bodySql, whereSql, userTransactionsInputView);
    }

}