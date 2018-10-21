package com.wardrobe.platform.service.impl;

import com.wardrobe.common.annotation.Desc;
import com.wardrobe.common.constant.IDBConstant;
import com.wardrobe.common.exception.MessageException;
import com.wardrobe.common.po.SysDeviceInfo;
import com.wardrobe.common.po.SysRfidInfo;
import com.wardrobe.common.util.StrUtil;
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
import com.wardrobe.platform.service.IUserShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by cxs on 2018/10/12.
 */
@Service
public class RfidServiceImpl extends BaseService implements IRfidService {

    @Autowired
    private IUserShoppingCartService userShoppingCartService;

    private Observer mObserver(String ip1, int port1){
        Observer mObserver = new RXObserver() {

            @Override
            protected void onInventoryTag(RXInventoryTag tag) {
                System.out.println("EPC data:" + tag.strEPC);
                RfidCache.getRfidBean(ip1, port1).pushEpc(tag.strEPC);
            }

            @Override
            protected void onInventoryTagEnd(RXInventoryTag.RXInventoryTagEnd endTag) {
                System.out.println("inventory end:" + endTag.mTotalRead);
                RfidBean rfidBean = RfidCache.getRfidBean(ip1, port1);
                int currentReadCount = rfidBean.getCurrentReadCount();
                if(currentReadCount <= rfidBean.getMaxReadCount()) {
                    System.out.println("Read...." + currentReadCount + "---" + rfidBean.getMaxReadCount());
                    rfidBean.setCurrentReadCount(currentReadCount+1);
                    ((RFIDReaderHelper) rfidBean.getReaderHelper()).realTimeInventory(RfidCache.BA, (byte) 0x01);
                }
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
            byte workAntenna = rfidBean.getWorkAntenna();
            final ReaderConnector mConnector = new ReaderConnector();
            ReaderHelper mReaderHelper = mConnector.connectNet(ip, port);

            if(mReaderHelper != null) {
                System.out.println("Connect success!");
                try {
                    mReaderHelper.registerObserver(mObserver(ip, port));
                    mReaderHelper.setRXTXListener(mListener(ip, port));
                    //((RFIDReaderHelper) mReaderHelper).getTagMask((byte) 0xff); //获取掩码设置
                    int success = ((RFIDReaderHelper) mReaderHelper).setWorkAntenna(RfidCache.BA, (byte)((int)workAntenna-1));//设置工作天线
                    System.out.println("设置天线：" + success);

                    rfidBean.setWorkAntennaStatus(success);
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
    public Map<String, Object> readEpcLabelIn(RfidBean rfidBean, int count){
        RfidBean rb = RfidCache.isConnect(rfidBean);
        if(rb == null) throw new MessageException("未连接射频");

        rb.clearEpcs();

        List<String> epcs = rb.getEpcs(count);
        System.out.println("epcs===>" + epcs);
        Map<String, Object> data = new HashMap<>(1, 1);
        data.put("epcs", epcs);
        return data;
    }

    @Override
    public Map<String, Object> readEpcLabelApi(RfidBean rfidBean, int count, int did){
        RfidBean rb = RfidCache.isConnect(rfidBean);
        if(rb == null) throw new MessageException("未连接射频");

        rb.clearEpcs();

        List<String> epcs = rb.getEpcs(count);
        System.out.println(epcs);
        //查询某个商场当天柜子里的衣服
        StringBuilder sql = new StringBuilder("SELECT cd.dbid, ci.cid, ci.commName, ci.price, sdc.`name`, 1 count, rfidEpc FROM sys_commodity_distribution cd, sys_device_control sdc, commodity_info ci");
        sql.append(" WHERE cd.dcid = sdc.dcid AND cd.cid = ci.cid AND sdc.did = ?1 AND sdc.`status` = ?2 AND cd.dbTime = CURDATE()");
        List<Map<String, Object>> list = baseDao.queryBySql(sql.toString(), did, IDBConstant.LOGIC_STATUS_YES);
        List<Map<String, Object>> payCommoditys = new ArrayList<>();
        for(Map<String, Object> map : list){
            String rfidEpc = map.get("rfidEpc").toString();
            boolean exist = false;
            for(String epc : epcs){
                if(epc.equals(rfidEpc)){
                    exist = true;
                    break;
                }
            }
            if(!exist){
                payCommoditys.add(map);
            }
        }
        Map<String, Object> map = new HashMap<>();
        map.put("commoditys", payCommoditys);
        map.put("sumPrice", userShoppingCartService.countSumPrice(payCommoditys));
        return map;
    }

    @Override
    public SysRfidInfo getRfidInfo(int rfid){
        return baseDao.getToEvict(SysRfidInfo.class, rfid);
    }

    @Override
    public Map<String, Object> getSysRfidIndexsIn(){
        Map<String, Object> data = new HashMap<>();
        SysDeviceInfo sysDeviceInfo = baseDao.queryByHqlFirst("FROM SysDeviceInfo");
        if(sysDeviceInfo != null) {
            Integer did = sysDeviceInfo.getDid();
            List<Map<String, Object>> sysRfidInfos = baseDao.queryBySql("SELECT * FROM sys_rfid_info WHERE did = ?1", did);
            sysRfidInfos.stream().forEach(map -> {
                map.put("rfidBean", RfidCache.getRfidBean(new RfidBean(map.get("ip").toString(), StrUtil.objToInt(map.get("port")))));
            });
            data.put("sysRfidInfos", sysRfidInfos);
        }
        return data;
    }

    @Desc("类型（1：商城射频  2：仓库射频）")
    @Override
    public SysRfidInfo getSysRfidInfoByDid(int did, String type){
        return baseDao.queryByHqlFirst("FROM SysRfidInfo WHERE did = ?1 AND type = ?2", did, type);
    }

}
