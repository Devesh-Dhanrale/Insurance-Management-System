package com.cg.services;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.dao.AgentDao;
import com.cg.entities.AgentEntity;

@Service
public class AgentServiceImpl implements AgentService {

	@Autowired
	AgentDao agentDao;

	@Override
	public AgentEntity addAgent(AgentEntity agent) {
		return agentDao.saveAndFlush(agent);
	}

	@Override
	public Map<String, String> getAgents() {
		List<AgentEntity> list = agentDao.findAll();
		Map<String, String> map = new LinkedHashMap<>();

		for (AgentEntity a : list) {
			String key = a.getAdharNo();
			String fullName = a.getFullName();
			String emailId = a.getEmailId();
			String mobileNo = a.getMobileNo();
			String address = a.getAddress();
			String higherEducation = a.getHigherEducation();

			map.put("adharNo", key);
			map.put("fullName", fullName);
			map.put("emailId", emailId);
			map.put("mobileNo", mobileNo);
			map.put("address", address);
			map.put("higherEducation", higherEducation);
		}

		return map;
	}

	@Override
	public AgentEntity updateAgent(String id, String emailId, String mobileNo, String address) {
		AgentEntity agent = agentDao.getOne(id);
		agent.setEmailId(emailId);
		agent.setMobileNo(mobileNo);
		agent.setAddress(address);
		return agentDao.saveAndFlush(agent);
	}

	@Override
	public List<AgentEntity> removeAgent(String id) {
		agentDao.deleteById(id);
		
		return agentDao.findAll();
	}

	@Override
	public AgentEntity getAgentById(String id) {
		return agentDao.getOne(id);
	}

}
