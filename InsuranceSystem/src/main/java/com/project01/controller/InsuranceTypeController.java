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

import com.project01.entity.InsuranceType;
import com.project01.entity.Message;
import com.project01.repository.InsuranceTypeRepository;
import com.project01.service.InsuranceTypeService;

@RestController()
@RequestMapping(path = "/api/v1/insuranceType")
public class InsuranceTypeController {
	
	@Autowired
	private static Logger logger = LoggerFactory.getLogger(InsuranceTypeController.class);
	@Autowired
	private InsuranceTypeService insuranceTypeService;
	@Autowired
	private InsuranceTypeRepository insuranceTypeRepository;
	
	public InsuranceTypeController() {
		logger.info("InsuranceType Controller 配置成功...");
	}

	@PostMapping(path = "/register",consumes = "application/json",produces = "application/json")
	public ResponseEntity<Message> registerInsuranceTypeController(@RequestBody InsuranceType type){
		ResponseEntity<Message> response = insuranceTypeService.registerInsuranceType(type);
		return response;
	}
	
	@GetMapping(path = "/findAll",produces = "application/json")
	public ResponseEntity<Message> findAllInsuranceTypeController(){
		ResponseEntity<Message> response = insuranceTypeService.findAllInsuranceType();
		return response;
	}
	
	@GetMapping(path = "/showAll",produces = "application/json")
	public List<InsuranceType> showAllInsuranceTypeController(){
		List<InsuranceType> list = insuranceTypeRepository.findAll();
		return list;
	}
	
	@PutMapping(path = "/update",consumes = "application/json",produces = "application/json")
	public ResponseEntity<Message> updateInsuranceTypeController(@RequestBody InsuranceType type){
		ResponseEntity<Message> response = insuranceTypeService.updateInsuranceType(type);
		return response;
	}
	
	@DeleteMapping(path = "/delete/{id}",produces = "application/json")
	public ResponseEntity<Message> deleteInsuranceTypeController(@PathVariable("id") int typeId){
		ResponseEntity<Message> response = insuranceTypeService.deleteInsuranceType(typeId);
		return response;
	}
	
}
