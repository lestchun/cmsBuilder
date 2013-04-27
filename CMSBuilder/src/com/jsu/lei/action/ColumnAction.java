package com.jsu.lei.action;

import java.io.IOException;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.jsu.lei.comment.BaseAction;
import com.jsu.lei.service.ColumnService;
import com.jsu.lei.service.ModulService;

@Controller
@Scope("prototype")
@Path("/")
public class ColumnAction extends BaseAction {

	@Autowired
	ColumnService cservice;
	@Autowired
	ModulService mservice;

	/**
	 * 列出项目指定模块的列
	 * 
	 * @param pid
	 * @return
	 */
	@GET
	@POST
	@Path("/listColumn/{pid:[0-9]+}.html")
	@Produces("application/json")
	public String listColumnByModul(@PathParam("pid") String pid) {
		logger.info("listColumnByModul() begin .. " + pid);

		try {
			logger.info("listColumnByModul() end .. ");
			return mapper.writeValueAsString(cservice .findByModulId(new Integer(pid)));
		} catch (JsonGenerationException e) {
			logger.info( "listColumnByModul() a error JsonGenerationException happend  .. ", e);
		} catch (JsonMappingException e) {
			logger.info( "listColumnByModul() a error JsonMappingException happend  .. ", e);
		} catch (IOException e) { 
			logger.info("listColumnByModul() a error IOException happend  .. ", e);
		}

		return "[]";
	}
}
