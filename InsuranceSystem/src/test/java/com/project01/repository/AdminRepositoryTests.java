package com.project01.repository;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.project01.entity.Admin;
import com.project01.insurancesystem.InsuranceSystemApplication;

@SpringBootTest(classes = InsuranceSystemApplication.class)
public class AdminRepositoryTests {

	@Autowired
	private AdminRepository adminRepository;
	
	
	public void testRegister() {
		Admin admin = new Admin();
		admin.setAccountId("lawson");
		admin.setPassword("123");
		
		int row = adminRepository.register(admin);
		System.out.println("受影響的行數：" + row);
		
		Assertions.assertEquals(1, row);
	}
	
	
	public void testFindAll() {
		List<Admin> result = adminRepository.findAll();
		
		System.out.println("總筆數：" + result.size());
		
		Assertions.assertEquals(9, result.size());
	}
	
	
	public void testUpdate() {
		Admin admin = new Admin();
		admin.setName("123");
		admin.setAccountId("test");
		admin.setPassword("123");
		admin.setEmail("test@gmail.com");
		
		int row = adminRepository.update(admin);
		System.out.println("受影響的行數：" + row);
		
		Assertions.assertEquals(1, row);
	}
	
	@Test
	public void testDeleteById() {
		String account = "test";
		
		int row = adminRepository.deleteById(account);
		System.out.println("受影響的行數：" + row);
		
		Assertions.assertEquals(1, row);
	}
	
}
