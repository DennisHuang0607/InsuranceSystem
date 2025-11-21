package com.project01.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.project01.dto.ResponseDTO;
import com.project01.entity.InsuranceCompany;
import com.project01.entity.InsuranceType;
import com.project01.entity.Message;
import com.project01.repository.InsuranceCompanyRepository;

import jakarta.transaction.Transactional;

@Service
public class InsuranceCompanyService {

//    private final InsurerDetailsService insurerDetailsService;

	@Autowired
	private InsuranceCompanyRepository insuranceCompanyRepository;

//    InsuranceCompanyService(InsurerDetailsService insurerDetailsService) {
//        this.insurerDetailsService = insurerDetailsService;
//    }
	
	//新增保險公司
	public ResponseEntity<ResponseDTO<InsuranceCompany>> registerInsuranceCompany(InsuranceCompany company){
		
		try {
			if(insuranceCompanyRepository.existsByCompanyName(company.getCompanyName())) {
				ResponseDTO<InsuranceCompany> response = new ResponseDTO<>(409,"保險公司已存在，禁止重複新增",null);
				return ResponseEntity.status(409).body(response);
			}
			else {
				InsuranceCompany newCompany = insuranceCompanyRepository.save(company);
				ResponseDTO<InsuranceCompany> response = new ResponseDTO<>(200,"保險公司新增成功",newCompany);
				return ResponseEntity.ok(response);
			}
		}
		catch(Exception e){
			ResponseDTO<InsuranceCompany> response = new ResponseDTO<>(500,"保險公司新增失敗，系統異常了",null);
			return ResponseEntity.status(500).body(response);
		}
	}
	
	//查詢保險公司
	public ResponseEntity<ResponseDTO<List<InsuranceCompany>>> findAllInsuranceCompany(){
		
		try {
			List<InsuranceCompany> result = insuranceCompanyRepository.findAll();
			ResponseDTO<List<InsuranceCompany>> response = new ResponseDTO<>(200,"保險公司查詢成功",result);
			return ResponseEntity.ok(response);
		}
		catch (Exception e) {
			ResponseDTO<List<InsuranceCompany>> response = new ResponseDTO<>(500,"保險公司查詢失敗，系統異常了",null);
			return ResponseEntity.status(500).body(response);
		}
	}
	
	//更新保險公司
	public ResponseEntity<ResponseDTO<InsuranceCompany>> updateInsuranceCompany(InsuranceCompany newCompany){
		
		try {
			InsuranceCompany existCompany = insuranceCompanyRepository.findByCompanyName(newCompany.getCompanyName());
			
			if(!insuranceCompanyRepository.existsByCompanyId(newCompany.getCompanyId())) {
				ResponseDTO<InsuranceCompany> response = new ResponseDTO<>(404,"資料更新失敗，保險公司可能不存在",null);
				return ResponseEntity.status(404).body(response);
			}
			else if(existCompany != null && !Integer.toString(existCompany.getCompanyId()).equals(Integer.toString(newCompany.getCompanyId()))) {
				ResponseDTO<InsuranceCompany> response = new ResponseDTO<>(409,"資料更新失敗，保險公司名稱已存在",null);
				return ResponseEntity.status(409).body(response);
			}
			else {
				InsuranceCompany company = insuranceCompanyRepository.save(newCompany);
				ResponseDTO<InsuranceCompany> response = new ResponseDTO<>(200,"保險公司更新成功",newCompany);
				return ResponseEntity.ok(response);
			}
		}
		catch (Exception e) {
			ResponseDTO<InsuranceCompany> response = new ResponseDTO<>(500,"保險公司更新失敗，系統異常了",null);
			return ResponseEntity.status(500).body(response);
		}
	}
	
	//刪除保險公司
	@Transactional
	public ResponseEntity<ResponseDTO<InsuranceCompany>> deleteInsuranceCompany(int companyId){

		try {
			if(insuranceCompanyRepository.existsByCompanyId(companyId)) {
				insuranceCompanyRepository.deleteByCompanyId(companyId);
				ResponseDTO<InsuranceCompany> response = new ResponseDTO<>(200,"保險公司刪除成功",null);
				return ResponseEntity.ok(response);
			}
			else {
				ResponseDTO<InsuranceCompany> response = new ResponseDTO<>(404,"保險公司刪除失敗，可能不存在",null);
				return ResponseEntity.status(404).body(response);
			}
		}
		catch(Exception e){
			ResponseDTO<InsuranceCompany> response = new ResponseDTO<>(500,"保險公司刪除失敗，系統異常了",null);
			return ResponseEntity.status(500).body(response);
		}
	}
	
	
}
