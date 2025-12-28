package com.project01.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project01.entity.InsuranceType;

public interface InsuranceTypeRepository extends JpaRepository<InsuranceType, Long> {
	
	//新增InsuranceType
	InsuranceType save(InsuranceType type);
	
	//查詢全部InsuranceType
	List<InsuranceType> findAll();
	
	//查詢單一InsuranceType(用typeName)
	InsuranceType findByTypeName(String typeName);
	
	//查詢InsuranceType是否存在(用typeName)
	boolean existsByTypeName(String typeName);
	
	//查詢InsuranceType是否存在(用typeId)
	boolean existsByTypeId(long typeId);
	
	//刪除InsuranceType
	void deleteByTypeId(long typeId);
	
	
}
