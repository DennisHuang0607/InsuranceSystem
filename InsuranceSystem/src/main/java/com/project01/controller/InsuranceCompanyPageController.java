package com.project01.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path = "/insuranceCompany/page")
public class InsuranceCompanyPageController {

	@GetMapping(path = "/showall")
	public String insuranceCompanyShowAll() {
		return "companyshowall";
	}
}
