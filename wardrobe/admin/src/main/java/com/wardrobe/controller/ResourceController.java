package com.wardrobe.controller;

import com.wardrobe.common.bean.ResponseBean;
import com.wardrobe.common.po.CommodityInfo;
import com.wardrobe.controller.annotation.CommodityResolver;
import com.wardrobe.platform.service.IResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

/**
 * Created by cxs on 2018/7/30.
 */
@RequestMapping("resource")
@Controller
public class ResourceController extends BaseController {

    @Autowired
    private IResourceService resourceService;

    @ResponseBody
    @RequestMapping("delRes")
    public ResponseBean delRes(int resourceId){
        resourceService.delete(resourceId);
        return new ResponseBean(true);
    }

}
