package com.wardrobe.common.po;

import javax.persistence.*;

import java.sql.Timestamp;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "sys_dict", schema = "")
public class SysDict {
    private int dictId;
    private Integer parentDictId;
    private String dictName;
    private String dictKey;
    private String dictValue;
    private String dictAdditional;
    private Integer seqNo = 0;
    private Timestamp createTime;

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "dictId")
    public int getDictId() {
        return dictId;
    }

    public void setDictId(int dictId) {
        this.dictId = dictId;
    }

    @Basic
    @Column(name = "parentDictId")
    public Integer getParentDictId() {
        return parentDictId;
    }

    public void setParentDictId(Integer parentDictId) {
        this.parentDictId = parentDictId;
    }

    @Basic
    @Column(name = "dictName")
    public String getDictName() {
        return dictName;
    }

    public void setDictName(String dictName) {
        this.dictName = dictName;
    }

    @Basic
    @Column(name = "dictKey")
    public String getDictKey() {
        return dictKey;
    }

    public void setDictKey(String dictKey) {
        this.dictKey = dictKey;
    }

    @Basic
    @Column(name = "dictValue")
    public String getDictValue() {
        return dictValue;
    }

    public void setDictValue(String dictValue) {
        this.dictValue = dictValue;
    }

    @Basic
    @Column(name = "dictAdditional")
    public String getDictAdditional() {
        return dictAdditional;
    }

    public void setDictAdditional(String dictAdditional) {
        this.dictAdditional = dictAdditional;
    }

    @Basic
    @Column(name = "seqNo")
    public Integer getSeqNo() {
        return seqNo;
    }

    public void setSeqNo(Integer seqNo) {
        this.seqNo = seqNo;
    }

    @Basic
    @Column(name = "createTime")
    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

}
