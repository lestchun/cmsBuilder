package com.jsu.lei.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.commons.lang.StringUtils;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.jsu.lei.vo.FileAttr;
import com.jsu.lei.vo.FileConf;

/**
 * 获取配置属性
 * 
 */
public class ConfigUtils {
    private static Properties p = new Properties();
    private static Map<String, FileConf> fileConf = new ConcurrentHashMap<String, FileConf>();
    private static Properties propertie;
    
    public static void init() throws Exception {
        // 读取config配置文件
       // p.load(this.getClass().getClassLoader().getResourceAsStream("config.properties"));
        // 加载文件配置
        loadFileConf();
    }

    public static String getString(String key) {
        return p.getProperty(key);
    }
    
    /**
     * 获取文件配置相关信息
     * @param key
     * @return
     */
    public static FileConf getFileConf(String key) {
        return fileConf.get(key);
    }
    
    /**
     * 加载文件配置
     * @throws Exception 
     */
    @SuppressWarnings("unchecked")
    private static void loadFileConf() throws Exception {
        SAXReader reader = new SAXReader();
        Document doc = reader.read(ConfigUtils.class.getClassLoader().getResourceAsStream("file.xml"));
        Element rootElement = doc.getRootElement();
        List<Element> file_confs = rootElement.elements();
        for (Element file_conf : file_confs) {
            FileConf fc = new FileConf();
            fc.setId(file_conf.attributeValue("id"));
            fc.setType(file_conf.attributeValue("type"));
            fc.setMaximumSize(Integer.parseInt(file_conf.attributeValue("maximumSize")));
            
            List<Element> file_attrs = file_conf.elements();
            List<FileAttr> fas = new ArrayList<FileAttr>();
            for (Element file_attr : file_attrs) {
                FileAttr fa = new FileAttr();
                fa.setUrl(file_attr.elementText("url"));
                String w = file_attr.elementText("width");
                if (StringUtils.isNotEmpty(w)) {
                    fa.setWidth(Integer.parseInt(w));
                }
                String h = file_attr.elementText("height");
                if (StringUtils.isNotEmpty(h)) {
                    fa.setHeight(Integer.parseInt(h));
                }
                String isSupportDynamic = file_attr.elementText("isSupportDynamic");
                if (StringUtils.isNotEmpty(isSupportDynamic)) {
                    fa.setIsSupportDynamic(Boolean.parseBoolean(isSupportDynamic));
                }
                String isRatio = file_attr.elementText("isRatio");
                if (StringUtils.isNotEmpty(isRatio)) {
                    fa.setIsRatio(Boolean.parseBoolean(isRatio));
                }
                fas.add(fa);
            }
            
            fc.setAttrs(fas);
            fileConf.put(fc.getId(), fc);
        }
    }
    
    /**
     * 取得属性key的值
     * @param key 取得其值的键
     * @return key的值
     */
    public String getProperty(String key)
    {
        if(propertie.containsKey(key)){
            return propertie.getProperty(key);//得到某一属性的值
        }
        else{
            return "";
        }    
    }
    
}
