package com.wardrobe.controller;

import com.wardrobe.common.bean.ResponseBean;
import com.wardrobe.common.view.UserTransactionsInputView;
import com.wardrobe.platform.service.IUserTransactionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by cxs on 2018/8/1.
 */
@RequestMapping("transactions")
@Controller
public class UserTransactionsController extends BaseController {

    @Autowired
    private IUserTransactionsService userTransactionsService;

    /*
     * 用户交易流水
     */
    @ResponseBody
    @RequestMapping("index")
    public ResponseBean index(UserTransactionsInputView userTransactionsInputView){
        return new ResponseBean(setPageInfo(userTransactionsService.getUserTransactionsList(userTransactionsInputView)));
    }

}
