package com.wardrobe.platform.service.impl;

import com.wardrobe.common.bean.PageBean;
import com.wardrobe.common.constant.IDBConstant;
import com.wardrobe.common.constant.IPlatformConstant;
import com.wardrobe.common.exception.MessageException;
import com.wardrobe.common.po.SysCommodityDistribution;
import com.wardrobe.common.po.SysDeviceControl;
import com.wardrobe.common.po.SysDeviceInfo;
import com.wardrobe.common.util.DateUtil;
import com.wardrobe.common.util.JsonUtils;
import com.wardrobe.common.util.StrUtil;
import com.wardrobe.common.view.DeviceInputView;
import com.wardrobe.platform.service.ISysDeviceService;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by cxs on 2018/8/24.
 */
@Service
public class SysDeviceServiceImpl extends BaseService implements ISysDeviceService {

    @Override
    public PageBean getDeviceList(DeviceInputView deviceInputView){
        PageBean pageBean = getDevices(deviceInputView);
        List<Map<String, Object>> list = pageBean.getList();
        list.parallelStream().forEach(map -> {
            map.put("areaNameFull", StrUtil.objToStr(map.get("areaNameFull")).replace("->", " "));
        });
        return pageBean;
    }

    @Override
    public Map<String, Object> getDevice(int did){
        return (Map<String, Object>)getDeviceList(new DeviceInputView(did)).getList().get(0);
    }

    private PageBean getDevices(DeviceInputView deviceInputView){
        Integer did = deviceInputView.getDid();

        StringBuilder headSql = new StringBuilder("SELECT sd.*, sa.areaNameFull");
        StringBuilder bodySql = new StringBuilder(" FROM sys_device_info sd, sys_area sa");
        StringBuilder whereSql = new StringBuilder(" WHERE sd.areaId = sa.areaId");
        if(did != null){
            whereSql.append(" AND sd.did = :did");
        }

        return super.getPageBean(headSql, bodySql, whereSql, deviceInputView);
    }

    @Override
    public SysDeviceInfo getDeviceInfo(int did){
        return baseDao.getToEvict(SysDeviceInfo.class, did);
    }

    //查询没有时间交集的设备柜子【因为一个时间段只有一个人进入，那么这里不是所有人都是1号柜子？【已解决，需求是只能预约第二天的柜子】】
    @Override
    public SysDeviceControl getDistributionDeviceControl(int did, Timestamp reserveStartTime, Timestamp reserveEndTime) throws ParseException{
        StringBuilder sql = new StringBuilder("SELECT COUNT(roi.dcid) count, sdc.dcid FROM sys_device_control sdc");
        sql.append(" LEFT JOIN reserve_order_info roi ON(sdc.dcid = roi.dcid AND roi.reserveStartTime BETWEEN ?1 AND ?2)");
        sql.append(" WHERE sdc.type = ?3 AND sdc.status = ?4 GROUP BY sdc.dcid ORDER BY count, sdc.dcid");

        String startDate = DateUtil.dateToString(new Date(reserveEndTime.getTime()), DateUtil.YYYYMMDD) + IPlatformConstant.time00;
        String endDate = DateUtil.dateToString(new Date(reserveEndTime.getTime()), DateUtil.YYYYMMDD) + IPlatformConstant.time24;
        Map<String, Object> deviceControlMap = baseDao.queryBySqlFirst(sql.toString(), startDate, endDate, IDBConstant.LOGIC_STATUS_NO, IDBConstant.LOGIC_STATUS_YES);
        if(deviceControlMap == null) return null;
        return getSysDeviceControl(StrUtil.objToInt(deviceControlMap.get("dcid")));
    }

    @Override
    public PageBean getSysDeviceInfoList(DeviceInputView deviceInputView){
        return getSysDeviceInfos(deviceInputView);
    }

    private PageBean getSysDeviceInfos(DeviceInputView deviceInputView){
        StringBuilder headSql = new StringBuilder("SELECT *");
        StringBuilder bodySql = new StringBuilder(" FROM sys_device_info sdi");
        StringBuilder whereSql = new StringBuilder(" WHERE 1=1");
        return super.getPageBean(headSql, bodySql, whereSql, deviceInputView);
    }

    @Override
    public void saveSysDeviceInfo(SysDeviceInfo sysDeviceInfo){
        Timestamp nowDate = DateUtil.getNowDate();
        Integer did = sysDeviceInfo.getDid();
        if(did == null) {
            sysDeviceInfo.setCreateTime(nowDate);
            sysDeviceInfo.setStartTime("00:00");
            sysDeviceInfo.setEndTime("24:00");
            baseDao.save(sysDeviceInfo, null);
        }else{
            SysDeviceInfo sysDeviceInfoDB = getSysDeviceInfo(did);
            sysDeviceInfoDB.setStatus(sysDeviceInfo.getStatus());
            sysDeviceInfoDB.setName(sysDeviceInfo.getName());
            sysDeviceInfoDB.setAddress(sysDeviceInfo.getAddress());
            sysDeviceInfoDB.setDoorIp(sysDeviceInfo.getDoorIp());
            sysDeviceInfoDB.setDoorPort(sysDeviceInfo.getDoorPort());
            sysDeviceInfoDB.setLockIp(sysDeviceInfo.getLockIp());
            sysDeviceInfoDB.setLockPort(sysDeviceInfo.getLockPort());
            baseDao.save(sysDeviceInfoDB, did);
        }
    }

    @Override
    public SysDeviceInfo getSysDeviceInfo(int did){
        return baseDao.getToEvict(SysDeviceInfo.class, did);
    }

    @Override
    public SysDeviceControl getSysDeviceControl(int dcid){
        return baseDao.getToEvict(SysDeviceControl.class, dcid);
    }

    @Override
    public List<SysDeviceControl> getDeviceControl(String deviceNo){
        List<SysDeviceControl> deviceControls = baseDao.queryByHql("SELECT sdc FROM SysDeviceInfo sdi, SysDeviceControl sdc WHERE sdi.did = sdc.did AND sdi.doorIp = ?1", deviceNo);
        return deviceControls;
    }

    @Override
    public Map<String, Object> getDistributionSetting(DeviceInputView deviceInputView){
        List<Map<String, Object>> deviceControlList = getSysDeviceControlList(deviceInputView);
        deviceControlList.stream().forEach(map -> {
            map.put("commoditys", getSysDeviceControlCommoditys(StrUtil.objToInt(map.get("dcid")), deviceInputView));
        });

        Map<String, Object> data = new HashMap<>();
        data.put("deviceControlList", deviceControlList);
        return data;
    }

    private List<Map<String, Object>> getSysDeviceControlList(DeviceInputView deviceInputView){
        Integer did = deviceInputView.getDid();
        String type = deviceInputView.getType();
        String dbTime = deviceInputView.getDbTime();
        StringBuilder sql = new StringBuilder("SELECT sdc.*, sdi.name deviceName");
        sql.append(" ,(SELECT COUNT(1) FROM sys_commodity_distribution cd WHERE cd.dcid = sdc.dcid AND cd.dbTime = :dbTime) cdCount");
        sql.append(" FROM sys_device_control sdc, sys_device_info sdi WHERE sdc.did = sdi.did");
        if(did != null) {
            sql.append(" AND sdc.did = :did");
        }
        if(StrUtil.isNotBlank(type)){
            sql.append(" AND sdc.type = :type");
        }
        return baseDao.queryBySql(sql.toString(), JsonUtils.fromJsonDF(deviceInputView));
    }

    private List<Map<String, Object>> getSysDeviceControlCommoditys(int dcid, DeviceInputView deviceInputView){
        String dbTime = deviceInputView.getDbTime();
        StringBuilder sql = new StringBuilder("SELECT ci.*, cs.size, cd.dbid, cd.rfidEpc, cd.dbTime, roi.rno FROM sys_commodity_distribution cd");
        sql.append(" INNER JOIN commodity_info ci ON(cd.cid = ci.cid)");
        sql.append(" INNER JOIN commodity_size cs ON(cd.sid = cs.sid)");
        sql.append(" LEFT JOIN reserve_order_info roi ON(cd.roid = roi.roid)");
        sql.append(" WHERE cd.dcid = ?1 AND cd.dbTime = ?2");
        return baseDao.queryBySql(sql.toString(), dcid, dbTime);
    }

    @Override
    public void deleteSysDeviceControlCommodity(int dbid){
        SysCommodityDistribution commodityDistribution = getCommodityDistribution(dbid);
        if(commodityDistribution != null){
            baseDao.delete(commodityDistribution);
        }
    }

    private SysCommodityDistribution getCommodityDistribution(int dbid){
        return baseDao.getToEvict(SysCommodityDistribution.class, dbid);
    }

    @Override
    public void saveSysDeviceControlCommodity(SysCommodityDistribution commodityDistribution){
        checkRfidEpc(commodityDistribution.getRfidEpc());

        commodityDistribution.setCreateTime(DateUtil.getNowDate());
        baseDao.save(commodityDistribution, null);
    }

    @Override
    public void updateRfidEpc(SysCommodityDistribution commodityDistribution){
        checkRfidEpc(commodityDistribution.getRfidEpc());

        SysCommodityDistribution commodityDistributionDB = getCommodityDistribution(commodityDistribution.getDbid());
        commodityDistributionDB.setRfidEpc(commodityDistribution.getRfidEpc());
        baseDao.save(commodityDistributionDB, commodityDistributionDB.getDbid());
    }

    @Override
    public Integer updateDistributionDate(String dbTime) throws ParseException{
        java.sql.Date date = new java.sql.Date(DateUtil.stringToDate(dbTime, DateUtil.YYYYMMDD).getTime());
        java.sql.Date nowDate = new java.sql.Date(System.currentTimeMillis());
        List<SysCommodityDistribution> commodityDistributions = baseDao.queryByHql("FROM SysCommodityDistribution cd WHERE cd.dbTime = ?1", date);
        if(commodityDistributions != null && commodityDistributions.size() > 0){
            commodityDistributions.stream().forEach(commodityDistribution -> {
                commodityDistribution.setDbTime(nowDate);
                baseDao.save(commodityDistribution, commodityDistribution.getDbid());
            });
        }
        return commodityDistributions.size();
    }

    private Map checkRfidEpc(String rfidEpc){
        Map<String, Object> map = baseDao.queryBySqlFirst("SELECT ci.commName FROM sys_commodity_distribution cd, commodity_info ci WHERE cd.cid = ci.cid AND cd.rfidEpc = ?1 AND cd.dbTime = CURDATE()", rfidEpc);
        if(map != null) throw new MessageException("标签码与【" + map.get("commName") + "】重复，请检查后再试！");
        return map;
    }

}
