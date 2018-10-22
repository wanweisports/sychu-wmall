package com.wardrobe.common.po;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by cxs on 2018/10/22.
 */
@Entity
@Table(name = "operator_info", schema = "")
public class OperatorInfo {
    private int operatorId;
    private String operatorName;
    private String operatorAccount;
    private String operatorPwd;
    private Timestamp createTime;
    private Timestamp updateTime;

    @Id
    @Column(name = "operatorId")
    public int getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(int operatorId) {
        this.operatorId = operatorId;
    }

    @Basic
    @Column(name = "operatorName")
    public String getOperatorName() {
        return operatorName;
    }

    public void setOperatorName(String operatorName) {
        this.operatorName = operatorName;
    }

    @Basic
    @Column(name = "operatorAccount")
    public String getOperatorAccount() {
        return operatorAccount;
    }

    public void setOperatorAccount(String operatorAccount) {
        this.operatorAccount = operatorAccount;
    }

    @Basic
    @Column(name = "operatorPwd")
    public String getOperatorPwd() {
        return operatorPwd;
    }

    public void setOperatorPwd(String operatorPwd) {
        this.operatorPwd = operatorPwd;
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

        OperatorInfo that = (OperatorInfo) o;

        if (operatorId != that.operatorId) return false;
        if (operatorName != null ? !operatorName.equals(that.operatorName) : that.operatorName != null) return false;
        if (operatorAccount != null ? !operatorAccount.equals(that.operatorAccount) : that.operatorAccount != null)
            return false;
        if (operatorPwd != null ? !operatorPwd.equals(that.operatorPwd) : that.operatorPwd != null) return false;
        if (createTime != null ? !createTime.equals(that.createTime) : that.createTime != null) return false;
        if (updateTime != null ? !updateTime.equals(that.updateTime) : that.updateTime != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = operatorId;
        result = 31 * result + (operatorName != null ? operatorName.hashCode() : 0);
        result = 31 * result + (operatorAccount != null ? operatorAccount.hashCode() : 0);
        result = 31 * result + (operatorPwd != null ? operatorPwd.hashCode() : 0);
        result = 31 * result + (createTime != null ? createTime.hashCode() : 0);
        result = 31 * result + (updateTime != null ? updateTime.hashCode() : 0);
        return result;
    }
}
