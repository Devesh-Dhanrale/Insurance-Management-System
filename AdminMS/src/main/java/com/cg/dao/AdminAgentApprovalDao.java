package com.cg.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cg.entities.AdminAgentApprovalEntity;

@Repository("adminAgent")
public interface AdminAgentApprovalDao extends JpaRepository<AdminAgentApprovalEntity, Integer>{

}
