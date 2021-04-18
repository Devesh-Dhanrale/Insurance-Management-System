package com.cg.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.*;
import com.cg.entity.PaymentEntity;

@Repository
public interface PaymentDAO extends JpaRepository<PaymentEntity, Integer> {

	@Query("select u from payment_entity u where u.adharNo=?1")
	public List<PaymentEntity> getDetailsOfPaymentHistory(String adharNo);
	
}
