package com.wardrobe.common.po;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * Created by cxs on 2018/9/30.
 */
@Entity
@Table(name = "user_old_info", schema = "")
public class UserOldInfo {
    private int uoid;
    private String mobile;
    private Integer score;
    private String nickname;

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "uoid")
    public int getUoid() {
        return uoid;
    }

    public void setUoid(int uoid) {
        this.uoid = uoid;
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
    @Column(name = "score")
    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    @Basic
    @Column(name = "nickname")
    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserOldInfo that = (UserOldInfo) o;

        if (uoid != that.uoid) return false;
        if (mobile != null ? !mobile.equals(that.mobile) : that.mobile != null) return false;
        if (score != null ? !score.equals(that.score) : that.score != null) return false;
        if (nickname != null ? !nickname.equals(that.nickname) : that.nickname != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = uoid;
        result = 31 * result + (mobile != null ? mobile.hashCode() : 0);
        result = 31 * result + (score != null ? score.hashCode() : 0);
        result = 31 * result + (nickname != null ? nickname.hashCode() : 0);
        return result;
    }
}
