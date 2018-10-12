package com.wardrobe.platform.rfid.cache;

import com.wardrobe.common.constant.IDBConstant;
import com.wardrobe.platform.rfid.bean.RfidBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cxs on 2018/10/12.
 */
public class RfidCache {

    private static List<RfidBean> rfidBeans = new ArrayList<>();

    public static synchronized void connectRfid(RfidBean rfidBean){
        RfidBean rb = getRfidBean(rfidBean);
        if(rb == null){
            if(rfidBean.getmConnector().isConnected()){
                rfidBean.setStatus(IDBConstant.LOGIC_STATUS_YES);
                rfidBeans.add(rfidBean);
            }
        }
    }

    public static synchronized void closeRfid(RfidBean rfidBean){
        RfidBean rb = getRfidBean(rfidBean);
        if(rb != null){
            rfidBeans.remove(rb);
            rb.getmConnector().disConnect();
        }
    }

    public static RfidBean getRfidBean(RfidBean rfidBean){
        for(RfidBean rb : rfidBeans){
            if(rb.getIp().equals(rfidBean.getIp()) && rb.getPort() == rfidBean.getPort()){
                return rb;
            }
        }
        return null;
    }

}
