package com.wardrobe.common.enum_;

import com.wardrobe.common.constant.SmsUrlConstant;

/**
 * Created by cxs on 2018/7/30.
 */
public enum MobileMessageEnum {

    USER_PERFECT(1, SmsUrlConstant.TPL_ID_CODE, "USER_PERFECT"),  //完善资料
    USER_UPDATE_MOBILE_OLD(2, SmsUrlConstant.TPL_ID_CODE, "USER_UPDATE_MOBILE_OLD"), //验证原手机号
    USER_UPDATE_MOBILE_NEW(3, SmsUrlConstant.TPL_ID_CODE, "USER_UPDATE_MOBILE_NEW"); //修改新手机号

    public int type;
    public String tplId;
    public String name; //session key名

    MobileMessageEnum(int type, String tplId, String name) {
        this.type = type;
        this.tplId = tplId;
        this.name = name;
    }

    public static MobileMessageEnum getMessageByType(Integer type){
        MobileMessageEnum[] values = values();
        for(MobileMessageEnum mobileMessageEnum : values){
            if(mobileMessageEnum.type == type) return mobileMessageEnum;
        }
        return null;
    }

}
