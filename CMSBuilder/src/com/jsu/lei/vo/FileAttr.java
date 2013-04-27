package com.jsu.lei.vo;

/**
 * 文件属性相关信息
 * @author Zhu Jian
 *
 */
public class FileAttr {
    /**
     * 存储路径
     */
    private String url;
    /**
     * 宽度
     */
    private Integer width;
    /**
     * 高度
     */
    private Integer height;
    /**
     * true:如果是动态图片就保持动态图片状态不做处理，不是动态图片都处理
     * false:任何图片都处理，动态图片取第一帧处理
     */
    private boolean isSupportDynamic;
    /**
     * 是否等比压缩
     */
    private boolean isRatio;
    
    public String getUrl() {
        return url;
    }
    public void setUrl(String url) {
        this.url = url;
    }
    public Integer getWidth() {
        return width;
    }
    public void setWidth(Integer width) {
        this.width = width;
    }
    public Integer getHeight() {
        return height;
    }
    public void setHeight(Integer height) {
        this.height = height;
    }
    public boolean getIsSupportDynamic() {
        return isSupportDynamic;
    }
    public void setIsSupportDynamic(boolean isSupportDynamic) {
        this.isSupportDynamic = isSupportDynamic;
    }
    public boolean getIsRatio() {
        return isRatio;
    }
    public void setIsRatio(boolean isRatio) {
        this.isRatio = isRatio;
    }
}
