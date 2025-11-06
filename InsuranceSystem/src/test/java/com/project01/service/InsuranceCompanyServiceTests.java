package com.project01.service;

import static org.mockito.ArgumentMatchers.intThat;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import com.project01.entity.InsuranceCompany;
import com.project01.entity.Message;
import com.project01.insurancesystem.InsuranceSystemApplication;

@SpringBootTest(classes = InsuranceSystemApplication.class)
public class InsuranceCompanyServiceTests {

	@Autowired
	private InsuranceCompanyService insuranceCompanyService;
	
	
	public void testRegisterInsuranceCompany() {
		InsuranceCompany company = new InsuranceCompany();
		company.setCompanyName("太陽保險");
		company.setAddress("台北松山區中山路2號");
		company.setTelephone("02-222578951");
		
		ResponseEntity<Message> result = insuranceCompanyService.registerInsuranceCompany(company);
		System.out.println(result.getBody().getMessage());
		
		Assertions.assertEquals(200,result.getBody().getCode());
	}
	
	
	public void testFindAllInsuranceCompany() {
		ResponseEntity<Message> result = insuranceCompanyService.findAllInsuranceCompany();
		System.out.println(result.getBody().getMessage());
		
		Assertions.assertEquals(200,result.getBody().getCode());
	}
	
	
	public void testUpdateInsuranceCompany() {
		InsuranceCompany updateCompany = new InsuranceCompany();
		updateCompany.setCompanyId(2);
		updateCompany.setCompanyName("太陽保險");
		updateCompany.setAddress("台北松山區中山路89號");
		updateCompany.setTelephone("02-22257895");
		
		ResponseEntity<Message> result = insuranceCompanyService.updateInsuranceCompany(updateCompany);
		System.out.println(result.getBody().getMessage());
		
		Assertions.assertEquals(200,result.getBody().getCode());
	}
	
	@Test
	public void testDeleteInsuranceCompany() {
		int companyId = 2;
		ResponseEntity<Message> result = insuranceCompanyService.deleteInsuranceCompany(companyId);
		System.out.println(result.getBody().getMessage());
		
		Assertions.assertEquals(200,result.getBody().getCode());
	}
	
}
