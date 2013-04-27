package com.jsu.lei.test;

import net.sf.json.JSONSerializer;

import org.junit.Test;

import com.jsu.lei.comment.Result;

public class TestJson {
	@Test
	public void test_01(){
		
		Result r= new Result();
		r.setMsg("你猜");
		
		//System.out.println(r.toJson());
		System.out.println(JSONSerializer.toJSON(r).toString());
	}
}
