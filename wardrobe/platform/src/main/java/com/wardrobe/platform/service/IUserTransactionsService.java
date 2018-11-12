package com.wardrobe.platform.service;

import com.wardrobe.common.bean.PageBean;
import com.wardrobe.common.po.UserOrderInfo;
import com.wardrobe.common.view.UserTransactionsInputView;

import java.math.BigDecimal;

/**
 * Created by cxs on 2018/8/1.
 */
public interface IUserTransactionsService {

    PageBean getUserTransactionsList(UserTransactionsInputView userTransactionsInputView);

    void addOrderUserTransactions(UserOrderInfo userOrderInfo, String serviceType, String type);

    void addUserTransactions(int uid, double price, String serviceType, String type);

    PageBean getUserTransactionsListIn(UserTransactionsInputView userTransactionsInputView);

}
