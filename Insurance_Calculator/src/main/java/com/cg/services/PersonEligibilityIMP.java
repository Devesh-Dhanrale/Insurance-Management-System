package com.cg.services;

import java.util.List;

import com.cg.entity.PersonEligibilityEntity;

public interface PersonEligibilityIMP {

	public PersonEligibilityEntity addPerson(PersonEligibilityEntity eligibilityEntity);
	
	public List<PersonEligibilityEntity> listAllEligiblePerson();
	
	
}
