package com.jsu.lei.service;

import java.util.List;

import com.jsu.lei.model.Modul;


public interface ModulService {
	/**
	 * 保存模块
	 * @param modul
	 */
	public void save(Modul modul);
	/**
	 * 根据项目编号列出所有的模块
	 * @param id  项目编号
	 * @return
	 */
	public List<Modul> listModulById(Integer id);
	/**
	 * 通过id找出对应模块
	 * @param id
	 * @return
	 */
	public Modul findById(Integer id);
	/**
	 * 修改模块
	 * @param modul
	 */
	public void update(Modul modul);
}
