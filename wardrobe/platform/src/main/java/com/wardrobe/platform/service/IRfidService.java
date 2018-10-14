package com.wardrobe.platform.service;

import com.wardrobe.common.po.SysRfidInfo;
import com.wardrobe.platform.rfid.bean.RfidBean;

import java.util.Map;

/**
 * Created by cxs on 2018/10/12.
 */
public interface IRfidService {

    boolean connectRfid(RfidBean rfidBean);

    void closeRfid(RfidBean rfidBean);

    Map<String, Object> readEpcLabelIn(RfidBean rfidBean, int count);

    Map<String, Object> readEpcLabelApi(RfidBean rfidBean, int count, int did);

    SysRfidInfo getRfidInfo(int rfid);

    Map<String, Object> getSysRfidIndexsIn();

    SysRfidInfo getSysRfidInfoByDid(int did, String type);

}
