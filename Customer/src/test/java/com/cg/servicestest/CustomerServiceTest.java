package com.cg.servicestest;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.cg.dao.PaymentDAO;
import com.cg.entity.PaymentEntity;
import com.cg.services.PaymentServicesIMP;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
class CustomerServiceTest {

	@Autowired
	PaymentServicesIMP pImp;
	
	@MockBean
	PaymentDAO pDao;
	
	@Test
	void getDetailsOfPaymentHistory() {
		PaymentEntity payment1 = new PaymentEntity();
		payment1.setAdharNo("123456789123");
		payment1.setFullName("Sahil Lotlikar");
		payment1.setPolicyName("Term Insurance With Disease");
		payment1.setAmount(420);
		payment1.setDate("18-04-2021");
		payment1.setEligibilityStatus("Eligible");
		payment1.setPaymentStatus("Successfull");
		
		PaymentEntity payment2 = new PaymentEntity();
		payment2.setAdharNo("123456789123");
		payment2.setFullName("Sahil Lotlikar");
		payment2.setPolicyName("Term Insurance With Disease");
		payment2.setAmount(440);
		payment2.setDate("18-03-2021");
		payment2.setEligibilityStatus("Eligible");
		payment2.setPaymentStatus("Successfull");
		
		List<PaymentEntity> paymentList = new ArrayList<>();
		paymentList.add(payment1);
		paymentList.add(payment2);

		Mockito.when(pDao.getDetailsOfPaymentHistory("123456789123")).thenReturn(paymentList);

		assertThat(pImp.getDetailsOfPaymentHistory("123456789123")).isEqualTo(paymentList);
	}
}
