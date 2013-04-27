package com.jsu.lei.action;

import java.lang.reflect.Field;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Context;

import net.sf.json.JSONSerializer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.jsu.lei.comment.BaseAction;
import com.jsu.lei.model.Component;
import com.jsu.lei.service.ComponentService;
import com.jsu.lei.service.ProjectService;
import com.jsu.lei.util.ClassUtil;

@Controller
@Scope("prototype")
@Path("/")
public class ComponentAction extends BaseAction {

	@Autowired ProjectService pservice;
	@Autowired ComponentService cservice;
	
	/**
	 * 通过模块ID找出对应的类信息
	 * @param request
	 * @param id 模块编号
	 * @return
	 */
	@GET
	@POST
	@Path("/listComponeClass/{pid:[0-9]+}.html")
	public String listComponeClass(@Context HttpServletRequest request,@PathParam("pid") String id) {
		
		logger.info("listComponeClass() begin .."+id);
		
		List<Map<String, String>> tree = new ArrayList<Map<String, String>>();
		 
		List<Component> l= cservice.findByModulId(new Integer(id));
		
		if(null==l||l.size()==0){
			logger.info("listComponeClass() end there is  no component avalible .."+id);
			return "[]";
		}
		
		String className=l.get(0).getMainClass();
		
		String searchv = request.getParameter("id");
		
		ClassLoader loder=null;
		try {
			loder = pservice.getClassLoaderByModulId(new Integer(id),request);
		} catch (NumberFormatException e1) {
			logger.info("listComponeClass() a NumberFormatException error happend ",e1); 
		} catch (MalformedURLException e1) {
			logger.info("listComponeClass() a MalformedURLException error happend ",e1); 
		}
		if (null != loder) {

			if (null == className || "".equals(className)) {
				return "[]";
			}
			try {
				Class<?> clazz = loder.loadClass(className);
				if (null != searchv && !"".equals(searchv)) {
					String[] fies = searchv.split("\\.");
					for (int i = 0; i < fies.length; i++) {
						clazz = clazz.getDeclaredField(fies[i]).getType();
					}
					searchv = searchv + ".";
				} else {
					searchv = "";
				}
				Field[] fields = clazz.getDeclaredFields();
				for (Field field : fields) {
					Map<String, String> param = new HashMap<String, String>();
					param.put("id", searchv + field.getName());
					param.put("text", field.getName());
					if (!ClassUtil.isWrapClass(field.getType())) {
						param.put("state", "closed");
					}
					tree.add(param);
				}
				return JSONSerializer.toJSON(tree).toString();
			} catch (ClassNotFoundException e) {
				logger.info("listComponeClass() a ClassNotFoundException error happend ",e); 
			} catch (NoSuchFieldException e) {
				logger.info("listComponeClass() a NoSuchFieldException error happend ",e); 
			} catch (SecurityException e) {
				logger.info("listComponeClass() a SecurityException error happend ",e); 
			}
		}
		logger.info("listComponeClass()  end ..."); 
		return "[]";
	}
	
}
