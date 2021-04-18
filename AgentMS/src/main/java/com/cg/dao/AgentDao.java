package com.cg.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cg.entities.AgentEntity;

@Repository
public interface AgentDao extends JpaRepository<AgentEntity, String>{

}
