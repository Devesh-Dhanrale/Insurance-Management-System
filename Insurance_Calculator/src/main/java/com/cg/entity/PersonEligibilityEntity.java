package com.cg.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity

public class PersonEligibilityEntity implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	private String adharNo;
	private String fullName;
	private String policyName;
	private String eligibleStatus;
	private double monthlyPlan;
	private double halfYearlyPlan;
	private double yearlyPlan;

	public PersonEligibilityEntity() {
		
	}
	
	public PersonEligibilityEntity(String adharNo, String fullName, String policyName, String eligibleStatus,
			double monthlyPlan, double halfYearlyPlan, double yearlyPlan) {
		super();
		this.adharNo = adharNo;
		this.fullName = fullName;
		this.policyName = policyName;
		this.eligibleStatus = eligibleStatus;
		this.monthlyPlan = monthlyPlan;
		this.halfYearlyPlan = halfYearlyPlan;
		this.yearlyPlan = yearlyPlan;
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

	public String getPolicyName() {
		return policyName;
	}

	public void setPolicyName(String policyName) {
		this.policyName = policyName;
	}

	public String getEligibleStatus() {
		return eligibleStatus;
	}

	public void setEligibleStatus(String eligibleStatus) {
		this.eligibleStatus = eligibleStatus;
	}

	public double getMonthlyPlan() {
		return monthlyPlan;
	}

	public void setMonthlyPlan(double monthlyPlan) {
		this.monthlyPlan = monthlyPlan;
	}

	public double getHalfYearlyPlan() {
		return halfYearlyPlan;
	}

	public void setHalfYearlyPlan(double halfYearlyPlan) {
		this.halfYearlyPlan = halfYearlyPlan;
	}

	public double getYearlyPlan() {
		return yearlyPlan;
	}

	public void setYearlyPlan(double yearlyPlan) {
		this.yearlyPlan = yearlyPlan;
	}

	@Override
	public String toString() {
		return "PersonEligibilityEntity [adharNo=" + adharNo + ", fullName=" + fullName + ", policyName=" + policyName
				+ ", eligibleStatus=" + eligibleStatus + ", monthlyPlan=" + monthlyPlan + ", halfYearlyPlan="
				+ halfYearlyPlan + ", yearlyPlan=" + yearlyPlan + "]";
	}	

}
