package com.project01.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project01.dto.ResponseDTO;
import com.project01.entity.PolicyPersonRole;
import com.project01.service.PolicyPersonRoleService;

@RestController()
@RequestMapping(path = "/api/v1/policyPersonRole")
public class PolicyPersonRoleController {
	
	@Autowired
	private static final Logger logger = LoggerFactory.getLogger(PolicyPersonRoleController.class);
	@Autowired
	private PolicyPersonRoleService policyPersonRoleService;
	
	public PolicyPersonRoleController() {
		logger.info("PolicyPersonRole Controller 配置成功...");
	};
	
	@PostMapping(path = "/register",consumes = "application/json",produces = "application/json")
	public ResponseEntity<ResponseDTO<PolicyPersonRole>> registerRoleController(@RequestBody int policyId, @RequestBody int personId, @RequestBody String role){
		try {
			PolicyPersonRole newRole = policyPersonRoleService.registerRole(policyId, personId, role);
			ResponseDTO<PolicyPersonRole> response = new ResponseDTO<PolicyPersonRole>(200,"",newRole);
			return ResponseEntity.ok(response);
		}
		catch(Exception e) {
			ResponseDTO<PolicyPersonRole> response = new ResponseDTO<>(500, "", null);
			return  ResponseEntity.status(500).body(response);
		}
	}
}
