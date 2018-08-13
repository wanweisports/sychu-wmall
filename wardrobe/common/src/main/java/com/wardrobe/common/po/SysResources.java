package com.wardrobe.common.po;

import javax.persistence.*;
import java.sql.Timestamp;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "sys_resources", schema = "")
public class SysResources {
    private int resourceId;
    private String resourceServiceType;
    private int resourceServiceId;
    private Integer resourceServiceParentId;
    private Integer resourceSeq;
    private String resourceType;
    private String resourceName;
    private String resourceOriginal;
    private String resourcePath;
    private Long fileSize;
    private String status;
    private Timestamp createTime;
    private Timestamp updateTime;

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "resourceId")
    public int getResourceId() {
        return resourceId;
    }

    public void setResourceId(int resourceId) {
        this.resourceId = resourceId;
    }

    @Basic
    @Column(name = "resourceServiceType")
    public String getResourceServiceType() {
        return resourceServiceType;
    }

    public void setResourceServiceType(String resourceServiceType) {
        this.resourceServiceType = resourceServiceType;
    }

    @Basic
    @Column(name = "resourceServiceId")
    public int getResourceServiceId() {
        return resourceServiceId;
    }

    public void setResourceServiceId(int resourceServiceId) {
        this.resourceServiceId = resourceServiceId;
    }

    @Basic
    @Column(name = "resourceServiceParentId")
    public Integer getResourceServiceParentId() {
        return resourceServiceParentId;
    }

    public void setResourceServiceParentId(Integer resourceServiceParentId) {
        this.resourceServiceParentId = resourceServiceParentId;
    }

    @Basic
    @Column(name = "resourceSeq")
    public Integer getResourceSeq() {
        return resourceSeq;
    }

    public void setResourceSeq(Integer resourceSeq) {
        this.resourceSeq = resourceSeq;
    }

    @Basic
    @Column(name = "resourceType")
    public String getResourceType() {
        return resourceType;
    }

    public void setResourceType(String resourceType) {
        this.resourceType = resourceType;
    }

    @Basic
    @Column(name = "resourceName")
    public String getResourceName() {
        return resourceName;
    }

    public void setResourceName(String resourceName) {
        this.resourceName = resourceName;
    }

    @Basic
    @Column(name = "resourceOriginal")
    public String getResourceOriginal() {
        return resourceOriginal;
    }

    public void setResourceOriginal(String resourceOriginal) {
        this.resourceOriginal = resourceOriginal;
    }

    @Basic
    @Column(name = "resourcePath")
    public String getResourcePath() {
        return resourcePath;
    }

    public void setResourcePath(String resourcePath) {
        this.resourcePath = resourcePath;
    }

    @Basic
    @Column(name = "fileSize")
    public Long getFileSize() {
        return fileSize;
    }

    public void setFileSize(Long fileSize) {
        this.fileSize = fileSize;
    }

    @Basic
    @Column(name = "status")
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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

        SysResources that = (SysResources) o;

        if (resourceId != that.resourceId) return false;
        if (resourceServiceId != that.resourceServiceId) return false;
        if (resourceServiceType != null ? !resourceServiceType.equals(that.resourceServiceType) : that.resourceServiceType != null)
            return false;
        if (resourceSeq != null ? !resourceSeq.equals(that.resourceSeq) : that.resourceSeq != null) return false;
        if (resourceType != null ? !resourceType.equals(that.resourceType) : that.resourceType != null) return false;
        if (resourceName != null ? !resourceName.equals(that.resourceName) : that.resourceName != null) return false;
        if (resourceOriginal != null ? !resourceOriginal.equals(that.resourceOriginal) : that.resourceOriginal != null)
            return false;
        if (resourcePath != null ? !resourcePath.equals(that.resourcePath) : that.resourcePath != null) return false;
        if (fileSize != null ? !fileSize.equals(that.fileSize) : that.fileSize != null) return false;
        if (status != null ? !status.equals(that.status) : that.status != null) return false;
        if (createTime != null ? !createTime.equals(that.createTime) : that.createTime != null) return false;
        if (updateTime != null ? !updateTime.equals(that.updateTime) : that.updateTime != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = resourceId;
        result = 31 * result + (resourceServiceType != null ? resourceServiceType.hashCode() : 0);
        result = 31 * result + resourceServiceId;
        result = 31 * result + (resourceSeq != null ? resourceSeq.hashCode() : 0);
        result = 31 * result + (resourceType != null ? resourceType.hashCode() : 0);
        result = 31 * result + (resourceName != null ? resourceName.hashCode() : 0);
        result = 31 * result + (resourceOriginal != null ? resourceOriginal.hashCode() : 0);
        result = 31 * result + (resourcePath != null ? resourcePath.hashCode() : 0);
        result = 31 * result + (fileSize != null ? fileSize.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (createTime != null ? createTime.hashCode() : 0);
        result = 31 * result + (updateTime != null ? updateTime.hashCode() : 0);
        return result;
    }
}
