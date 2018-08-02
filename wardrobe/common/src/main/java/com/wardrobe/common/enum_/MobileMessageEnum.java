package com.wardrobe.common.enum_;

/**
 * Created by cxs on 2018/7/30.
 */
public enum MobileMessageEnum {

    USER_PERFECT(1, "验证码是：CODE，完善资料", "USER_PERFECT"),
    USER_UPDATE_MOBILE_OLD(2, "验证码是：CODE，验证原手机号", "USER_UPDATE_MOBILE_OLD"),
    USER_UPDATE_MOBILE_NEW(3, "验证码是：CODE，修改新手机号", "USER_UPDATE_MOBILE_NEW");

    public int type;
    public String message;
    public String name; //session key名

    MobileMessageEnum(int type, String message, String name) {
        this.type = type;
        this.message = message;
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
