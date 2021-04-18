package com.cg.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cg.entity.PersonEntity;

@Repository
public interface PersonDAO extends JpaRepository<PersonEntity,Integer> {

}
