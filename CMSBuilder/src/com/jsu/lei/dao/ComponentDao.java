package com.jsu.lei.dao;

import com.jsu.lei.comment.IBaseDao;
import com.jsu.lei.model.Component;

public interface ComponentDao extends IBaseDao<Component> {
	public void updateMainClassByModulId(String main,Integer mid);
}
