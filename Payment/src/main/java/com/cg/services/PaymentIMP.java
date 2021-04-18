package com.cg.services;

import com.cg.entity.PaymentEntity;

public interface PaymentIMP {

	public PaymentEntity addPaymentEntity(PaymentEntity p);
	
	public boolean isAvailable(String adharNo);
}
