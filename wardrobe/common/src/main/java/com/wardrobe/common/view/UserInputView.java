package com.wardrobe.common.view;

/**
 * Created by cxs on 2018/8/7.
 */
public class UserInputView extends BaseInputView {

    private String nickname;
    private String mobile;
    private Integer uid;

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }
}
