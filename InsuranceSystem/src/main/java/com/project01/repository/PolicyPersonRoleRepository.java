package com.project01.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.project01.entity.PolicyPersonRole;

public interface PolicyPersonRoleRepository extends JpaRepository<PolicyPersonRole, Integer>{

	
	//新增PolicyPersonRole
	PolicyPersonRole save(PolicyPersonRole role);
	
	//查詢所有PolicyPersonRole
	//List<PolicyPersonRole> findAll();
	
	//查詢PolicyPersonRole是否存在(id)
	//boolean existsById(int id);
	
	//刪除Policy
	//void deleteById(int id);
	
	
}
