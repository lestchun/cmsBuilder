package com.jsu.lei.action;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.jsu.lei.comment.BaseAction;
import com.jsu.lei.comment.Result;
import com.jsu.lei.model.Project;
import com.jsu.lei.service.ProjectService;


@Controller
@Scope("prototype")
@Path("/")
public class ProjectAction extends BaseAction{
	
	@Autowired ProjectService pservice;
	/**
	 * 列出所有项目
	 * @return
	 */
	@GET
	@POST
	@Path("/listProject.html")
	public String listProject(){
		logger.info("listProject() begin ..");
		try {
			String result=mapper.writeValueAsString(pservice.listAll());
			logger.info("listProject() end  ..");
			return result;
		} catch (Exception e) {
			logger.info("listProject() a error happen ",e);
			return "[]";
		}
	}
	/**
	 * 列出项目的所有类
	 * id 模块编号
	 * @return
	 */
	@GET
	@POST
	@Path("/listProjectClass/{mid:[0-9]+}.html")
	public String listProjectClasses(@PathParam("mid") Integer id,@Context HttpServletRequest request){
		logger.info("listProjectClasses() begin ..");
		try {
			String result=mapper.writeValueAsString(pservice.getClassesByProjectId(id, request));
			logger.info("listProjectClasses() end  ..");
			return result;
		} catch (Exception e) {
			logger.info("listProjectClasses() a Exception happen ",e);
			return "[]";
		}
	}
	
	/**
	 * 根据项目编号删除
	 * @param id
	 * @return
	 */
	@GET
	@POST
	@Produces("application/json")
	@Path("/deleteProject/{projectId}.html")
	public String deleteProject(@PathParam("projectId") String id){
		logger.info("deleteProject() begin .."+id); 
		result.setResultCode(Result.ERROR_CODE);
		if(!NumberUtils.isNumber(id)){
			result.setMsg("删除的id不对");
		}else{
			pservice.deleteProject(new Integer(id));
			result.setSuccess(true);
			result.setResultCode(Result.SUCCESS_CODE);
			result.setMsg("删除成功");
		}
		logger.info("deleteProject() end .."+result.toJson()); 
		return result.toJson();
	}
	/**
	 * 添加一个项目
	 * @param request
	 * @return
	 */
	@POST
	@Path("/addProject.html")
	@Produces("application/json")
	public String addProject(@Context HttpServletRequest request){
		logger.info("addProject() begin ..");
		result.setSuccess(false);
		result.setResultCode(Result.ERROR_CODE);
		Project project= new Project();
		try {
			BeanUtils.populate(project, request.getParameterMap());
			pservice.saveProject(project);
			result.setRows(project);
			result.setSuccess(true);
			result.setResultCode(Result.SUCCESS_CODE);
			result.setMsg("保存成功");
			logger.info("addProject() end  .."+result.toJson());
		} catch (Exception e) {
			result.setMsg("保存数据出错");
			logger.info("addProject() a error happen ",e);
		}
		return result.toJson();
	}
	
}
