package com.project01.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import com.project01.entity.Admin;
import com.project01.entity.Message;
import com.project01.insurancesystem.InsuranceSystemApplication;

@SpringBootTest(classes = InsuranceSystemApplication.class)
public class AdminServiceTests {
	
	@Autowired
	private AdminService adminService;
	
	
	public void testRegisterAdmin() {
		Admin admin = new Admin();
		admin.setAccountId("test03");
		admin.setPassword("123");
		ResponseEntity<Message> response = adminService.registerAdmin(admin);
		System.out.println("Service回傳：" + response.getBody().getMessage());
		
		Assertions.assertEquals(200, response.getBody().getCode());
	}
	
	
	public void testFindAllAdmin() {
		ResponseEntity<Message> response = adminService.findAllAdmin();
		System.out.println("Service回傳：" + response.getBody().getMessage());
		
		Assertions.assertEquals(200, response.getBody().getCode());
	}
	
	
	public void testUpadteAdmin() {
		Admin admin = new Admin();
		admin.setName("測試");
		admin.setAccountId("test");
		admin.setPassword("123");
		admin.setEmail("test@gmail.com");
		ResponseEntity<Message> response = adminService.upadteAdmin(admin);
		System.out.println("Service回傳：" + response.getBody().getMessage());
		
		Assertions.assertEquals(200, response.getBody().getCode());
	}
	
	@Test
	public void testDeleteAdminById() {
		String account = "test";
		ResponseEntity<Message> response = adminService.deleteAdminById(account);
		System.out.println("Service回傳：" + response.getBody().getMessage());
		
		Assertions.assertEquals(200, response.getBody().getCode());
	}
	
}
