package com.cg.services;

import com.cg.entity.CustomerCredentialsEntity;

public interface CustomerCredentialsIMP {

	public CustomerCredentialsEntity addCustomerCredentials(CustomerCredentialsEntity credentialsEntity);

	public int getRandomNumber(int min, int max);

	public String generateRandomPassword(int len);

}
