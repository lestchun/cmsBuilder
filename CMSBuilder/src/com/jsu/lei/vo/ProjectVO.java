package com.jsu.lei.vo;

import javax.ws.rs.FormParam;

public class ProjectVO {
	@FormParam("id")
	private Integer id;
	@FormParam("name")
	private String name;
	@FormParam("descript")
	private String descript;
	@FormParam("logo")
	private String logo;
	@FormParam("addr")
	private String addr;
	@FormParam("databaseType")
	private String databaseType;
	@FormParam("databseName")
	private String databseName;
	@FormParam("username")
	private String username;
	@FormParam("password")
	private String password;
	@FormParam("driverClass")
	private String driverClass;
	@FormParam("dialect")
	private String dialect;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescript() {
		return descript;
	}
	public void setDescript(String descript) {
		this.descript = descript;
	}
	public String getLogo() {
		return logo;
	}
	public void setLogo(String logo) {
		this.logo = logo;
	}
	public String getAddr() {
		return addr;
	}
	public void setAddr(String addr) {
		this.addr = addr;
	}
	public String getDatabaseType() {
		return databaseType;
	}
	public void setDatabaseType(String databaseType) {
		this.databaseType = databaseType;
	}
	public String getDatabseName() {
		return databseName;
	}
	public void setDatabseName(String databseName) {
		this.databseName = databseName;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getDriverClass() {
		return driverClass;
	}
	public void setDriverClass(String driverClass) {
		this.driverClass = driverClass;
	}
	public String getDialect() {
		return dialect;
	}
	public void setDialect(String dialect) {
		this.dialect = dialect;
	}
	
}
