package com.project01.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path = "/policyPerson/page")
public class PolicyPersonPageController {
	
	@GetMapping(path = "/showall")
	public String policyPersonShowAll() {
		return "policypersonshowall";
	}
	
}
