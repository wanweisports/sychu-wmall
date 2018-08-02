package com.wardrobe.common.po;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "user_account", schema = "")
public class UserAccount {
    private int uid;
    private BigDecimal balance;
    private BigDecimal ycoid;
    private Integer score;
    private Timestamp createTime;
    private Timestamp updateTime;

    @Id
    @Column(name = "uid")
    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    @Basic
    @Column(name = "balance")
    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    @Basic
    @Column(name = "ycoid")
    public BigDecimal getYcoid() {
        return ycoid;
    }

    public void setYcoid(BigDecimal ycoid) {
        this.ycoid = ycoid;
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
    @Column(name = "createTime")
    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    @Basic
    @Column(name = "updateTime")
    public Timestamp getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Timestamp updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserAccount that = (UserAccount) o;

        if (uid != that.uid) return false;
        if (balance != null ? !balance.equals(that.balance) : that.balance != null) return false;
        if (ycoid != null ? !ycoid.equals(that.ycoid) : that.ycoid != null) return false;
        if (score != null ? !score.equals(that.score) : that.score != null) return false;
        if (createTime != null ? !createTime.equals(that.createTime) : that.createTime != null) return false;
        if (updateTime != null ? !updateTime.equals(that.updateTime) : that.updateTime != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = uid;
        result = 31 * result + (balance != null ? balance.hashCode() : 0);
        result = 31 * result + (ycoid != null ? ycoid.hashCode() : 0);
        result = 31 * result + (score != null ? score.hashCode() : 0);
        result = 31 * result + (createTime != null ? createTime.hashCode() : 0);
        result = 31 * result + (updateTime != null ? updateTime.hashCode() : 0);
        return result;
    }
}
