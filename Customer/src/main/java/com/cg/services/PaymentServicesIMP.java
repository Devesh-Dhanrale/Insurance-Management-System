package com.cg.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.dao.PaymentDAO;
import com.cg.entity.PaymentEntity;

@Service
public class PaymentServicesIMP implements PaymentIMP {

	@Autowired
	PaymentDAO dao;
	
	
	@Override
	public List<PaymentEntity> getDetailsOfPaymentHistory(String adharNo) {
		dao.getDetailsOfPaymentHistory(adharNo);
		return dao.getDetailsOfPaymentHistory(adharNo);
	}
	

}
