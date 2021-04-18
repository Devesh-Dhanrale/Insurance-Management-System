package com.cg.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

@Entity
@Table(name="person")
public class PersonEntity implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@NotEmpty(message="Adhar Number is mandatory")
	@Pattern(regexp = "[0-9]{12}", message = "Adhar number must be 12 digit number")
	private String adharNo;
	private String fullName;
	private int age;
	private double salary;
	@NotEmpty(message="Disease field is mandatory, give Yes or No")
	private String disease;
	private String gender;
	private String dob;
	@Pattern(regexp = "[0-9]{10}", message = "Mobile number must be 10 digit number")
	private String mobileNo;
	private String address;
	private String emailId;
	private String nationality;
	private String state;
	private int pincode;
	private int agentRefNo;

	public PersonEntity() {
	}

	public PersonEntity(String adharNo, String fullName, int age, double salary, String disease, String gender, String dob, String mobileNo, String address, String emailId, String nationality, String state, int pincode,
			int agentRefNo) {
		super();
		this.adharNo = adharNo;
		this.fullName = fullName;
		this.age = age;
		this.salary = salary;
		this.disease = disease;
		this.gender = gender;
		this.dob = dob;
		this.mobileNo = mobileNo;
		this.address = address;
		this.emailId = emailId;
		this.nationality = nationality;
		this.state = state;
		this.pincode = pincode;
		this.agentRefNo = agentRefNo;
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

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}

	public String getDisease() {
		return disease;
	}

	public void setDisease(String disease) {
		this.disease = disease;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
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

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getNationality() {
		return nationality;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public int getPincode() {
		return pincode;
	}

	public void setPincode(int pincode) {
		this.pincode = pincode;
	}

	public int getAgentRefNo() {
		return agentRefNo;
	}

	public void setAgentRefNo(int agentRefNo) {
		this.agentRefNo = agentRefNo;
	}

	@Override
	public String toString() {
		return "PersonEntity [adharNo=" + adharNo + ", fullName=" + fullName + ", age=" + age + ", salary=" + salary
				+ ", disease=" + disease + ", gender=" + gender + ", dob=" + dob + ", mobileNo=" + mobileNo
				+ ", address=" + address + ", emailId=" + emailId + ", nationality=" + nationality + ", state=" + state
				+ ", pincode=" + pincode + ", agentRefNo=" + agentRefNo + "]";
	}
	
}
