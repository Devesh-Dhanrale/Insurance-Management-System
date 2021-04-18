package com.cg.services;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.dao.AdminAgentApprovalDao;
import com.cg.dao.AdminDao;
import com.cg.entities.AdminAgentApprovalEntity;
import com.cg.entities.Policy;

@Service
public class AdminServiceImpl implements AdminService{

	@Autowired
	AdminDao adminDao;
	
	@Autowired
	AdminAgentApprovalDao adminAgentDao;
	
	@Override
	public Policy addPolicy(Policy p) {
		return adminDao.saveAndFlush(p);
	}

	@Override
	public Map<Integer,String> getAllPolicy() {
		List<Policy> list = adminDao.findAll();
		Map<Integer,String> map = new TreeMap<>();
		
		for(Policy p: list) {
			Integer key = p.getPolicyId();
			String value = key+"-"+p.getPolicyName();
			map.put(key, value);
		}
		
		return map;
	}

	@Override
	public Policy updatePolicy(Integer id, String policyName, String policyDescription, Integer duration, Integer coverage) {
		Policy policy = adminDao.getOne(id);
		policy.setPolicyName(policyName);
		policy.setPolicyDescription(policyDescription);
		policy.setPolicyDuration(duration);
		policy.setPolicyCoverage(coverage);
		return adminDao.saveAndFlush(policy);
	}

	@Override
	public List<Policy> removePolicy(Integer id) {
		adminDao.deleteById(id);
		return adminDao.findAll();
	}

	@Override
	public List<Policy> getPolicyDetails() {
		return adminDao.findAll();
	}

	@Override
	public List<AdminAgentApprovalEntity> viewAgents() {
		return adminAgentDao.findAll();
	}

	@Override
	public AdminAgentApprovalEntity adminApproveAgent(AdminAgentApprovalEntity agent) {
		return adminAgentDao.saveAndFlush(agent);
	}

}
