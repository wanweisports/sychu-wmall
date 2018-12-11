package com.wardrobe.platform.rfid.cache;

import com.wardrobe.common.constant.IDBConstant;
import com.wardrobe.platform.rfid.bean.RfidBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cxs on 2018/10/12.
 */
public class RfidCache {

    public static byte BA = (byte) 0xFF; //广播地址
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

    public static synchronized void closeRfid(String ip, int port){
        RfidBean rb = getRfidBean(ip, port);
        if(rb != null){
            rfidBeans.remove(rb);
            rb.getmConnector().disConnect();
        }
    }

    public static synchronized void closeRfid(RfidBean rfidBean){
        closeRfid(rfidBean.getIp(), rfidBean.getPort());
    }

    public static RfidBean getRfidBean(RfidBean rfidBean){
        for(RfidBean rb : rfidBeans){
            if(rb.getIp().equals(rfidBean.getIp()) && rb.getPort() == rfidBean.getPort()){
                return rb;
            }
        }
        return null;
    }

    public static RfidBean getRfidBean(String ip, int port){
        for(RfidBean rb : rfidBeans){
            if(rb.getIp().equals(ip) && rb.getPort() == port){
                return rb;
            }
        }
        return null;
    }

    public static RfidBean isConnect(RfidBean rfidBean){
        RfidBean rb = getRfidBean(rfidBean);
        return rb != null && rb.getmConnector().isConnected() ? rb : null;
    }

    public static RfidBean getRfidBeanAndOpen(RfidBean rfidBean){
        RfidBean rb = getRfidBean(rfidBean);
        if(rb != null){
            rb = isConnect(rb); //判断是否连接
            if(rb == null){
                closeRfid(rb);
            }
            return rb;
        }
        return null;
    }

}
