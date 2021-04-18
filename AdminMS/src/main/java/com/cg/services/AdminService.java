package com.cg.services;

import java.util.List;
import java.util.Map;

import com.cg.entities.AdminAgentApprovalEntity;
import com.cg.entities.Policy;

public interface AdminService {

	public Policy addPolicy(Policy p);

	public Policy updatePolicy(Integer id, String policyName, String policyDescription, Integer duration, Integer coverage);

	public List<Policy> removePolicy(Integer id);

	public List<Policy> getPolicyDetails();

	public Map<Integer, String> getAllPolicy();

	public AdminAgentApprovalEntity adminApproveAgent(AdminAgentApprovalEntity agent);

	public List<AdminAgentApprovalEntity> viewAgents();

}
