package com.jsu.lei.service;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.jsu.lei.model.Project;


public interface ProjectService {
	/**
	 * 列出所有的项目
	 * @return
	 */
	public List<Project> listAll();
	/**
	 * 保存项目
	 * @param pro
	 */
	public void saveProject(Project pro);
	/**
	 * 删除项目
	 * @param id
	 */
	public void deleteProject(Integer  id);
	/**
	 * 通过ID找出项目
	 * @param id
	 * @return
	 */
	public Project findById(Integer id);
	/**
	 * 获得上传的类加载器
	 * @param pid
	 * @param request
	 * @return
	 */
	public ClassLoader  getClassLoader(Integer pid,HttpServletRequest request) throws MalformedURLException ;
	/**
	 * 获得上传的类加载器
	 * @param pid
	 * @param request
	 * @return
	 */
	public ClassLoader  getClassLoaderByModulId(Integer pid,HttpServletRequest request) throws MalformedURLException;
	/**
	 * 通过模块编号找出项目中的所有类
	 * @param pid
	 * @param request
	 * @return
	 */
	public List<Map<String,String>>  getClassesByModulId(Integer pid,HttpServletRequest request)throws IOException;
	/**
	 * 通过模块编号找出项目中的所有类
	 * @param pid
	 * @param request
	 * @return
	 */
	public List<Map<String,String>>  getClassesByProjectId(Integer pid,HttpServletRequest request)throws IOException;
}
