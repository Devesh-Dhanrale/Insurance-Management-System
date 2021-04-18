package com.cg.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cg.entity.PersonEligibilityEntity;

@Repository
public interface PersonEligibilityDAO extends JpaRepository<PersonEligibilityEntity, Integer> {

}
