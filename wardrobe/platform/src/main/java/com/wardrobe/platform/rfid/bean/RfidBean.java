package com.wardrobe.platform.rfid.bean;

import com.wardrobe.platform.rfid.module.interaction.ReaderHelper;
import com.wardrobe.platform.rfid.rfid.RFIDReaderHelper;
import com.wardrobe.platform.rfid.rfid.ReaderConnector;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cxs on 2018/10/12.
 */
public class RfidBean {

    private ReaderConnector mConnector;
    private ReaderHelper readerHelper;
    private String ip;
    private int port;
    private String status; //连接状态1: 连接
    private List<String> epcList = new ArrayList<>(); //当前读到的EPC标签

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

    public void setReaderHelper(ReaderHelper readerHelper) {
        this.readerHelper = readerHelper;
    }

    public List<String> getEpcs(int count) { //标签个数
        int i = 1;
        do {
            ((RFIDReaderHelper) readerHelper).realTimeInventory((byte) 0xFF, (byte) 0x01);
            try {
                Thread.sleep(100L);
            }catch (Exception e){
                e.printStackTrace();
            }
            i++;
        }while (i < count);
        return epcList;
    }

    public synchronized void pushEpc(String epc){
        if(!epcList.contains(epc)) {
            epcList.add(epc);
        }
    }

    public synchronized void clearEpcs(){
        epcList.clear();
    }

}
