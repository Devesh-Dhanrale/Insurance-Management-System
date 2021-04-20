package com.cg.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.entity.CustomerCredentialsEntity;
import com.cg.entity.PaymentEntity;
import com.cg.services.CustomerCredentialsIMP;
import com.cg.services.PaymentIMP;

import io.swagger.annotations.Api;

@RestController
@RequestMapping("/Payment")
@Api(tags = {"payment-controller"})
public class PaymentController {

	@Autowired
	PaymentIMP imp;

	@Autowired
	CustomerCredentialsIMP imp2;

	private static final Logger logger = LoggerFactory.getLogger(PaymentController.class);
	
	/**
	 Method Name: doPayment
	 Input Parameters: String adharNo, String fullName, String policyName, int amount, PaymentEntity p 
	 Return type: String
	 Author: Capgemini
	 Creation Date: 19-04-2021
	 Description: Customer can pay for the policy.
	 */
	@GetMapping("/process/{adharNo}/{fullName}/{policyName}/{amount}")
	public String doPayment(@PathVariable("adharNo") @Valid String adharNo,
			@PathVariable("fullName") String fullName, @PathVariable("policyName") String policyName,
			@PathVariable("amount") int amount, PaymentEntity p) {
		CustomerCredentialsEntity credentialsEntity = new CustomerCredentialsEntity();
		int log = 0;
		String c = null;
		String credentials;

		if (imp.isAvailable(adharNo)) {

			p.setAdharNo(adharNo);
			p.setFullName(fullName);
			p.setEligibilityStatus("Eligible");
			p.setPolicyName(policyName);
			p.setAmount(amount);
			p.setPaymentStatus("Successfull");
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
			LocalDateTime now = LocalDateTime.now();
			String str = dtf.format(now);
			logger.info(str);
			p.setDate(dtf.format(now));
			imp.addPaymentEntity(p);

			logger.info("Payment entity: {} ", p);

			credentialsEntity.setAdharNo(adharNo);
			log = imp2.getRandomNumber(100, 1000);
			credentialsEntity.setLogin(log);
			c = imp2.generateRandomPassword(4);
			credentialsEntity.setPassword(c);

			imp2.addCustomerCredentials(credentialsEntity);
			logger.info("Credentials Entity: {} ", credentialsEntity);
			
			credentials = p.getAdharNo() + " " + p.getFullName() + " Login Id " + Integer.toString(log)
			+ " Password " + c;

		} else {
			logger.error("Customer must check First Eligibility Status");
			credentials = "Customer must check First Eligibility Status";
		}
		
		return credentials;

	}
	
	/**
	 Method Name: addPaymentEntity
	 Input Parameters: PaymentEntity p 
	 Return type: PaymentEntity
	 Author: Capgemini
	 Creation Date: 19-04-2021
	 Description: Add payment entity to database.
	 */
	public PaymentEntity addPaymentEntity(PaymentEntity p) {
        return imp.addPaymentEntity(p);
	}

}
