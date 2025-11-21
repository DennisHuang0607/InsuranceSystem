package com.project01.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.project01.dto.ResponseDTO;
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
	public ResponseEntity<ResponseDTO<Insurer>> registerInsurer(Insurer insurer) {
		
		try {
			if(insurerRepository.existsByAccountId(insurer.getAccountId())) {
				ResponseDTO<Insurer> response = new ResponseDTO<>(409,"帳號已存在，請換一個試試",null);
				return ResponseEntity.status(409).body(response);
			}
			else {
				String rawPasswword = insurer.getPassword();
				String encodePassword = passwordEncoder.encode(rawPasswword);
				insurer.setPassword(encodePassword);
				Insurer newInsurer = insurerRepository.save(insurer);
				ResponseDTO<Insurer> response = new ResponseDTO<>(200,"保險員新增成功",newInsurer);
				return ResponseEntity.ok(response);
			}
		}
		catch (Exception e){
			ResponseDTO<Insurer> response = new ResponseDTO<>(500,"管理員新增失敗，系統異常了",null);
			return ResponseEntity.status(500).body(response);
		}
	}
	
	//查詢全部Insurer
	public ResponseEntity<ResponseDTO<List<Insurer>>> findAllInsurer(){

		try {
			List<Insurer> result = insurerRepository.findAll();
			ResponseDTO<List<Insurer>> response = new ResponseDTO<>(200,"保險員資料查詢成功",result);
			return ResponseEntity.ok(response);
		}
		catch(Exception e){
			ResponseDTO<List<Insurer>> response = new ResponseDTO<>(500,"保險員資料查詢失敗，資料庫可能連線異常",null);
			return ResponseEntity.status(500).body(response);
		}
	}
	
	//查詢單一Insurer
	public ResponseEntity<ResponseDTO<Optional<Insurer>>> findInsurerByAccountId(String accountId){

		try {
			Optional<Insurer> insurer = insurerRepository.findByAccountId(accountId);
			if(insurer.isPresent()) {
				ResponseDTO<Optional<Insurer>> response = new ResponseDTO<>(200,"保險員查詢成功",insurer);
				return ResponseEntity.ok(response);
			}
			else {
				ResponseDTO<Optional<Insurer>> response = new ResponseDTO<>(404,"保險員查詢失敗",null);
				return ResponseEntity.status(404).body(response);
			}
		}
		catch(Exception e){
			ResponseDTO<Optional<Insurer>> response = new ResponseDTO<>(500,"保險員查詢失敗，資料庫可能連線異常",null);
			return ResponseEntity.status(500).body(response);
		}		
	}
	
	//更新Insurer
	public ResponseEntity<ResponseDTO<Insurer>> updateInsurer(Insurer updateInsurer){

		try {
			Optional<Insurer> findInsurer = insurerRepository.findByAccountId(updateInsurer.getAccountId());
			if(findInsurer.isPresent()) {
				Insurer insurer = findInsurer.get();
				insurer.setName(updateInsurer.getName());
				insurer.setPassword(updateInsurer.getPassword());
				insurer.setPhone(updateInsurer.getPhone());
				insurer.setEmail(updateInsurer.getEmail());
				
				Insurer newInsurer = insurerRepository.save(insurer);
				ResponseDTO<Insurer> response = new ResponseDTO<>(200,"保險員資料更新成功",newInsurer);
				return ResponseEntity.ok(response);
			}
			else {
				ResponseDTO<Insurer> response = new ResponseDTO<>(404,"保險員資料更新成功",null);
				return ResponseEntity.status(404).body(response);
			}
		}
		catch(Exception e){
			ResponseDTO<Insurer> response = new ResponseDTO<>(500,"保險員資料更新失敗，資料庫可能連線異常",null);
			return ResponseEntity.status(500).body(response);
		}
	}
	
	//刪除Insurer
	@Transactional
	public ResponseEntity<ResponseDTO<Insurer>> deleteInsurerByAccountId(String accountId) {

		try {
			boolean result = insurerRepository.existsByAccountId(accountId);
			if(result) {
				insurerRepository.deleteByAccountId(accountId);
				ResponseDTO<Insurer> response = new ResponseDTO<>(200,"刪除保險員成功",null);
				return ResponseEntity.ok(response);
			}
			else {
				ResponseDTO<Insurer> response = new ResponseDTO<>(404,"保險員資料刪除失敗，帳號可能不存在",null);
				return ResponseEntity.status(404).body(response);
			}
		}
		catch(Exception e){
			ResponseDTO<Insurer> response = new ResponseDTO<>(500,"保險員資料刪除失敗，資料庫可能連線異常",null);
			return ResponseEntity.status(500).body(response);
		}
	}
	
	//查詢當前登入的Insurer
	public ResponseEntity<ResponseDTO<Insurer>> getCurrentInsurer(Authentication authentication){
		String account = authentication.getName();
		Optional<Insurer> findInsurer = insurerRepository.findByAccountId(account);
		if(findInsurer.isPresent()) {
			ResponseDTO<Insurer> response = new ResponseDTO<>(200,"已查到當前登入的保險員",findInsurer.get());
			return ResponseEntity.ok(response);
		}
		else {
			ResponseDTO<Insurer> response = new ResponseDTO<>(404,"當前未有保險員登入或帳號可能不存在",null);
			return ResponseEntity.status(404).body(response);
		}
	}

	
}
