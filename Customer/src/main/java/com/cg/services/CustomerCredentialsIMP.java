package com.cg.services;

public interface CustomerCredentialsIMP {

	public String getAdharNo(Integer login);

	public boolean isvalidLoginCredentials(Integer login, String password);

}
