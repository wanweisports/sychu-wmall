package com.wardrobe.common.enum_;

/**
 * Created by cxs on 2018/7/30.
 */
public enum MobileMessageEnum {

    USER_PERFECT(1, "验证码是：CODE，完善资料");

    public int type;
    public String message;

    MobileMessageEnum(int type, String message) {
        this.type = type;
        this.message = message;
    }

    public static MobileMessageEnum getMessageByType(Integer type){
        MobileMessageEnum[] values = values();
        for(MobileMessageEnum mobileMessageEnum : values){
            if(mobileMessageEnum.type == type) return mobileMessageEnum;
        }
        return null;
    }

}
