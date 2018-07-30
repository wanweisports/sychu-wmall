package com.wardrobe.common.po;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by 雷达 on 2018/7/30.
 */
@Entity
@Table(name = "sys_area", schema = "")
public class SysArea {
    private String areaId;
    private String areaParentId;
    private String areaRootId;
    private String areaCode;
    private String areaName;
    private String abbreviation;
    private String areaNameFull;
    private String areaPinYinFull;
    private String areaPinyinInit;
    private String sortNo;
    private String rank;
    private String seqNo;
    private Timestamp updateTime;

    @Id
    @Column(name = "areaId")
    public String getAreaId() {
        return areaId;
    }

    public void setAreaId(String areaId) {
        this.areaId = areaId;
    }

    @Basic
    @Column(name = "areaParentId")
    public String getAreaParentId() {
        return areaParentId;
    }

    public void setAreaParentId(String areaParentId) {
        this.areaParentId = areaParentId;
    }

    @Basic
    @Column(name = "areaRootId")
    public String getAreaRootId() {
        return areaRootId;
    }

    public void setAreaRootId(String areaRootId) {
        this.areaRootId = areaRootId;
    }

    @Basic
    @Column(name = "areaCode")
    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }

    @Basic
    @Column(name = "areaName")
    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    @Basic
    @Column(name = "abbreviation")
    public String getAbbreviation() {
        return abbreviation;
    }

    public void setAbbreviation(String abbreviation) {
        this.abbreviation = abbreviation;
    }

    @Basic
    @Column(name = "areaNameFull")
    public String getAreaNameFull() {
        return areaNameFull;
    }

    public void setAreaNameFull(String areaNameFull) {
        this.areaNameFull = areaNameFull;
    }

    @Basic
    @Column(name = "areaPinYinFull")
    public String getAreaPinYinFull() {
        return areaPinYinFull;
    }

    public void setAreaPinYinFull(String areaPinYinFull) {
        this.areaPinYinFull = areaPinYinFull;
    }

    @Basic
    @Column(name = "areaPinyinInit")
    public String getAreaPinyinInit() {
        return areaPinyinInit;
    }

    public void setAreaPinyinInit(String areaPinyinInit) {
        this.areaPinyinInit = areaPinyinInit;
    }

    @Basic
    @Column(name = "sortNo")
    public String getSortNo() {
        return sortNo;
    }

    public void setSortNo(String sortNo) {
        this.sortNo = sortNo;
    }

    @Basic
    @Column(name = "rank")
    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    @Basic
    @Column(name = "seqNo")
    public String getSeqNo() {
        return seqNo;
    }

    public void setSeqNo(String seqNo) {
        this.seqNo = seqNo;
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

        SysArea sysArea = (SysArea) o;

        if (areaId != null ? !areaId.equals(sysArea.areaId) : sysArea.areaId != null) return false;
        if (areaParentId != null ? !areaParentId.equals(sysArea.areaParentId) : sysArea.areaParentId != null)
            return false;
        if (areaRootId != null ? !areaRootId.equals(sysArea.areaRootId) : sysArea.areaRootId != null) return false;
        if (areaCode != null ? !areaCode.equals(sysArea.areaCode) : sysArea.areaCode != null) return false;
        if (areaName != null ? !areaName.equals(sysArea.areaName) : sysArea.areaName != null) return false;
        if (abbreviation != null ? !abbreviation.equals(sysArea.abbreviation) : sysArea.abbreviation != null)
            return false;
        if (areaNameFull != null ? !areaNameFull.equals(sysArea.areaNameFull) : sysArea.areaNameFull != null)
            return false;
        if (areaPinYinFull != null ? !areaPinYinFull.equals(sysArea.areaPinYinFull) : sysArea.areaPinYinFull != null)
            return false;
        if (areaPinyinInit != null ? !areaPinyinInit.equals(sysArea.areaPinyinInit) : sysArea.areaPinyinInit != null)
            return false;
        if (sortNo != null ? !sortNo.equals(sysArea.sortNo) : sysArea.sortNo != null) return false;
        if (rank != null ? !rank.equals(sysArea.rank) : sysArea.rank != null) return false;
        if (seqNo != null ? !seqNo.equals(sysArea.seqNo) : sysArea.seqNo != null) return false;
        if (updateTime != null ? !updateTime.equals(sysArea.updateTime) : sysArea.updateTime != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = areaId != null ? areaId.hashCode() : 0;
        result = 31 * result + (areaParentId != null ? areaParentId.hashCode() : 0);
        result = 31 * result + (areaRootId != null ? areaRootId.hashCode() : 0);
        result = 31 * result + (areaCode != null ? areaCode.hashCode() : 0);
        result = 31 * result + (areaName != null ? areaName.hashCode() : 0);
        result = 31 * result + (abbreviation != null ? abbreviation.hashCode() : 0);
        result = 31 * result + (areaNameFull != null ? areaNameFull.hashCode() : 0);
        result = 31 * result + (areaPinYinFull != null ? areaPinYinFull.hashCode() : 0);
        result = 31 * result + (areaPinyinInit != null ? areaPinyinInit.hashCode() : 0);
        result = 31 * result + (sortNo != null ? sortNo.hashCode() : 0);
        result = 31 * result + (rank != null ? rank.hashCode() : 0);
        result = 31 * result + (seqNo != null ? seqNo.hashCode() : 0);
        result = 31 * result + (updateTime != null ? updateTime.hashCode() : 0);
        return result;
    }
}
