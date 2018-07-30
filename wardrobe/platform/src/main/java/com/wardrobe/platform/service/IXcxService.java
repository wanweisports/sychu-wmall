package com.wardrobe.platform.service;

import net.sf.json.JSONObject;

/**
 * Created by 雷达 on 2018/7/26.
 */
public interface IXcxService {

    JSONObject xcxLogn(String session_key, String code, String xcxAppId, String xcxAppsecret, String iv, String encryptedData);

}
