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
import com.project01.entity.PolicyPerson;
import com.project01.repository.PolicyPersonRepository;
import com.project01.service.PolicyPersonService;

@RestController
@RequestMapping(path = "/api/v1/policyPerson")
public class PolicyPersonController {
	private static final Logger logger = LoggerFactory.getLogger(PolicyPersonController.class);
	
	@Autowired
	private PolicyPersonService policyPersonService;
	@Autowired
	private PolicyPersonRepository policyPersonRepository;
	
	public PolicyPersonController() {
		logger.info("PolicyPerson Controller 已配置...");
	}
	
	@PostMapping(path = "/register",consumes = "application/json",produces = "application/json")
	public ResponseEntity<ResponseDTO<PolicyPerson>> registerPolicyPersonController(@RequestBody PolicyPerson person){
		return policyPersonService.registerPolicyPerson(person);
	}
	
	@GetMapping(path = "/findAll",produces = "application/json")
	public ResponseEntity<ResponseDTO<List<PolicyPerson>>> findAllPolicyPersonController(){
		return policyPersonService.findAllPolicyPerson();
	}
	
	@GetMapping(path = "/showAll",produces = "application/json")
	public List<PolicyPerson> showAllPolicyPersonController(){
		return policyPersonRepository.findAll();
	}
	
	@PutMapping(path = "/update",consumes = "application/json",produces = "application/json")
	public ResponseEntity<ResponseDTO<PolicyPerson>> updatePolicyPersonController(@RequestBody PolicyPerson person){
		return policyPersonService.updatePolicyPerson(person);
	}
	
	@DeleteMapping(path = "/delete/{id}",produces = "application/json")
	public ResponseEntity<ResponseDTO<PolicyPerson>>  deletePolicyPersonController(@PathVariable("id") int personId){
		return policyPersonService.deletePolicyPerson(personId);
	}
	
	
}
