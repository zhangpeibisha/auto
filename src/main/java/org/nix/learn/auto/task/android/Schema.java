package org.nix.learn.auto.task.android;

import org.nix.learn.auto.utils.Base64;

/**
 * schema信息实体类
 * @author zhangpei341@pingan.cn.com 2018/8/21 下午1:27
 * @version 1.0
 */
public class Schema {

    /**
     * 含义、页面名字
     */
    private String name;

    /**
     * 请求地址
     */
    private String path;

    /**
     * 参数
     */
    private String parameter;

    /**
     * 是否使用（存入数据库时使用）
     */
    private Boolean use;

    /**
     * 支持版本（暂时不用，后续完善使用）
     */
    private String supportVersion;

    /**
     * 原始视觉稿，是视觉设计师设计的图片，用于后续匹配的
     * 截图使用的
     */
    private String manuscriptPath;
    /**
     * 有参数的跳转
     * @param name
     * @param path
     * @param parameter
     * @param use
     * @param supportVersion
     */
    public Schema(String name, String path, String parameter, Boolean use, String supportVersion, String manuscriptPath) {
        this.name = name;
        this.path = path;
        this.parameter = parameter;
        this.use = use;
        this.supportVersion = supportVersion;
        this.manuscriptPath = manuscriptPath;
    }

    /**
     * 无参数的的跳转
     * @param name
     * @param path
     * @param use
     * @param supportVersion
     */
    public Schema(String name, String path, Boolean use, String supportVersion, String manuscriptPath) {
        this.name = name;
        this.path = path;
        this.use = use;
        this.supportVersion = supportVersion;
        this.manuscriptPath = manuscriptPath;
    }

    /**
     * 获取schema的完整信息
     * @return 完整的路径信息
     */
    public String obtainCompletePath(){
        if (parameter == null){
            return path;
        }
        // 暂时不加，等完善的时候进行版本检测加入参数校验
        return path + "?_tpl=" + Base64.encode(parameter);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getParameter() {
        return parameter;
    }

    public void setParameter(String parameter) {
        this.parameter = parameter;
    }

    public Boolean getUse() {
        return use;
    }

    public void setUse(Boolean use) {
        this.use = use;
    }

    public String getSupportVersion() {
        return supportVersion;
    }

    public void setSupportVersion(String supportVersion) {
        this.supportVersion = supportVersion;
    }

    @Override
    public String toString() {
        return "Schema{" +
                "name='" + name + '\'' +
                ", path='" + path + '\'' +
                ", parameter='" + parameter + '\'' +
                ", use=" + use +
                ", supportVersion='" + supportVersion + '\'' +
                '}';
    }
}
