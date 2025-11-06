package com.project01.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.project01.entity.Insurer;
import com.project01.entity.Message;
import com.project01.repository.InsurerRepository;

import jakarta.transaction.Transactional;

@Service
public class InsurerService {

	@Autowired
	private InsurerRepository insurerRepository;
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	//新增Insurer
	public ResponseEntity<Message> registerInsurer(Insurer insurer) {
		
		ResponseEntity<Message> response = null;
		Message message = new Message();
		
		try {
			if(insurerRepository.existsByAccountId(insurer.getAccountId())) {
				message.setCode(409);
				message.setMessage("帳號已存在，請換一個試試");
				return ResponseEntity.badRequest().body(message);
			}
			else {
				String rawPasswword = insurer.getPassword();
				String encodePassword = passwordEncoder.encode(rawPasswword);
				insurer.setPassword(encodePassword);
				Insurer newInsurer = insurerRepository.save(insurer);
				message.setCode(200);
				message.setMessage("保險員新增成功");
				response = ResponseEntity.ok(message);
			}
		}
		catch (Exception e){
			message.setCode(500);
			message.setMessage("管理員新增失敗，系統異常了");
			response = ResponseEntity.status(500).body(message);
		}

		return response;
	}
	
	//查詢全部Insurer
	public ResponseEntity<Message> findAllInsurer(){
		
		ResponseEntity<Message> response = null;
		Message message = new Message();
		
		try {
			List<Insurer> result = insurerRepository.findAll();
			message.setCode(200);
			message.setMessage("保險員資料查詢成功");
			response = ResponseEntity.ok(message);
		}
		catch(Exception e){
			message.setCode(500);
			message.setMessage("保險員資料查詢失敗，資料庫可能連線異常");
			response = ResponseEntity.status(500).body(message);
		}
		
		return response;
		
	}
	
	//查詢單一Insurer
	public ResponseEntity<Message> findInsurerByAccountId(String accountId){
		
		ResponseEntity<Message> response = null;
		Message message = new Message();

		try {
			Optional<Insurer> insurer = insurerRepository.findByAccountId(accountId);
			if(insurer.isPresent()) {
				message.setCode(200);
				message.setMessage("查到保險員： " + accountId);
				response = ResponseEntity.ok(message);
			}
			else {
				message.setCode(200);
				message.setMessage("查無保險員: " + accountId);
			}
		}
		catch(Exception e){
			message.setCode(500);
			message.setMessage("保險員資料查詢失敗，資料庫可能連線異常");
			response = ResponseEntity.status(500).body(message);
		}
		
		return response;
		
	}
	
	//更新Insurer
	public ResponseEntity<Message> updateInsurer(Insurer updateInsurer){
		
		ResponseEntity<Message> response = null;
		Message message = new Message();
		
		try {
			Optional<Insurer> findInsurer = insurerRepository.findByAccountId(updateInsurer.getAccountId());
			if(findInsurer.isPresent()) {
				Insurer insurer = findInsurer.get();
				insurer.setName(updateInsurer.getName());
				insurer.setPassword(updateInsurer.getPassword());
				insurer.setPhone(updateInsurer.getPhone());
				insurer.setEmail(updateInsurer.getEmail());
				
				Insurer newInsurer = insurerRepository.save(insurer);
				message.setCode(200);
				message.setMessage("保險員資料更新成功");
				response = ResponseEntity.ok(message);
			}
			else {
				message.setCode(404);
				message.setMessage("保險員資料更新失敗，帳號可能不存在");
				response = ResponseEntity.status(404).body(message);
			}
		}
		catch(Exception e){
			message.setCode(500);
			message.setMessage("保險員資料更新失敗，資料庫可能連線異常");
			response = ResponseEntity.status(500).body(message);
		}
		
		return response;
		
	}
	
	//刪除Insurer
	@Transactional
	public ResponseEntity<Message> deleteInsurerByAccountId(String accountId) {
		
		ResponseEntity<Message> response = null;
		Message message = new Message();
		
		try {
			boolean result = insurerRepository.existsByAccountId(accountId);
			if(result) {
				insurerRepository.deleteByAccountId(accountId);
				message.setCode(200);
				message.setMessage("刪除保險員成功");
				response = ResponseEntity.ok(message);
			}
			else {
				message.setCode(404);
				message.setMessage("保險員資料刪除失敗，帳號可能不存在");
				response = ResponseEntity.status(404).body(message);
			}
		}
		catch(Exception e){
			message.setCode(500);
			message.setMessage("保險員資料刪除失敗，資料庫可能連線異常" + e);
			response = ResponseEntity.status(500).body(message);
		}
		
		return response;
	}

	
}
