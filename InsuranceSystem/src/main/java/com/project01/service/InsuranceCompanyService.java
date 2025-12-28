package com.project01.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.project01.dto.ResponseDTO;
import com.project01.entity.InsuranceCompany;
import com.project01.repository.InsuranceCompanyRepository;

@Service
public class InsuranceCompanyService {

	private static final Logger logger = LoggerFactory.getLogger(InsuranceCompanyService.class); 
	@Autowired
	private InsuranceCompanyRepository insuranceCompanyRepository;
	
	//新增保險公司
	@Transactional(rollbackFor = Exception.class)
	public ResponseEntity<ResponseDTO<InsuranceCompany>> registerInsuranceCompany(InsuranceCompany company){
		
		try {
			if(insuranceCompanyRepository.existsByCompanyName(company.getCompanyName())) {
				logger.warn("保險公司:{} 已存在，禁止重複新增",company.getCompanyName());
				ResponseDTO<InsuranceCompany> response = new ResponseDTO<>(409,"保險公司已存在，禁止重複新增",null);
				return ResponseEntity.status(409).body(response);
			}
			else {
				InsuranceCompany newCompany = insuranceCompanyRepository.save(company);
				logger.info("保險公司:{} 新增成功",newCompany.getCompanyName());
				ResponseDTO<InsuranceCompany> response = new ResponseDTO<>(200,"保險公司新增成功",newCompany);
				return ResponseEntity.ok(response);
			}
		}
		catch(Exception e){
			logger.error("保險公司新增失敗，後端發生異常:",e);
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			ResponseDTO<InsuranceCompany> response = new ResponseDTO<>(500,"保險公司新增失敗，系統異常了",null);
			return ResponseEntity.status(500).body(response);
		}
	}
	
	//查詢保險公司
	@Transactional(readOnly = true)
	public ResponseEntity<ResponseDTO<List<InsuranceCompany>>> findAllInsuranceCompany(){
		
		try {
			List<InsuranceCompany> result = insuranceCompanyRepository.findAll();
			logger.info("查詢所有保險公司成功，共{}筆",result.size());
			ResponseDTO<List<InsuranceCompany>> response = new ResponseDTO<>(200,"保險公司查詢成功",result);
			return ResponseEntity.ok(response);
		}
		catch (Exception e) {
			logger.error("查詢所有保險公司失敗，後端發生異常:",e);
			ResponseDTO<List<InsuranceCompany>> response = new ResponseDTO<>(500,"保險公司查詢失敗，系統異常了",null);
			return ResponseEntity.status(500).body(response);
		}
	}
	
	//更新保險公司
	@Transactional(rollbackFor = Exception.class)
	public ResponseEntity<ResponseDTO<InsuranceCompany>> updateInsuranceCompany(InsuranceCompany newCompany){
		
		try {
			InsuranceCompany existCompany = insuranceCompanyRepository.findByCompanyName(newCompany.getCompanyName());
			
			if(!insuranceCompanyRepository.existsByCompanyId(newCompany.getCompanyId())) {
				logger.warn("保險公司:{} 不存在，資料更新失敗",newCompany.getCompanyName());
				ResponseDTO<InsuranceCompany> response = new ResponseDTO<>(404,"資料更新失敗，保險公司可能不存在",null);
				return ResponseEntity.status(404).body(response);
			}
			else if(existCompany != null && existCompany.getCompanyId() != newCompany.getCompanyId()) {
				logger.warn("保險公司:{} 更新失敗，名稱已存在",newCompany.getCompanyName());
				ResponseDTO<InsuranceCompany> response = new ResponseDTO<>(409,"資料更新失敗，保險公司名稱已存在",null);
				return ResponseEntity.status(409).body(response);
			}
			else {
				InsuranceCompany company = insuranceCompanyRepository.save(newCompany);
				logger.info("保險公司:{} 更新成功",company.getCompanyName());
				ResponseDTO<InsuranceCompany> response = new ResponseDTO<>(200,"保險公司更新成功",newCompany);
				return ResponseEntity.ok(response);
			}
		}
		catch (Exception e) {
			logger.error("保險公司更新失敗，後端發生異常:",e);
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			ResponseDTO<InsuranceCompany> response = new ResponseDTO<>(500,"保險公司更新失敗，系統異常了",null);
			return ResponseEntity.status(500).body(response);
		}
	}
	
	//刪除保險公司
	@Transactional(rollbackFor = Exception.class)
	public ResponseEntity<ResponseDTO<InsuranceCompany>> deleteInsuranceCompany(long companyId){

		try {
			if(insuranceCompanyRepository.existsByCompanyId(companyId)) {
				insuranceCompanyRepository.deleteByCompanyId(companyId);
				logger.info("保險公司ID:{} 刪除成功",companyId);
				ResponseDTO<InsuranceCompany> response = new ResponseDTO<>(200,"保險公司刪除成功",null);
				return ResponseEntity.ok(response);
			}
			else {
				logger.warn("保險公司ID:{} 不存在，刪除失敗",companyId);
				ResponseDTO<InsuranceCompany> response = new ResponseDTO<>(404,"保險公司刪除失敗，可能不存在",null);
				return ResponseEntity.status(404).body(response);
			}
		}
		catch(Exception e){
			logger.error("保險公司刪除失敗，後端發生異常:",e);
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			ResponseDTO<InsuranceCompany> response = new ResponseDTO<>(500,"保險公司刪除失敗，系統異常了",null);
			return ResponseEntity.status(500).body(response);
		}
	}
	
	
}
