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
        System.out.println("response--->" + response);
        Map map = JsonUtils.fromJson(response, Map.class);
        String code = map.get("code").toString();
        if(IPlatformConstant.SUCCESS_CODE.equals(code)) {
            map = orderService.getRfidSettlement(map, getUserInfo().getUid());
            return new ResponseBean(map);
        }
        return new ResponseBean(code, map.get("message").toString());
    }

    public static void main(String[] args) {
        String response = "{\n" +
                "\t\"code\": \"1\",\n" +
                "\t\"message\": \"操作成功\",\n" +
                "\t\"data\": {\n" +
                "\t\t\"commoditys\": [{\n" +
                "\t\t\t\"rfidEpc\": null,\n" +
                "\t\t\t\"price\": 1280.00,\n" +
                "\t\t\t\"dbid\": 76,\n" +
                "\t\t\t\"resourcePath\": \"https://oss-admin.oss-cn-beijing.aliyuncs.com/img/b9d20c091145445a8e99c5d68e456f91_YS.jpg\",\n" +
                "\t\t\t\"name\": \"柜子1号\",\n" +
                "\t\t\t\"count\": 1,\n" +
                "\t\t\t\"commName\": \"材质优选|腰带阔腿裤\",\n" +
                "\t\t\t\"couPrice\": 1280.00,\n" +
                "\t\t\t\"cid\": 100\n" +
                "\t\t}, {\n" +
                "\t\t\t\"rfidEpc\": null,\n" +
                "\t\t\t\"price\": 680.00,\n" +
                "\t\t\t\"dbid\": 77,\n" +
                "\t\t\t\"resourcePath\": \"https://oss-admin.oss-cn-beijing.aliyuncs.com/img/7239ca5e474f4d75b2d4361215af05d4.jpg\",\n" +
                "\t\t\t\"name\": \"柜子1号\",\n" +
                "\t\t\t\"count\": 1,\n" +
                "\t\t\t\"commName\": \"高领长袖针织衫\",\n" +
                "\t\t\t\"couPrice\": 680.00,\n" +
                "\t\t\t\"cid\": 178\n" +
                "\t\t}, {\n" +
                "\t\t\t\"rfidEpc\": null,\n" +
                "\t\t\t\"price\": 2488.00,\n" +
                "\t\t\t\"dbid\": 78,\n" +
                "\t\t\t\"resourcePath\": \"https://oss-admin.oss-cn-beijing.aliyuncs.com/img/491c719ead0e40d0821910bc10d9e15d.jpg\",\n" +
                "\t\t\t\"name\": \"柜子1号\",\n" +
                "\t\t\t\"count\": 1,\n" +
                "\t\t\t\"commName\": \"拼接片状连衣裙\",\n" +
                "\t\t\t\"couPrice\": 2065.00,\n" +
                "\t\t\t\"cid\": 298\n" +
                "\t\t}, {\n" +
                "\t\t\t\"rfidEpc\": null,\n" +
                "\t\t\t\"price\": 1280.00,\n" +
                "\t\t\t\"dbid\": 80,\n" +
                "\t\t\t\"resourcePath\": \"https://oss-admin.oss-cn-beijing.aliyuncs.com/img/ad95c65bf03744d1ba0fc9c0db7fae99_YS.jpg\",\n" +
                "\t\t\t\"name\": \"柜子2号\",\n" +
                "\t\t\t\"count\": 1,\n" +
                "\t\t\t\"commName\": \"材质优选|印花连衣裙\",\n" +
                "\t\t\t\"couPrice\": 1280.00,\n" +
                "\t\t\t\"cid\": 135\n" +
                "\t\t}],\n" +
                "\t\t\"sumPrice\": 5305.0\n" +
                "\t}\n" +
                "}";

        Map map = JsonUtils.fromJson(response, Map.class);
        System.out.println(map);
        System.out.println(((Map)map.get("data")).get("sumPrice"));
    }

}
