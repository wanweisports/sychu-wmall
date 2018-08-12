package com.wardrobe.platform.service;

import com.wardrobe.common.bean.PageBean;
import com.wardrobe.common.view.UserTransactionsInputView;

import java.math.BigDecimal;

/**
 * Created by cxs on 2018/8/1.
 */
public interface IUserTransactionsService {

    PageBean getUserTransactionsList(UserTransactionsInputView userTransactionsInputView);

    void addUserTransactions(int uid, int serviceId, String serviceType, BigDecimal price);

    PageBean getUserTransactionsListIn(UserTransactionsInputView userTransactionsInputView);

}
