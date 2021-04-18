package com.cg.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="admin_agent_approval_entity")
public class AdminAgentApprovalEntity implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	private String adharNo;
	private String fullName;
	private String emailId;
	private String mobileNo;
	private String address;
	private String higherEducation;
	private String approveStatus;
	
	public AdminAgentApprovalEntity() {

	}

	public AdminAgentApprovalEntity(String adharNo, String fullName, String emailId, String mobileNo, String address,
			String higherEducation, String approveStatus) {
		super();
		this.adharNo = adharNo;
		this.fullName = fullName;
		this.emailId = emailId;
		this.mobileNo = mobileNo;
		this.address = address;
		this.higherEducation = higherEducation;
		this.approveStatus = approveStatus;
	}

	public String getAdharNo() {
		return adharNo;
	}

	public void setAdharNo(String adharNo) {
		this.adharNo = adharNo;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getHigherEducation() {
		return higherEducation;
	}

	public void setHigherEducation(String higherEducation) {
		this.higherEducation = higherEducation;
	}

	public String getApproveStatus() {
		return approveStatus;
	}

	public void setApproveStatus(String approveStatus) {
		this.approveStatus = approveStatus;
	}

	@Override
	public String toString() {
		return "AdminAgentApprovalEntity [adharNo=" + adharNo + ", fullName=" + fullName + ", emailId=" + emailId
				+ ", mobileNo=" + mobileNo + ", address=" + address + ", higherEducation=" + higherEducation
				+ ", approveStatus=" + approveStatus + "]";
	}

}
