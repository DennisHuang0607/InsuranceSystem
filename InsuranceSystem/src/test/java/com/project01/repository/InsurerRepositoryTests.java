package com.project01.repository;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.project01.entity.Insurer;
import com.project01.insurancesystem.InsuranceSystemApplication;

@SpringBootTest(classes = InsuranceSystemApplication.class)
public class InsurerRepositoryTests {
	
	@Autowired
	private InsurerRepository insurerRepository;
	
	
	public void testSave() {
		Insurer insurer = new Insurer();
		insurer.setName("楊凱卉");
		insurer.setAccountId("kelly");
		insurer.setPassword("123");
		insurer.setPhone("0912547895");
		insurer.setEmail("kelly@gmail.com");
		
		Insurer newInsurer = insurerRepository.save(insurer);
		System.out.println("新增帳號：" + newInsurer.getAccountId());
		
		Assertions.assertEquals("kelly",newInsurer.getAccountId());
	}
	
	
	public void testFindAll() {
		List<Insurer> result = insurerRepository.findAll();
		System.out.println("查詢成功，共 " + result.size() + " 筆資料");
		
		Assertions.assertEquals(2,result.size());
		
	}
	
	
	public void testFindByAccountId() {
		String accountid = "kelly";
		
		Optional<Insurer> insurer = insurerRepository.findByAccountId(accountid);
		System.out.println("找到帳號：" + insurer.get());
		
		Assertions.assertEquals(true,insurer.isPresent());
	}
	
	@Test
	public void testExistsById() {
		String accountid = "kelly";
		
		boolean result = insurerRepository.existsById(accountid);
		System.out.println("查詢成功，帳號存在： " + result);
		
		Assertions.assertEquals(true,result);
	}
	
	
}
