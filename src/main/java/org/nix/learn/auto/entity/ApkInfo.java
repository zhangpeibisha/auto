package org.nix.learn.auto.entity;

import org.apache.log4j.Logger;

import java.nio.file.Path;
import java.util.Date;

/**
 * 进行测试的目的apk详细信息
 * @author zhangpei341@pingan.cn.com 2018/8/22 下午6:16
 * @version 1.0
 */
public class ApkInfo {

    private static final Logger logger = Logger.getLogger(ApkInfo.class);

    /**
     * apk版本号
     */
    private String version;

    /**
     * 运行环境
     */
    private String runEnvironment;

    /**
     * apk安装路径
     */
    private Path installPath;

    /**
     * apk的包名
     */
    private String appPackage;

    /**
     * apk的启动页
     */
    private String appActivity;

    /**
     * 生成时间
     */
    private Date createTime;

    public ApkInfo(String version) {
        this.version = version;
    }

    public static Logger getLogger() {
        return logger;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getRunEnvironment() {
        return runEnvironment;
    }

    public void setRunEnvironment(String runEnvironment) {
        this.runEnvironment = runEnvironment;
    }

    public Path getInstallPath() {
        return installPath;
    }

    public void setInstallPath(Path installPath) {
        this.installPath = installPath;
    }

    public String getAppPackage() {
        return appPackage;
    }

    public void setAppPackage(String appPackage) {
        this.appPackage = appPackage;
    }

    public String getAppActivity() {
        return appActivity;
    }

    public void setAppActivity(String appActivity) {
        this.appActivity = appActivity;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "ApkInfo{" +
                "version='" + version + '\'' +
                ", runEnvironment='" + runEnvironment + '\'' +
                ", installPath=" + installPath +
                ", appPackage='" + appPackage + '\'' +
                ", appActivity='" + appActivity + '\'' +
                ", createTime=" + createTime +
                '}';
    }
}
