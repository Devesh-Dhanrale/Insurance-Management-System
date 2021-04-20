package com.cg.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.cg.entities.AdminAgentApprovalEntity;
import com.cg.entities.Policy;
import com.cg.exceptions.PolicyNotFoundException;
import com.cg.services.AdminServiceImpl;

import io.swagger.annotations.Api;

@RestController
@RequestMapping("/admin")
@Validated
@Api(tags = {"admin-controller"})
public class AdminController {
	
	private static final Logger logger = LoggerFactory.getLogger(AdminController.class);

	@Autowired
	AdminServiceImpl adminSer;

	@Autowired
	RestTemplate rest;

	/**
	 Method Name: addPolicy
	 Input Parameters: Policy policy
	 Return type: Policy
	 Author: Capgemini
	 Creation Date: 19-04-2021
	 Description: Adding policy details
	 */
	@PostMapping("/addPolicy")
	public Policy addPolicy(@Valid @RequestBody Policy policy) {
		logger.info("In AdminController to add policy");
		return adminSer.addPolicy(policy);
	}

	/**
	 Method Name: getAllPolicy
	 Input Parameters: 
	 Return type: Map<Integer,String>
	 Author: Capgemini
	 Creation Date: 19-04-2021
	 Description: Retrieving policy details which is used for other in insurance controller
	 */
	@GetMapping("/getAllPolicy")
	public Map<Integer, String> getAllPolicy() {
		logger.info("In AdminController to retrieve policy using map");
		return adminSer.getAllPolicy();
	}

	/**
	 Method Name: updatePolicy
	 Input Parameters: Integer policyId, String policyName, String policyDescription, Integer policyDuration, Integer policyCoverage
	 Return type: Policy
	 Author: Capgemini
	 Creation Date: 19-04-2021
	 Description: Update an existing policy details.
	 */
	@CrossOrigin
	@PutMapping(value="/updatePolicy/policyId/{policyId}/policyName/{policyName}/policyDescription/{policyDescription}/policyDuration/{policyDuration}/policyCoverage/{policyCoverage}",produces=MediaType.APPLICATION_JSON_VALUE)
	public Policy updatePolicy(@PathVariable("policyId") @Valid Integer policyId, @PathVariable("policyName") @Valid String policyName, @PathVariable("policyDescription") @Valid String policyDescription, @PathVariable("policyDuration") @Valid Integer policyDuration, @PathVariable("policyCoverage") @Valid Integer policyCoverage) {
		logger.info("In AdminController to update policy");
		return adminSer.updatePolicy(policyId,policyName,policyDescription,policyDuration,policyCoverage);
	}
	
	/**
	 Method Name: removePolicy
	 Input Parameters: Integer id
	 Return type: List<Policy>
	 Author: Capgemini
	 Creation Date: 19-04-2021
	 Description: Delete an existing policy details.
	 */
	@DeleteMapping("/removePolicy/{id}")
	public List<Policy> removePolicy(@Valid @PathVariable Integer id) throws PolicyNotFoundException {
		logger.info("In AdminController to remove policy");
		List<Policy> policies = null;

		try {
			policies = adminSer.removePolicy(id);
		} catch (Exception exp) {
			throw new PolicyNotFoundException("Policy id given is doesnot exists");
		}

		return policies;
	}

	/**
	 Method Name: getPolicyDetails
	 Input Parameters: 
	 Return type: List<Policy>
	 Author: Capgemini
	 Creation Date: 19-04-2021
	 Description: Get all policy details.
	 */
	@GetMapping("/getPolicyDetails")
	public List<Policy> getPolicyDetails() throws PolicyNotFoundException {
		logger.info("In AdminController to get all policy details");

		List<Policy> policies = null;

		try {
			policies = adminSer.getPolicyDetails();
		} catch (Exception exp) {
			throw new PolicyNotFoundException("Policies are not found");
		}

		return policies;
	}

	/**
	 Method Name: approveAgent
	 Input Parameters: AdminAgentApprovalEntity agent
	 Return type: List<String>
	 Author: Capgemini
	 Creation Date: 19-04-2021
	 Description: Approves an agent.
	 */
	@GetMapping("/approveAgent")
	public List<String> approveAgent(AdminAgentApprovalEntity agent) {
		
		logger.info("In AdminController to approve agent");

		ParameterizedTypeReference<LinkedHashMap<String, String>> responseType = new ParameterizedTypeReference<LinkedHashMap<String, String>>() {
		};

		RequestEntity<Void> request = RequestEntity.get("http://localhost/agents/agent/getAgents")
				.accept(MediaType.APPLICATION_JSON).build();
		ResponseEntity<LinkedHashMap<String, String>> jsonDictionary = rest.exchange(request, responseType);

		LinkedHashMap<String, String> map = jsonDictionary.getBody();

		List<String> list = new ArrayList<>();

		for (Entry<String, String> entry : map.entrySet()) {
			if (entry.getKey().equalsIgnoreCase("adharNo")) {
				list.add(entry.getValue());
			}
			if (entry.getKey().equalsIgnoreCase("fullName")) {
				list.add(entry.getValue());
			}
			if (entry.getKey().equalsIgnoreCase("emailId")) {
				list.add(entry.getValue());
			}
			if (entry.getKey().equalsIgnoreCase("mobileNo")) {
				list.add(entry.getValue());
			}
			if (entry.getKey().equalsIgnoreCase("address")) {
				list.add(entry.getValue());
			}
			if (entry.getKey().equalsIgnoreCase("higherEducation")) {
				if (entry.getValue().equalsIgnoreCase("Graduate")) {
					list.add(entry.getValue());
					list.add("Agent Approved");
				} else {
					list.add(entry.getValue());
					list.add("Agent Not Approved");
				}
			}
		}

		String[] a = list.toString().split(",");

		String[] find = new String[a.length];

		List<String> li = new ArrayList<>();

		for (int i = 0; i < a.length; i++) {
			find[i] = a[i].replaceAll("[^a-zA-Z0-9=.,_@]", " ").trim();
			li.add(a[i].replaceAll("[^a-zA-Z0-9=.,_@]", " ").trim());
		}

		for (int i = 0; i < li.size(); i++) {

			agent.setAdharNo(li.get(0));
			agent.setFullName(li.get(1));
			agent.setEmailId(li.get(2));
			agent.setMobileNo(li.get(3));
			agent.setAddress(li.get(4));
			agent.setHigherEducation(li.get(5));
			agent.setApproveStatus(li.get(6));

			adminSer.adminApproveAgent(agent);
		}

		return li;
	}

	/**
	 Method Name: viewAgents
	 Input Parameters: 
	 Return type: List<AdminAgentApprovalEntity>
	 Author: Capgemini
	 Creation Date: 19-04-2021
	 Description: View all agents.
	 */
	@GetMapping("/viewAgents")
	public List<AdminAgentApprovalEntity> viewAgents() {
		return adminSer.viewAgents();
	}

	/**
	 Method Name: handleValidationExceptions
	 Input Parameters: MethodArgumentNotValidException ex
	 Return type: Map<String, String>
	 Author: Capgemini
	 Creation Date: 19-04-2021
	 Description: Exception handler for validation constraints.
	 */
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {
		Map<String, String> errors = new HashMap<>();
		ex.getBindingResult().getAllErrors().forEach(error -> {
			String fieldName = ((FieldError) error).getField();
			String errorMessage = error.getDefaultMessage();
			errors.put(fieldName, errorMessage);
		});
		return errors;
	}

}
