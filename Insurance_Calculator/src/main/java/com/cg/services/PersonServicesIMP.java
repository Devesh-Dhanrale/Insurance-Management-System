package com.cg.services;
import java.text.SimpleDateFormat;

import java.time.Instant;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.List;
import java.text.ParseException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.dao.PersonDAO;
import com.cg.entity.PersonEntity;

@Service
public class PersonServicesIMP implements PersonIMP  {
	
	private static final Logger logger = LoggerFactory.getLogger(PersonServicesIMP.class);

	@Autowired
	PersonDAO dao;
	
	@Override
	public long[] checkEligibilityWithoutDisease(PersonEntity p) {
		
		long [] eligibleValue=new long[5];
		
		int premiumAmount=400;
		
		if((p.getAge()<18 || p.getAge()>60) || p.getSalary()<300000){
			logger.error("Person is Not Eligible");
			logger.info("Please make sure the age is greater than 18 and less than 60 and salary should be greater than 3lac");
			eligibleValue[0]= 1;//0 true and 1 false
			eligibleValue[1]= Long.parseLong(p.getAdharNo());
			eligibleValue[2]= 0000;
		}
		else
		{
			if(p.getAge()==18 && p.getSalary()>300000)
			{
				eligibleValue[0]= 0;//0 true and 1 false
				eligibleValue[1]=Long.parseLong(p.getAdharNo());
				eligibleValue[2]=premiumAmount;
				eligibleValue[3]=(premiumAmount*6);
				eligibleValue[4]=(premiumAmount*12);
			}
			else {
				
				String dob=p.getDob();
				
				SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
				Date date = null;
				try {
					date = formatter.parse(dob);
				} catch (ParseException | NullPointerException e) {
					e.printStackTrace();
				}
	
				// Converting obtained Date object to LocalDate object
				Instant instant = date.toInstant();
				ZonedDateTime zone = instant.atZone(ZoneId.systemDefault());
				LocalDate givenDate = zone.toLocalDate();
				// Calculating the difference between given date to current date.
				Period period = Period.between(givenDate, LocalDate.now());
				int age=period.getYears();
				
				int rate=age-18;
				
				eligibleValue[0]=0;//0 true and 1 false
				eligibleValue[1]=Long.parseLong(p.getAdharNo());
				int b=premiumAmount/100*rate;
				eligibleValue[2]=(premiumAmount+b);
				eligibleValue[3]=((premiumAmount+b)*6);
				eligibleValue[4]=((premiumAmount+b)*12);
			}
		}
		
		return eligibleValue;
	}
	
	
	public long[] checkEligibilityWithDisease(PersonEntity p) {
		
		long [] eligibleValue=new long[5];
		
		int premiumAmount=400;
		
		if((p.getAge()<18 || p.getAge()>60) || p.getSalary()<300000){
			logger.error("Person is Not Eligible");
			logger.info("Please make sure the age is greater than 18 and less than 60 and salary should be greater than 3lac");
			eligibleValue[0]= 1;//0 true and 1 false
			eligibleValue[1]=Long.parseLong(p.getAdharNo());
			eligibleValue[2]=0000;
		}
		else
		{
			if(p.getAge()==18 && p.getSalary()>300000)
			{
				eligibleValue[0]= 0;//0 true and 1 false
				eligibleValue[1]=Long.parseLong(p.getAdharNo());
				eligibleValue[2]=premiumAmount;
				eligibleValue[3]=(premiumAmount*6);
				eligibleValue[4]=(premiumAmount*12);
			}
			else {
				
				String dob=p.getDob();
				
				SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
				Date date = null;
				try {
					date = formatter.parse(dob);
				} catch (ParseException e) {
					e.printStackTrace();
				}
				// Converting obtained Date object to LocalDate object
				Instant instant = date.toInstant();
				ZonedDateTime zone = instant.atZone(ZoneId.systemDefault());
				LocalDate givenDate = zone.toLocalDate();
				// Calculating the difference between given date to current date.
				Period period = Period.between(givenDate, LocalDate.now());
				int age=period.getYears();
				
				int rate=age-18;
	
					eligibleValue[0]=0;//0 true and 1 false
					eligibleValue[1]=Long.parseLong(p.getAdharNo());
					int b=(premiumAmount/100*2)*rate;
					eligibleValue[2]=(premiumAmount+b);
					eligibleValue[3]=((premiumAmount+b)*6);
					eligibleValue[4]=((premiumAmount+b)*12);
			}
		}

		return eligibleValue;
	}

	@Override
	public PersonEntity addPerson(PersonEntity p) {
		logger.info("Person Data adding in database");
		return dao.saveAndFlush(p);
	}
	
	@Override
	public PersonEntity updatePerson(PersonEntity p) {
		logger.info("Person Data updating in database");
		return dao.saveAndFlush(p);
	}

	@Override
	public List<PersonEntity> removePerson(int id) {
		logger.info("Person Data Deleted successfull");
		dao.deleteById(id);	
		return dao.findAll();
	}

	@Override
	public List<PersonEntity> listAllPerson() {
		logger.info("List all person"); 
		return dao.findAll();
	}

}
