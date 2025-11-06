package com.project01.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.project01.entity.InsuranceCompany;
import com.project01.entity.Message;
import com.project01.repository.InsuranceCompanyRepository;

import jakarta.transaction.Transactional;

@Service
public class InsuranceCompanyService {

    private final InsurerDetailsService insurerDetailsService;

	@Autowired
	private InsuranceCompanyRepository insuranceCompanyRepository;

    InsuranceCompanyService(InsurerDetailsService insurerDetailsService) {
        this.insurerDetailsService = insurerDetailsService;
    }
	
	//新增保險公司
	public ResponseEntity<Message> registerInsuranceCompany(InsuranceCompany company){
		
		ResponseEntity<Message> response = null;
		Message message = new Message();
		
		try {
			if(insuranceCompanyRepository.existsByCompanyName(company.getCompanyName())) {
				message.setCode(409);
				message.setMessage("保險公司已存在，禁止重複新增");
				response = ResponseEntity.badRequest().body(message);
			}
			else {
				InsuranceCompany newCompany = insuranceCompanyRepository.save(company);
				message.setCode(200);
				message.setMessage("保險公司新增成功");
				response = ResponseEntity.ok(message);
			}
		}
		catch(Exception e){
			message.setCode(500);
			message.setMessage("保險公司新增失敗，系統異常了");
			response = ResponseEntity.status(500).body(message);
		}
		return response;
	}
	
	//查詢保險公司
	public ResponseEntity<Message> findAllInsuranceCompany(){
		
		ResponseEntity<Message> response = null;
		Message message = new Message();
		
		try {
			List<InsuranceCompany> result = insuranceCompanyRepository.findAll();
			message.setCode(200);
			message.setMessage("保險公司查詢成功");
			response = ResponseEntity.ok(message);
		}
		catch (Exception e) {
			message.setCode(500);
			message.setMessage("保險公司查詢失敗，系統異常了");
			response = ResponseEntity.status(500).body(message);
		}
		return response;
	}
	
	//更新保險公司
	public ResponseEntity<Message> updateInsuranceCompany(InsuranceCompany newCompany){
		
		ResponseEntity<Message> response = null;
		Message message = new Message();
		
		try {
			InsuranceCompany existCompany = insuranceCompanyRepository.findByCompanyName(newCompany.getCompanyName());
			
			if(!insuranceCompanyRepository.existsByCompanyId(newCompany.getCompanyId())) {
				message.setCode(404);
				message.setMessage("資料更新失敗，保險公司可能不存在");
				response = ResponseEntity.status(404).body(message);
			}
			else if(existCompany != null && !Integer.toString(existCompany.getCompanyId()).equals(Integer.toString(newCompany.getCompanyId()))) {
				message.setCode(409);
	            message.setMessage("資料更新失敗，保險公司名稱已存在");
	            return ResponseEntity.status(409).body(message);
			}
			else {
				InsuranceCompany company = insuranceCompanyRepository.save(newCompany);
				message.setCode(200);
				message.setMessage("保險公司更新成功");
				response = ResponseEntity.ok(message);
			}
		}
		catch (Exception e) {
			message.setCode(500);
			message.setMessage("保險公司更新失敗，系統異常了");
			response = ResponseEntity.status(500).body(message);
		}
		return response;
	}
	
	//刪除保險公司
	@Transactional
	public ResponseEntity<Message> deleteInsuranceCompany(int companyId){
		
		ResponseEntity<Message> response = null;
		Message message = new Message();

		try {
			if(insuranceCompanyRepository.existsByCompanyId(companyId)) {
				insuranceCompanyRepository.deleteByCompanyId(companyId);
				message.setCode(200);
				message.setMessage("保險公司刪除成功");
				response = ResponseEntity.ok(message);
			}
			else {
				message.setCode(404);
				message.setMessage("保險公司刪除失敗，可能不存在");
				response = ResponseEntity.status(404).body(message);
			}
		}
		catch(Exception e){
			message.setCode(500);
			message.setMessage("保險公司刪除失敗，系統異常了");
			response = ResponseEntity.status(500).body(message);
		}
		return response;
	}
	
	
}
