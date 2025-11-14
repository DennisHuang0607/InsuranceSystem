package com.project01.controller;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project01.dto.Response;
import com.project01.entity.Insurer;
import com.project01.entity.Message;
import com.project01.repository.InsurerRepository;
import com.project01.service.InsurerService;

@RestController
@RequestMapping(path = "/api/v1/insurer")
public class InsurerController {

	@Autowired
	private InsurerService insurerService;
	@Autowired
	private InsurerRepository insurerRepository;
	@Autowired
	private static Logger logger = LoggerFactory.getLogger(InsurerController.class);
	
	public InsurerController() {
		logger.info("Insurer Controller 配置成功...");
	}
	
	//新增Insurer
	@PostMapping(path = "/register",consumes = "application/json",produces = "application/json")
	public ResponseEntity<Response<Insurer>> registerInsurerController(@RequestBody Insurer insurer) {
		return insurerService.registerInsurer(insurer);
	}
	
	//查詢全部Insurer
	@GetMapping(path = "/findAll",produces = "application/json")
	public ResponseEntity<Response<List<Insurer>>> findAllInsurerController(){
		return insurerService.findAllInsurer();
	}
	
	//查詢單一Insurer
	@GetMapping(path = "/find/{account}",produces = "application/json")
	public ResponseEntity<Response<Optional<Insurer>>> findInsurerByAccountIdController(@PathVariable("account") String accountId){
		return insurerService.findInsurerByAccountId(accountId);
	}
	
	//更新Insurer
	@PutMapping(path = "/update",consumes = "application/json",produces = "application/json")
	public ResponseEntity<Response<Insurer>> updateInsurerController(@RequestBody Insurer updateInsurer){
		return insurerService.updateInsurer(updateInsurer);
	}

	//刪除Insurer
	@DeleteMapping(path = "/delete/{account}",produces = "application/json")
	public ResponseEntity<Response<Insurer>> deleteInsurerByAccountIdController(@PathVariable("account") String accountId){
		return insurerService.deleteInsurerByAccountId(accountId);
	}
	
	//查詢當前登入的Insurer
	@GetMapping("/current")
	public ResponseEntity<Response<Insurer>> getCurrentInsurerController(Authentication authentication) {
	    return insurerService.getCurrentInsurer(authentication);
	}
	
}
