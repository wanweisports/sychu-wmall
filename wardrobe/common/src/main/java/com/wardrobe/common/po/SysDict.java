package com.wardrobe.common.po;

import javax.persistence.*;

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
    private Integer seqNo;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SysDict sysDict = (SysDict) o;

        if (dictId != sysDict.dictId) return false;
        if (parentDictId != null ? !parentDictId.equals(sysDict.parentDictId) : sysDict.parentDictId != null)
            return false;
        if (dictName != null ? !dictName.equals(sysDict.dictName) : sysDict.dictName != null) return false;
        if (dictKey != null ? !dictKey.equals(sysDict.dictKey) : sysDict.dictKey != null) return false;
        if (dictValue != null ? !dictValue.equals(sysDict.dictValue) : sysDict.dictValue != null) return false;
        if (dictAdditional != null ? !dictAdditional.equals(sysDict.dictAdditional) : sysDict.dictAdditional != null)
            return false;
        if (seqNo != null ? !seqNo.equals(sysDict.seqNo) : sysDict.seqNo != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = dictId;
        result = 31 * result + (parentDictId != null ? parentDictId.hashCode() : 0);
        result = 31 * result + (dictName != null ? dictName.hashCode() : 0);
        result = 31 * result + (dictKey != null ? dictKey.hashCode() : 0);
        result = 31 * result + (dictValue != null ? dictValue.hashCode() : 0);
        result = 31 * result + (dictAdditional != null ? dictAdditional.hashCode() : 0);
        result = 31 * result + (seqNo != null ? seqNo.hashCode() : 0);
        return result;
    }
}
