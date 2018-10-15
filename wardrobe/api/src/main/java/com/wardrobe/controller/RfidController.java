package com.wardrobe.controller;

import com.wardrobe.common.annotation.Desc;
import com.wardrobe.common.bean.ResponseBean;
import com.wardrobe.common.util.HttpUtil;
import com.wardrobe.common.util.JsonUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
 * Created by cxs on 2018/10/12.
 */
@RequestMapping("rfid")
@Controller
public class RfidController extends BaseController {

    @Desc("读取商场射频电子标签")
    @ResponseBody
    @RequestMapping(value = "/readRfid")
    public ResponseBean readRfid(int did){
        String response = HttpUtil.sendGet("http://localhost:8090/rfid/readEpcLabelSC?did=" + did);
        Map map = JsonUtils.fromJson(response, Map.class);
        return new ResponseBean(map.get("code").toString(), map.get("message").toString());
    }

}
