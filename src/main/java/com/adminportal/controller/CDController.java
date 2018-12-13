package com.adminportal.controller;

import java.awt.print.Book;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import com.adminportal.domain.CD;
import com.adminportal.service.CDService;

@Controller
@RequestMapping("/cd")
public class CDController {

	@Autowired
	private CDService cdService;
	
	
	@RequestMapping(value="/add", method=RequestMethod.GET)
	public String addCD(Model model) {
		CD cd = new CD();
		model.addAttribute("cd", cd);
		return "addCD";
	}
	
	@RequestMapping(value="/add", method=RequestMethod.POST)
	public String addCDPost(
			@ModelAttribute("cd") CD cd, HttpServletRequest request		
			) {
		cdService.save(cd);
		
		MultipartFile cdImage = cd.getCdImage();
		
		try {
			byte[] bytes = cdImage.getBytes();
			String name = cd.getId()+".png";
			BufferedOutputStream stream = new BufferedOutputStream(
					new FileOutputStream(new File("src/main/resources/static/image/cd/"+name)));
			stream.write(bytes);
			stream.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "redirect:cdList";
	}
	
	
	@RequestMapping("/cdList")
	public String cdList(Model model) {
		List<CD> cdList = cdService.findAll();
		model.addAttribute("cdList", cdList);
		return "cdList";
	}
	
}
