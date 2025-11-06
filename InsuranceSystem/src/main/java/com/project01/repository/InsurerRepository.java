package com.project01.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project01.entity.Insurer;

public interface InsurerRepository extends JpaRepository<Insurer, String>{

	//新增Insurer
	Insurer save(Insurer insurer);
	
	//查詢全部Insurer
	List<Insurer> findAll();
	
	//查詢單一Insurer
	Optional<Insurer> findByAccountId(String accountId);
	
	//查詢帳號是否存在
	boolean existsByAccountId(String accountId);
	
	//刪除Insurer
	void deleteByAccountId(String accountId);

	
}
