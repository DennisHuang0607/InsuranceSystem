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

import com.project01.dto.Response;
import com.project01.entity.Policy;
import com.project01.service.PolicyService;

@RestController
@RequestMapping(path = "/api/v1/policy")
public class PolicyController {
	
	private static final Logger logger = LoggerFactory.getLogger(PolicyController.class);
	
	@Autowired
	private PolicyService policyService;
	
	public PolicyController() {
		logger.info("Policy Controller 已配置...");
	}

	@PostMapping(path = "/register",consumes = "application/json",produces = "application/json")
	public ResponseEntity<Response<Policy>> registerPolicyController(@RequestBody Policy policy){
		return policyService.registerPolicy(policy);
	}
	
	@GetMapping(path = "/findAll",produces = "application/json")
	public ResponseEntity<Response<List<Policy>>> findAllPolicyController(){
		return policyService.findAllPolicy();
	}
	
	@PutMapping(path = "/update",consumes = "application/json",produces = "application/json")
	public ResponseEntity<Response<Policy>> updatePolicyController(@RequestBody Policy policy){
		return policyService.updatePolicy(policy);
	}
	
	@DeleteMapping(path = "/delete/{id}",produces = "application/json")
	public ResponseEntity<Response<Policy>> deletePolicy(@PathVariable("id") int policyId){
		return policyService.deletePolicy(policyId);
	}
	
}
