package com.wardrobe.common.view;

/**
 * Created by cxs on 2018/8/1.
 */
public class UserTransactionsInputView extends BaseInputView {

    private Integer uid;
    private String nickname;
    private String mobile;
    private String type;

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
