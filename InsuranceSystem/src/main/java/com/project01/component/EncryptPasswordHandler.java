package com.project01.component;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.project01.entity.Admin;
import com.project01.entity.Insurer;
import com.project01.repository.AdminRepository;
import com.project01.repository.InsurerRepository;

//Spring Boot啟動時會自動執行CommandLineRunner
//@Component
public class EncryptPasswordHandler implements CommandLineRunner{
	
	@Autowired
	private AdminRepository adminRepository;
	@Autowired
	private InsurerRepository insurerRepository;
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	//Admin-修改已存在DB中的明文密碼
	public void encryptAllExistingPasswordsForAdmin() {
		List<Admin> admins = adminRepository.findAll();
		for(Admin admin : admins) {
			String rawPassword = admin.getPassword();
			//每次啟動都會執行，所以加一個判斷(只加密明文密碼)
			if(rawPassword.startsWith("$2a$") || rawPassword.startsWith("$2b$")) {
				System.out.println(admin.getAccountId() + " 已經加密，跳過");
				continue;
			}
			else {
				String encodedPassword = passwordEncoder.encode(rawPassword);
				admin.setPassword(encodedPassword);
				adminRepository.updatePassword(admin.getAccountId(),encodedPassword);
				System.out.println(admin.getAccountId() + " 密碼已經加密");
			}
		}
		System.out.println("所有管理員密碼已加密完成");
	}
	
	//Insurer-修改已存在DB中的明文密碼
	public void encryptAllExistingPasswordsForInsurer(){
		List<Insurer> insurers = insurerRepository.findAll();
		for(Insurer insurer : insurers) {
			String rawPassword = insurer.getPassword();
			if(rawPassword.startsWith("$2s$") || rawPassword.startsWith("$2b$")) {
				System.out.println(insurer.getAccountId() + " 已經加密，跳過");
				continue;
			}
			else {
				String encodePassword = passwordEncoder.encode(rawPassword);
				insurer.setPassword(encodePassword);
				Insurer newInsurer = insurerRepository.save(insurer);
				System.out.println(newInsurer.getAccountId() + " 密碼已經加密");
			}
		}
		System.out.println("所有保險員密碼已加密完成");
	}

	@Override
	public void run(String... args) throws Exception {
		encryptAllExistingPasswordsForAdmin();
		encryptAllExistingPasswordsForInsurer();
	}
}
