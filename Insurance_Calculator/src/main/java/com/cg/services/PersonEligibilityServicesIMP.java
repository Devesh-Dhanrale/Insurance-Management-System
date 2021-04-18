package com.cg.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.dao.PersonEligibilityDAO;
import com.cg.entity.PersonEligibilityEntity;

@Service
public class PersonEligibilityServicesIMP implements PersonEligibilityIMP {

	@Autowired
	PersonEligibilityDAO personEligibilityDao;
	
	@Override
	public PersonEligibilityEntity addPerson(PersonEligibilityEntity eligibilityEntity) {
		return personEligibilityDao.saveAndFlush(eligibilityEntity);
	}

	@Override
	public List<PersonEligibilityEntity> listAllEligiblePerson() {
		return personEligibilityDao.findAll();
	}

}
