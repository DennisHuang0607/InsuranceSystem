package com.project01.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.project01.dto.ResponseDTO;
import com.project01.entity.Admin;
import com.project01.repository.AdminRepository;

@Service
public class AdminService {

	private static final Logger logger = LoggerFactory.getLogger(AdminService.class);
	@Autowired
	private AdminRepository adminRepository;
	@Autowired
	private PasswordEncoder passwordEncoder;

	//新增管理員
	public ResponseEntity<ResponseDTO<Admin>> registerAdmin(Admin admin) {
		
		String encodedPassword = passwordEncoder.encode(admin.getPassword());
		admin.setPassword(encodedPassword);
		
		try {
			int row = adminRepository.register(admin);
			
			if(row==1) {
				logger.info("管理員新增成功，新增帳號：{}",admin.getAccountId());
				ResponseDTO<Admin> response = new ResponseDTO<Admin>(200,"管理員新增成功",admin);
			    return ResponseEntity.ok(response);
			}
			else { 
				logger.error("管理員新增失敗，資料庫操作未生效");
	            ResponseDTO<Admin> response = new ResponseDTO<Admin>(500,"管理員新增失敗，資料庫操作未生效",null);
	            return ResponseEntity.status(500).body(response); 
	        }
		}
		catch (DuplicateKeyException e) {
			logger.warn("管理員新增失敗，帳號已存在，新增帳號:{}",admin.getAccountId());
		    ResponseDTO<Admin> response = new ResponseDTO<Admin>(409,"帳號已存在，請換一個試試",null);
		    return ResponseEntity.status(409).body(response);
		}
		catch (DataAccessException e){
			logger.error("管理員新增失敗，後端發生異常:",e);
			ResponseDTO<Admin> response = new ResponseDTO<Admin>(500,"管理員新增失敗，後端發生異常",null);
		    return ResponseEntity.status(500).body(response);
		}

	}
	
	//查詢所有管理員
	public ResponseEntity<ResponseDTO<List<Admin>>> findAllAdmin() {
		
		try {
			List<Admin> result = adminRepository.findAll();
			logger.info("管理員查詢成功");
			ResponseDTO<List<Admin>> response = new ResponseDTO<List<Admin>>(200,"管理員查詢成功",result);
		    return ResponseEntity.ok(response);
		}
		catch (DataAccessException e) {
			logger.error("管理員查詢失敗，後端發生異常，可能連線不到資料庫:",e);
			ResponseDTO<List<Admin>> response = new ResponseDTO<List<Admin>>(500,"系統異常，可能連線不到資料庫",null);
		    return ResponseEntity.status(500).body(response);
		}
		
	}
	
	//更新管理員資料
	public ResponseEntity<ResponseDTO<Admin>> updateAdmin(Admin admin) {
		
		String encodedPassword = passwordEncoder.encode(admin.getPassword());
		admin.setPassword(encodedPassword);
		
		try {
			int row = adminRepository.update(admin);
			if(row == 1) {
				logger.info("管理員資料更新成功，更新帳號：{}",admin.getAccountId());
				ResponseDTO<Admin> response = new ResponseDTO<Admin>(200,"管理員資料更新成功",admin);
			    return ResponseEntity.ok(response);
			}
			else {
				logger.error("管理員資料更新失敗，資料不存在");
				ResponseDTO<Admin> response = new ResponseDTO<Admin>(404,"資料更新失敗，管理員資料不存在",null);
			    return ResponseEntity.status(404).body(response);
			}
		}
		catch (DataAccessException e) {
			logger.error("管理員資料更新失敗，後端發生異常，可能連線不到資料庫:",e);
			ResponseDTO<Admin> response = new ResponseDTO<Admin>(500,"系統異常，可能連線不到資料庫",null);
		    return ResponseEntity.status(500).body(response);
		}

	}
	
	//刪除管理員
	public ResponseEntity<ResponseDTO<Admin>> deleteAdminById(String account) {
		
		try {
			int row = adminRepository.deleteById(account);
			if(row == 1) {
				logger.info("管理員刪除成功，刪除帳號：{}",account);
				ResponseDTO<Admin> response = new ResponseDTO<Admin>(200,"管理員刪除成功",null);
			    return ResponseEntity.ok(response);
			}
			else {
				logger.error("管理員刪除失敗");
				ResponseDTO<Admin> response = new ResponseDTO<Admin>(404,"管理員刪除失敗",null);
			    return ResponseEntity.status(404).body(response);
			}
		}
		catch (DataAccessException e) {
			logger.error("管理員刪除失敗，後端發生異常，可能連線不到資料庫:",e);
			ResponseDTO<Admin> response = new ResponseDTO<Admin>(500,"系統異常，可能連線不到資料庫",null);
		    return ResponseEntity.status(500).body(response);
		}

	}

}
