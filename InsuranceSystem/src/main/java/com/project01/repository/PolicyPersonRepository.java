package com.project01.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project01.entity.PolicyPerson;

public interface PolicyPersonRepository extends JpaRepository<PolicyPerson, Long>{

	//新增PolicyPerson
	PolicyPerson save(PolicyPerson person);
	
	//查詢全部PolicyPerson
	List<PolicyPerson> findAll();
	
	//查詢單一PolicyPerson
	PolicyPerson findByIdNumber(String IdNumber);
	
	//查詢PolicyPerson是否存在(personId)
	boolean existsByPersonId(long personId);
	
	//查詢PolicyPerson是否存在(idNumber)
	boolean existsByIdNumber(String idNumber);
	
	//刪除PolicyPerson
	void deleteByPersonId(long personId);
	
}
