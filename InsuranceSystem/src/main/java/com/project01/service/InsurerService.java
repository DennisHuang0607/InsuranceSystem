package com.project01.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.project01.dto.ResponseDTO;
import com.project01.entity.Insurer;
import com.project01.repository.InsurerRepository;

@Service
public class InsurerService {
	private static final Logger logger = LoggerFactory.getLogger(InsurerService.class);

	@Autowired
	private InsurerRepository insurerRepository;
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	//新增Insurer
	@Transactional(rollbackFor = Exception.class)
	public ResponseEntity<ResponseDTO<Insurer>> registerInsurer(Insurer insurer) {
		
		try {
			if(insurerRepository.existsByAccountId(insurer.getAccountId())) {
				logger.warn("保險員新增失敗，帳號已存在，新增帳號:{}",insurer.getAccountId());
				ResponseDTO<Insurer> response = new ResponseDTO<>(409,"帳號已存在，請換一個試試",null);
				return ResponseEntity.status(409).body(response);
			}
			else {
				String rawPasswword = insurer.getPassword();
				String encodePassword = passwordEncoder.encode(rawPasswword);
				insurer.setPassword(encodePassword);
				Insurer newInsurer = insurerRepository.save(insurer);
				logger.info("保險員新增成功，帳號:{}",insurer.getAccountId());
				ResponseDTO<Insurer> response = new ResponseDTO<>(200,"保險員新增成功",newInsurer);
				return ResponseEntity.ok(response);
			}
		}
		catch (Exception e){
			logger.error("保險員新增失敗，系統發生未預期異常:",e);
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			ResponseDTO<Insurer> response = new ResponseDTO<>(500,"保險員新增失敗，系統異常了",null);
			return ResponseEntity.status(500).body(response);
		}
	}
	
	//查詢全部Insurer
	@Transactional(readOnly = true)
	public ResponseEntity<ResponseDTO<List<Insurer>>> findAllInsurer(){

		try {
			List<Insurer> result = insurerRepository.findAll();
			logger.info("查詢全部保險員成功，共{}筆",result.size());
			ResponseDTO<List<Insurer>> response = new ResponseDTO<>(200,"保險員資料查詢成功",result);
			return ResponseEntity.ok(response);
		}
		catch(Exception e){
			logger.error("查詢全部保險員失敗，後端發生異常:",e);
			ResponseDTO<List<Insurer>> response = new ResponseDTO<>(500,"保險員資料查詢失敗，資料庫可能連線異常",null);
			return ResponseEntity.status(500).body(response);
		}
	}
	
	//查詢單一Insurer
	@Transactional(readOnly = true)
	public ResponseEntity<ResponseDTO<Optional<Insurer>>> findInsurerByAccountId(String accountId){

		try {
			Optional<Insurer> insurer = insurerRepository.findByAccountId(accountId);
			if(insurer.isPresent()) {
				logger.info("保險員查詢成功，帳號{}",accountId);
				ResponseDTO<Optional<Insurer>> response = new ResponseDTO<>(200,"保險員查詢成功",insurer);
				return ResponseEntity.ok(response);
			}
			else {
				logger.warn("保險員查無資料:{}",accountId);
				ResponseDTO<Optional<Insurer>> response = new ResponseDTO<>(404,"保險員查詢失敗",null);
				return ResponseEntity.status(404).body(response);
			}
		}
		catch(Exception e){
			logger.error("保險員查詢失敗，後端發生異常:",e);
			ResponseDTO<Optional<Insurer>> response = new ResponseDTO<>(500,"保險員查詢失敗，資料庫可能連線異常",null);
			return ResponseEntity.status(500).body(response);
		}		
	}
	
	//更新Insurer
	@Transactional(rollbackFor = Exception.class)
	public ResponseEntity<ResponseDTO<Insurer>> updateInsurer(Insurer updateInsurer){

		try {
			Optional<Insurer> findInsurer = insurerRepository.findByAccountId(updateInsurer.getAccountId());
			if(findInsurer.isPresent()) {
				Insurer insurer = findInsurer.get();
				insurer.setName(updateInsurer.getName());
				insurer.setPassword(passwordEncoder.encode(updateInsurer.getPassword()));
				insurer.setPhone(updateInsurer.getPhone());
				insurer.setEmail(updateInsurer.getEmail());
				
				Insurer newInsurer = insurerRepository.save(insurer);
				logger.info("保險員資料更新成功，帳號{}",updateInsurer.getAccountId());
				ResponseDTO<Insurer> response = new ResponseDTO<>(200,"保險員資料更新成功",newInsurer);
				return ResponseEntity.ok(response);
			}
			else {
				logger.warn("保險員更新失敗，帳號不存在:{}",updateInsurer.getAccountId());
				ResponseDTO<Insurer> response = new ResponseDTO<>(404,"保險員資料更新失敗",null);
				return ResponseEntity.status(404).body(response);
			}
		}
		catch(Exception e){
			logger.error("保險員資料更新失敗，後端發生異常:",e);
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			ResponseDTO<Insurer> response = new ResponseDTO<>(500,"保險員資料更新失敗，資料庫可能連線異常",null);
			return ResponseEntity.status(500).body(response);
		}
	}
	
	//刪除Insurer
	@Transactional(rollbackFor = Exception.class)
	public ResponseEntity<ResponseDTO<Insurer>> deleteInsurerByAccountId(String accountId) {

		try {
			boolean result = insurerRepository.existsByAccountId(accountId);
			if(result) {
				insurerRepository.deleteByAccountId(accountId);
				logger.info("保險員刪除成功，帳號{}",accountId);
				ResponseDTO<Insurer> response = new ResponseDTO<>(200,"刪除保險員成功",null);
				return ResponseEntity.ok(response);
			}
			else {
				logger.warn("保險員刪除失敗，帳號可能不存在，帳號{}",accountId);
				ResponseDTO<Insurer> response = new ResponseDTO<>(404,"保險員資料刪除失敗，帳號可能不存在",null);
				return ResponseEntity.status(404).body(response);
			}
		}
		catch(Exception e){
			logger.error("保險員資料刪除失敗，後端可能異常",e);
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			ResponseDTO<Insurer> response = new ResponseDTO<>(500,"保險員資料刪除失敗，資料庫可能連線異常",null);
			return ResponseEntity.status(500).body(response);
		}
	}
	
	//查詢當前登入的Insurer
	@Transactional(readOnly = true)
	public ResponseEntity<ResponseDTO<Insurer>> getCurrentInsurer(Authentication authentication){
		String account = authentication.getName();
		Optional<Insurer> findInsurer = insurerRepository.findByAccountId(account);
		if(findInsurer.isPresent()) {
			logger.info("已查到當前登入的保險員，帳號{}",account);
			ResponseDTO<Insurer> response = new ResponseDTO<>(200,"已查到當前登入的保險員",findInsurer.get());
			return ResponseEntity.ok(response);
		}
		else {
			logger.error("嚴重警告：持有合法Token但資料庫無此帳號:{}",account);
			ResponseDTO<Insurer> response = new ResponseDTO<>(404,"當前未有保險員登入或帳號可能不存在",null);
			return ResponseEntity.status(404).body(response);
		}
	}

	
}
