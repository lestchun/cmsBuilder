package com.jsu.lei.comment;

import javax.xml.bind.annotation.XmlRootElement;

import net.sf.json.JSONSerializer;

import org.codehaus.jackson.map.ObjectMapper;

@XmlRootElement
public class Result {

	public final static String SUCCESS_CODE = "1000";
	public final static String ERROR_CODE = "1001";
	protected static ObjectMapper mapper = new ObjectMapper(); 
	private Long total;
	private Object rows;
	private String resultCode;
	private boolean success;
	private String msg;

	public Long getTotal() {
		return total;
	}

	public void setTotal(Long total) {
		this.total = total;
	}

	public Object getRows() {
		return rows;
	}

	public void setRows(Object rows) {
		this.rows = rows;
	}

	public String getResultCode() {
		return resultCode;
	}

	public void setResultCode(String resultCode) {
		this.resultCode = resultCode;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String toJson(){
//		JSONObject js = new JSONObject();
//		js.put("msg", msg);
//		js.put("resultCode", resultCode);
//		js.put("total", total);
//		js.put("rows", rows);
//		js.put("success", success);
//		return js.toString();
		return JSONSerializer.toJSON(this).toString();
//		return mapper.writeValueAsString(this);
	}
}
