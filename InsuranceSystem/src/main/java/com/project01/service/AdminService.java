package com.project01.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.project01.config.DbConfig;
import com.project01.entity.Admin;
import com.project01.entity.Message;
import com.project01.repository.AdminRepository;

@Service
public class AdminService {

	@Autowired
	private AdminRepository adminRepository;
	@Autowired
	private PasswordEncoder passwordEncoder;

	//新增管理員
	public ResponseEntity<Message> registerAdmin(Admin admin) {
		
		String encodedPassword = passwordEncoder.encode(admin.getPassword());
		admin.setPassword(encodedPassword);
		
		ResponseEntity<Message> response = null;
		Message message = new Message();
		
		try {
			int row = adminRepository.register(admin);
			
			if(row==1) {
				message.setCode(200);
				message.setMessage("管理員新增成功");
				response = ResponseEntity.ok(message);
			}
		}
		catch (DuplicateKeyException e) {
		    message.setCode(409);
		    message.setMessage("帳號已存在，請換一個試試");
		    return ResponseEntity.badRequest().body(message);
		}
		catch (DataAccessException e){
			message.setCode(500);
			message.setMessage("管理員新增失敗，系統異常了");
			response = ResponseEntity.status(500).body(message);
		}
		
		return response;
	}
	
	//查詢所有管理員
	public ResponseEntity<Message> findAllAdmin() {
		
		ResponseEntity<Message> response = null;
		Message message = new Message();
		
		try {
			List<Admin> result = adminRepository.findAll();
			message.setCode(200);
			message.setMessage("管理員查詢成功");
			response = ResponseEntity.ok(message);
		}
		catch (DataAccessException e) {
			message.setCode(500);
			message.setMessage("系統異常，可能連線不到資料庫");
			response = ResponseEntity.status(500).body(message);
		}
		
		return response;
	}
	
	//更新管理員資料
	public ResponseEntity<Message> upadteAdmin(Admin admin) {
		
		String encodedPassword = passwordEncoder.encode(admin.getPassword());
		admin.setPassword(encodedPassword);
		
		ResponseEntity<Message> response = null;
		Message message = new Message();
		
		try {
			int row = adminRepository.update(admin);
			if(row == 1) {
				message.setCode(200);
				message.setMessage("管理員資料更新成功");
				response = ResponseEntity.ok(message);
			}
			else {
				message.setCode(404);
				message.setMessage("資料更新失敗，管理員資料不存在");
				response = ResponseEntity.status(404).body(message);
			}
		}
		catch (DataAccessException e) {
			message.setCode(500);
			message.setMessage("系統異常，可能連線不到資料庫");
			response = ResponseEntity.status(500).body(message);
		}
		
		return response;
	}
	
	//刪除管理員
	public ResponseEntity<Message> deleteAdminById(String account) {
		ResponseEntity<Message> response = null;
		Message message = new Message();
		
		try {
			int row = adminRepository.deleteById(account);
			if(row == 1) {
				message.setCode(200);
				message.setMessage("管理員:" + account + " 刪除成功");
				response = ResponseEntity.ok(message);
			}
			else {
				message.setCode(404);
				message.setMessage("管理員:" + account + " 刪除不到資料");
				response = ResponseEntity.status(404).body(message);
			}
		}
		catch (DataAccessException e) {
			message.setCode(500);
			message.setMessage("系統異常，可能連線不到資料庫");
			response = ResponseEntity.status(500).body(message);
		}
		
		return response;
	}

}
