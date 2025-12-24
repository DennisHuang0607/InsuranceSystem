package com.project01.service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.project01.component.PolicyNumberComponent;
import com.project01.dto.PolicyAndRolesDTO;
import com.project01.dto.PolicyListDTO;
import com.project01.dto.PolicyRolesDTO;
import com.project01.dto.ResponseDTO;
import com.project01.entity.Policy;
import com.project01.entity.PolicyPersonRole;
import com.project01.repository.PolicyRepository;

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
	@Transactional(rollbackFor = Exception.class)
	public ResponseEntity<ResponseDTO<Policy>> registerPolicy(Policy policy){
	    try {
	        //自動產生policyNumber
	        String newPolicyNumber = policyNumberComponent.generateNewPolicyNumber();
	        policy.setPolicyNumber(newPolicyNumber);

	        Policy newPolicy = policyRepository.save(policy);
	        logger.info("保單單號:{} 新增成功",newPolicy.getPolicyNumber());
	        ResponseDTO<Policy> response = new ResponseDTO<>(200,"保單新增成功",newPolicy);
	        return ResponseEntity.ok(response);
	    }
	    catch(Exception e){
	        logger.error("保單新增失敗，後端發生異常:",e);
	        TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
	        ResponseDTO<Policy> response = new ResponseDTO<>(500,"保單新增失敗，系統異常了",null);
	        return ResponseEntity.status(500).body(response);
	    }
	}
	
	//查詢所有Policy
	@Transactional(readOnly = true)
	public ResponseEntity<ResponseDTO<List<Policy>>> findAllPolicy(){
		try {
			List<Policy> result = policyRepository.findAll();
			logger.info("所有保單查詢成功，共{}筆",result.size());
			ResponseDTO<List<Policy>> response = new ResponseDTO<>(200,"查詢保單成功",result);
			return ResponseEntity.ok(response);
		}
		catch (Exception e) {
			logger.error("所有保單查詢失敗，後端發生異常:",e);
			ResponseDTO<List<Policy>> response = new ResponseDTO<>(500,"保單查詢失敗，系統異常了",null);
			return ResponseEntity.status(500).body(response);
		}
	}
			
	//更新Policy
	@Transactional(rollbackFor = Exception.class)
	public ResponseEntity<ResponseDTO<Policy>> updatePolicy(Policy updatePolicy){
		try {
			if(policyRepository.existsByPolicyId(updatePolicy.getPolicyId())) {
				Policy policy = policyRepository.save(updatePolicy);
				logger.info("保單單號:{} 更新成功",policy.getPolicyNumber());
				ResponseDTO<Policy> response = new ResponseDTO<>(200,"保單更新成功",policy);
				return ResponseEntity.ok(response);
			}
			else {
				logger.warn("保單單號:{} 不存在，更新失敗",updatePolicy.getPolicyNumber());
				ResponseDTO<Policy> response = new ResponseDTO<>(404,"更新失敗，保單可能不存在",null);
				return ResponseEntity.status(404).body(response);
			}
		}
		catch(Exception e) {
			logger.error("保單更新失敗，後端發生異常:",e);
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			ResponseDTO<Policy> response = new ResponseDTO<>(500,"保單更新失敗，系統異常了",null);
			return ResponseEntity.status(500).body(response);
		}
	}
	
	//刪除Policy
	@Transactional(rollbackFor = Exception.class)
	public ResponseEntity<ResponseDTO<Policy>> deletePolicy(int policyId){
		try {
			if(policyRepository.existsByPolicyId(policyId)) {
				policyRepository.deleteByPolicyId(policyId);
				logger.info("保單ID:{} 刪除成功",policyId);
				ResponseDTO<Policy> response = new ResponseDTO<>(200,"保單刪除成功",null);
				return ResponseEntity.ok(response);
			}
			else {
				logger.warn("保單ID:{} 不存在，刪除失敗",policyId);
				ResponseDTO<Policy> response = new ResponseDTO<>(404,"刪除失敗，保單可能不存在",null);
				return ResponseEntity.status(404).body(response);
			}
		}
		catch(Exception e) {
			logger.error("保單刪除失敗，後端發生異常:",e);
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			ResponseDTO<Policy> response = new ResponseDTO<>(500,"保單刪除失敗，系統異常了",null);
			return ResponseEntity.status(500).body(response);
		}
	}
	
	//新增Policy及所有保單角色
	@Transactional(rollbackFor = Exception.class)
    public ResponseEntity<ResponseDTO<Policy>> registerPolicyAndRoles(PolicyAndRolesDTO request) {
        try {
        	if (request.getPolicyRoles() == null || request.getPolicyRoles().isEmpty()) {
        		logger.warn("保單&角色新增失敗，必須指定保單角色");
        		ResponseDTO<Policy> response = new ResponseDTO<Policy>(400, "保單&角色新增失敗，必須指定保單角色", null);
        		return ResponseEntity.status(400).body(response);
            }
        	
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

            logger.info("保單單號:{} &角色新增成功",newPolicyNumber);
            ResponseDTO<Policy> response = new ResponseDTO<>(200,"保單&角色新增成功",newPolicy);
            return ResponseEntity.ok(response);
        } 
        catch(Exception e) {
        	logger.error("保單&角色新增失敗，後端發生異常:",e);
        	TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            ResponseDTO<Policy> response = new ResponseDTO<>(500, "保單&角色新增失敗，系統異常了",null);
            return ResponseEntity.status(500).body(response);
        }
    }
	
	//取得policy+role
	@Transactional(rollbackFor = Exception.class)
	public ResponseEntity<ResponseDTO<List<PolicyListDTO>>> getAllPolicyOverview() {
	    try {
	        //撈出所有保單(包含關聯資料)
	        List<Policy> policies = policyRepository.findAllWithDetails();

	        //轉換成DTO
	        List<PolicyListDTO> dtoList = policies.stream().map(policy -> {
	            PolicyListDTO dto = new PolicyListDTO();
	            
	            //基本欄位搬移
	            dto.setPolicyId(policy.getPolicyId());
	            dto.setPolicyNumber(policy.getPolicyNumber());
	            dto.setInsuranceType(policy.getType().getTypeName());
	            dto.setInsuranceCompany(policy.getCompany().getCompanyName());
	            dto.setInsurer(policy.getInsurer().getName());
	            dto.setAcceptDate(policy.getAcceptDate());
	            dto.setBeginDate(policy.getBeginDate());
	            dto.setEndDate(policy.getEndDate());
	            dto.setInsuredAmount(policy.getInsuredAmount());
	            dto.setPaymentType(policy.getPaymentType());
	            
	            //防呆機制，先取出List，如果為null則給一個空List，避免stream()報錯
	            List<PolicyPersonRole> roles = policy.getPolicyPersonRoles();
	            if (roles == null) {
	                roles = Collections.emptyList();
	            }
	            
	            //找投保人
	            String policyholder = roles.stream()
	                .filter(r -> "投保人".equals(r.getRole())) // 請依你實際 DB 存的字串修改
	                .map(r -> r.getPerson().getName())
	                .findFirst()
	                .orElse("未知");
	            dto.setPolicyholder(policyholder);

	            //找被保人
	            String insured = roles.stream()
	                .filter(r -> "被保人".equals(r.getRole()))
	                .map(r -> r.getPerson().getName())
	                .findFirst()
	                .orElse("未知");
	            dto.setInsured(insured);

	            //找受益人
	            String beneficiaries = roles.stream()
	                .filter(r -> "受益人".equals(r.getRole()))
	                .map(r -> r.getPerson().getName())
	                .findFirst()
	                .orElse("未知");
	            dto.setBeneficiary(beneficiaries);

	            return dto;
	        }).collect(Collectors.toList());

	        logger.info("查詢保單總覽成功，共{}筆",dtoList.size());
	        return ResponseEntity.ok(new ResponseDTO<>(200,"查詢成功",dtoList));

	    } catch (Exception e) {
	        logger.error("查詢保單總覽失敗",e);
	        return ResponseEntity.status(500).body(new ResponseDTO<>(500,"系統異常",null));
	    }
	}
	
	
}
