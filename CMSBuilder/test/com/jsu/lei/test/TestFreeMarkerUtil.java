package com.jsu.lei.test;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.junit.Test;

import com.jsu.lei.util.FreeMarkerUtil;

import freemarker.template.Template;
import freemarker.template.TemplateException;

public class TestFreeMarkerUtil {
    private static final  Logger logger = Logger.getLogger(TestFreeMarkerUtil.class);
	@Test
	public void test_free(){
		
		try {
			Template temp=  FreeMarkerUtil.getTemp("login.ftl");
			 Map<String, Object> root=new HashMap<String, Object>();
			 root.put("a", "{'a':'b'}");
			 System.out.println(FreeMarkerUtil.readTemp(temp, root));
			
			
		} catch (IOException | TemplateException e) {
			e.printStackTrace();
		}
		
	}
	@Test
	public void testClass(){
//		org.apache.log4j.DailyRollingFileAppender
//		LocalContainerEntityManagerFactoryBean b;
		logger.info("sss");
	}
}