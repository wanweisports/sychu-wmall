package com.wardrobe.common.po;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * OtherResource entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "other_resource", catalog = "wardrobe")
public class OtherResource implements java.io.Serializable {

	// Fields

	private Long resourceId;
	private Integer resourceServiceId;
	private String resourceServiceType;
	private Integer resourceSeq;
	private String resourceType;
	private String resourceName;
	private String resourceOriginal;
	private String resourcePath;
	private Integer fileSize;
	private String status;
	private String createTime;
	private String updateTime;

	// Constructors

	/** default constructor */
	public OtherResource() {
	}

	/** minimal constructor */
	public OtherResource(Integer resourceServiceId, String resourceServiceType) {
		this.resourceServiceId = resourceServiceId;
		this.resourceServiceType = resourceServiceType;
	}

	/** full constructor */
	public OtherResource(Integer resourceServiceId, String resourceServiceType,
			Integer resourceSeq, String resourceType, String resourceName,
			String resourceOriginal, String resourcePath, Integer fileSize,
			String status, String createTime, String updateTime) {
		this.resourceServiceId = resourceServiceId;
		this.resourceServiceType = resourceServiceType;
		this.resourceSeq = resourceSeq;
		this.resourceType = resourceType;
		this.resourceName = resourceName;
		this.resourceOriginal = resourceOriginal;
		this.resourcePath = resourcePath;
		this.fileSize = fileSize;
		this.status = status;
		this.createTime = createTime;
		this.updateTime = updateTime;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "resourceId", unique = true, nullable = false)
	public Long getResourceId() {
		return this.resourceId;
	}

	public void setResourceId(Long resourceId) {
		this.resourceId = resourceId;
	}

	@Column(name = "resourceServiceId", nullable = false)
	public Integer getResourceServiceId() {
		return this.resourceServiceId;
	}

	public void setResourceServiceId(Integer resourceServiceId) {
		this.resourceServiceId = resourceServiceId;
	}

	@Column(name = "resourceServiceType", nullable = false, length = 16)
	public String getResourceServiceType() {
		return this.resourceServiceType;
	}

	public void setResourceServiceType(String resourceServiceType) {
		this.resourceServiceType = resourceServiceType;
	}

	@Column(name = "resourceSeq")
	public Integer getResourceSeq() {
		return this.resourceSeq;
	}

	public void setResourceSeq(Integer resourceSeq) {
		this.resourceSeq = resourceSeq;
	}

	@Column(name = "resourceType", length = 16)
	public String getResourceType() {
		return this.resourceType;
	}

	public void setResourceType(String resourceType) {
		this.resourceType = resourceType;
	}

	@Column(name = "resourceName", length = 64)
	public String getResourceName() {
		return this.resourceName;
	}

	public void setResourceName(String resourceName) {
		this.resourceName = resourceName;
	}

	@Column(name = "resourceOriginal", length = 64)
	public String getResourceOriginal() {
		return this.resourceOriginal;
	}

	public void setResourceOriginal(String resourceOriginal) {
		this.resourceOriginal = resourceOriginal;
	}

	@Column(name = "resourcePath", length = 65535)
	public String getResourcePath() {
		return this.resourcePath;
	}

	public void setResourcePath(String resourcePath) {
		this.resourcePath = resourcePath;
	}

	@Column(name = "fileSize")
	public Integer getFileSize() {
		return this.fileSize;
	}

	public void setFileSize(Integer fileSize) {
		this.fileSize = fileSize;
	}

	@Column(name = "status", length = 16)
	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Column(name = "createTime", length = 20)
	public String getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	@Column(name = "updateTime", length = 20)
	public String getUpdateTime() {
		return this.updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

}