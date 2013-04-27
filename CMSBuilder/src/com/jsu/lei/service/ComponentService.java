package com.jsu.lei.service;

import java.util.List;

import com.jsu.lei.model.Component;


public interface ComponentService {
	/**
	 * 通过ID找出对应的组建
	 * @param sid
	 * @return
	 */
	public Component findById(Integer sid);
	
	public List<Component> findByModulId(Integer mid);
	
}
