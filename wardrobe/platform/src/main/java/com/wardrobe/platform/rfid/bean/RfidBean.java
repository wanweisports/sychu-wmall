package com.wardrobe.platform.rfid.bean;

import com.wardrobe.platform.rfid.cache.RfidCache;
import com.wardrobe.platform.rfid.module.interaction.ReaderHelper;
import com.wardrobe.platform.rfid.rfid.RFIDReaderHelper;
import com.wardrobe.platform.rfid.rfid.ReaderConnector;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cxs on 2018/10/12.
 * btReadId - Reader Address(0xFF Public Address)
    btWorkAntenna - Ant ID(0x00:Ant 1, 0x01:Ant 2, 0x02:Ant 3, 0x03:Ant 4,0x04:Ant 5, 0x05:Ant 6, 0x06:Ant 7, 0x07:Ant 8)
    返回:
    Succeeded :0, Failed:-1
 */
public class RfidBean {
    private ReaderConnector mConnector;
    private ReaderHelper readerHelper;
    private String ip;
    private int port;
    private byte workAntenna; //工作天线
    private int workAntennaStatus; //工作天线连接状态0:成功  -1:失败
    private String status; //连接状态1: 连接
    private List<String> epcList = new ArrayList<>(); //当前读到的EPC标签
    private int currentReadCount = 1; //当前读取次数
    private int maxReadCount = 1;     //最大读取次数

    public RfidBean(String ip, int port, int workAntenna) {
        this.ip = ip;
        this.port = port;
        this.workAntenna = (byte)workAntenna;
    }

    public RfidBean(String ip, int port) {
        this.ip = ip;
        this.port = port;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public byte getWorkAntenna() {
        return workAntenna;
    }

    public void setWorkAntenna(byte workAntenna) {
        this.workAntenna = workAntenna;
    }

    public int getWorkAntennaStatus() {
        return workAntennaStatus;
    }

    public void setWorkAntennaStatus(int workAntennaStatus) {
        this.workAntennaStatus = workAntennaStatus;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public ReaderConnector getmConnector() {
        return mConnector;
    }

    public void setmConnector(ReaderConnector mConnector) {
        this.mConnector = mConnector;
    }

    public ReaderHelper getReaderHelper() {
        return readerHelper;
    }

    public void setReaderHelper(ReaderHelper readerHelper) {
        this.readerHelper = readerHelper;
    }

    public int getCurrentReadCount() {
        return currentReadCount;
    }

    public void setCurrentReadCount(int currentReadCount) {
        this.currentReadCount = currentReadCount;
    }

    public int getMaxReadCount() {
        return maxReadCount;
    }

    public void setMaxReadCount(int maxReadCount) {
        this.maxReadCount = maxReadCount;
    }

    public List<String> getEpcs(int count) { //最大读取次数
        this.maxReadCount = count;
        ((RFIDReaderHelper) readerHelper).realTimeInventory(RfidCache.BA, (byte) 0x01);
        sleep(100*count);
        return epcList;
    }

    public RfidBean pushEpc(String epc){
        if(!epcList.contains(epc)) {
            System.out.println(epc);
            epcList.add(epc);
        }
        return this;
    }

    public synchronized void clearEpcs(){
        epcList.clear();
    }

    public void sleep(long time){
        try {
            Thread.sleep(time);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        System.out.println(0x02);
        System.out.println(0x2);
        System.out.println(0x00);
    }

}
