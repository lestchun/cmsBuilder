package com.jsu.lei.service.impl;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jsu.lei.dao.ModulDao;
import com.jsu.lei.dao.ProjectDao;
import com.jsu.lei.model.Project;
import com.jsu.lei.service.ProjectService;

@Service
@Transactional
public class ProjectServiceImpl   implements ProjectService {
	@Autowired ProjectDao pdao;
	@Autowired ModulDao mdao;
	@Override
	public List<Project> listAll() {
		return pdao.listAll();
	}

	@Override
	public void saveProject(Project pro) {
		if(null==pro.getId()){
			pdao.save(pro);
		}else{
			pdao.update(pro);
		}
	}

	@Override
	public void deleteProject(Integer id) {
		 pdao.delete(id);
	}

	@Override
	public Project findById(Integer id) {
		return pdao.findById(id);
	}

	@Override
	public ClassLoader getClassLoader(Integer pid,HttpServletRequest request) throws MalformedURLException {
		Project p=pdao.findById(pid);
		return new URLClassLoader(new URL[]{new URL("file:///"+request.getSession().getServletContext().getRealPath(p.getBeans()))});
	}

	@Override
	public ClassLoader getClassLoaderByModulId(Integer pid,
			HttpServletRequest request)throws MalformedURLException {
		Project p=mdao.findById(pid).getProject(); 
		return new URLClassLoader(new URL[]{new URL("file:///"+request.getSession().getServletContext().getRealPath(p.getBeans()))});
	 
	}

	@Override
	public List<Map<String, String>> getClassesByModulId(Integer pid,
			HttpServletRequest request) throws IOException{
		List<Map<String,String>> entity=new ArrayList<Map<String,String>>();
		Project p=mdao.findById(pid).getProject();
		Map<String,String> map;
		String real=request.getSession().getServletContext().getRealPath(p.getBeans());
		JarFile jfile = new JarFile(real);
		Enumeration<JarEntry> em= jfile.entries();
		int i=0;
		while(em.hasMoreElements()){
			JarEntry ja=em.nextElement();
			String name=ja.getName();
			if(name.endsWith(".class")){
				map= new HashMap<String,String>();
				map.put("id",""+(i++));
				String cls=name.replaceAll("/", ".");
				cls=cls.substring(0,cls.lastIndexOf("."));
				map.put("value",cls);
				entity.add(map);
			}
		}
		jfile.close();
		return entity;
	}
	@Override
	public List<Map<String, String>> getClassesByProjectId(Integer pid,
			HttpServletRequest request) throws IOException{
		List<Map<String,String>> entity=new ArrayList<Map<String,String>>();
		Project p=pdao.findById(pid);
		Map<String,String> map;
		String path=URLDecoder.decode(p.getBeans(),"utf-8");
		String real=request.getSession().getServletContext().getRealPath(path);
		JarFile jfile = new JarFile(real);
		Enumeration<JarEntry> em= jfile.entries();
		int i=0;
		while(em.hasMoreElements()){
			JarEntry ja=em.nextElement();
			String name=ja.getName();
			if(name.endsWith(".class")){
				map= new HashMap<String,String>();
				map.put("id",""+(i++));
				String cls=name.replaceAll("/", ".");
				cls=cls.substring(0,cls.lastIndexOf("."));
				map.put("value",cls);
				entity.add(map);
			}
		}
		jfile.close();
		return entity;
	}
	
}
