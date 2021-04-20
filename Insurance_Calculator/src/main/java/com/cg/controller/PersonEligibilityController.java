package com.cg.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.entity.PersonEligibilityEntity;
import com.cg.services.PersonEligibilityServicesIMP;

import io.swagger.annotations.Api;

@RestController
@RequestMapping("/Insurance_Calculator")
@Api(tags = {"person-eligibility-controller"})
public class PersonEligibilityController {
	
	private static final Logger logger = LoggerFactory.getLogger(PersonEligibilityController.class);

	@Autowired 
	PersonEligibilityServicesIMP imp;
	
	/**
	 Method Name: getAllEligibleCustomers
	 Input Parameters: 
	 Return type: List<PersonEligibilityEntity>
	 Author: Capgemini
	 Creation Date: 19-04-2021
	 Description: Get all eligible customer list.
	 */
	@GetMapping("/getAllEligibleCustomer")
	public List<PersonEligibilityEntity> getAllEligibleCustomers(){
		logger.info("In Person Eligibilty Controller to retrieve all eligible customers");
		return imp.listAllEligiblePerson();
	}
	
}
