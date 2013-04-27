package com.jsu.lei.service.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jsu.lei.dao.ComponentDao;
import com.jsu.lei.dao.ModulDao;
import com.jsu.lei.model.Component;
import com.jsu.lei.model.Modul;
import com.jsu.lei.service.ModulService;

@Service
@Transactional
public class ModulServiceImpl  implements ModulService {
	
	@Autowired ModulDao mdao;
	@Autowired ComponentDao cdao;
	@Override
	public void save(Modul modul) {
		Component com= new Component();
		com.setModul(modul);
		com.setMainClass(modul.getMainClass());
		Set<Component> l= new HashSet<Component>();
		l.add(com);
		modul.setComponent(l);
		mdao.save(modul);
		
	}
 
	@Override
	public List<Modul> listModulById(Integer id) {
		return mdao.findByProperty("project.id", id);
	}

	@Override
	public Modul findById(Integer id) {
		return mdao.findById(id);
	}

	@Override
	public void update(Modul modul) {
		mdao.update(modul);
		cdao.updateMainClassByModulId(modul.getMainClass(), modul.getId());
	}
	
}
