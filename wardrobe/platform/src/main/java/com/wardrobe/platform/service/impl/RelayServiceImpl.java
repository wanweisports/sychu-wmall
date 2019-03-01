package com.wardrobe.platform.service.impl;

import com.wardrobe.common.annotation.Desc;
import com.wardrobe.common.bean.UserDriveBean;
import com.wardrobe.common.constant.IDBConstant;
import com.wardrobe.common.exception.MessageException;
import com.wardrobe.common.po.SysDeviceControl;
import com.wardrobe.common.po.SysDeviceInfo;
import com.wardrobe.common.util.DateUtil;
import com.wardrobe.common.util.StrUtil;
import com.wardrobe.platform.netty.client.ClientChannelUtil2;
import com.wardrobe.platform.netty.client.bean.ClientBean;
import com.wardrobe.platform.rfid.bean.RfidBean;
import com.wardrobe.platform.rfid.cache.RfidCache;
import com.wardrobe.platform.service.IOrderService;
import com.wardrobe.platform.service.IRelayService;
import com.wardrobe.platform.service.ISysDeviceService;
import com.wardrobe.platform.service.IUserShoppingCartService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by cxs on 2018/9/10.
 */
@Service
public class RelayServiceImpl extends BaseService implements IRelayService {

    private Logger logger = Logger.getLogger(RelayServiceImpl.class);

    ExecutorService executorService = Executors.newFixedThreadPool(10);

    @Autowired
    private ISysDeviceService deviceService;

    @Autowired
    private IOrderService orderService;

    @Override
    public SysDeviceInfo getSysDeviceInfo(int did) {
        return deviceService.getSysDeviceInfo(did);
    }

    @Override
    public SysDeviceControl getSysDeviceControl(int dcid) {
        return baseDao.getToEvict(SysDeviceControl.class, dcid);
    }

/*    @Override
    public synchronized boolean connectServer(String ip, int port) throws Exception{
        Channel serverChannel = ClientChannelUtil.getServerChannel(ip, port);
        if(serverChannel == null) {
            NettyClient nettyClient = new NettyClient(ip, port, deviceService);
            ChannelFuture future = nettyClient.clientServer();
            return future != null && future.isSuccess();
        }else{
            throw new MessageException(ClientChannelUtil.getNowStatus(ip, port));
        }
        return true;
    }*/

/*    @Override
    public synchronized void openServerLock(int did, int lockId) throws Exception{
        //判断是否连接中
        ClientChannelUtil2.isOpenThrowable();

        ClientBean clientBean = ClientChannelUtil2.getClientBean(did);
        ClientChannelUtil2.sendEvent(clientBean, new String[]{"DB", "0"+lockId, "01"});
    }

    @Override
    public synchronized void closeServerLock(int did, int lockId){
        //判断是否连接中
        ClientChannelUtil2.isOpenThrowable();

        ClientBean clientBean = ClientChannelUtil2.getClientBean(did);
        ClientChannelUtil2.sendEvent(clientBean, new String[]{"DB", "0"+lockId, "00"});
    }*/

/*    @Override
    public synchronized void openServerAllLock(String ip, int port){
        //判断是否连接中
        if(ClientChannelUtil.isOpen(ip, port)) {
            Channel serverChannel = ClientChannelUtil.getServerChannel(ip, port);
            serverChannel.writeAndFlush(Unpooled.copiedBuffer(ClientChannelUtil.LOCK_OPEN_ALL, CharsetUtil.UTF_8));
        }else{
            throw new MessageException(ClientChannelUtil.getNowStatus(ip, port));
        }
    }

    @Override
    public synchronized void closeServerAllLock(String ip, int port){
        //判断是否连接中
        if(ClientChannelUtil.isOpen(ip, port)) {
            Channel serverChannel = ClientChannelUtil.getServerChannel(ip, port);
            serverChannel.writeAndFlush(Unpooled.copiedBuffer(ClientChannelUtil.LOCK_CLOSE_ALL, CharsetUtil.UTF_8));
        }else{
            throw new MessageException(ClientChannelUtil.getNowStatus(ip, port));
        }
    }*/

/*    @Override
    public synchronized void openServerDrive(int did){
        //判断是否连接中
        ClientChannelUtil2.isOpenThrowable();

        ClientBean clientBean = ClientChannelUtil2.getClientBean(did);
        ClientChannelUtil2.sendEvent(clientBean, new String[]{"DA", "01"});
    }

    @Override
    public synchronized void closeServerDrive(int did){
        //判断是否连接中
        //判断是否连接中
        ClientChannelUtil2.isOpenThrowable();

        ClientBean clientBean = ClientChannelUtil2.getClientBean(did);
        //判断是否为关闭状态才能打开
        ClientChannelUtil2.sendEvent(clientBean, new String[]{"DA", "00"});
    }*/

/*    @Override
    public Map<String, Object> getRealyIndexsIn(){
        Map<String, Object> data = new HashMap<>(5, 1);
        SysDeviceInfo sysDeviceInfo = baseDao.queryByHqlFirst("FROM SysDeviceInfo");
        if(sysDeviceInfo != null) {
            //获取8个柜子的状态
            *//*String doorIp = sysDeviceInfo.getDoorIp();
            Integer doorPort = sysDeviceInfo.getDoorPort();
            String lockIp = sysDeviceInfo.getLockIp();
            Integer lockPort = sysDeviceInfo.getLockPort();*//*
            data.put("deviceInfo", sysDeviceInfo);
            //获取硬件的状态
            if(ClientChannelUtil2.isOpen()) {
                List<SysDeviceControl> deviceDoorControls = baseDao.queryByHql("FROM SysDeviceControl WHERE did = ?1 AND type = ?2", sysDeviceInfo.getDid(), IDBConstant.LOGIC_STATUS_YES);
                deviceDoorControls.stream().forEach(deviceControl -> {
                    SysDeviceControl deviceBean = ClientChannelUtil.readDriveStatus(ClientChannelUtil.getServerChannel(doorIp, doorPort), deviceControl.getLockId());
                    deviceControl.setStatus(deviceBean.getStatus());
                });
                data.put("deviceDoorControls", deviceDoorControls);

                data.put("conStatus", true);
            }else{
                data.put("conStatus", false); //服务器连接状态
            }
            //获取锁的状态
            *//*data.put("deviceControls", getAllDeviceControls(lockIp, lockPort, sysDeviceInfo.getDid()));*//*
        }
        return data;
    }*/

    @Override
    public void openDoor(int did, int uid) {
        /*SysDeviceInfo sysDeviceInfo = deviceService.getSysDeviceInfo(did);
        ReserveOrderInfo reserveOrderInfo = orderService.getLastReserveOrderInfo(uid);
        //1.判断射频（除了已支付的衣服标签，是否都检查到了，再开门）
        if(1==1){}
        //2.判断自己的柜子已经关闭
        //  A.查询当前预约的订单
        SysDeviceControl deviceBean = ClientChannelUtil.readDriveStatus(sysDeviceInfo.getLockIp(), sysDeviceInfo.getLockPort(), reserveOrderInfo.getDcid());
        if(!ClientChannelUtil.READ_CLOSE.equals(deviceBean.getStatus())) throw new MessageException("出门前请您关闭柜子");
        if(1==1) {
            try {
                openServerDrive(sysDeviceInfo.getDoorIp(), sysDeviceInfo.getDoorPort(), 1);
            } catch (MessageException e) {
                e.printStackTrace();
            }
        }*/
    }

    /*@Override
    public void closeDoor(int did, int uid){
        SysDeviceInfo sysDeviceInfo = deviceService.getSysDeviceInfo(did);
        try {
            closeServerDrive(sysDeviceInfo.getDoorIp(), sysDeviceInfo.getDoorPort(), 1);
        } catch (MessageException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void openLock(int dcid) throws Exception{
        SysDeviceControl sysDeviceControl = deviceService.getSysDeviceControl(dcid);
        SysDeviceInfo sysDeviceInfo = deviceService.getSysDeviceInfo(sysDeviceControl.getDid());
        try {
            openServerLock(sysDeviceInfo.getLockIp(), sysDeviceInfo.getLockPort(), dcid);
        } catch (MessageException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void closeLock(int dcid) throws Exception{
        SysDeviceControl sysDeviceControl = deviceService.getSysDeviceControl(dcid);
        SysDeviceInfo sysDeviceInfo = deviceService.getSysDeviceInfo(sysDeviceControl.getDid());
        try {
            closeServerLock(sysDeviceInfo.getLockIp(), sysDeviceInfo.getLockPort(), dcid);
        } catch (MessageException e) {
            e.printStackTrace();
        }
    }*/

/*    @Override
    public void downlineRelay(String ip, int port){
        Channel serverChannel = ClientChannelUtil.getServerChannel(ip, port);
        if(serverChannel != null){
            serverChannel.eventLoop().parent().shutdownGracefully();
        }
    }*/

    @Desc("扫码开门==进门")
    @Override
    public synchronized void saveUserOpenServerDrive(UserDriveBean userDriveBean) throws Exception {
        /*SysDeviceInfo sysDeviceInfo = userDriveBean.getSysDeviceInfo();
        //2. 试衣间可用？
        if(!IDBConstant.LOGIC_STATUS_YES.equals(sysDeviceInfo.getStatus())){ //N: 提示 试衣间当前不可用，请稍后再试
            Date datePrev15 = DateUtil.addHHMMTime(new Date(), Calendar.MINUTE, -16);
            Timestamp openLockTime = sysDeviceInfo.getOpenLockTime();
            if(openLockTime != null && openLockTime.after(datePrev15)) { //开门十五分钟内是否扫码开柜门？N：试衣间状态更新为启用【以当前时间，15分钟以后未开柜子，则算没有人使用】
                throw new MessageException("试衣间当前不可用，请稍后再试！");
            }
        }
        //3. 服务端下发（进）大门开门指令
        try{
            openServerDrive(sysDeviceInfo.getDoorIp(), sysDeviceInfo.getDoorPort(), userDriveBean.getDriveId());
        }catch (MessageException e){
            e.printStackTrace();
        }
        //4. 调用RIDF记录当前库存状态

        //5.试衣间状态更新为占用
        Timestamp nowDate = DateUtil.getNowDate();
        sysDeviceInfo.setOpenTime(nowDate);
        sysDeviceInfo.setStatus(IDBConstant.LOGIC_STATUS_NO); //占用
        sysDeviceInfo.setOpenLockTime(nowDate);
        baseDao.save(sysDeviceInfo, sysDeviceInfo.getDid());*/
    }

    @Desc("扫码开柜门")
    @Override
    public synchronized void saveUserOpenServerLock(UserDriveBean userDriveBean) throws Exception {
        /*SysDeviceInfo sysDeviceInfo = userDriveBean.getSysDeviceInfo();
        //7.下发柜门开门指令
        openServerLock(sysDeviceInfo.getLockIp(), sysDeviceInfo.getLockPort(), userDriveBean.getDriveId());

        Timestamp nowDate = DateUtil.getNowDate();
        SysDeviceControl sysDeviceControl = deviceService.getSysDeviceControl(userDriveBean.getDriveId());
        sysDeviceControl.setOpenTime(nowDate);
        sysDeviceControl.setLock(IDBConstant.LOGIC_STATUS_YES); //开启
        baseDao.save(sysDeviceControl, sysDeviceControl.getDcid());

        sysDeviceInfo.setOpenLockTime(nowDate);
        baseDao.save(sysDeviceInfo, sysDeviceInfo.getDid());*/
        //8.用户试衣
    }

    @Desc("扫码关柜门")
    @Override
    public synchronized void saveUserCloseServerLock(UserDriveBean userDriveBean) throws Exception {
        /*SysDeviceInfo sysDeviceInfo = userDriveBean.getSysDeviceInfo();
        //7.下发柜门开门指令
        closeServerLock(sysDeviceInfo.getLockIp(), sysDeviceInfo.getLockPort(), userDriveBean.getDriveId());

        SysDeviceControl sysDeviceControl = deviceService.getSysDeviceControl(userDriveBean.getDriveId());
        sysDeviceControl.setCloseTime(DateUtil.getNowDate());
        sysDeviceControl.setLock(IDBConstant.LOGIC_STATUS_NO); //锁住
        baseDao.save(sysDeviceControl, sysDeviceControl.getDcid());*/
    }

    @Desc("扫码开门==出门")
    @Override
    public synchronized void saveUserCloseServerDrive(UserDriveBean userDriveBean) throws Exception {
        /*SysDeviceInfo sysDeviceInfo = userDriveBean.getSysDeviceInfo();

        //9.出门与支付
        //判断是否所有锁关闭状态才能打开（出）大门
        List<SysDeviceControl> allDeviceControls = getAllDeviceControls(sysDeviceInfo.getLockIp(), sysDeviceInfo.getLockPort(), sysDeviceInfo.getDid());
        StringBuilder msg = new StringBuilder();
        if(allDeviceControls != null){
            allDeviceControls.stream().forEach(deviceControl -> {
                *//*if (ClientChannelUtil.READ_OPEN.equals(deviceControl.getStatus())) { //暂时
                    if(msg.length() > 0) msg.append("、");
                    msg.append(deviceControl.getName());
                }*//*
            });
        }
        if(msg.length() > 0){ //检查柜门关闭状态已全部关闭？N： 提示 请关好柜门再出门
            //检测所有柜子是否已经关闭
            throw new MessageException("出门前请关好柜子：" + msg.toString());
        }

        //10.调用RIDF检查商品库存变化

        if(1==1){ //库存有变化？N：下发大门开门指令，提示感谢使用

        }else{
            //11.创建试衣结算订单【这里需要检测拿走了哪些衣服，必须和标签做对应】

        }

        //服务端下发（出）大门开门指令
        try{
            openServerDrive(sysDeviceInfo.getDoorIp(), sysDeviceInfo.getDoorPort(), userDriveBean.getDriveId());
        }catch (MessageException e){
            e.printStackTrace();
        }

        //试衣间状态更新为解除占用
        sysDeviceInfo.setCloseTime(DateUtil.getNowDate());
        sysDeviceInfo.setStatus(IDBConstant.LOGIC_STATUS_NO); //解除占用
        sysDeviceInfo.setOpenLockTime(null);
        baseDao.save(sysDeviceInfo, sysDeviceInfo.getDid());*/
    }

    @Desc("扫码开门==进门")
    @Override
    public synchronized void apiOpenDoor(UserDriveBean userDriveBean) {
        //判断柜子是否都关闭，外面用户才能进大门
        int did = userDriveBean.getDid();
        Map<String, Object> readAllMap = readAll(did);
        if (IDBConstant.LOGIC_STATUS_YES.equals(readAllMap.get("status"))) {
            List<SysDeviceControl> deviceControls = (List<SysDeviceControl>) readAllMap.get("deviceControls");
            for (SysDeviceControl deviceControl : deviceControls) { //检查8个柜子状态
                if (!"0".equals(deviceControl.getReadStatus())) throw new MessageException("试衣间已有顾客在试衣，请稍候再试^^");
            }
            openDoor(did);
            //几秒后关闭
            executorService.execute(()-> {
                    try{
                        Thread.sleep(5000L);
                        closeDoor(did);
                    }catch (Exception e){
                        e.printStackTrace();
                    }
            });
        } else {
            throw new MessageException("设备未连接，请联系管理员！");
        }
    }

    @Desc("扫码开门==出门")
    @Override
    public synchronized void apiCloseDoor(UserDriveBean userDriveBean){
        //判断柜子是否都关闭，里面用户才能出大门
        int did = userDriveBean.getDid();
        Map<String, Object> readAllMap = readAll(did);
        if (IDBConstant.LOGIC_STATUS_YES.equals(readAllMap.get("status"))) {
            List<SysDeviceControl> deviceControls = (List<SysDeviceControl>) readAllMap.get("deviceControls");
            for (SysDeviceControl deviceControl : deviceControls) { //检查8个柜子状态
                if (!"0".equals(deviceControl.getReadStatus())) throw new MessageException("请放回所有衣服并关闭所有柜子后再离开试衣间^^");
            }
            openDoor(did);
            //几秒后关闭
            executorService.execute(() -> {
                try {
                    Thread.sleep(5000L);
                    closeDoor(did);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        } else {
            throw new MessageException("设备未连接，请联系管理员！");
        }
    }

    @Desc("用户开柜")
    @Override
    public synchronized void userOpenLock(UserDriveBean userDriveBean){
        //判断柜子是否都关闭，里面用户才能出大门
        int did = userDriveBean.getDid();
        Map<String, Object> readAllMap = readAll(did);
        if (IDBConstant.LOGIC_STATUS_YES.equals(readAllMap.get("status"))) {
            List<SysDeviceControl> deviceControls = (List<SysDeviceControl>) readAllMap.get("deviceControls");
            for (SysDeviceControl deviceControl : deviceControls) { //检查大门状态
                if(deviceControl.getLockId() == 0) { //大门
                    if (!"0".equals(deviceControl.getReadStatus())) throw new MessageException("请关闭大门再开启柜子，谢谢^^");
                }
            }
            openLock(did, userDriveBean.getLockId());

            //几秒后关闭柜子
            executorService.execute(() -> {
                try {
                    Thread.sleep(3000L);
                    closeLock(did, userDriveBean.getLockId());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        } else {
            throw new MessageException("设备未连接，请联系管理员！");
        }
    }

    /***********************************************************************/

    @Desc("开大门")
    @Override
    public synchronized Map<String, Object> openDoor(int did){
        //判断是否连接中
        ClientChannelUtil2.isOpenThrowable();
        ClientBean clientBean = ClientChannelUtil2.getClientBean(did);
        ClientChannelUtil2.sendEventWait(clientBean, new String[]{"DA", "01"});

        sleep();
        Map<String, Object> data = new HashMap<>(1, 1);
        data.put("status", clientBean.getDeviceControl(0).getReadStatus());
        return data;
    }

    public void sleep(){
        try{
            //Thread.sleep(500L);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Desc("关大门")
    @Override
    public synchronized Map<String, Object> closeDoor(int did){
        //判断是否连接中
        ClientChannelUtil2.isOpenThrowable();
        ClientBean clientBean = ClientChannelUtil2.getClientBean(did);
        ClientChannelUtil2.sendEventWait(clientBean, new String[]{"DA", "00"});

        sleep();
        Map<String, Object> data = new HashMap<>(1, 1);
        data.put("status", clientBean.getDeviceControl(0).getReadStatus());
        return data;
    }

    @Desc("开柜门")
    @Override
    public synchronized Map<String, Object> openLock(int did, int lock){
        //判断是否连接中
        ClientChannelUtil2.isOpenThrowable();
        ClientBean clientBean = ClientChannelUtil2.getClientBean(did);
        ClientChannelUtil2.sendEventWait(clientBean, new String[]{"DB", "0" + lock, "01"});

        sleep();
        Map<String, Object> data = new HashMap<>(1, 1);
        data.put("status", clientBean.getDeviceControl(lock).getReadStatus());
        return data;
    }

    @Desc("关柜门")
    @Override
    public synchronized Map<String, Object> closeLock(int did, int lock){
        //判断是否连接中
        ClientChannelUtil2.isOpenThrowable();
        ClientBean clientBean = ClientChannelUtil2.getClientBean(did);
        ClientChannelUtil2.sendEventWait(clientBean, new String[]{"DB", "0" + lock, "00"});

        sleep();
        Map<String, Object> data = new HashMap<>(1, 1);
        data.put("status", clientBean.getDeviceControl(lock).getReadStatus());
        return data;
    }

    @Desc("读取某个试衣间所有硬件状态")
    @Override
    public synchronized Map<String, Object> readAll(int did){
        logger.info("readAll===did:" + did);
        Map<String, Object> data = new HashMap<>();
        SysDeviceInfo sysDeviceInfo = baseDao.queryByHqlFirst("FROM SysDeviceInfo WHERE did = ?1", did);
        data.put("deviceInfo", sysDeviceInfo);
        if(ClientChannelUtil2.isOpen()) {
            ClientBean clientBean = ClientChannelUtil2.getClientBean(did);
            ClientChannelUtil2.sendEventWait(clientBean, new String[]{"DC"});

            sleep();
            data.put("status", IDBConstant.LOGIC_STATUS_YES); //连接状态
            data.put("deviceControls", clientBean.getSysDeviceControls());
            data.put("connDate", DateUtil.dateToString(clientBean.getConnDate(), DateUtil.YYYYMMDDHHMMSS));
        }else{
            data.put("status", IDBConstant.LOGIC_STATUS_NO); //连接状态
        }
        return data;
    }

    @Desc("测试读取射频电子标签")
    @Override
    public synchronized Map<String, Object> rfidRead(int did){
        //判断是否连接中
        ClientChannelUtil2.isOpenThrowable();
        ClientBean clientBean = ClientChannelUtil2.getClientBean(did);
        ClientChannelUtil2.sendEventWait(clientBean, new String[]{"DD"});

        sleep();
        Map<String, Object> data = new HashMap<>(1, 1);
        data.put("rfids", clientBean.getRfidDatas());
        return data;
    }

    @Autowired
    private IUserShoppingCartService userShoppingCartService;

    @Desc("查询某个商场当天柜子里的衣服(查询状态=1)，支付后，配送表衣服状态变为已售出(=2)")
    @Override
    public Map<String, Object> readEpcLabelApi(int did){
        //判断是否连接中
        Map<String, Object> rfidMap = rfidRead(did);
        System.out.println(rfidMap);

        List<String> epcs = (List<String>) rfidMap.get("rfids");

        //查询某个商场当天柜子里的衣服
        StringBuilder sql = new StringBuilder("SELECT cd.dbid, ci.cid, ci.commName, ci.couPrice, ci.price, sdc.`name`, 1 count, rfidEpc, cc.colorName, cz.size FROM sys_commodity_distribution cd, sys_device_control sdc, commodity_info ci, commodity_color cc, commodity_size cz");
        sql.append(" WHERE cd.dcid = sdc.dcid AND cd.cid = ci.cid AND ci.cid = cc.cid AND cz.sid = cd.sid AND sdc.did = ?1 AND sdc.`status` = ?2 AND cd.status = ?3 AND cd.dbTime = CURDATE()");
        List<Map<String, Object>> list = baseDao.queryBySql(sql.toString(), did, IDBConstant.LOGIC_STATUS_YES, IDBConstant.LOGIC_STATUS_YES);
        List<Map<String, Object>> payCommoditys = new ArrayList<>();
        for(Map<String, Object> map : list){
            String rfidEpc = StrUtil.objToStrDefEmpty(map.get("rfidEpc"));
            if(epcs == null || !epcs.contains(rfidEpc)){ //未在射频读取的标签内，则需要返回给小程序用户
                payCommoditys.add(map);
            }
        }
        Map<String, Object> map = new HashMap<>();
        map.put("commoditys", payCommoditys);
        map.put("sumPrice", userShoppingCartService.countSumPrice(payCommoditys));
        logger.info("readEpcLabelApi======>" + payCommoditys + "，sumPrice====>" + map.get("sumPrice"));
        return map;
    }

}
