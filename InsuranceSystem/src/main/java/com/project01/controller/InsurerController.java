package com.project01.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project01.entity.Insurer;
import com.project01.entity.Message;
import com.project01.service.InsurerService;

@RestController
@RequestMapping(path = "/api/v1/insurer")
public class InsurerController {

	@Autowired
	private InsurerService insurerService;
	@Autowired
	private static Logger logger = LoggerFactory.getLogger(InsurerController.class);
	
	public InsurerController() {
		logger.info("Insurer Controller 配置成功...");
	}
	
	//新增Insurer
	@PostMapping(path = "/register",consumes = "application/json",produces = "application/json")
	public ResponseEntity<Message> registerInsurerController(@RequestBody Insurer insurer) {
		ResponseEntity<Message> response = insurerService.registerInsurer(insurer);
		return response;
	}
	
	//查詢全部Insurer
	@GetMapping(path = "/findAll",produces = "application/json")
	public ResponseEntity<Message> findAllInsurerController(){
		ResponseEntity<Message> response = insurerService.findAllInsurer();
		return response;
	}
	
	//查詢單一Insurer
	@GetMapping(path = "/find/{account}",produces = "application/json")
	public ResponseEntity<Message> findInsurerByAccountIdController(@PathVariable("account") String accountId){
		ResponseEntity<Message> response = insurerService.findInsurerByAccountId(accountId);
		return response;
	}
	
	//更新Insurer
	@PutMapping(path = "/update",consumes = "application/json",produces = "application/json")
	public ResponseEntity<Message> updateInsurerController(@RequestBody Insurer updateInsurer){
		ResponseEntity<Message> response = insurerService.updateInsurer(updateInsurer);
		return response;
	}

	//刪除Insurer
	@DeleteMapping(path = "/delete/{account}",produces = "application/json")
	public ResponseEntity<Message> deleteInsurerByAccountIdController(@PathVariable("account") String accountId){
		ResponseEntity<Message> response = insurerService.deleteInsurerByAccountId(accountId);
		return response;
	}
	
}
