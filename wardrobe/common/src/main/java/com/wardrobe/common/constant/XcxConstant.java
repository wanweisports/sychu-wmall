package com.wardrobe.common.constant;

/**
 * Created by 雷达 on 2018/7/26.
 */
public interface XcxConstant {

    //获取access_token
    String ACCESS_TOKEN_URL = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=SECRET";

    //小程序授权登录
    String LOGIN_URL = "https://api.weixin.qq.com/sns/jscode2session?appid=APPID&secret=SECRET&js_code=JSCODE&grant_type=authorization_code";

    //模版消息推送
    String TPL_MESSAGE_URL = "https://api.weixin.qq.com/cgi-bin/message/wxopen/template/send?access_token=ACCESS_TOKEN";

}
