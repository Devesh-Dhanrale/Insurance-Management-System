package com.cg.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cg.entities.Policy;

@Repository("admin")
public interface AdminDao extends JpaRepository<Policy, Integer>{

}
