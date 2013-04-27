package com.jsu.lei.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jsu.lei.dao.ComponentDao;
import com.jsu.lei.model.Component;
import com.jsu.lei.service.ComponentService;

@Service
@Transactional
public class ComponentServiceImpl  implements ComponentService {

	@Autowired ComponentDao cdao;
	
	@Override
	public Component findById(Integer sid) {
		return cdao.findById(sid);
	}

	@Override
	public List<Component> findByModulId(Integer mid) {
		return cdao.findByProperty("modul.id", mid);
	}
	
 
}
