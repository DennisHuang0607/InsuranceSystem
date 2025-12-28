package com.project01.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.project01.dto.ResponseDTO;
import com.project01.entity.PolicyPerson;
import com.project01.repository.PolicyPersonRepository;

@Service
public class PolicyPersonService {

	private static final Logger logger = LoggerFactory.getLogger(PolicyPersonService.class);
	@Autowired
	private PolicyPersonRepository policyPersonRepository;
	
	//新增PolicyPerson
	@Transactional(rollbackFor = Exception.class)
	public ResponseEntity<ResponseDTO<PolicyPerson>> registerPolicyPerson(PolicyPerson person){
		try{
			if(policyPersonRepository.existsByIdNumber(person.getIdNumber())) {
				logger.warn("保險關係人新增失敗，身分證字號:{} 已存在，禁止重複新增",person.getIdNumber());
				ResponseDTO<PolicyPerson> response = new ResponseDTO<>(409, "身分證字號已存在，禁止重複新增", null);
				return  ResponseEntity.status(409).body(response);
			}
			else {
				PolicyPerson newPerson = policyPersonRepository.save(person);
				logger.info("保險關係人:{} 新增成功",person.getName());
				ResponseDTO<PolicyPerson> response = new ResponseDTO<>(200, "保險關係人新增成功", newPerson);
				return  ResponseEntity.ok(response);
			}
		}
		catch(Exception e) {
			logger.error("保險關係人新增失敗，後端異常了:",e);
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			ResponseDTO<PolicyPerson> response = new ResponseDTO<>(500, "保險關係人新增失敗，系統異常了", null);
			return  ResponseEntity.status(500).body(response);
		}
	}
	
	//查詢全部PolicyPerson
	@Transactional(readOnly = true)
	public ResponseEntity<ResponseDTO<List<PolicyPerson>>> findAllPolicyPerson(){
		try {
			List<PolicyPerson> result = policyPersonRepository.findAll();
			logger.info("所有保險關係人查詢成功，共{}筆",result.size());
			ResponseDTO<List<PolicyPerson>> response = new ResponseDTO<>(200, "所有保險關係人查詢成功", result);
			return ResponseEntity.ok(response);
		}
		catch (Exception e) {
			logger.error("所有保險關係人查詢失敗，後端異常了:",e);
			ResponseDTO<List<PolicyPerson>> response = new ResponseDTO<>(500, "所有保險關係人查詢失敗，系統異常了", null);
			return  ResponseEntity.status(500).body(response);
		}
	}
	
	//更新PolicyPerson
	@Transactional(rollbackFor = Exception.class)
	public ResponseEntity<ResponseDTO<PolicyPerson>> updatePolicyPerson(PolicyPerson updatePerson){
		try {
			if(policyPersonRepository.existsByPersonId(updatePerson.getPersonId())) {
				PolicyPerson person = policyPersonRepository.findByIdNumber(updatePerson.getIdNumber());
				if(person != null && person.getPersonId() != updatePerson.getPersonId()) {
					logger.warn("保險關係人:{} 更新失敗，身分證字號重複",person.getName());
					ResponseDTO<PolicyPerson> response = new ResponseDTO<>(409, "更新失敗，身分證字號重複", null);
					return ResponseEntity.status(409).body(response);
				}
				else {
					PolicyPerson newPerson = policyPersonRepository.save(updatePerson);
					logger.info("保險關係人:{} 更新成功",newPerson.getName());
					ResponseDTO<PolicyPerson> response = new ResponseDTO<>(200, "保險關係人更新成功", newPerson);
					return ResponseEntity.ok(response);
				}
			}
			else {
				logger.warn("保險關係人:{} 不存在，更新失敗",updatePerson.getName());
				ResponseDTO<PolicyPerson> response = new ResponseDTO<>(404,"更新失敗，保險關係人可能不存在",null);
				return  ResponseEntity.status(404).body(response);
			}
		}
		catch(Exception e) {
			logger.error("保險關係人更新失敗，後端異常了:",e);
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			ResponseDTO<PolicyPerson> response = new ResponseDTO<>(500, "保險關係人更新失敗，系統異常了", null);
			return  ResponseEntity.status(500).body(response);
		}
	}
	
	//刪除PolicyPerson
	@Transactional(rollbackFor = Exception.class)
	public ResponseEntity<ResponseDTO<PolicyPerson>> deletePolicyPerson(long personId){
		try {
			if(policyPersonRepository.existsByPersonId(personId)) {
				policyPersonRepository.deleteByPersonId(personId);
				logger.info("保險關係人ID:{} 刪除成功",personId);
				ResponseDTO<PolicyPerson> response = new ResponseDTO<>(200, "保險關係人刪除成功", null);
				return ResponseEntity.ok(response);
			}
			else {
				logger.warn("保險關係人ID:{} 不存在，刪除失敗",personId);
				ResponseDTO<PolicyPerson> response = new ResponseDTO<>(404, "刪除失敗，保險關係人可能不存在", null);
				return ResponseEntity.status(404).body(response);
			}
		}
		catch(Exception e){
			logger.error("保險關係人刪除失敗，後端異常了:",e);
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			ResponseDTO<PolicyPerson> response = new ResponseDTO<>(500, "保險關係人刪除失敗，系統異常了", null);
			return ResponseEntity.status(500).body(response);
		}
	}
	
	
}
