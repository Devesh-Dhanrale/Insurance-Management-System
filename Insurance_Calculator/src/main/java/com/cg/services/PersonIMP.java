package com.cg.services;

import java.util.List;

import com.cg.entity.PersonEntity;

public interface PersonIMP {
	
	
	public long[] checkEligibilityWithoutDisease(PersonEntity p);
	
	public long[] checkEligibilityWithDisease(PersonEntity p);
	
	public PersonEntity addPerson(PersonEntity p);
	
	public PersonEntity updatePerson(PersonEntity p);
	
	public List<PersonEntity> removePerson(int id);
	
	public List<PersonEntity> listAllPerson();
	
}
