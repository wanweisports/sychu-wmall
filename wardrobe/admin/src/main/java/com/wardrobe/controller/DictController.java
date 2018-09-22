package com.wardrobe.controller;

import com.intermec.datacollection.rfid.BasicEPCReader;
import com.intermec.datacollection.rfid.BasicReaderEvent;
import com.intermec.datacollection.rfid.BasicTagEventListener;
import com.wardrobe.common.bean.ResponseBean;
import com.wardrobe.platform.service.IDictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import sun.rmi.transport.tcp.TCPChannel;
import sun.rmi.transport.tcp.TCPConnection;

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
