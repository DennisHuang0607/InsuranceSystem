package com.project01.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project01.dto.ResponseDTO;
import com.project01.entity.InsuranceType;
import com.project01.repository.InsuranceTypeRepository;
import com.project01.service.InsuranceTypeService;

@RestController()
@RequestMapping(path = "/api/v1/insuranceType")
public class InsuranceTypeController {
	private static final Logger logger = LoggerFactory.getLogger(InsuranceTypeController.class);
	
	@Autowired
	private InsuranceTypeService insuranceTypeService;
	@Autowired
	private InsuranceTypeRepository insuranceTypeRepository;
	
	public InsuranceTypeController() {
		logger.info("InsuranceType Controller 配置成功...");
	}

	@PostMapping(path = "/register",consumes = "application/json",produces = "application/json")
	public ResponseEntity<ResponseDTO<InsuranceType>> registerInsuranceTypeController(@RequestBody InsuranceType type){
		return insuranceTypeService.registerInsuranceType(type);
	}
	
	@GetMapping(path = "/findAll",produces = "application/json")
	public ResponseEntity<ResponseDTO<List<InsuranceType>>> findAllInsuranceTypeController(){
		return insuranceTypeService.findAllInsuranceType();
	}
	
	@GetMapping(path = "/showAll",produces = "application/json")
	public List<InsuranceType> showAllInsuranceTypeController(){
		return insuranceTypeRepository.findAll();
	}
	
	@PutMapping(path = "/update",consumes = "application/json",produces = "application/json")
	public ResponseEntity<ResponseDTO<InsuranceType>> updateInsuranceTypeController(@RequestBody InsuranceType type){
		return insuranceTypeService.updateInsuranceType(type);
	}
	
	@DeleteMapping(path = "/delete/{id}",produces = "application/json")
	public ResponseEntity<ResponseDTO<InsuranceType>> deleteInsuranceTypeController(@PathVariable("id") long typeId){
		return insuranceTypeService.deleteInsuranceType(typeId);
	}
	
}
