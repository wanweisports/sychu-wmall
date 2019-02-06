package com.wardrobe.platform.netty.client.bean;

import com.wardrobe.common.po.SysDeviceControl;
import io.netty.channel.Channel;

import java.util.Date;
import java.util.List;

/**
 * Created by cxs on 2018/9/10.
 */
public class ClientBean {

    private int did;
    private String deviceNo;
    private String host;
    private int port;
    private Channel serviceChannel;
    private List<SysDeviceControl> sysDeviceControls; //继电器列表
    private List<String> rfidDatas; //射频标签列表
    private String readStatus; //读取状态
    private Date connDate;     //连接时间

    public ClientBean(List<SysDeviceControl> sysDeviceControls){ //初始化8个设备的状态
        this.sysDeviceControls = sysDeviceControls;
    }

    public SysDeviceControl getDeviceControl(int lockId){
        for(SysDeviceControl deviceControl : sysDeviceControls){
            if(lockId == deviceControl.getLockId()){
                return deviceControl;
            }
        }
        return null;
    }

    public String getDeviceNo() {
        return deviceNo;
    }

    public void setDeviceNo(String deviceNo) {
        this.deviceNo = deviceNo;
    }

    public int getDid() {
        return did;
    }

    public void setDid(int did) {
        this.did = did;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public Channel getServiceChannel() {
        return serviceChannel;
    }

    public void setServiceChannel(Channel serviceChannel) {
        this.serviceChannel = serviceChannel;
    }

    public List<SysDeviceControl> getSysDeviceControls() {
        return sysDeviceControls;
    }

    public void setSysDeviceControls(List<SysDeviceControl> sysDeviceControls) {
        this.sysDeviceControls = sysDeviceControls;
    }

    public List<String> getRfidDatas() {
        return rfidDatas;
    }

    public void setRfidDatas(List<String> rfidDatas) {
        this.rfidDatas = rfidDatas;
    }

    public String getReadStatus() {
        return readStatus;
    }

    public void setReadStatus(String readStatus) {
        this.readStatus = readStatus;
    }

    public Date getConnDate() {
        return connDate;
    }

    public void setConnDate(Date connDate) {
        this.connDate = connDate;
    }
}
