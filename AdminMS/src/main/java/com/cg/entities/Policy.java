package com.cg.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;


@Entity
@Table(name="policy")
public class Policy implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	private Integer policyId;
	@NotEmpty(message="Policy Name is mandatory")
	private String policyName;
	@NotEmpty(message="Policy Description is mandatory")
	private String policyDescription;
	private Integer policyDuration;
	private Integer policyCoverage;
	
	public Policy() {
	}

	public Policy(Integer policyId, String policyName, String policyDescription, Integer policyDuration,
			Integer policyCoverage) {
		super();
		this.policyId = policyId;
		this.policyName = policyName;
		this.policyDescription = policyDescription;
		this.policyDuration = policyDuration;
		this.policyCoverage = policyCoverage;
	}

	public Integer getPolicyId() {
		return policyId;
	}

	public void setPolicyId(Integer policyId) {
		this.policyId = policyId;
	}

	public String getPolicyName() {
		return policyName;
	}

	public void setPolicyName(String policyName) {
		this.policyName = policyName;
	}

	public String getPolicyDescription() {
		return policyDescription;
	}

	public void setPolicyDescription(String policyDescription) {
		this.policyDescription = policyDescription;
	}

	public Integer getPolicyDuration() {
		return policyDuration;
	}

	public void setPolicyDuration(Integer policyDuration) {
		this.policyDuration = policyDuration;
	}

	public Integer getPolicyCoverage() {
		return policyCoverage;
	}

	public void setPolicyCoverage(Integer policyCoverage) {
		this.policyCoverage = policyCoverage;
	}

	@Override
	public String toString() {
		return "Policy [policyId=" + policyId + ", policyName=" + policyName + ", policyDescription="
				+ policyDescription + ", policyDuration=" + policyDuration + ", policyCoverage=" + policyCoverage + "]";
	}

}
