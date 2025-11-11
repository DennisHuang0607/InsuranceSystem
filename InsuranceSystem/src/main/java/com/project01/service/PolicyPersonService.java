package com.project01.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.project01.dto.Response;
import com.project01.entity.PolicyPerson;
import com.project01.repository.PolicyPersonRepository;

@Service
public class PolicyPersonService {

	@Autowired
	private PolicyPersonRepository policyPersonRepository;
	
	//新增PolicyPerson
	public ResponseEntity<Response<PolicyPerson>> registerPolicyPerson(PolicyPerson person){
		try{
			if(policyPersonRepository.existsByIdNumber(person.getIdNumber())) {
				Response<PolicyPerson> response = new Response<>(409, "身分證字號已存在，禁止重複新增", null);
				return  ResponseEntity.status(409).body(response);
			}
			else {
				PolicyPerson newPerson = policyPersonRepository.save(person);
				Response<PolicyPerson> response = new Response<>(200, "人員新增成功", newPerson);
				return  ResponseEntity.ok(response);
			}
		}
		catch(Exception e) {
			Response<PolicyPerson> response = new Response<>(500, "人員新增失敗，系統異常了", null);
			return  ResponseEntity.status(500).body(response);
		}
	}
	
	//查詢全部PolicyPerson
	public ResponseEntity<Response<List<PolicyPerson>>> findAllPolicyPerson(){
		try {
			List<PolicyPerson> result = policyPersonRepository.findAll();
			Response<List<PolicyPerson>> response = new Response<>(200, "人員查詢成功", result);
			return ResponseEntity.ok(response);
		}
		catch (Exception e) {
			Response<List<PolicyPerson>> response = new Response<>(500, "人員查詢失敗，系統異常了", null);
			return  ResponseEntity.status(500).body(response);
		}
	}
	
	//更新PolicyPerson
	public ResponseEntity<Response<PolicyPerson>> updatePolicyPerson(PolicyPerson updatePerson){
		try {
			if(policyPersonRepository.existsByPersonId(updatePerson.getPersonId())) {
				PolicyPerson person = policyPersonRepository.findByIdNumber(updatePerson.getIdNumber());
				if(person != null && person.getPersonId() == updatePerson.getPersonId()) {
					PolicyPerson newPerson = policyPersonRepository.save(updatePerson);
					Response<PolicyPerson> response = new Response<>(200, "人員更新成功", newPerson);
					return ResponseEntity.ok(response);
				}
				else {
					Response<PolicyPerson> response = new Response<>(409, "更新失敗，身分證字號重複", null);
					return ResponseEntity.status(409).body(response);
				}
			}
			else {
				Response<PolicyPerson> response = new Response<>(404,"更新失敗，人員可能不存在",null);
				return  ResponseEntity.status(404).body(response);
			}
		}
		catch(Exception e) {
			Response<PolicyPerson> response = new Response<>(500, "人員更新失敗，系統異常了", null);
			return  ResponseEntity.status(500).body(response);
		}
	}
	
	//刪除PolicyPerson
	public ResponseEntity<Response<PolicyPerson>> deletePolicyPerson(int personId){
		try {
			if(policyPersonRepository.existsByPersonId(personId)) {
				policyPersonRepository.deleteByPersonId(personId);
				Response<PolicyPerson> response = new Response<>(200, "刪除成功", null);
				return ResponseEntity.ok(response);
			}
			else {
				Response<PolicyPerson> response = new Response<>(404, "刪除失敗，人員可能不存在", null);
				return ResponseEntity.status(404).body(response);
			}
		}
		catch(Exception e){
			Response<PolicyPerson> response = new Response<>(500, "刪除失敗，人員可能不存在", null);
			return ResponseEntity.status(500).body(response);
		}
	}
	
	
}
