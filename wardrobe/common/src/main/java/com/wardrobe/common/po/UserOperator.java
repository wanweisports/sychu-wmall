package com.wardrobe.common.po;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "user_operator", catalog = "wardrobe", uniqueConstraints = @UniqueConstraint(columnNames = "operatorId"))
public class UserOperator implements java.io.Serializable {

	// Fields

	private Integer id;
	private String operatorId;
	private String operatorName;
	private String operatorPwd;
	private String operatorMobile;
	private String status;
	private String createTime;
	private String updateTime;
	private String lastLoginTime;
	private String operatorNo;
	private String operatorEffectDate;
	private String operatorEndDate;
	private String operatorBirthday;
	private String operatorContact;
	private String operatorAddress;
	private String operatorSex;
	private String operatorType;
	private String openId;

	// Constructors

	/** default constructor */
	public UserOperator() {
	}

	/** minimal constructor */
	public UserOperator(String operatorId) {
		this.operatorId = operatorId;
	}

	/** full constructor */
	public UserOperator(String operatorId, String operatorName,
			String operatorPwd, String operatorMobile, String status,
			String createTime, String updateTime, String lastLoginTime,
			String operatorNo, String operatorEffectDate,
			String operatorEndDate, String operatorBirthday,
			String operatorContact, String operatorAddress, String operatorSex,
			String operatorType, String openId) {
		this.operatorId = operatorId;
		this.operatorName = operatorName;
		this.operatorPwd = operatorPwd;
		this.operatorMobile = operatorMobile;
		this.status = status;
		this.createTime = createTime;
		this.updateTime = updateTime;
		this.lastLoginTime = lastLoginTime;
		this.operatorNo = operatorNo;
		this.operatorEffectDate = operatorEffectDate;
		this.operatorEndDate = operatorEndDate;
		this.operatorBirthday = operatorBirthday;
		this.operatorContact = operatorContact;
		this.operatorAddress = operatorAddress;
		this.operatorSex = operatorSex;
		this.operatorType = operatorType;
		this.openId = openId;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "operatorId", unique = true, nullable = false, length = 32)
	public String getOperatorId() {
		return this.operatorId;
	}

	public void setOperatorId(String operatorId) {
		this.operatorId = operatorId;
	}

	@Column(name = "operatorName", length = 32)
	public String getOperatorName() {
		return this.operatorName;
	}

	public void setOperatorName(String operatorName) {
		this.operatorName = operatorName;
	}

	@Column(name = "operatorPwd", length = 32)
	public String getOperatorPwd() {
		return this.operatorPwd;
	}

	public void setOperatorPwd(String operatorPwd) {
		this.operatorPwd = operatorPwd;
	}

	@Column(name = "operatorMobile", length = 16)
	public String getOperatorMobile() {
		return this.operatorMobile;
	}

	public void setOperatorMobile(String operatorMobile) {
		this.operatorMobile = operatorMobile;
	}

	@Column(name = "status", length = 1)
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

	@Column(name = "lastLoginTime", length = 20)
	public String getLastLoginTime() {
		return this.lastLoginTime;
	}

	public void setLastLoginTime(String lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}

	@Column(name = "operatorNo", length = 32)
	public String getOperatorNo() {
		return this.operatorNo;
	}

	public void setOperatorNo(String operatorNo) {
		this.operatorNo = operatorNo;
	}

	@Column(name = "operatorEffectDate", length = 20)
	public String getOperatorEffectDate() {
		return this.operatorEffectDate;
	}

	public void setOperatorEffectDate(String operatorEffectDate) {
		this.operatorEffectDate = operatorEffectDate;
	}

	@Column(name = "operatorEndDate", length = 20)
	public String getOperatorEndDate() {
		return this.operatorEndDate;
	}

	public void setOperatorEndDate(String operatorEndDate) {
		this.operatorEndDate = operatorEndDate;
	}

	@Column(name = "operatorBirthday", length = 20)
	public String getOperatorBirthday() {
		return this.operatorBirthday;
	}

	public void setOperatorBirthday(String operatorBirthday) {
		this.operatorBirthday = operatorBirthday;
	}

	@Column(name = "operatorContact", length = 32)
	public String getOperatorContact() {
		return this.operatorContact;
	}

	public void setOperatorContact(String operatorContact) {
		this.operatorContact = operatorContact;
	}

	@Column(name = "operatorAddress", length = 512)
	public String getOperatorAddress() {
		return this.operatorAddress;
	}

	public void setOperatorAddress(String operatorAddress) {
		this.operatorAddress = operatorAddress;
	}

	@Column(name = "operatorSex", length = 1)
	public String getOperatorSex() {
		return this.operatorSex;
	}

	public void setOperatorSex(String operatorSex) {
		this.operatorSex = operatorSex;
	}

	@Column(name = "operatorType", length = 1)
	public String getOperatorType() {
		return this.operatorType;
	}

	public void setOperatorType(String operatorType) {
		this.operatorType = operatorType;
	}

	@Column(name = "openId", length = 32)
	public String getOpenId() {
		return this.openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}

}