package com.wardrobe.controller;

import com.wardrobe.common.bean.ResponseBean;
import com.wardrobe.platform.service.IDictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;

@RequestMapping("dict")
@Controller
public class DictController extends BaseController {

    @Autowired
    private IDictService dictService;

    @ResponseBody
    @RequestMapping("delDict")
    public ResponseBean delDict(int dictId){
        dictService.deleteDict(dictId);
        return new ResponseBean(true);
    }

}
