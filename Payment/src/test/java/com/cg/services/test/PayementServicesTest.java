package com.cg.services.test;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.cg.dao.CustomerCredentialsDAO;
import com.cg.dao.PaymentDAO;
import com.cg.entity.CustomerCredentialsEntity;
import com.cg.entity.PaymentEntity;
import com.cg.services.CustomerCredentialsServicesIMP;
import com.cg.services.PaymentServicesIMP;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
class PayementServicesTest {

	@Autowired
	PaymentServicesIMP paymentImp;
	
	@Autowired
	CustomerCredentialsServicesIMP custImp;
	
	@MockBean
	PaymentDAO pDao;
	
	@MockBean
	CustomerCredentialsDAO cDao;
	
	@Test
	void addPaymentEntityTest() {
		PaymentEntity payment = new PaymentEntity();
		payment.setAdharNo("123456789123");
		payment.setFullName("Sahil Lotlikar");
		payment.setPolicyName("Term Insurance With Disease");
		payment.setAmount(420);
		payment.setDate("18-04-2021");
		payment.setEligibilityStatus("Eligible");
		payment.setPaymentStatus("Successfull");
		

		Mockito.when(pDao.saveAndFlush(payment)).thenReturn(payment);

		assertThat(paymentImp.addPaymentEntity(payment)).isEqualTo(payment);
	}
	
	@Test
	void addCustomerCredentialsEntityTest() {
		CustomerCredentialsEntity customer = new CustomerCredentialsEntity();
		customer.setAdharNo("123456789123");
		customer.setLogin(123);
		customer.setPassword("jhkt");

		Mockito.when(cDao.saveAndFlush(customer)).thenReturn(customer);

		assertThat(custImp.addCustomerCredentials(customer)).isEqualTo(customer);
	}

}
