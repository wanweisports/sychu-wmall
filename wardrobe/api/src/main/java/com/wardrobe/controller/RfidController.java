package com.wardrobe.controller;

import com.wardrobe.common.annotation.Desc;
import com.wardrobe.common.bean.ResponseBean;
import com.wardrobe.common.constant.IPlatformConstant;
import com.wardrobe.common.util.HttpUtil;
import com.wardrobe.common.util.JsonUtils;
import com.wardrobe.platform.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by cxs on 2018/10/12.
 */
@RequestMapping("rfid")
@Controller
public class RfidController extends BaseController {

    @Autowired
    private IOrderService orderService;

    @Desc("读取商场射频电子标签")
    @ResponseBody
    @RequestMapping(value = "/readRfid")
    public ResponseBean readRfid(int did) throws ParseException{
        String response = HttpUtil.sendGet(rfidUrl + "/readEpcLabelSC?did=" + did);
        Map map = JsonUtils.fromJson(response, Map.class);
        String code = map.get("code").toString();
        if(IPlatformConstant.SUCCESS_CODE.equals(code)) {
            map = orderService.getRfidSettlement(map, getUserInfo().getUid());
            return new ResponseBean(map);
        }
        return new ResponseBean(code, map.get("message").toString());
    }

}
