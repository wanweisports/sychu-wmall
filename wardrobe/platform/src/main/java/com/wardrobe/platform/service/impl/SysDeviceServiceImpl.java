package com.wardrobe.platform.service.impl;

import com.wardrobe.common.bean.PageBean;
import com.wardrobe.common.constant.IDBConstant;
import com.wardrobe.common.po.SysDeviceControl;
import com.wardrobe.common.po.SysDeviceInfo;
import com.wardrobe.common.util.DateUtil;
import com.wardrobe.common.util.StrUtil;
import com.wardrobe.common.view.DeviceInputView;
import com.wardrobe.platform.service.ISysDeviceService;
import org.springframework.stereotype.Service;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.Date;
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
    public SysDeviceControl getDistributionDeviceControl(int did, Timestamp reserveStartTime, Timestamp reserveEndTime){
        StringBuilder hql = new StringBuilder("SELECT sdc");
        hql.append(" FROM SysDeviceInfo sd, SysDeviceControl sdc");
        hql.append(" WHERE sd.did = sdc.did AND sd.did = ?1");
        hql.append(" AND NOT EXISTS(SELECT 1 FROM ReserveOrderInfo roi WHERE roi.dcid = sdc.dcid AND NOT(roi.reserveEndTime <= ?2 OR roi.reserveStartTime >= ?3))"); //预约结束时间小于库里数据开始时间 或者 预约开始时间大于库里数据结束时间
        hql.append(" AND sdc.status = ?4");
        hql.append(" ORDER BY sdc.dcid");
        return baseDao.queryByHqlFirst(hql.toString(), did, reserveStartTime, reserveEndTime, IDBConstant.LOGIC_STATUS_YES);
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

    private SysDeviceInfo getSysDeviceInfo(int did){
        return baseDao.getToEvict(SysDeviceInfo.class, did);
    }

}
