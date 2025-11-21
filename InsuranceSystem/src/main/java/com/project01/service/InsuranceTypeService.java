package com.project01.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.project01.dto.ResponseDTO;
import com.project01.entity.InsuranceType;
import com.project01.repository.InsuranceTypeRepository;

import jakarta.transaction.Transactional;

@Service
public class InsuranceTypeService {
	
	@Autowired
	private InsuranceTypeRepository insuranceTypeRepository;
	
	//新增保單類型
	public ResponseEntity<ResponseDTO<InsuranceType>> registerInsuranceType(InsuranceType type){
		
		try {
			boolean exist = insuranceTypeRepository.existsByTypeName(type.getTypeName());
			if(exist) {
				ResponseDTO<InsuranceType> response = new ResponseDTO<>(409,"保單類型已存在，禁止重複新增",null);
				return ResponseEntity.status(409).body(response);
			}
			else {
				InsuranceType newType = insuranceTypeRepository.save(type);
				ResponseDTO<InsuranceType> response = new ResponseDTO<>(200,"保單類型新增成功",newType);
				return ResponseEntity.ok(response);
			}
		}
		catch(Exception e){
			ResponseDTO<InsuranceType> response = new ResponseDTO<>(500,"保單類型新增失敗，系統異常了",null);
			return ResponseEntity.status(500).body(response);
		}
	}
	
	//查詢全部保單類型
	public ResponseEntity<ResponseDTO<List<InsuranceType>>> findAllInsuranceType(){
		
		try {
			List<InsuranceType> result = insuranceTypeRepository.findAll();
			ResponseDTO<List<InsuranceType>> response = new ResponseDTO<>(200,"保單類型查詢成功",result);
			return ResponseEntity.ok(response);
		}
		catch(Exception e) {
			ResponseDTO<List<InsuranceType>> response = new ResponseDTO<>(500,"保單類型查詢失敗，系統異常了",null);
			return ResponseEntity.status(500).body(response);
		}
	}
	
	//更新保單類型
	public ResponseEntity<ResponseDTO<InsuranceType>> updateInsuranceType(InsuranceType newType){
		
		try {
			if(insuranceTypeRepository.existsByTypeId(newType.getTypeId())) {
				InsuranceType exist = insuranceTypeRepository.findByTypeName(newType.getTypeName());
				if(exist != null && exist.getTypeId() != newType.getTypeId()) {
					ResponseDTO<InsuranceType> response = new ResponseDTO<>(409,"更新失敗，保單名稱已存在",null);
					return ResponseEntity.status(409).body(response);
				}
				else {
					InsuranceType type = insuranceTypeRepository.save(newType);
					ResponseDTO<InsuranceType> response = new ResponseDTO<>(200,"保單類型更新成功",type);
					return ResponseEntity.ok(response);
				}
			}
			else {
				ResponseDTO<InsuranceType> response = new ResponseDTO<>(404,"更新失敗，保單類型可能不存在",null);
				return ResponseEntity.status(404).body(response);
			}
		}
		catch(Exception e) {
			ResponseDTO<InsuranceType> response = new ResponseDTO<>(500,"保單類型更新失敗，系統異常了",null);
			return ResponseEntity.status(500).body(response);
		}
	}
	
	//刪除保單類型
	@Transactional
	public ResponseEntity<ResponseDTO<InsuranceType>> deleteInsuranceType(int typeId){
		
		try {
			if(insuranceTypeRepository.existsByTypeId(typeId)) {
				insuranceTypeRepository.deleteByTypeId(typeId);
				ResponseDTO<InsuranceType> response = new ResponseDTO<>(200,"保單類型刪除成功",null);
				return ResponseEntity.ok(response);
			}
			else {
				ResponseDTO<InsuranceType> response = new ResponseDTO<>(404,"刪除失敗，保單類型可能不存在",null);
				return ResponseEntity.status(404).body(response);
			}
		}
		catch(Exception e) {
			ResponseDTO<InsuranceType> response = new ResponseDTO<>(500,"保單類型刪除失敗，系統異常了",null);
			return ResponseEntity.status(500).body(response);
		}
	}
	
	
}
