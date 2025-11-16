package com.project01.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path = "/insurer/page")
public class InsurerPageController {
	private static final Logger logger = LoggerFactory.getLogger(AdminPageController.class);
	
	public InsurerPageController(){
		logger.info("Insurer Page Controller 配置成功...");
	}
	
	@GetMapping(path = "/home")
	public String insurerPage() {
		return "insurerpage";
	}
}
