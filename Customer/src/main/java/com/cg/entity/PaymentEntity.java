package com.cg.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "payment_entity")
public class PaymentEntity {

	@Id
	private String adharNo;
	private String fullName;
	private Integer amount;
	private String paymentStatus;
	private String policyName;
	private String eligibilityStatus;
	private String date;

	public PaymentEntity() {
	}

	public PaymentEntity(String adharNo, String fullName, Integer amount, String paymentStatus, String policyName,
			String eligibilityStatus, String date) {
		super();
		this.adharNo = adharNo;
		this.fullName = fullName;
		this.amount = amount;
		this.paymentStatus = paymentStatus;
		this.policyName = policyName;
		this.eligibilityStatus = eligibilityStatus;
		this.date = date;
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

	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

	public String getPaymentStatus() {
		return paymentStatus;
	}

	public void setPaymentStatus(String paymentStatus) {
		this.paymentStatus = paymentStatus;
	}

	public String getPolicyName() {
		return policyName;
	}

	public void setPolicyName(String policyName) {
		this.policyName = policyName;
	}

	public String getEligibilityStatus() {
		return eligibilityStatus;
	}

	public void setEligibilityStatus(String eligibilityStatus) {
		this.eligibilityStatus = eligibilityStatus;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	@Override
	public String toString() {
		return "PaymentEntity [adharNo=" + adharNo + ", fullName=" + fullName + ", amount=" + amount
				+ ", paymentStatus=" + paymentStatus + ", policyName=" + policyName + ", eligibilityStatus="
				+ eligibilityStatus + ", date=" + date + "]";
	}
	
}
