package com.cg.services;

import java.security.SecureRandom;

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
	public int getRandomNumber(int min, int max){
	    return (int) ((Math.random() * (max - min)) + min);
	}

	@Override
	public String generateRandomPassword(int len)   {
        // ASCII range – alphanumeric (0-9, a-z, A-Z)
        final String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
 
        SecureRandom random = new SecureRandom();
        StringBuilder sb = new StringBuilder();
 
        // each iteration of the loop randomly chooses a character from the given
        // ASCII range and appends it to the `StringBuilder` instance
 
        for (int i = 0; i < len; i++)
        {
            int randomIndex = random.nextInt(chars.length());
            sb.append(chars.charAt(randomIndex));
        }
 
        return sb.toString();
    }

	public CustomerCredentialsEntity addCustomerCredentials(CustomerCredentialsEntity credentialsEntity) {
			
		logger.info("in service of customer");
		return dao.saveAndFlush(credentialsEntity);
				
	}

}
