package com.cg.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

@Entity
public class CustomerCredentialsEntity {

	@Id
	@NotEmpty(message = "Adhar Number is mandatory")
	@Pattern(regexp = "[0-9]{12}", message = "Adhar number must be 12 digit number")
	@Column(length = 12)
	private String adharNo;
	private Integer login;
	private String password;
	
	public CustomerCredentialsEntity() {
	}

	public CustomerCredentialsEntity(String adharNo, int login, String password) {
		super();
		this.adharNo = adharNo;
		this.login = login;
		this.password = password;
	}

	public String getAdharNo() {
		return adharNo;
	}

	public void setAdharNo(String adharNo) {
		this.adharNo = adharNo;
	}

	public Integer getLogin() {
		return login;
	}

	public void setLogin(Integer login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "CustomerCredentialsEntity [adharNo=" + adharNo + ", login=" + login + ", password=" + password + "]";
	}

}
