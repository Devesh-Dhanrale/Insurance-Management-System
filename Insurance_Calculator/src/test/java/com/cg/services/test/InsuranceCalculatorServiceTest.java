package com.cg.services.test;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.cg.dao.PersonDAO;
import com.cg.dao.PersonEligibilityDAO;
import com.cg.entity.PersonEligibilityEntity;
import com.cg.entity.PersonEntity;
import com.cg.services.PersonEligibilityServicesIMP;
import com.cg.services.PersonServicesIMP;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
class InsuranceCalculatorServiceTest {

	@Autowired
	PersonServicesIMP personSer;
	
	@Autowired
	PersonEligibilityServicesIMP personESer;
	
	@MockBean
	PersonDAO personDao;
	
	@MockBean
	PersonEligibilityDAO personEDao;
	
	@Test
	void addPersonTest() {
		PersonEntity person = new PersonEntity();
		person.setAdharNo("123456789123");
		person.setFullName("Sahil Lotlikar");
		person.setAddress("Margao");
		person.setAge(23);
		person.setAgentRefNo(0);
		person.setDisease("yes");
		person.setDob("12-01-1998");
		person.setEmailId("sahil@gmail.com");
		person.setGender("Male");
		person.setMobileNo("9604609750");
		person.setNationality("Indian");
		person.setPincode(403703);
		person.setSalary(400000);
		person.setState("Goa");

		Mockito.when(personDao.saveAndFlush(person)).thenReturn(person);

		assertThat(personSer.addPerson(person)).isEqualTo(person);
	}
	
	@Test
	void listAllPersonTest() {
		PersonEntity person1 = new PersonEntity();
		person1.setAdharNo("123456789123");
		person1.setFullName("Sahil Lotlikar");
		person1.setAddress("Margao");
		person1.setAge(23);
		person1.setAgentRefNo(0);
		person1.setDisease("yes");
		person1.setDob("12-01-1998");
		person1.setEmailId("sahil@gmail.com");
		person1.setGender("Male");
		person1.setMobileNo("9604609750");
		person1.setNationality("Indian");
		person1.setPincode(403703);
		person1.setSalary(400000);
		person1.setState("Goa");

		PersonEntity person2 = new PersonEntity();
		person2.setAdharNo("789456123789");
		person2.setFullName("Sagar Lotlikar");
		person2.setAddress("Margao");
		person2.setAge(17);
		person2.setAgentRefNo(0);
		person2.setDisease("no");
		person2.setDob("12-01-2003");
		person2.setEmailId("sagar@gmail.com");
		person2.setGender("Male");
		person2.setMobileNo("9604609750");
		person2.setNationality("Indian");
		person2.setPincode(403703);
		person2.setSalary(400000);
		person2.setState("Goa");

		List<PersonEntity> personList = new ArrayList<>();
		personList.add(person1);
		personList.add(person2);

		Mockito.when(personDao.findAll()).thenReturn(personList);

		assertThat(personSer.listAllPerson()).isEqualTo(personList);
	}
	
	@Test
	void addPersonEligibleEntityTest() {
		PersonEligibilityEntity person = new PersonEligibilityEntity();
		person.setAdharNo("123456789123");
		person.setFullName("Sahil Lotlikar");
		person.setPolicyName("Term Insurance With Disease");
		person.setEligibleStatus("Eligible");
		person.setMonthlyPlan(420);
		person.setHalfYearlyPlan(2520);
		person.setYearlyPlan(5040);
		

		Mockito.when(personEDao.saveAndFlush(person)).thenReturn(person);

		assertThat(personESer.addPerson(person)).isEqualTo(person);
	}
	
	@Test
	void listAllEligiblePerson() {
		PersonEligibilityEntity person1 = new PersonEligibilityEntity();
		person1.setAdharNo("123456789123");
		person1.setFullName("Sahil Lotlikar");
		person1.setPolicyName("Term Insurance With Disease");
		person1.setEligibleStatus("Eligible");
		person1.setMonthlyPlan(420);
		person1.setHalfYearlyPlan(2520);
		person1.setYearlyPlan(5040);

		PersonEligibilityEntity person2 = new PersonEligibilityEntity();
		person2.setAdharNo("789456123789");
		person2.setFullName("Sagar Lotlikar");
		person2.setPolicyName("Term Insurance Without Disease");
		person2.setEligibleStatus("Eligible");
		person2.setMonthlyPlan(400);
		person2.setHalfYearlyPlan(2400);
		person2.setYearlyPlan(4800);

		List<PersonEligibilityEntity> personList = new ArrayList<>();
		personList.add(person1);
		personList.add(person2);

		Mockito.when(personEDao.findAll()).thenReturn(personList);

		assertThat(personESer.listAllEligiblePerson()).isEqualTo(personList);
	}
}
