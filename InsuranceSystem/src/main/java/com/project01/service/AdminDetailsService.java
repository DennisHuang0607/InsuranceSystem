package com.project01.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.project01.entity.Admin;
import com.project01.repository.AdminRepository;

@Service
public class AdminDetailsService implements UserDetailsService{
	
	@Autowired
	private AdminRepository adminRepository;

	@Override
	public UserDetails loadUserByUsername(String account) throws UsernameNotFoundException {

		Admin admin = adminRepository.findById(account)
				.orElseThrow(() -> new UsernameNotFoundException("找不到管理員: " + account));
		
		return org.springframework.security.core.userdetails.User.builder()
				.username(admin.getAccountId())
				.password(admin.getPassword())
				.roles("ADMIN")
				.build();
	}
	
	
}
