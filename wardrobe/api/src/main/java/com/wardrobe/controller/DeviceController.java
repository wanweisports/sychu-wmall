package com.wardrobe.controller;

import com.wardrobe.common.bean.ResponseBean;
import com.wardrobe.common.view.DeviceInputView;
import com.wardrobe.platform.service.ISysDeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by cxs on 2018/8/24.
 */
@RequestMapping("device")
@Controller
public class DeviceController extends BaseController {

    @Autowired
    private ISysDeviceService deviceService;

    @ResponseBody
    @RequestMapping("devices")
    public ResponseBean devices(DeviceInputView deviceInputView){
        return new ResponseBean(setPageInfo(deviceService.getDeviceList(deviceInputView)));
    }

    @ResponseBody
    @RequestMapping("detail")
    public ResponseBean detail(int did){
        return new ResponseBean(deviceService.getDevice(did));
    }

}
