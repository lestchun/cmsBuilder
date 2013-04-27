package com.jsu.lei.util;


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;
import freemarker.template.TemplateException;

public class FreeMarkerUtil {
    private  final static Configuration cfg = new Configuration(); 
    public static String FTL_PATH="WebRoot/WEB-INF/temp"; 
    public static String DEFAULT_CHARESET="utf-8";
    static{
		try {
			cfg.setDirectoryForTemplateLoading(new File("."));
			cfg.setObjectWrapper(new DefaultObjectWrapper());
		    cfg.setDefaultEncoding(DEFAULT_CHARESET);
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    
    public static void insert2Xml(String fileName,String content,String comment) throws IOException, TemplateException{
    	String file="src/"+fileName;
    	Template temp=getTemp(file);
    	Map<String, String> root= new HashMap<String, String>();
    	root.put("start", comment+"-->\n");
    	root.put("content", content);
    	root.put("end", "\n\t<!--${start}${content}${end}");
    	save(temp, root	, file);
    }
    
    /**
     * 把模板插入指定的java文件,不带注释
     */
    public static void insertTemp2Java(Class<?> clazz,String tempName,Object rootMap) throws TemplateException, IOException{
		insertTemp2Java(clazz, tempName, rootMap,"");
    }
    /**
     * 把模板插入指定的java文件带注释
     * 
     */
    public static void insertTemp2Java(Class<?> clazz,String tempName,Object rootMap,String comment) throws TemplateException, IOException{
    	String s =readTemp(getTemp(tempName),rootMap);
    	insertContent(clazz, s,comment);
    }
    /**
     * 给java 文件插入内容
     */
    public static  void insertContent(Class<?> clazz,String content) throws IOException, TemplateException{
    	insertContent(clazz, content,""); 
    }
    
    /**
     * 把模板作为string读出来
     */
    public static String readTemp(Template temp ,Object rootMap) throws TemplateException, IOException{
    	Writer out = new StringWriter();
    	temp.process(rootMap, out);
    	return out.toString() ;
    }
    /**
     * 把模板作为string读出来
     */
    public static String readTemp(String tempName ,Object rootMap) throws TemplateException, IOException{
    	Template temp=getTemp(tempName);
    	 return readTemp(temp, rootMap);
    }
    
    /**
     *插入数据到java文件中，带注释
     */
    public static void insertContent(Class<?> clazz,String content,String about) throws IOException, TemplateException{
    	Template temp =getTemp(clazz);
    	Map<String, Object> map=new HashMap<String, Object>();
    	map.put("start", about+"*/");
    	map.put("end", "/*${start}${content}${end}");
    	map.put("content", content);
    	String path=clazz.getName().replaceAll("\\.", "/");
    	Writer out= new OutputStreamWriter(new FileOutputStream("src/"+path+".java"),DEFAULT_CHARESET);
    	temp.process(map, out);
    	out.flush();
    	out.close();
    }
    /**
     * 通过文件名获得模板
     * @param ftl
     * @return
     * @throws IOException
     */
    public static  Template getTemp(String ftl) throws IOException{
    	if(ftl.endsWith(".ftl")){
    		String filePath=ftl.startsWith("/")?"":"/";
    		return cfg.getTemplate(FTL_PATH+filePath+ftl,DEFAULT_CHARESET);
    	}
    	return  cfg.getTemplate(ftl,DEFAULT_CHARESET);
    }
    /**
     * 获得java模板
     * @param clazz
     * @return
     * @throws IOException
     */
    public static  Template getTemp(Class<?> clazz) throws IOException{
    	String path=clazz.getName().replaceAll("\\.", "/");
    	return cfg.getTemplate("src/"+path+".java",DEFAULT_CHARESET);
    }
    /**
     * 执行并保存模板
     * @param tempName
     * @param rootMap
     * @param saveTo
     * @throws TemplateException
     * @throws IOException
     */
    public  static  void save(String tempName ,Object rootMap,String saveTo) throws TemplateException, IOException{
    	save(getTemp(tempName) , rootMap,saveTo);
    }
    /**
     * 执行并保存模板
     * @param temp
     * @param rootMap
     * @param saveTo
     * @throws TemplateException
     * @throws IOException
     */
    public  static  void save(Template temp ,Object rootMap,String saveTo) throws TemplateException, IOException{
    	File f= new File(saveTo);
    	if(!f.exists()){
    		File parent=f.getParentFile();
    		if(null!=parent&&parent.exists()){
    			parent.mkdirs();
    		}
    	}
    	Writer out= new OutputStreamWriter(new FileOutputStream(f),DEFAULT_CHARESET);
    	temp.process(rootMap, out);
    	out.flush();
    	out.close();
    } 
}
