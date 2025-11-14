//package com.project01.service;
//
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.http.ResponseEntity;
//
//import com.project01.entity.Insurer;
//import com.project01.entity.Message;
//import com.project01.insurancesystem.InsuranceSystemApplication;
//
//import jakarta.transaction.Transactional;
//
//@SpringBootTest(classes = InsuranceSystemApplication.class)
//public class InsurerServiceTests {
//	
//	@Autowired
//	private InsurerService insurerService;
//	
//	@Test
//	public void testRegisterInsurer() {
//		Insurer insurer = new Insurer();
//		insurer.setName("周傑輪");
//		insurer.setAccountId("circle");
//		insurer.setPassword("123");
//		insurer.setPhone("0985214785");
//		insurer.setEmail("circle@gmail.com");
//		
//		ResponseEntity<Message> response = insurerService.registerInsurer(insurer);
//		System.out.println(response.getBody().getMessage());
//		
//		Assertions.assertEquals(200, response.getBody().getCode());
//	}
//	
//	
//	public void testFindAllInsurer() {
//		ResponseEntity<Message> response = insurerService.findAllInsurer();
//		System.out.println(response.getBody().getMessage());
//		
//		Assertions.assertEquals(200, response.getBody().getCode());
//	}
//	
//	
//	public void testFindInsurerByAccountId() {
//		String accountId = "lawson";
//		ResponseEntity<Message> response = insurerService.findInsurerByAccountId(accountId);
//		System.out.println(response.getBody().getMessage());
//		
//		Assertions.assertEquals(200, response.getBody().getCode());
//	}
//	
//	
//	public void testUpdateInsurer() {
//		Insurer insurer = new Insurer();
//		insurer.setName("羅智翔");
//		insurer.setAccountId("circle");
//		insurer.setPassword("123");
//		insurer.setPhone("0985214785");
//		insurer.setEmail("circle@gmail.com");
//		
//		ResponseEntity<Message> response = insurerService.updateInsurer(insurer);
//		System.out.println(response.getBody().getMessage());
//		
//		Assertions.assertEquals(200, response.getBody().getCode());
//	}
//	
//	
//	public void testDeleteInsurerByAccountId() {
//		String accountId = "circle";
//		ResponseEntity<Message> response = insurerService.deleteInsurerByAccountId(accountId);
//		System.out.println(response.getBody().getMessage());
//		
//		Assertions.assertEquals(200, response.getBody().getCode());
//	}
//	
//}
