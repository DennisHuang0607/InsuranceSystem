package com.project01.controller;



import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project01.entity.InsuranceCompany;
import com.project01.entity.Message;
import com.project01.repository.InsuranceCompanyRepository;
import com.project01.service.InsuranceCompanyService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController()
@RequestMapping(path = "/api/v1/insuranceCompany")
public class InsuranceCompanyController {
	
	@Autowired
	private static Logger logger = LoggerFactory.getLogger(InsuranceCompanyController.class);
	@Autowired
	private InsuranceCompanyService insuranceCompanyService;
	@Autowired
	private InsuranceCompanyRepository insuranceCompanyRepository;
	
	public InsuranceCompanyController() {
		logger.info("InsuranceCompany Controller 配置成功...");
	}
	
	@PostMapping(path = "/register",consumes = "application/json",produces = "application/json")
	public ResponseEntity<Message> registerInsuranceCompanyController(@RequestBody InsuranceCompany company) {
		ResponseEntity<Message> response = insuranceCompanyService.registerInsuranceCompany(company);
		return response;
	}
	
	@GetMapping(path = "/findAll",produces = "application/json")
	public ResponseEntity<Message> findAllInsuranceCompanyController() {
		ResponseEntity<Message> response = insuranceCompanyService.findAllInsuranceCompany();
		return response;
	}
	
	@GetMapping(path = "/showAll",produces = "application/json")
	public List<InsuranceCompany> showAllInsuranceCompanyController() {
		List<InsuranceCompany> list = insuranceCompanyRepository.findAll();
		return list;
	}
	
	@PutMapping(path = "/update",consumes = "application/json",produces = "application/json")
	public ResponseEntity<Message> updateInsuranceCompanyController(@RequestBody InsuranceCompany company) {
		ResponseEntity<Message> response = insuranceCompanyService.updateInsuranceCompany(company);
		return response;
	}
	
	@DeleteMapping(path = "/delete/{id}",produces = "application/json")
	public ResponseEntity<Message> deleteInsuranceCompanyController(@PathVariable("id") int companyId) {
		ResponseEntity<Message> response = insuranceCompanyService.deleteInsuranceCompany(companyId);
		return response;
	}

}
