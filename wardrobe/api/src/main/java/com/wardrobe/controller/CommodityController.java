package com.wardrobe.controller;

import com.wardrobe.common.bean.ResponseBean;
import com.wardrobe.common.view.CommodityInputView;
import com.wardrobe.platform.service.ICommodityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by cxs on 2018/7/30.
 */
@RequestMapping("commodity")
@Controller
public class CommodityController extends BaseController {

    @Autowired
    private ICommodityService commodityService;

    @ResponseBody
    @RequestMapping("index")
    public ResponseBean index(CommodityInputView commodityInputView){
        return new ResponseBean(super.setPageInfo(commodityService.getCommodityList(commodityInputView)));
    }

    @ResponseBody
    @RequestMapping("detail")
    public ResponseBean index(int cid){
        return new ResponseBean(commodityService.getCommodityDetail(cid));
    }

}
