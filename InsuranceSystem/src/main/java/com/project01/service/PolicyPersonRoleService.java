package com.project01.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
//	@Transactional(rollbackFor = Exception.class)
//	public ResponseEntity<ResponseDTO<PolicyPersonRole>> registerRole(long policyId,long personId,String role){
//		try {
//			if (!policyRepository.existsByPolicyId(policyId)) {
//				ResponseDTO<PolicyPersonRole> response = new ResponseDTO<>(404,"保單不存在",null);
//                return ResponseEntity.status(404).body(response);
//            }
//			else if (!policyPersonRepository.existsByPersonId(personId)) {
//				ResponseDTO<PolicyPersonRole> response = new ResponseDTO<>(404,"人員不存在",null);
//                return ResponseEntity.status(404).body(response);
//            }
//			else {
//				Policy policyRef = policyRepository.getReferenceById(policyId);
//	            PolicyPerson personRef = policyPersonRepository.getReferenceById(personId);
//	            
//	            PolicyPersonRole newRole = new PolicyPersonRole();
//	            newRole.setPolicy(policyRef);
//	            newRole.setPerson(personRef);
//	            newRole.setRole(role);
//	            
//	            PolicyPersonRole savedRole = policyPersonRoleRepository.save(newRole);
//	            ResponseDTO<PolicyPersonRole> response = new ResponseDTO<>(200, "保單人員角色新增成功", savedRole);
//	            return ResponseEntity.ok(response);
//			}
//            
//		}
//		catch(Exception e) {
//			System.err.println("新增角色失敗: " + e.getMessage());
//			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
//            ResponseDTO<PolicyPersonRole> response = new ResponseDTO<>(500, "角色新增失敗，系統異常", null);
//            return ResponseEntity.status(500).body(response);
//		}
//	}
	
	//新增保單角色
	@Transactional(rollbackFor = Exception.class)
	public PolicyPersonRole registerRole(long policyId, long personId, String role){
		Policy policyRef = policyRepository.getReferenceById(policyId);
        PolicyPerson personRef = policyPersonRepository.getReferenceById(personId);
        
        PolicyPersonRole newRole = new PolicyPersonRole();
        newRole.setPolicy(policyRef);
        newRole.setPerson(personRef);
        newRole.setRole(role);
        
        PolicyPersonRole saveRole = policyPersonRoleRepository.save(newRole);
        return saveRole;
	}
	
	
}
