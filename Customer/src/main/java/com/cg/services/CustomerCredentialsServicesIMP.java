package com.cg.services;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.dao.CustomerCredentialsDAO;
import com.cg.entity.CustomerCredentialsEntity;

@Service
public class CustomerCredentialsServicesIMP implements CustomerCredentialsIMP {

	@Autowired
	CustomerCredentialsDAO dao;
	
	private static final Logger logger = LoggerFactory.getLogger(CustomerCredentialsServicesIMP.class);


	@Override
	public boolean isvalidLoginCredentials(Integer login, String password) {
        boolean flag = true;
		List<CustomerCredentialsEntity> li = dao.isvalidLoginCredentials(login, password);

		if (li.isEmpty()) {
			flag = false;
		}

		return flag;
	}

	@Override
	public String getAdharNo(Integer login) {

		List<CustomerCredentialsEntity> li = dao.getAdharNo(login);
		String[] a = li.toString().split(",");
		List<String> li1 = new ArrayList<>();
		String[] find = new String[a.length];

		for (int i = 0; i < a.length; i++) {

			find[i] = a[i].replaceAll("[^a-zA-Z0-9=.,_\\s]", " ").trim();
			li1.add(a[i].replaceAll("[^a-zA-Z0-9=.,_\\s]", " ").trim());

		}

		logger.info("In array");
		for (int i = 0; i < find.length; i++) {
			logger.info(find[i]);
		}

		String adharNo = find[0].replaceAll("[^0-9\\s]", " ").trim();
		logger.info("Adhar_no {} ", adharNo);

		logger.info("In list print {} ", li1);

		return adharNo;
	}

}
