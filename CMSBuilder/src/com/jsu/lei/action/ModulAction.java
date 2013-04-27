package com.jsu.lei.action;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Context;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.jsu.lei.comment.BaseAction;
import com.jsu.lei.comment.Result;
import com.jsu.lei.model.Modul;
import com.jsu.lei.service.ModulService;

@Controller
@Scope("prototype")
@Path("/")
public class ModulAction extends BaseAction{

	@Autowired ModulService mservice;
	
	/**
	 * 列出项目模块信息
	 * @param id
	 * @return
	 */
	@GET
	@POST
	@Path("/listModul/{id}.html")
	public String listModul(@PathParam("id") String id){
		logger.info("listModul() begin ..");
		try {
			String result="[]";
			if(NumberUtils.isNumber(id)){
				result=mapper.writeValueAsString(mservice.listModulById(new Integer(id)));
				logger.info("listModul() end  ..");
			}
			return result;
		} catch (Exception e) {
			logger.info("listModul() a error happen ",e);
			return "[]";
		}
	}
	/**
	 * 添加一个模块
	 * @param request
	 * @return
	 */
	
	@POST
	@Path("/addModul.html")
	public String addModul(@Context HttpServletRequest request){
		logger.info("addModul() begin ..");
		result.setResultCode(Result.ERROR_CODE);
		result.setSuccess(false);
		try {
			 Modul m= new Modul();
			 BeanUtils.populate(m, request.getParameterMap());
			 if(m.getId()==null){
				 mservice.save(m);
			 }else{
				 mservice.update(m);
			 }
		} catch (Exception e) {
			result.setMsg("数据库保存错误");
			logger.info("addModul() a error happen ",e);
		}
		
		return result.toJson();
	}
	
	
	
	
}
