package com.adminportal.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.adminportal.domain.CD;

@Controller
@RequestMapping("/cd")
public class CDController {

	@RequestMapping("/add")
	public String addCD(Model model) {
		CD cd = new CD();
		model.addAttribute("cd", cd);
		return "addCD";
	}
}
