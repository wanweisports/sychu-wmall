package com.wardrobe.common.po;

import com.wardrobe.common.constant.IDBConstant;
import com.wardrobe.common.util.DateUtil;
import com.wardrobe.common.util.StrUtil;

import javax.persistence.*;
import java.sql.Timestamp;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "user_info", schema = "")
public class UserInfo {
    private int uid;
    private String openId;
    private String unionId;
    private String nickname;
    private String headImg;
    private String sex;
    private String age;
    private String dressStyle;
    private String usualSize;
    private String mobile;
    private String inviteCode;
    private Integer invitedBy;
    private Integer rank;
    private Integer point;
    private String isPerfect;
    private Timestamp registerTime;
    private Timestamp createTime;

    public UserInfo() {
    }

    public UserInfo(String openId, String unionId, String nickname, String headImg) {
        this.openId = openId;
        this.unionId = unionId;
        this.nickname = nickname;
        this.headImg = headImg;
    }

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "uid")
    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    @Basic
    @Column(name = "openId")
    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    @Basic
    @Column(name = "unionId")
    public String getUnionId() {
        return unionId;
    }

    public void setUnionId(String unionId) {
        this.unionId = unionId;
    }

    @Basic
    @Column(name = "nickname")
    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    @Basic
    @Column(name = "headImg")
    public String getHeadImg() {
        return headImg;
    }

    public void setHeadImg(String headImg) {
        this.headImg = headImg;
    }

    @Basic
    @Column(name = "sex")
    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    @Basic
    @Column(name = "age")
    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    @Basic
    @Column(name = "dressStyle")
    public String getDressStyle() {
        return dressStyle;
    }

    public void setDressStyle(String dressStyle) {
        this.dressStyle = dressStyle;
    }

    @Basic
    @Column(name = "usualSize")
    public String getUsualSize() {
        return usualSize;
    }

    public void setUsualSize(String usualSize) {
        this.usualSize = usualSize;
    }

    @Basic
    @Column(name = "mobile")
    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    @Basic
    @Column(name = "inviteCode")
    public String getInviteCode() {
        return inviteCode;
    }

    public void setInviteCode(String inviteCode) {
        this.inviteCode = inviteCode;
    }

    @Basic
    @Column(name = "invitedBy")
    public Integer getInvitedBy() {
        return invitedBy;
    }

    public void setInvitedBy(Integer invitedBy) {
        this.invitedBy = invitedBy;
    }

    @Basic
    @Column(name = "rank")
    public Integer getRank() {
        return rank;
    }

    public void setRank(Integer rank) {
        this.rank = rank;
    }

    @Basic
    @Column(name = "point")
    public Integer getPoint() {
        return point;
    }

    public void setPoint(Integer point) {
        this.point = point;
    }

    @Basic
    @Column(name = "isPerfect")
    public String getIsPerfect() {
        return isPerfect;
    }

    public void setIsPerfect(String isPerfect) {
        this.isPerfect = isPerfect;
    }

    @Basic
    @Column(name = "registerTime")
    public Timestamp getRegisterTime() {
        return registerTime;
    }

    public void setRegisterTime(Timestamp registerTime) {
        this.registerTime = registerTime;
    }

    @Basic
    @Column(name = "createTime")
    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public UserInfo init(){
        Timestamp timestamp = DateUtil.getNowDate();
        this.isPerfect = IDBConstant.LOGIC_STATUS_NO; //待完善资料
        this.createTime = timestamp;
        this.registerTime = timestamp;
        this.rank = 1;
        this.point = 0;
        this.inviteCode = StrUtil.objToStr(StrUtil.initCode(4));
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserInfo userInfo = (UserInfo) o;

        if (uid != userInfo.uid) return false;
        if (openId != null ? !openId.equals(userInfo.openId) : userInfo.openId != null) return false;
        if (unionId != null ? !unionId.equals(userInfo.unionId) : userInfo.unionId != null) return false;
        if (nickname != null ? !nickname.equals(userInfo.nickname) : userInfo.nickname != null) return false;
        if (headImg != null ? !headImg.equals(userInfo.headImg) : userInfo.headImg != null) return false;
        if (sex != null ? !sex.equals(userInfo.sex) : userInfo.sex != null) return false;
        if (age != null ? !age.equals(userInfo.age) : userInfo.age != null) return false;
        if (dressStyle != null ? !dressStyle.equals(userInfo.dressStyle) : userInfo.dressStyle != null) return false;
        if (usualSize != null ? !usualSize.equals(userInfo.usualSize) : userInfo.usualSize != null) return false;
        if (mobile != null ? !mobile.equals(userInfo.mobile) : userInfo.mobile != null) return false;
        if (inviteCode != null ? !inviteCode.equals(userInfo.inviteCode) : userInfo.inviteCode != null) return false;
        if (invitedBy != null ? !invitedBy.equals(userInfo.invitedBy) : userInfo.invitedBy != null) return false;
        if (rank != null ? !rank.equals(userInfo.rank) : userInfo.rank != null) return false;
        if (point != null ? !point.equals(userInfo.point) : userInfo.point != null) return false;
        if (registerTime != null ? !registerTime.equals(userInfo.registerTime) : userInfo.registerTime != null)
            return false;
        if (createTime != null ? !createTime.equals(userInfo.createTime) : userInfo.createTime != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = uid;
        result = 31 * result + (openId != null ? openId.hashCode() : 0);
        result = 31 * result + (unionId != null ? unionId.hashCode() : 0);
        result = 31 * result + (nickname != null ? nickname.hashCode() : 0);
        result = 31 * result + (headImg != null ? headImg.hashCode() : 0);
        result = 31 * result + (sex != null ? sex.hashCode() : 0);
        result = 31 * result + (age != null ? age.hashCode() : 0);
        result = 31 * result + (dressStyle != null ? dressStyle.hashCode() : 0);
        result = 31 * result + (usualSize != null ? usualSize.hashCode() : 0);
        result = 31 * result + (mobile != null ? mobile.hashCode() : 0);
        result = 31 * result + (inviteCode != null ? inviteCode.hashCode() : 0);
        result = 31 * result + (invitedBy != null ? invitedBy.hashCode() : 0);
        result = 31 * result + (rank != null ? rank.hashCode() : 0);
        result = 31 * result + (point != null ? point.hashCode() : 0);
        result = 31 * result + (registerTime != null ? registerTime.hashCode() : 0);
        result = 31 * result + (createTime != null ? createTime.hashCode() : 0);
        return result;
    }
}
