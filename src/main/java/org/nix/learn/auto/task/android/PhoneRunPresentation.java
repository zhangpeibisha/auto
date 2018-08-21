package org.nix.learn.auto.task.android;

import java.util.ArrayList;
import java.util.List;

/**
 * 手机、服务器信息集合
 *
 * @author zhangpei341@pingan.cn.com 2018/8/21 下午2:21
 * @version 1.0
 */
public class PhoneRunPresentation {
    /**
     * 服务器地址
     */
    private String serverPath;

    /**
     * 手机操作系统
     */
    private String os;

    /**
     * 手机分辨率
     */
    private String resolvingPower;

    /**
     * 这个手机上运行的运行报告结果
     */
    private List<SchemaRunPresentation> presentations;

    public PhoneRunPresentation(String serverPath, String os, String resolvingPower) {
        this.serverPath = serverPath;
        this.os = os;
        this.resolvingPower = resolvingPower;
    }

    public PhoneRunPresentation(String serverPath, String os, String resolvingPower, List<SchemaRunPresentation> presentations) {
        this.serverPath = serverPath;
        this.os = os;
        this.resolvingPower = resolvingPower;
        this.presentations = presentations;
    }

    /**
     * 添加一个schema报告进入单管道报告中
     * @param schemaRunPresentation schema运行结果
     */
    public void addSchemaPresentation(SchemaRunPresentation schemaRunPresentation){
        if (presentations == null){
            presentations = new ArrayList<>();
        }
        presentations.add(schemaRunPresentation);
    }






    public String getServerPath() {
        return serverPath;
    }

    public void setServerPath(String serverPath) {
        this.serverPath = serverPath;
    }

    public String getOs() {
        return os;
    }

    public void setOs(String os) {
        this.os = os;
    }

    public String getResolvingPower() {
        return resolvingPower;
    }

    public void setResolvingPower(String resolvingPower) {
        this.resolvingPower = resolvingPower;
    }

    public List<SchemaRunPresentation> getPresentations() {
        return presentations;
    }

    public void setPresentations(List<SchemaRunPresentation> presentations) {
        this.presentations = presentations;
    }

    @Override
    public String toString() {
        return "PhoneRunPresentation{" +
                "serverPath='" + serverPath + '\'' +
                ", os='" + os + '\'' +
                ", resolvingPower='" + resolvingPower + '\'' +
                ", presentations=" + presentations +
                '}';
    }
}
