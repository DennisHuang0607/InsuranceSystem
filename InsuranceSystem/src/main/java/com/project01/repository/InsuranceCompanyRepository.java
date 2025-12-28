package com.project01.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project01.entity.InsuranceCompany;

public interface InsuranceCompanyRepository extends JpaRepository<InsuranceCompany, Long>{
	
	//新增InsuranceCompany
	InsuranceCompany save(InsuranceCompany company);

	//查詢全部InsuranceCompany
	List<InsuranceCompany> findAll();
	
	//查詢單一InsuranceCompany(用companyName)
	InsuranceCompany findByCompanyName(String companyName);
	
	//查詢InsuranceCompany是否存在(用companyId)
	boolean existsByCompanyId(long companyId);
	
	//查詢InsuranceCompany是否存在(用companyName)
	boolean existsByCompanyName(String companyName);
	
	//刪除InsuranceCompany
	void deleteByCompanyId(long companyId);
	
}
