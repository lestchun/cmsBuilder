package com.jsu.lei.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jsu.lei.dao.ColumnDao;
import com.jsu.lei.model.Column;
import com.jsu.lei.service.ColumnService;

@Service
@Transactional
public class ColumnServiceImpl implements ColumnService {

	@Autowired ColumnDao cdao;
	@Override
	public List<Column> findByComponentId(Integer cid) {
		 return cdao.findByProperty("component.id", cid);
	}
	@Override
	public List<Column> findByModulId(Integer cid) {
		return cdao.findByProperty("component.modul.id", cid);
	}
	
}
