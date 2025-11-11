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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project01.dto.Response;
import com.project01.entity.PolicyPerson;
import com.project01.service.PolicyPersonService;

@RestController
@RequestMapping(path = "/api/v1/PolicyPerson")
public class PolicyPersonController {

	@Autowired
	private static Logger logger = LoggerFactory.getLogger(PolicyPersonController.class);
	@Autowired
	private PolicyPersonService policyPersonService;
	
	public PolicyPersonController() {
		logger.info("PolicyPerson Controller 已配置...");
	}
	
	@PostMapping(path = "/register",consumes = "application/json",produces = "application/json")
	public ResponseEntity<Response<PolicyPerson>> registerPolicyPersonController(PolicyPerson person){
		return policyPersonService.registerPolicyPerson(person);
	}
	
	@GetMapping(path = "/findAll",produces = "application/json")
	public ResponseEntity<Response<List<PolicyPerson>>> findAllPolicyPersonController(){
		return policyPersonService.findAllPolicyPerson();
	}
	
	@PutMapping(path = "/update",consumes = "application/json",produces = "application/json")
	public ResponseEntity<Response<PolicyPerson>> updatePolicyPersonController(PolicyPerson person){
		return policyPersonService.updatePolicyPerson(person);
	}
	
	@DeleteMapping(path = "/delete/{id}",produces = "application/json")
	public ResponseEntity<Response<PolicyPerson>>  deletePolicyPersonController(@PathVariable("id") int personId){
		return policyPersonService.deletePolicyPerson(personId);
	}
	
	
}
