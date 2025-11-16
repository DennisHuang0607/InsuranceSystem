package com.project01.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.project01.entity.Admin;
import com.project01.entity.Insurer;
import com.project01.repository.AdminRepository;
import com.project01.repository.InsurerRepository;

@Controller
@RequestMapping(path = "/admin/page")
public class AdminPageController {
	private static final Logger logger = LoggerFactory.getLogger(AdminPageController.class);
	
	public AdminPageController(){
		logger.info("Admin Page Controller 配置成功...");
	}
	
	@Autowired
	private AdminRepository adminRepository;
	@Autowired
	private InsurerRepository insurerRepository;
	
	@GetMapping(path = "/home")
	public String adminPage() {
		return "adminpage";
	}
	
	@GetMapping(path = "/adminshowall")
	public String adminShowAll(Model model) {
		List<Admin> adminList = adminRepository.findAll();
		model.addAttribute("admins",adminList);
		
		return "adminshowall";
	}
	
	@GetMapping(path = "/insurershowall")
	public ModelAndView insurerShowAll() {
		List<Insurer> insurerList = insurerRepository.findAll();
		ModelAndView mav = new ModelAndView("insurershowall");
		mav.addObject("insurers", insurerList);
		
		return mav;
	}
	
	
}
