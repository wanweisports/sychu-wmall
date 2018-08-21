package com.wardrobe.controller;

import com.wardrobe.common.bean.PageBean;
import com.wardrobe.common.bean.ResponseBean;
import com.wardrobe.common.po.CommodityColor;
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

import java.io.IOException;

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
        setPageInfo(model, pageBean, "/commodity/getCommoditys", commodityInputView);
        return "";
    }

    @ResponseBody
    @RequestMapping("addUpdateCommodity")
    public ResponseBean addUpdateCommodity(@CommodityResolver CommodityInfo commodityInfo, MultipartHttpServletRequest request) throws IOException {
       commodityService.addUpdateCommodityIn(commodityInfo, request);
        return new ResponseBean(true);
    }

    @ResponseBody
    @RequestMapping("updateStatus")
    public ResponseBean updateStatus(int cid, String status){
        commodityService.updateCommodityStatusIn(cid, status);
        return new ResponseBean(true);
    }

    @ResponseBody
    @RequestMapping("updateHot")
    public ResponseBean updateHot(int cid, String hot){
        commodityService.updateCommodityHotIn(cid, hot);
        return new ResponseBean(true);
    }

    @ResponseBody
    @RequestMapping("updateNewly")
    public ResponseBean updateNewly(int cid, String newly){
        commodityService.updateCommodityNewlyIn(cid, newly);
        return new ResponseBean(true);
    }

}
