package com.wardrobe.platform.service.impl;

import com.wardrobe.common.po.SysRfidInfo;
import com.wardrobe.platform.rfid.bean.RfidBean;
import com.wardrobe.platform.rfid.cache.RfidCache;
import com.wardrobe.platform.rfid.module.interaction.RXTXListener;
import com.wardrobe.platform.rfid.module.interaction.ReaderHelper;
import com.wardrobe.platform.rfid.rfid.RFIDReaderHelper;
import com.wardrobe.platform.rfid.rfid.ReaderConnector;
import com.wardrobe.platform.rfid.rfid.rxobserver.RXObserver;
import com.wardrobe.platform.rfid.rfid.rxobserver.bean.RXInventoryTag;
import com.wardrobe.platform.rfid.util.StringTool;
import com.wardrobe.platform.service.IRfidService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Observer;

/**
 * Created by cxs on 2018/10/12.
 */
@Service
public class RfidServiceImpl extends BaseService implements IRfidService {

    private Observer mObserver(String ip1, int port1){
        Observer mObserver = new RXObserver() {
            public String ip = ip1;
            public int port = port1;

            @Override
            protected void onInventoryTag(RXInventoryTag tag) {
                System.out.println("EPC data:" + tag.strEPC);
                RfidCache.getRfidBean(new RfidBean(ip, port1)).pushEpc(tag.strEPC);
            }

            @Override
            protected void onInventoryTagEnd(RXInventoryTag.RXInventoryTagEnd endTag) {
                System.out.println("inventory end:" + endTag.mTotalRead);
                //((RFIDReaderHelper) mReaderHelper).realTimeInventory((byte) 0xff, (byte) 0x01);
            }

            @Override
            protected void onExeCMDStatus(byte cmd,byte status) {
                System.out.format("CDM:%s  Execute status:%S", String.format("%02X", cmd), String.format("%02x", status));
            }
        };
        return mObserver;
    }

    private RXTXListener mListener(String ip1, int port1){
        RXTXListener mListener = new RXTXListener() {
            public String ip = ip1;
            public int port = port1;

            @Override
            public void reciveData(byte[] btAryReceiveData) {
                System.out.println("reciveData：" + StringTool.byteArrayToString(btAryReceiveData, 0, btAryReceiveData.length));
            }

            @Override
            public void sendData(byte[] btArySendData) {
                System.out.println("sendData：" + StringTool.byteArrayToString(btArySendData, 0, btArySendData.length));
            }

            @Override
            public void onLostConnect() {

            }
        };
        return mListener;
    }

    @Override
    public boolean connectRfid(RfidBean rfidBean){
        RfidBean rb = RfidCache.getRfidBean(rfidBean);
        if(rb == null){
            String ip = rfidBean.getIp();
            int port = rfidBean.getPort();
            final ReaderConnector mConnector = new ReaderConnector();
            ReaderHelper mReaderHelper = mConnector.connectNet(ip, port);

            if(mReaderHelper != null) {
                System.out.println("Connect success!");
                try {
                    mReaderHelper.registerObserver(mObserver(ip, port));
                    mReaderHelper.setRXTXListener(mListener(ip, port));
                    //((RFIDReaderHelper) mReaderHelper).getTagMask((byte) 0xff);
                    //((RFIDReaderHelper) mReaderHelper).realTimeInventory((byte) 0xFF, (byte) 0x01);
                    rfidBean.setReaderHelper(mReaderHelper);
                    rfidBean.setmConnector(mConnector);

                    RfidCache.connectRfid(rfidBean); //放入缓存
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                System.out.println("Connect faild!");
                mConnector.disConnect();
            }
            return mConnector.isConnected();
        }else{ //若缓存里有，但未连接，则清除，再连接
            if(!rb.getmConnector().isConnected()){
                RfidCache.closeRfid(rfidBean);
                connectRfid(rfidBean);
            }
        }
        return rb.getmConnector().isConnected();
    }

    @Override
    public void closeRfid(RfidBean rfidBean){
        RfidCache.closeRfid(rfidBean);
    }

    @Override
    public Map<String, Object> readEpcLabel(RfidBean rfidBean, int count){
        RfidBean rb = RfidCache.getRfidBean(rfidBean);
        rb.clearEpcs();

        Map<String, Object> data = new HashMap<>(1, 1);
        data.put("epcs", rb.getEpcs(1));
        return data;
    }

    @Override
    public SysRfidInfo getRfidInfo(int rfid){
        return baseDao.getToEvict(SysRfidInfo.class, rfid);
    }

}
