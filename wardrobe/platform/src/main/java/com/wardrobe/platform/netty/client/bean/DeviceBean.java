package com.wardrobe.platform.netty.client.bean;

/**
 * Created by 陈小松 on 2018/9/15.
 */
public class DeviceBean {

    private int deviceNo;
    private String status;  //8号为开状态：Relayon 8，8号为关状态：Relayoff 8

    public int getDeviceNo() {
        return deviceNo;
    }

    public void setDeviceNo(int deviceNo) {
        this.deviceNo = deviceNo;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
