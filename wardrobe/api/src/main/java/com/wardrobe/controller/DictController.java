package com.wardrobe.controller;

import com.wardrobe.common.annotation.NotProtected;
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
    @NotProtected
    @RequestMapping("getDicts")
    public ResponseBean getDicts(String dictName){
        return new ResponseBean(new HashMap(1, 1){{put("dicts", dictService.getDicts(dictName));}}); //dictName其实是final类型，jdk1.8特性自动转换
    }

}
