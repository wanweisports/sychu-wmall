package com.wardrobe.common.bean;

/**
 * Created by cxs on 2018/7/30.
 */
public class UserPerfectBean {

    private Integer userId;
    private String sex;
    private String age;
    private String dressStyle;
    private String usualSize;
    private String mobile;
    private String inviteCode;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getDressStyle() {
        return dressStyle;
    }

    public void setDressStyle(String dressStyle) {
        this.dressStyle = dressStyle;
    }

    public String getUsualSize() {
        return usualSize;
    }

    public void setUsualSize(String usualSize) {
        this.usualSize = usualSize;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getInviteCode() {
        return inviteCode;
    }

    public void setInviteCode(String inviteCode) {
        this.inviteCode = inviteCode;
    }
}
