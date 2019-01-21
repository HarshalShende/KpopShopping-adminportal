package com.adminportal.controller;


import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.adminportal.service.CDService;

@RestController
public class ResourceController {

	@Autowired
	private CDService cdService;
	
	
	@RequestMapping(value="/cd/removeList", method=RequestMethod.POST)
	public String removeList(
			@RequestBody ArrayList<String> cdIdList, 
			Model model
			) {
		for (String id: cdIdList) {
			String cdId = id.substring(8);
			cdService.removeOne(Long.parseLong(cdId));		
		}
		
		return "delete success";
	}
}
