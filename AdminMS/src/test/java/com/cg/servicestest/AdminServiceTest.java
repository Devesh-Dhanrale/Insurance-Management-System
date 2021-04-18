package com.cg.servicestest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.mockito.junit.MockitoJUnitRunner;

import com.cg.dao.AdminAgentApprovalDao;
import com.cg.dao.AdminDao;
import com.cg.entities.AdminAgentApprovalEntity;
import com.cg.entities.Policy;
import com.cg.services.AdminServiceImpl;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
class AdminServiceTest {

	@Autowired
	private AdminServiceImpl adminService;

	@MockBean
	private AdminDao adminDao;

	@MockBean
	private AdminAgentApprovalDao adminAgentDao;

	@Test
	void addPolicyTest() {
		Policy policy = new Policy();
		policy.setPolicyId(1001);
		policy.setPolicyName("Term Insurance With Disease");
		policy.setPolicyDescription("Policy for diseased");
		policy.setPolicyDuration(16);
		policy.setPolicyCoverage(400000);

		Mockito.when(adminDao.saveAndFlush(policy)).thenReturn(policy);

		assertThat(adminService.addPolicy(policy)).isEqualTo(policy);
	}

	@Test
	void updatePolicyTest() {
		Policy policy = new Policy();
		policy.setPolicyId(1001);
		policy.setPolicyName("Term Insurance With Disease");
		policy.setPolicyDescription("Policy for diseased");
		policy.setPolicyDuration(16);
		policy.setPolicyCoverage(400000);

		Mockito.when(adminDao.getOne(1001)).thenReturn(policy);

		policy.setPolicyName("Health insurance");
		policy.setPolicyDescription("Policy for health issue");
		policy.setPolicyDuration(20);
		policy.setPolicyCoverage(600000);

		Mockito.when(adminDao.saveAndFlush(policy)).thenReturn(policy);

		assertThat(adminService.updatePolicy(1001, "Health insurance", "Policy for health issue", 20, 600000)).isEqualTo(policy);
	}

	@Test
	void removePolicyTest() {
		Policy policy = new Policy();
		policy.setPolicyId(1001);
		policy.setPolicyName("Term Insurance With Disease");
		policy.setPolicyDescription("Policy for diseased");
		policy.setPolicyDuration(16);
		policy.setPolicyCoverage(400000);

		Mockito.when(adminDao.getOne(1001)).thenReturn(policy);

		Mockito.when(adminDao.existsById(policy.getPolicyId())).thenReturn(false);
		assertFalse(adminDao.existsById(policy.getPolicyId()));
	}

	@Test
    void getPolicyDetailsTest() {
		Policy policy1 = new Policy();
		policy1.setPolicyId(1001);
		policy1.setPolicyName("Term Insurance With Disease");
		policy1.setPolicyDescription("Policy for diseased");
		policy1.setPolicyDuration(16);
		policy1.setPolicyCoverage(400000);

		Policy policy2 = new Policy();
		policy2.setPolicyId(1002);
		policy2.setPolicyName("Term Insurance Without Disease");
		policy2.setPolicyDescription("Policy for without diseased");
		policy2.setPolicyDuration(16);
		policy2.setPolicyCoverage(300000);

		List<Policy> policyList = new ArrayList<>();
		policyList.add(policy1);
		policyList.add(policy2);

		Mockito.when(adminDao.findAll()).thenReturn(policyList);

		assertThat(adminService.getPolicyDetails()).isEqualTo(policyList);
	}

	@Test
	void getAllPolicyTest() {
		Policy policy1 = new Policy();
		policy1.setPolicyId(1001);
		policy1.setPolicyName("Term Insurance With Disease");
		policy1.setPolicyDescription("Policy for diseased");
		policy1.setPolicyDuration(16);
		policy1.setPolicyCoverage(400000);

		Policy policy2 = new Policy();
		policy2.setPolicyId(1002);
		policy2.setPolicyName("Term Insurance Without Disease");
		policy2.setPolicyDescription("Policy for without diseased");
		policy2.setPolicyDuration(16);
		policy2.setPolicyCoverage(300000);

		List<Policy> policyList = new ArrayList<>();
		policyList.add(policy1);
		policyList.add(policy2);

		Mockito.when(adminDao.findAll()).thenReturn(policyList);

		Map<Integer, String> map = new TreeMap<>();
		Integer key1 = policy1.getPolicyId();
		String value1 = policy1.getPolicyId() + "-" + policy1.getPolicyName();

		Integer key2 = policy2.getPolicyId();
		String value2 = policy2.getPolicyId() + "-" + policy2.getPolicyName();

		map.put(key1, value1);
		map.put(key2, value2);

		assertThat(adminService.getAllPolicy()).isEqualTo(map);
	}

	@Test
	void adminApproveAgentTest() {
		AdminAgentApprovalEntity agent = new AdminAgentApprovalEntity();

		agent.setAdharNo("123456789123");
		agent.setFullName("Sahil Lotlikar");
		agent.setEmailId("sahil@gmail.com");
		agent.setMobileNo("9604609750");
		agent.setAddress("Goa");
		agent.setHigherEducation("Graduate");
		agent.setApproveStatus("Approved");

		Mockito.when(adminAgentDao.saveAndFlush(agent)).thenReturn(agent);

		assertThat(adminService.adminApproveAgent(agent)).isEqualTo(agent);
	}

	@Test
	void viewAgentsTest() {
		AdminAgentApprovalEntity agent1 = new AdminAgentApprovalEntity();

		agent1.setAdharNo("123456789123");
		agent1.setFullName("Sahil Lotlikar");
		agent1.setEmailId("sahil@gmail.com");
		agent1.setMobileNo("9604609750");
		agent1.setAddress("Goa");
		agent1.setHigherEducation("Graduate");
		agent1.setApproveStatus("Approved");

		AdminAgentApprovalEntity agent2 = new AdminAgentApprovalEntity();

		agent2.setAdharNo("789456123789");
		agent2.setFullName("Sagar Lotlikar");
		agent2.setEmailId("sagar@gmail.com");
		agent2.setMobileNo("8208848715");
		agent2.setAddress("Goa");
		agent2.setHigherEducation("Non Graduate");
		agent2.setApproveStatus("Not Approved");

		List<AdminAgentApprovalEntity> agentList = new ArrayList<>();
		agentList.add(agent1);
		agentList.add(agent2);

		Mockito.when(adminAgentDao.findAll()).thenReturn(agentList);

		assertThat(adminService.viewAgents()).isEqualTo(agentList);
	}

}
