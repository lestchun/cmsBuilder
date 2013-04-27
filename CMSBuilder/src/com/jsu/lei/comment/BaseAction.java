package com.jsu.lei.comment;

import org.apache.log4j.Logger;
import org.codehaus.jackson.map.ObjectMapper;


public class BaseAction {
	
	protected Logger logger=Logger.getLogger(this.getClass());
	protected static ObjectMapper mapper = new ObjectMapper(); 
	protected Result result = new Result();
	protected Integer page;
	protected Integer rows;
	
	public Result getResult() {
		return result;
	}
	public void setResult(Result result) {
		this.result = result;
	}
	public Integer getPage() {
		return page;
	}
	public void setPage(Integer page) {
		this.page = page;
	}
	public Integer getRows() {
		return rows;
	}
	public void setRows(Integer rows) {
		this.rows = rows;
	}
	
}
