package com.cg.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cg.entity.CustomerCredentialsEntity;

@Repository
public interface CustomerCredentialsDAO  extends JpaRepository<CustomerCredentialsEntity, Integer>{

}
