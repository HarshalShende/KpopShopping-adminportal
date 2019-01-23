package com.adminportal.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.adminportal.domain.CD;
import com.adminportal.domain.CloudinaryConfig;
import com.adminportal.service.CDService;
import com.cloudinary.utils.ObjectUtils;




@Controller
@RequestMapping("/cd")
public class CDController {

	@Autowired
	private CDService cdService;
	
	@Autowired
    CloudinaryConfig cloudc;
	
	
	@RequestMapping(value="/add", method=RequestMethod.GET)
	public String addCD(Model model) {
		CD cd = new CD();
		model.addAttribute("cd", cd);
		return "addCD";
	}
	
	@RequestMapping(value="/add", method=RequestMethod.POST)
	public String addCDPost(
			@ModelAttribute("cd") CD cd, HttpServletRequest request		
			) throws IOException {
		cdService.save(cd);
		
		MultipartFile cdImage = cd.getCdImage();

		
		if (cdImage.isEmpty()){
			return "redirect:cdList";
        }
        try {
            Map uploadResult =  cloudc.upload(cdImage.getBytes(), 
	    	ObjectUtils.asMap("resourcetype", "auto", "folder", "kpop/", 
	    		    "public_id", cd.getId()+""));
            
        } catch (IOException e){
            e.printStackTrace();
            return "addCD";
        }

		return "redirect:cdList";
	}
	
	
	@RequestMapping("/cdList")
	public String cdList(Model model) {
		List<CD> cdList = cdService.findAll();
		model.addAttribute("cdList", cdList);
		return "cdList";
	}
	
	@RequestMapping("/cdInfo")
	public String cdInfo(@RequestParam("id") Long id, Model model) {
		CD cd = cdService.findOne(id);
		model.addAttribute("cd", cd);
		
		return "cdInfo";
	}
	
	@RequestMapping("/updateCD")
	public String updateCD(@RequestParam("id") Long id, Model model) {
		CD cd = cdService.findOne(id);
		model.addAttribute("cd", cd);
		return "updateCD";
	}
	
	@RequestMapping(value="/updateCD", method=RequestMethod.POST)
	public String updateCDPost(@ModelAttribute("cd") CD cd, HttpServletRequest request) {
		cdService.save(cd);
		
		MultipartFile cdImage = cd.getCdImage();
		
		if (!cdImage.isEmpty()) {
			try {
				 Map uploadResult =  cloudc.upload(cdImage.getBytes(), 
					    	ObjectUtils.asMap("resourcetype", "auto", "folder", "kpop/", 
					    		    "public_id", cd.getId()+""));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return "redirect:/cd/cdInfo?id="+cd.getId();	
	}
	
	@RequestMapping(value="/remove", method=RequestMethod.POST)
	public String remove(
			@ModelAttribute("id") String id,
			Model model
			) {
		cdService.removeOne(Long.parseLong(id.substring(6)));//‘oneCd-***'
		List<CD> cdList = cdService.findAll();
		model.addAttribute("cdList", cdList);
		
		return "redirect:/cd/cdList";
	}
	
}
