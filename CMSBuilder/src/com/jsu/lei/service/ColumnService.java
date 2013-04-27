package com.jsu.lei.service;

import java.util.List;

import com.jsu.lei.model.Column;


public interface ColumnService  {
	/**
	 * 通过 组件包编号找出对应的列 
	 * @param cid
	 * @return
	 */
	public List<Column> findByComponentId(Integer cid);
	/**
	 * 通过项目模块找出对应的列
	 * @param cid
	 * @return
	 */
	public List<Column> findByModulId(Integer cid);
	
	
}
