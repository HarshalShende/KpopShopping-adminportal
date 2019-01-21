package com.adminportal.service;

import java.util.List;

import com.adminportal.domain.CD;

public interface CDService {
	CD save(CD cd);
	
	List<CD> findAll();
	
	CD findOne(Long id);
	
	void removeOne(Long id);
}
