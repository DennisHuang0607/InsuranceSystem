package com.project01.controller;

import java.util.List;

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

import com.project01.dto.ResponseDTO;
import com.project01.entity.Admin;
import com.project01.entity.Message;
import com.project01.repository.AdminRepository;
import com.project01.repository.PolicyRepository;
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
	public ResponseEntity<ResponseDTO<Admin>> registerAdminController(@RequestBody Admin admin) {
		ResponseEntity<ResponseDTO<Admin>> response = adminService.registerAdmin(admin);
		return response;
	}
	
	//查詢所有管理員
	@GetMapping(path = "/findAll",produces = "application/json")
	public ResponseEntity<ResponseDTO<List<Admin>>> findAllAdminController(){
		ResponseEntity<ResponseDTO<List<Admin>>> response = adminService.findAllAdmin();
		return response;
	}
	
	//更新管理員資料
	@PutMapping(path = "/update",consumes = "application/json",produces = "application/json")
	public ResponseEntity<ResponseDTO<Admin>> updateAdminController(@RequestBody Admin admin){
		ResponseEntity<ResponseDTO<Admin>> response = adminService.updateAdmin(admin);
		return response;
	}
	
	//刪除管理員
	@DeleteMapping(path = "/delete/{account}",produces = "application/json")
	public ResponseEntity<ResponseDTO<Admin>> deleteAdminByIdController(@PathVariable("account") String account){
		ResponseEntity<ResponseDTO<Admin>> response = adminService.deleteAdminById(account);
		return response;
	}
	
	
}
