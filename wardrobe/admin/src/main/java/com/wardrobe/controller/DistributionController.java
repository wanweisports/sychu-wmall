package com.wardrobe.controller;

import com.wardrobe.common.annotation.Desc;
import com.wardrobe.common.annotation.NotProtected;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 配送管理
 */
@Controller
@RequestMapping("/admin/distribution")
public class DistributionController extends BaseController {

    @Desc("柜子列表")
    @NotProtected
    @RequestMapping(value = "/settings")
    public String renderDistributionSettings() {
        return "Distribution/Settings";
    }

}
