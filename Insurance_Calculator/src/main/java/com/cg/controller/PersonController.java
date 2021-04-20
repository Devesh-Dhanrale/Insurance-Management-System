package com.cg.controller;

import java.util.ArrayList;
import java.util.HashMap;
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
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.cg.entity.PersonEligibilityEntity;
import com.cg.entity.PersonEntity;
import com.cg.services.PersonEligibilityServicesIMP;
import com.cg.services.PersonServicesIMP;

import io.swagger.annotations.Api;

@RestController
@RequestMapping("/Insurance_Calculator")
@Validated
@Api(tags = { "person-controller" })
public class PersonController {

	private static final Logger logger = LoggerFactory.getLogger(PersonController.class);

	@Autowired
	PersonServicesIMP imp;

	@Autowired
	PersonEligibilityServicesIMP pesimp;

	@Autowired
	RestTemplate rest;

	/**
	 Method Name: getEligibility
	 Input Parameters: PersonEntity p, PersonEligibilityEntity personEligibilityEntity
	 Return type: List<String>
	 Author: Capgemini
	 Creation Date: 19-04-2021
	 Description: Customer can check elligiblity for applying policy
	 */
	@PostMapping("/checkEligibility")
	public List<String> getEligibility(@RequestBody @Valid PersonEntity p,
			PersonEligibilityEntity personEligibilityEntity) {

		logger.info("Person object: {} ", p);
		imp.addPerson(p);

		List<String> listofpolicy = new ArrayList<>();

		ParameterizedTypeReference<HashMap<Integer, String>> responseType = new ParameterizedTypeReference<HashMap<Integer, String>>() {
		};

		RequestEntity<Void> request = RequestEntity.get("http://localhost/admins/admin/getAllPolicy")
				.accept(MediaType.APPLICATION_JSON).build();
		ResponseEntity<HashMap<Integer, String>> jsonDictionary = rest.exchange(request, responseType);

		HashMap<Integer, String> map = jsonDictionary.getBody();

		for (Entry<Integer, String> entry : map.entrySet()) {
			listofpolicy.add(entry.getValue());
			logger.info("Policy name: {} ", entry.getValue());
		}

		List<String> list1 = new ArrayList<>();
		List<String> list2 = new ArrayList<>();

		for (String str : listofpolicy) {
			String[] arr = str.split("-");
			String id = arr[0];
			String value = arr[1];
			list1.add(id); // has list of all policy ids
			list2.add(value); // has list of all policy names
		}

		logger.info("List of policy id: {} ", list1);
		logger.info("List of policy names: {} ", list2);

		String msg = null;
		long[] a = null;

		List<String> list = new ArrayList<>();

		if (p.getDisease().equalsIgnoreCase("no") && list2.get(0).equals("Term Insurance Without Disease")) {
			a = imp.checkEligibilityWithoutDisease(p);
			msg = list2.get(0) + " " + ", Monthly Rate: " + a[2] + ", Half Yearly Rate: " + a[3] + ", Yearly Rate: "
					+ a[4];

			if (a[0] == 0) {
				list.add("Adhar Number: " + p.getAdharNo() + " is eligible for " + msg);
			} else if (a[0] == 1) {
				list.add("Adhar Number: " + p.getAdharNo() + " is not eligible for the policies");
			}
			personEligibilityEntity.setPolicyName(list2.get(0));
			logger.info("{}", list);
		}

		if (p.getDisease().equalsIgnoreCase("yes") && list2.get(1).equals("Term Insurance With Disease")) {
			a = imp.checkEligibilityWithDisease(p);
			msg = list2.get(1) + " " + ", Monthly Rate: " + a[2] + ", Half Yearly Rate: " + a[3] + ", Yearly Rate: "
					+ a[4];

			if (a[0] == 0) {
				list.add("Adhar Number: " + p.getAdharNo() + " is eligible for " + msg);
			} else if (a[0] == 1) {
				list.add("Adhar Number: " + p.getAdharNo() + " is not eligible for the policies");
			}
			personEligibilityEntity.setPolicyName(list2.get(1));
			logger.info("{}", list);
		}
		logger.info("{}", list);

		personEligibilityEntity.setAdharNo(p.getAdharNo());
		personEligibilityEntity.setFullName(p.getFullName());
		String eligible = "";

		try {
			if (a[0] == 0) {
				eligible = "Not Eligible";
			}
			if (a[0] == 0) {
				eligible = "Eligible";
			}
		} catch (NullPointerException e) {
			e.printStackTrace();
		}

		personEligibilityEntity.setEligibleStatus(eligible);
		personEligibilityEntity.setMonthlyPlan(a[2]);
		personEligibilityEntity.setHalfYearlyPlan(a[3]);
		personEligibilityEntity.setYearlyPlan(a[4]);

		pesimp.addPerson(personEligibilityEntity);

		return list;
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
