package com.cg.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cg.entity.PaymentEntity;

public interface PaymentDAO extends JpaRepository<PaymentEntity, Integer> {

}
