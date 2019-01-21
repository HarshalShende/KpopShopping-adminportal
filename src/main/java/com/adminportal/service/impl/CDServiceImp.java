package com.adminportal.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.adminportal.domain.CD;
import com.adminportal.repository.CDRepository;
import com.adminportal.service.CDService;

@Service
public class CDServiceImp implements CDService{
	
	@Autowired
	private CDRepository cdRepository;
	
	@Override
	public CD save(CD cd) {
		return cdRepository.save(cd);
	}
	
	@Override
	public List<CD> findAll(){
		return (List<CD>)cdRepository.findAll();
	}
	
	@Override
	public CD findOne(Long id) {
		return cdRepository.findById(id).orElse(null);
	}
	
	@Override
	public void removeOne(Long id) {
		cdRepository.delete(findOne(id));
	}

}
