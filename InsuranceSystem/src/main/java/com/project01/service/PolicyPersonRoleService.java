package com.project01.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.project01.dto.Response;
import com.project01.entity.Policy;
import com.project01.entity.PolicyPerson;
import com.project01.entity.PolicyPersonRole;
import com.project01.repository.PolicyPersonRepository;
import com.project01.repository.PolicyPersonRoleRepository;
import com.project01.repository.PolicyRepository;

@Service
public class PolicyPersonRoleService {
	
	@Autowired
 	private PolicyPersonRoleRepository policyPersonRoleRepository;
	@Autowired
	private PolicyRepository policyRepository;
	@Autowired
	private PolicyPersonRepository policyPersonRepository;
	
	//新增保單角色
	public ResponseEntity<Response<PolicyPersonRole>> register(int policyId, int personId, String role){
		try {
			if (!policyRepository.existsByPolicyId(policyId)) {
				Response<PolicyPersonRole> response = new Response<>(404,"保單不存在",null);
                return ResponseEntity.status(404).body(response);
            }
			else if (!policyPersonRepository.existsByPersonId(personId)) {
				Response<PolicyPersonRole> response = new Response<>(404,"人員不存在",null);
                return ResponseEntity.status(404).body(response);
            }
			else {
				Policy policyRef = policyRepository.getReferenceById(policyId);
	            PolicyPerson personRef = policyPersonRepository.getReferenceById(personId);
	            
	            PolicyPersonRole newRole = new PolicyPersonRole();
	            newRole.setPolicy(policyRef);
	            newRole.setPerson(personRef);
	            newRole.setRole(role);
	            
	            
	            PolicyPersonRole savedRole = policyPersonRoleRepository.save(newRole);
	            Response<PolicyPersonRole> response = new Response<>(200, "保單人員角色新增成功", savedRole);
	            return ResponseEntity.ok(response);
			}
            
		}
		catch(Exception e) {
			System.err.println("新增角色失敗: " + e.getMessage());
            Response<PolicyPersonRole> response = new Response<>(500, "角色新增失敗，系統異常", null);
            return ResponseEntity.status(500).body(response);
		}
	}
	
	
}
