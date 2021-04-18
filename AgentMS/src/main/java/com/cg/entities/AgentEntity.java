package com.cg.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;


@Entity
@Table(name="agent_entity")
public class AgentEntity implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@NotEmpty(message="Adhar Number is mandatory")
	@Pattern(regexp = "[0-9]{12}", message = "Adhar number must be 12 digit number")
	private String adharNo;
	@NotEmpty(message="Agent Name is mandatory")
	@Size(min = 3, max = 20, message = "Agent Name must be between 3-20 characters!!!")
	private String fullName;
	@NotEmpty(message="Email Id is mandatory")
	@Email(message="Enter a valid Email Id")
	private String emailId;
	@NotEmpty(message="Mobile number is mandatory")
	@Pattern(regexp = "[0-9]{10}", message = "Mobile number must be 10 digit number")
	private String mobileNo;
	@NotEmpty(message="Address is mandatory")
	private String address;
	@NotEmpty(message="Higher Education is mandatory")
	private String higherEducation;
	
	public AgentEntity() {

	}

	public AgentEntity(String adharNo, String fullName, String emailId, String mobileNo,
			String address,String higherEducation) {
		super();
		this.adharNo = adharNo;
		this.fullName = fullName;
		this.emailId = emailId;
		this.mobileNo = mobileNo;
		this.address = address;
		this.higherEducation = higherEducation;
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

	@Override
	public String toString() {
		return "AgentEntity [adharNo=" + adharNo + ", fullName=" + fullName + ", emailId=" + emailId + ", mobileNo="
				+ mobileNo + ", address=" + address + ", higherEducation=" + higherEducation + "]";
	}

}
