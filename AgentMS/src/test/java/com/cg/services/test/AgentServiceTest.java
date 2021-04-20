package com.cg.services.test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.cg.dao.AgentDao;
import com.cg.entities.AgentEntity;
import com.cg.services.AgentServiceImpl;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
class AgentServiceTest {

	@Autowired
	AgentServiceImpl agentServ;
	
	@MockBean
	AgentDao agentDao;
	
	@Test
	void addAgentTest() {
		AgentEntity agent = new AgentEntity();
		agent.setAdharNo("123456789123");
		agent.setFullName("Sahil Lotlikar");
		agent.setEmailId("sahil@gmail.com");
		agent.setMobileNo("9604609750");
		agent.setAddress("Goa");
		agent.setHigherEducation("Graduate");

		Mockito.when(agentDao.saveAndFlush(agent)).thenReturn(agent);

		assertThat(agentServ.addAgent(agent)).isEqualTo(agent);
	}

	@Test
	void updateAgentTest() {
		AgentEntity agent = new AgentEntity();
		agent.setAdharNo("123456789123");
		agent.setFullName("Sahil Lotlikar");
		agent.setEmailId("sahil@gmail.com");
		agent.setMobileNo("9604609750");
		agent.setAddress("Goa");
		agent.setHigherEducation("Graduate");

		Mockito.when(agentDao.getOne("123456789123")).thenReturn(agent);

		agent.setEmailId("sahil65@gmail.com");
		agent.setMobileNo("8208848715");
		agent.setAddress("Margao Goa");

		Mockito.when(agentDao.saveAndFlush(agent)).thenReturn(agent);

		assertThat(agentServ.updateAgent("123456789123", "sahil65@gmail.com", "8208848715", "Margao Goa")).isEqualTo(agent);
	}
	
	@Test
	void removeAgentTest() {
		AgentEntity agent = new AgentEntity();
		agent.setAdharNo("123456789123");
		agent.setFullName("Sahil Lotlikar");
		agent.setEmailId("sahil@gmail.com");
		agent.setMobileNo("9604609750");
		agent.setAddress("Goa");
		agent.setHigherEducation("Graduate");

		Mockito.when(agentDao.getOne("123456789123")).thenReturn(agent);

		String adharNo = agent.getAdharNo();
		
		Mockito.when(agentDao.existsById(adharNo)).thenReturn(false);
		
		assertFalse(agentDao.existsById(agent.getAdharNo()));
	}
	
	@Test
	void getAgentByIdTest() {
		AgentEntity agent1 = new AgentEntity();

		agent1.setAdharNo("123456789123");
		agent1.setFullName("Sahil Lotlikar");
		agent1.setEmailId("sahil@gmail.com");
		agent1.setMobileNo("9604609750");
		agent1.setAddress("Goa");
		agent1.setHigherEducation("Graduate");

		Mockito.when(agentDao.getOne("123456789123")).thenReturn(agent1);

		assertThat(agentServ.getAgentById("123456789123")).isEqualTo(agent1);
	}

	@Test
	void getAgents() {
		AgentEntity agent1 = new AgentEntity();
		
		agent1.setAdharNo("123456789123");
		agent1.setFullName("Sahil Lotlikar");
		agent1.setEmailId("sahil@gmail.com");
		agent1.setMobileNo("9604609750");
		agent1.setAddress("Goa");
		agent1.setHigherEducation("Graduate");

		AgentEntity agent2 = new AgentEntity();
		
		agent2.setAdharNo("789456123789");
		agent2.setFullName("Sagar Lotlikar");
		agent2.setEmailId("sagar@gmail.com");
		agent2.setMobileNo("8208848715");
		agent2.setAddress("Goa");
		agent2.setHigherEducation("Non Graduate");
		
		List<AgentEntity> agentList = new ArrayList<>();
		agentList.add(agent1);
		agentList.add(agent2);

		Mockito.when(agentDao.findAll()).thenReturn(agentList);
		
		Map<String, String> map = new LinkedHashMap<>();
		
		for (AgentEntity a : agentList) {
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
		
		assertThat(agentServ.getAgents()).isEqualTo(map);
		
	}
}
