package com.project01.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.project01.entity.Policy;

public interface PolicyRepository extends JpaRepository<Policy, Long>{
	
	//新增Policy
	Policy save(Policy policy);
	
	//查詢所有Policy
	List<Policy> findAll();
	
	//查詢Policy是否存在(policyId)
	boolean existsByPolicyId(long policyId);
	
	//刪除Policy
	void deleteByPolicyId(long policyId);
	
	//使用JOIN FETCH一次把關聯的人員角色資料抓出來，避免N+1
    @Query("SELECT DISTINCT p FROM Policy p " +
           "LEFT JOIN FETCH p.policyPersonRoles ppr " +
           "LEFT JOIN FETCH ppr.person " +
           "LEFT JOIN FETCH p.type " +
           "LEFT JOIN FETCH p.company " +
           "LEFT JOIN FETCH p.insurer")
    List<Policy> findAllWithDetails();
}
