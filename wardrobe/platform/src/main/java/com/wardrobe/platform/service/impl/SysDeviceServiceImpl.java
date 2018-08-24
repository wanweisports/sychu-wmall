package com.wardrobe.platform.service.impl;

import com.wardrobe.common.bean.PageBean;
import com.wardrobe.common.util.DateUtil;
import com.wardrobe.common.util.StrUtil;
import com.wardrobe.common.view.DeviceInputView;
import com.wardrobe.platform.service.ISysDeviceService;
import org.springframework.stereotype.Service;

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

}
