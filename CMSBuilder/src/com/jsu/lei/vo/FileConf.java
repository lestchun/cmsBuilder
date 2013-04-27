package com.jsu.lei.vo;

import java.util.List;

/**
 * 文件配置相关信息
 * @author Zhu Jian
 *
 */
public class FileConf {
    /**
     * 终端文件上传配置id以t开始,web端文件上传配置id以w开始
     */
    private String id;
    /**
     * type=1 表示音频文件不做任何处理里直接存储
     * 2 表示图片文件
     */
    private String type;
    /**
     * 最大文件大小
     */
    private int maximumSize; 
    /**
     * 允许上传的类型
     */
    private String allowedTypes;
    /**
     * 属性列表
     */
    private List<FileAttr> attrs;
    
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public int getMaximumSize() {
        return maximumSize;
    }
    public void setMaximumSize(int maximumSize) {
        this.maximumSize = maximumSize;
    }
    public String getAllowedTypes() {
        return allowedTypes;
    }
    public void setAllowedTypes(String allowedTypes) {
        this.allowedTypes = allowedTypes;
    }
    public List<FileAttr> getAttrs() {
        return attrs;
    }
    public void setAttrs(List<FileAttr> attrs) {
        this.attrs = attrs;
    }
}
