package com.cg.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.cg.entity.CustomerCredentialsEntity;

public interface CustomerCredentialsDAO  extends JpaRepository<CustomerCredentialsEntity, Integer>{

	@Query("select u from customer_credentials_entity u where u.adharNo = ?1")
	public List<CustomerCredentialsEntity> findCustomerWithAdharNo(String adharNo);
	
	@Query("select u from customer_credentials_entity u where u.login = ?1")
	public List<CustomerCredentialsEntity>  getAdharNo(Integer login);
	
	@Query("select u from customer_credentials_entity u where u.login =:login and u.password=:password")
	public List<CustomerCredentialsEntity> isvalidLoginCredentials(Integer login,String password);
	
}
