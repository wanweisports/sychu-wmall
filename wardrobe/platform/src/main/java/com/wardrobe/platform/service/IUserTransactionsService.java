package com.wardrobe.platform.service;

import com.wardrobe.common.bean.PageBean;
import com.wardrobe.common.view.UserTransactionsInputView;

/**
 * Created by cxs on 2018/8/1.
 */
public interface IUserTransactionsService {

    PageBean getUserTransactionsList(UserTransactionsInputView userTransactionsInputView);

}
