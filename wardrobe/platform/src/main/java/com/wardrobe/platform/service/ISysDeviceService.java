package com.wardrobe.platform.service;

import com.wardrobe.common.bean.PageBean;
import com.wardrobe.common.view.DeviceInputView;

import java.util.Map;

/**
 * Created by cxs on 2018/8/24.
 */
public interface ISysDeviceService {

    PageBean getDeviceList(DeviceInputView deviceInputView);

    Map<String, Object> getDevice(int did);

}
