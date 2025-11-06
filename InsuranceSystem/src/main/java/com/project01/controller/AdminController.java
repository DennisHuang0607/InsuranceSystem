package com.project01.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.project01.entity.Admin;
import com.project01.entity.Message;
import com.project01.service.AdminService;

@RestController
@RequestMapping(path = "/api/v1/admin")
public class AdminController {
	
	private static final Logger logger = LoggerFactory.getLogger(AdminController.class);
	
	public AdminController() {
		logger.info("Admin Controller 配置成功...");
	}
	
	@Autowired
	private AdminService adminService;
	
	//新增管理員
	@PostMapping(path = "/register",consumes = "application/json",produces = "application/json")
	public ResponseEntity<Message> registerAdminController(@RequestBody Admin admin) {
		ResponseEntity<Message> response = adminService.registerAdmin(admin);

		return response;
	}
	
	//查詢所有管理員
	@GetMapping(path = "/findall",produces = "application/json")
	public ResponseEntity<Message> findAllAdminController(){
		ResponseEntity<Message> response = adminService.findAllAdmin();
		
		return response;
	}
	
	//更新管理員資料
	@PutMapping(path = "/update",consumes = "application/json",produces = "application/json")
	public ResponseEntity<Message> updateAdminController(@RequestBody Admin admin){
		ResponseEntity<Message> response = adminService.upadteAdmin(admin);
		
		return response;
	}
	
	//刪除管理員
	@DeleteMapping(path = "/delete/{account}",produces = "application/json")
	public ResponseEntity<Message> deleteAdminByIdController(@PathVariable("account") String account){
		ResponseEntity<Message> response = adminService.deleteAdminById(account);
		
		return response;
	}
	
	
}
