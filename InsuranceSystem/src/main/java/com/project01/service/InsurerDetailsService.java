package com.project01.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.project01.entity.Insurer;
import com.project01.repository.InsurerRepository;

@Service
public class InsurerDetailsService implements UserDetailsService{

	@Autowired
	private InsurerRepository insurerRepository;
	
	@Override
	public UserDetails loadUserByUsername(String account) throws UsernameNotFoundException {
		
		Insurer insurer = insurerRepository.findByAccountId(account)
				.orElseThrow(() -> new UsernameNotFoundException("找不到保險員: " + account));
		
		
		return org.springframework.security.core.userdetails.User.builder()
					.username(insurer.getAccountId())
					.password(insurer.getPassword())
					.roles("USER")
					.build();

		
	}
	
}
