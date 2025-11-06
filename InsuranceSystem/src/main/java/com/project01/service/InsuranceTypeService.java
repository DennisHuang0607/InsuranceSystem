package com.project01.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.project01.entity.InsuranceType;
import com.project01.entity.Message;
import com.project01.repository.InsuranceTypeRepository;

import jakarta.transaction.Transactional;

@Service
public class InsuranceTypeService {
	
	@Autowired
	private InsuranceTypeRepository insuranceTypeRepository;
	
	//新增保單類型
	public ResponseEntity<Message> registerInsuranceType(InsuranceType type){
		
		ResponseEntity<Message> response = null;
		Message message = new Message();
		
		try {
			boolean exist = insuranceTypeRepository.existsByTypeName(type.getTypeName());
			if(exist) {
				message.setCode(409);
				message.setMessage("保單類型已存在，禁止重複新增");
				response = ResponseEntity.status(409).body(message);
			}
			else {
				InsuranceType newType = insuranceTypeRepository.save(type);
				message.setCode(200);
				message.setMessage("保單類型新增成功");
				response = ResponseEntity.ok(message);
			}
		}
		catch(Exception e){
			message.setCode(500);
			message.setMessage("保單類型新增失敗，系統異常了");
			response = ResponseEntity.status(500).body(message);
		}
		return response;
	}
	
	//查詢全部保單類型
	public ResponseEntity<Message> findAllInsuranceType(){
		
		ResponseEntity<Message> response = null;
		Message message = new Message();
		
		try {
			List<InsuranceType> result = insuranceTypeRepository.findAll();
			message.setCode(200);
			message.setMessage("保單類型查詢成功");
			response = ResponseEntity.ok(message);
		}
		catch(Exception e) {
			message.setCode(500);
			message.setMessage("保單類型查詢失敗，系統異常了");
			response = ResponseEntity.status(500).body(message);
		}
		return response;
	}
	
	//更新保單類型
	public ResponseEntity<Message> updateInsuranceType(InsuranceType newType){
		
		ResponseEntity<Message> response = null;
		Message message = new Message();
		
		try {
			if(insuranceTypeRepository.existsByTypeId(newType.getTypeId())) {
				InsuranceType exist = insuranceTypeRepository.findByTypeName(newType.getTypeName());
				if(exist != null && exist.getTypeId() != newType.getTypeId()) {
					message.setCode(409);
					message.setMessage("更新失敗，保單名稱已存在");
					response = ResponseEntity.status(409).body(message);
				}
				else {
					InsuranceType type = insuranceTypeRepository.save(newType);
					message.setCode(200);
					message.setMessage("保單類型更新成功");
					response = ResponseEntity.ok(message);
				}
			}
			else {
				message.setCode(404);
				message.setMessage("更新失敗，保單類型可能不存在");
				response = ResponseEntity.status(404).body(message);
			}
		}
		catch(Exception e) {
			message.setCode(500);
			message.setMessage("保單類型更新失敗，系統異常了");
			response = ResponseEntity.status(500).body(message);
		}
		return response;
	}
	
	//刪除保單類型
	@Transactional
	public ResponseEntity<Message> deleteInsuranceType(int typeId){
		
		ResponseEntity<Message> response = null;
		Message message = new Message();
		
		try {
			if(insuranceTypeRepository.existsByTypeId(typeId)) {
				insuranceTypeRepository.deleteByTypeId(typeId);
				message.setCode(200);
				message.setMessage("保單類型刪除成功");
				response = ResponseEntity.ok(message);
			}
			else {
				message.setCode(404);
				message.setMessage("刪除失敗，保單類型可能不存在");
				response = ResponseEntity.status(404).body(message);
			}
		}
		catch(Exception e) {
			message.setCode(500);
			message.setMessage("保單類型刪除失敗，系統異常了");
			response = ResponseEntity.status(500).body(message);
		}
		return response;
	}
	
	
}
