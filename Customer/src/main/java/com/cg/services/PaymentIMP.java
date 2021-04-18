package com.cg.services;

import java.util.List;

import com.cg.entity.PaymentEntity;

public interface PaymentIMP {

	public List<PaymentEntity> getDetailsOfPaymentHistory(String adharNo);
	
}
