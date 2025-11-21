package com.project01.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.project01.component.PolicyNumberComponent;
import com.project01.dto.PolicyAndRolesDTO;
import com.project01.dto.PolicyRolesDTO;
import com.project01.dto.ResponseDTO;
import com.project01.entity.Policy;
import com.project01.repository.PolicyRepository;

import jakarta.transaction.Transactional;

@Service
public class PolicyService {
	private static final Logger logger = LoggerFactory.getLogger(PolicyService.class);

	@Autowired
	private PolicyRepository policyRepository;
	@Autowired
    private PolicyNumberComponent policyNumberComponent;
	@Autowired
	private PolicyPersonRoleService policyPersonRoleService;
	
	//新增Policy
	public ResponseEntity<ResponseDTO<Policy>> registerPolicy(Policy policy){
	    try {
	        //自動產生policyNumber
	        String newPolicyNumber = policyNumberComponent.generateNewPolicyNumber();
	        policy.setPolicyNumber(newPolicyNumber);

	        Policy newPolicy = policyRepository.save(policy);
	        ResponseDTO<Policy> response = new ResponseDTO<>(200,"保單新增成功",newPolicy);
	        return ResponseEntity.ok(response);
	    }
	    catch(Exception e){
	        logger.error("保單新增失敗，後端發生異常:",e);
	        ResponseDTO<Policy> response = new ResponseDTO<>(500,"保單新增失敗，系統異常了",null);
	        return ResponseEntity.status(500).body(response);
	    }
	}
	
	//查詢所有Policy
	public ResponseEntity<ResponseDTO<List<Policy>>> findAllPolicy(){
		try {
			List<Policy> result = policyRepository.findAll();
			ResponseDTO<List<Policy>> response = new ResponseDTO<>(200,"查詢保單成功",result);
			return ResponseEntity.ok(response);
		}
		catch (Exception e) {
			ResponseDTO<List<Policy>> response = new ResponseDTO<>(500,"保單新增失敗，系統異常了",null);
			return ResponseEntity.status(500).body(response);
		}
	}
			
	//更新Policy
	public ResponseEntity<ResponseDTO<Policy>> updatePolicy(Policy updatePolicy){
		try {
			if(policyRepository.existsByPolicyId(updatePolicy.getPolicyId())) {
				Policy policy = policyRepository.save(updatePolicy);
				ResponseDTO<Policy> response = new ResponseDTO<>(200,"保單更新成功",policy);
				return ResponseEntity.ok(response);
			}
			else {
				ResponseDTO<Policy> response = new ResponseDTO<>(404,"更新失敗，保單可能不存在",null);
				return ResponseEntity.status(404).body(response);
			}
		}
		catch(Exception e) {
			ResponseDTO<Policy> response = new ResponseDTO<>(500,"保單更新失敗，系統異常了",null);
			return ResponseEntity.status(500).body(response);
		}
	}
	
	//刪除Policy
	public ResponseEntity<ResponseDTO<Policy>> deletePolicy(int policyId){
		try {
			if(policyRepository.existsByPolicyId(policyId)) {
				policyRepository.deleteByPolicyId(policyId);
				ResponseDTO<Policy> response = new ResponseDTO<>(200,"保單刪除成功",null);
				return ResponseEntity.ok(response);
			}
			else {
				ResponseDTO<Policy> response = new ResponseDTO<>(404,"刪除失敗，保單可能不存在",null);
				return ResponseEntity.status(404).body(response);
			}
		}
		catch(Exception e) {
			ResponseDTO<Policy> response = new ResponseDTO<>(500,"保單刪除失敗，系統異常了",null);
			return ResponseEntity.status(500).body(response);
		}
	}
	
	//新增Policy及所有相關角色
	@Transactional
    public ResponseEntity<ResponseDTO<Policy>> registerPolicyWithRoles(PolicyAndRolesDTO request) {
        try {
        	//取得policy及產生policyNumber
            Policy policy = request.getPolicy();
            String newPolicyNumber = policyNumberComponent.generateNewPolicyNumber();
            policy.setPolicyNumber(newPolicyNumber);

            Policy newPolicy = policyRepository.save(policy);
            int newPolicyId = newPolicy.getPolicyId();

            //取得每一個角色並儲存
            for (PolicyRolesDTO role:request.getPolicyRoles()) {
                policyPersonRoleService.registerRole(newPolicyId,role.getPersonId(),role.getRole());
            }

            ResponseDTO<Policy> response = new ResponseDTO<>(200,"保單及角色新增成功",newPolicy);
            return ResponseEntity.ok(response);
        } catch(Exception e) {
            ResponseDTO<Policy> response = new ResponseDTO<>(500, "保單/角色新增失敗，系統異常了",null);
            return ResponseEntity.status(500).body(response);
        }
    }
	
	
}
