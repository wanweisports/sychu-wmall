package com.wardrobe.controller;

import com.wardrobe.common.bean.PageBean;
import com.wardrobe.common.bean.ResponseBean;
import com.wardrobe.common.po.CommodityInfo;
import com.wardrobe.common.view.CommodityInputView;
import com.wardrobe.controller.annotation.CommodityResolver;
import com.wardrobe.platform.service.ICommodityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

/**
 * Created by cxs on 2018/7/30.
 */
@RequestMapping("commodity")
@Controller
public class CommodityController extends BaseController {

    @Autowired
    private ICommodityService commodityService;

    @RequestMapping("getCommoditys")
    public String getCommoditys(CommodityInputView commodityInputView, Model model){
        PageBean pageBean = commodityService.getCommodityListIn(commodityInputView);
        setPageInfo(model, pageBean);
        return "";
    }

    @ResponseBody
    @RequestMapping("addCommodity")
    public ResponseBean addCommodity(@CommodityResolver CommodityInfo commodityInfo, MultipartFile[] multipartFiles){
       commodityService.addCommodity(commodityInfo);
        return new ResponseBean(true);
    }

}
