package com.project01.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path = "/policy/page")
public class PolicyPageController {

	@GetMapping(path = "/create")
	public String policyCreate() {
		return "policycreate";
	}
	
	@GetMapping(path = "/showall")
	public String policyShowAll() {
		return "policyshowall";
	}
	
}
