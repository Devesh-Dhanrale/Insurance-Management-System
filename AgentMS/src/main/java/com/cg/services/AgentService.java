package com.cg.services;

import java.util.List;
import java.util.Map;

import com.cg.entities.AgentEntity;

public interface AgentService {

	public AgentEntity addAgent(AgentEntity agent);
	
	public Map<String,String> getAgents();
	
	public AgentEntity updateAgent(String id, String emailId, String mobileNo, String address);
	
	public List<AgentEntity> removeAgent(String id);
	
	public AgentEntity getAgentById(String id);
}
