package com.cg.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

import com.cg.entities.AgentEntity;
import com.cg.exceptions.AgentNotFoundException;
import com.cg.services.AgentServiceImpl;

import io.swagger.annotations.Api;

@RestController
@RequestMapping("/agent")
@Validated
@Api("An API for Agent Operations....")
public class AgentController {
	
	private static final Logger logger = LoggerFactory.getLogger(AgentController.class);

	@Autowired
	AgentServiceImpl agentServ;

	@Autowired
	RestTemplate rest;

	@PostMapping("/addAgent")
	public AgentEntity addAgent(@RequestBody @Valid AgentEntity agent) {
		logger.info("In AgentController to add agent");
		return agentServ.addAgent(agent);
	}

	@CrossOrigin
	@PutMapping(value="/updateAgent/adharNo/{adharNo}/emailId/{emailId}/mobileNo/{mobileNo}/address/{address}",produces=MediaType.APPLICATION_JSON_VALUE)
	public AgentEntity updateAgent(@PathVariable("adharNo") @Valid String adharNo, @PathVariable("emailId") @Valid String emailId, @PathVariable("mobileNo") @Valid String mobileNo, @PathVariable("address") @Valid String address) throws AgentNotFoundException {
		logger.info("In AgentController to update agent");
		
		AgentEntity agent1 = null;
		
		try {
			agent1 = agentServ.getAgentById(adharNo);
		}
		catch (Exception exp) {
			throw new AgentNotFoundException("Agent with given id not found");
		}
		return agentServ.updateAgent(adharNo,emailId,mobileNo,address);
	}

	@DeleteMapping("/removeAgent/{id}")
	public List<AgentEntity> removeAgent(@PathVariable String id) throws AgentNotFoundException {
		logger.info("In AgentController to remove an agent");

		List<AgentEntity> agent = null;

		try {
			agent = agentServ.removeAgent(id);	
		} 
		catch (Exception exp) {
			throw new AgentNotFoundException("Agent with given id not found");
		}

		return agent;
	}

	@GetMapping("/getAgents")
	public Map<String, String> getAgents() {
		logger.info("In AgentController to retrieve agents");

		return agentServ.getAgents();
	}

	@GetMapping("/agentAprrovals")
	public List<Object> getAgentApprovals() {
		logger.info("In AgentController to retrieve list agent approvals");

		ParameterizedTypeReference<List<Object>> responseType = new ParameterizedTypeReference<List<Object>>() {
		};

		RequestEntity<Void> request = RequestEntity.get("http://localhost/admins/admin/viewAgents")
				.accept(MediaType.APPLICATION_JSON).build();

		ResponseEntity<List<Object>> jsonDictionary = rest.exchange(request, responseType);

		return jsonDictionary.getBody();

	}

	//Exception handler for validation constraints
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
