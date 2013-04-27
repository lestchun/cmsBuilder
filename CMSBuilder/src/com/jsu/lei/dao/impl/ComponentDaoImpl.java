package com.jsu.lei.dao.impl;

import org.springframework.stereotype.Repository;

import com.jsu.lei.comment.BaseDao;
import com.jsu.lei.dao.ComponentDao;
import com.jsu.lei.model.Component;

@Repository
public class ComponentDaoImpl extends BaseDao<Component> implements ComponentDao {

	@Override
	public void updateMainClassByModulId(String main, Integer mid) {
		 em.createQuery("update Component m set m.mainClass=? where m.mid=?")
		 .setParameter(1, main)
		 .setParameter(2, mid).executeUpdate();
	}

}
