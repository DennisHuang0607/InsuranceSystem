package com.project01.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.project01.component.PolicyNumberComponent;
import com.project01.dto.Response;
import com.project01.entity.Policy;
import com.project01.repository.PolicyRepository;

@Service
public class PolicyService {

	@Autowired
	private PolicyRepository policyRepository;
	@Autowired
    private PolicyNumberComponent policyNumberComponent;
	
	//新增Policy
	public ResponseEntity<Response<Policy>> registerPolicy(Policy policy){
		try {
			//自動產生policyNumber
			String newPolicyNumber = policyNumberComponent.generateNewPolicyNumber();
            policy.setPolicyNumber(newPolicyNumber);

			Policy newPolicy = policyRepository.save(policy);
			Response<Policy> response = new Response<>(200,"保險單新增成功",newPolicy);
			return ResponseEntity.ok(response);
		}
		catch(Exception e){
			Response<Policy> response = new Response<>(500,"保單新增失敗，系統異常了",null);
			return ResponseEntity.status(500).body(response);
		}
	}
	
	//查詢所有Policy
	public ResponseEntity<Response<List<Policy>>> findAllPolicy(){
		try {
			List<Policy> result = policyRepository.findAll();
			Response<List<Policy>> response = new Response<>(200,"查詢保單成功",result);
			return ResponseEntity.ok(response);
		}
		catch (Exception e) {
			Response<List<Policy>> response = new Response<>(500,"保單新增失敗，系統異常了",null);
			return ResponseEntity.status(500).body(response);
		}
	}
			
	//更新Policy
	public ResponseEntity<Response<Policy>> updatePolicy(Policy updatePolicy){
		try {
			if(policyRepository.existsByPolicyId(updatePolicy.getPolicyId())) {
				Policy policy = policyRepository.save(updatePolicy);
				Response<Policy> response = new Response<>(200,"保單更新成功",policy);
				return ResponseEntity.ok(response);
			}
			else {
				Response<Policy> response = new Response<>(404,"更新失敗，保單可能不存在",null);
				return ResponseEntity.status(404).body(response);
			}
		}
		catch(Exception e) {
			Response<Policy> response = new Response<>(500,"保單更新失敗，系統異常了",null);
			return ResponseEntity.status(500).body(response);
		}
	}
	
	//刪除Policy
	public ResponseEntity<Response<Policy>> deletePolicy(int policyId){
		try {
			if(policyRepository.existsByPolicyId(policyId)) {
				policyRepository.deleteByPolicyId(policyId);
				Response<Policy> response = new Response<>(200,"保單刪除成功",null);
				return ResponseEntity.ok(response);
			}
			else {
				Response<Policy> response = new Response<>(404,"刪除失敗，保單可能不存在",null);
				return ResponseEntity.status(404).body(response);
			}
		}
		catch(Exception e) {
			Response<Policy> response = new Response<>(500,"保單刪除失敗，系統異常了",null);
			return ResponseEntity.status(500).body(response);
		}
	}
	
	
}
