package com.project01.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project01.entity.Policy;

public interface PolicyRepository extends JpaRepository<Policy, Integer>{
	
	//新增Policy
	Policy save(Policy policy);
	
	//查詢所有Policy
	List<Policy> findAll();
	
	//查詢Policy是否存在(policyId)
	boolean existsByPolicyId(int policyId);
	
	//刪除Policy
	void deleteByPolicyId(int policyId);
	
}
