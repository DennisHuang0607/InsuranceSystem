package com.project01.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path = "/insuranceType/page")
public class InsuranceTypePageController {
	
	@GetMapping(path = "/showall")
	public String insuranceTypeShowAll() {
		return "typeshowall";
	}
	
}
