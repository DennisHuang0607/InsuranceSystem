package com.project01.repository;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.project01.entity.InsuranceCompany;
import com.project01.insurancesystem.InsuranceSystemApplication;

@SpringBootTest(classes = InsuranceSystemApplication.class)
public class InsuranceCompanyRepositoryTests {

	@Autowired
	private InsuranceCompanyRepository insuranceCompanyRepository;
	
	@Test
	public void testSave() {
		InsuranceCompany insuranceCompany = new InsuranceCompany();
		insuranceCompany.setCompanyName("台中人壽");
		insuranceCompany.setAddress("台中市北區民權路85號");
		insuranceCompany.setTelephone("04-26287459");
		
		InsuranceCompany newInsuranceCompany = insuranceCompanyRepository.save(insuranceCompany);
		System.out.println("新增保險公司: " + insuranceCompany.getCompanyName() + " 成功");
		
		Assertions.assertEquals("台中人壽",newInsuranceCompany.getCompanyName());
	}
	
	
	public void testFindAll() {
		List<InsuranceCompany> result = insuranceCompanyRepository.findAll();
		System.out.println("查詢所有保險公司成功");
		
		Assertions.assertEquals(1, result.size());
	}
	
	
	public void testExistsByCompanyId() {
		boolean result = insuranceCompanyRepository.existsByCompanyId(1);
		System.out.println("保險公司存在");
		
		Assertions.assertEquals(true, result);
	}
	
	
}
