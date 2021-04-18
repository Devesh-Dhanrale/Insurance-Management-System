package com.cg.services;

import java.util.ArrayList;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.cg.dao.PaymentDAO;
import com.cg.entity.PaymentEntity;

@Service
public class PaymentServicesIMP implements PaymentIMP {

	@Autowired
	PaymentDAO dao;

	@Autowired
	RestTemplate rest;
	
	private static final Logger logger = LoggerFactory.getLogger(PaymentServicesIMP.class);

	@Override
	public PaymentEntity addPaymentEntity(PaymentEntity p) {
		return dao.saveAndFlush(p);
	}

	@Override
	public boolean isAvailable(String adharNo) {

		ParameterizedTypeReference<List<Object>> responseType = new ParameterizedTypeReference<List<Object>>() {
		};

		RequestEntity<Void> request = RequestEntity
				.get("http://localhost/Insurance_Calculators/Insurance_Calculator/getAllEligibleCustomer")
				.accept(MediaType.APPLICATION_JSON).build();
		ResponseEntity<List<Object>> jsonDictionary = rest.exchange(request, responseType);

		List<Object> map = jsonDictionary.getBody();
        String string = map.toString();
		logger.info(string);

		String[] a = map.toString().split(",");

		List<String> li = new ArrayList<>();

		String[] find = new String[a.length];

		for (int i = 0; i < a.length; i++) {
			find[i] = a[i].replaceAll("[^a-zA-Z0-9=.,_\\s]", " ").trim();
			li.add(a[i].replaceAll("[^a-zA-Z0-9=.,_\\s]", " ").trim());
		}

		logger.info("In array");
		for (int i = 0; i < find.length; i++) {
			logger.info(find[i]);
		}

		logger.info("In list print {}", li);

		String msg = "adharNo=" + adharNo;

		return li.contains((Object) msg);

	}

}
