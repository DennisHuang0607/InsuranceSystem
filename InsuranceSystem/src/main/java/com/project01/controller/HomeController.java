package com.project01.controller;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.project01.entity.Admin;
import com.project01.entity.Insurer;
import com.project01.repository.InsurerRepository;

import jakarta.servlet.http.HttpServletRequest;

@Controller
@RequestMapping(path = "/insurancesystem")
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	public HomeController() {
		logger.info("Home Controller 配置成功...");
	}
	
	@Autowired
	private InsurerRepository insurerRepository;
	
	@GetMapping(path = "/home")
	public String home(){
		return "insurancehome";
	}
	
	//管理員登入
	@RequestMapping(path = "/admin/login",method = {RequestMethod.GET,RequestMethod.POST})
	public String login(Admin admin,HttpServletRequest request) {
		if(request.getMethod().equals("POST")) {
			System.out.println("管理員登入...");
		}
		
		return "adminlogin";
	}
	
	//保險員登入
	@RequestMapping(path = "/insurer/login",method = {RequestMethod.GET,RequestMethod.POST})
	public String login(Insurer insurer,HttpServletRequest request) {
		if(request.getMethod().equals("POST")) {
			Optional<Insurer> insure = insurerRepository.findByAccountId(insurer.getAccountId());
			if(insure.isPresent()) {
				System.out.println("保險員登入...");
			}
			else {
				System.out.println("帳號密碼可能輸入錯誤...");
			}
		}
		
		return "insurerlogin";
	}
	
	
}
