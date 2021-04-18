package com.cg.controller;

import java.util.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.entity.PaymentEntity;
import com.cg.services.CustomerCredentialsIMP;
import com.cg.services.PaymentIMP;

@RestController
@RequestMapping("/Customer")
@Validated
public class CustomerController {

	@Autowired
	CustomerCredentialsIMP imp;

	@Autowired
	PaymentIMP imp2;

	private static final Logger logger = LoggerFactory.getLogger(CustomerController.class);

	@GetMapping("/getPaymentHistory/{login}/{password}")
	public List<PaymentEntity> nothing(@PathVariable("login") Integer login,
			@PathVariable("password") String password) {
		String adharNo = null;
		List<PaymentEntity> p = new ArrayList<>();
		if (imp.isvalidLoginCredentials(login, password)) {
			adharNo = imp.getAdharNo(login);

			p = imp2.getDetailsOfPaymentHistory(adharNo);

			logger.info("Payment history {}",p);

		} else {
			logger.info("Customer must register first");
		}
		return p;
	}

}
