package com.wardrobe.platform.service.impl;

import com.wardrobe.common.bean.PageBean;
import com.wardrobe.common.constant.IDBConstant;
import com.wardrobe.common.constant.IPlatformConstant;
import com.wardrobe.common.po.SysDeviceControl;
import com.wardrobe.common.po.SysDeviceInfo;
import com.wardrobe.common.util.DateUtil;
import com.wardrobe.common.util.StrUtil;
import com.wardrobe.common.view.DeviceInputView;
import com.wardrobe.platform.service.ISysDeviceService;
import org.springframework.stereotype.Service;

import java.sql.Time;
import java.sql.Timestamp;
import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by cxs on 2018/8/24.
 */
@Service()
public class SysDeviceServiceImpl extends BaseService implements ISysDeviceService {

    @Override
    public PageBean getDeviceList(DeviceInputView deviceInputView){
        PageBean pageBean = getDevices(deviceInputView);
        List<Map<String, Object>> list = pageBean.getList();
        list.parallelStream().forEach(map -> {
            String startTime = StrUtil.objToStr(map.get("startTime"));
            String endTime = StrUtil.objToStr(map.get("endTime"));
            map.put("startTime", startTime.substring(0, startTime.lastIndexOf(":")));
            map.put("endTime", endTime.substring(0, endTime.lastIndexOf(":")));
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

    //查询没有时间交集的设备柜子【因为一个时间段只有一个人进入，那么这里不是所有人都是1号柜子？】
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
            sysDeviceInfoDB.setUpdateTime(nowDate);
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
    public List<SysDeviceControl> getDeviceControl(String ip, int port){
        List<SysDeviceControl> deviceControls = baseDao.queryByHql("SELECT sdc FROM SysDeviceInfo sdi, SysDeviceControl sdc WHERE sdi.did = sdc.did AND sdi.doorIp = ?1 AND sdi.doorPort = ?2 AND sdc.type = ?3", ip, port, IDBConstant.LOGIC_STATUS_YES);
        if(deviceControls == null || deviceControls.size() == 0){
            deviceControls = baseDao.queryByHql("SELECT sdc FROM SysDeviceInfo sdi, SysDeviceControl sdc WHERE sdi.did = sdc.did AND sdi.lockIp = ?1 AND sdi.lockPort = ?2 AND sdc.type = ?3", ip, port, IDBConstant.LOGIC_STATUS_NO);
        }
        return deviceControls;
    }

}
