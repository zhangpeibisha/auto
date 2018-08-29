package org.nix.learn.auto.model;

import org.apache.log4j.Logger;
import org.nix.learn.auto.model.base.BaseModel;

import java.nio.file.Path;
import java.util.Date;

/**
 * 进行测试的目的apk详细信息
 * @author zhangpei341@pingan.cn.com 2018/8/22 下午6:16
 * @version 1.0
 */
public class ApkInfoModel extends BaseModel {

    private static final Logger logger = Logger.getLogger(ApkInfoModel.class);

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

    public ApkInfoModel() {
    }

    /**
     * 已经安装了信息
     * @param version
     * @param runEnvironment
     * @param appPackage
     * @param appActivity
     * @param createTime
     */
    public ApkInfoModel(String version, String runEnvironment, String appPackage, String appActivity, Date createTime) {
        this.version = version;
        this.runEnvironment = runEnvironment;
        this.appPackage = appPackage;
        this.appActivity = appActivity;
        this.createTime = createTime;
    }

    /**
     * 安装包信息
     * @param version
     * @param runEnvironment
     * @param installPath
     * @param createTime
     */
    public ApkInfoModel(String version, String runEnvironment, Path installPath, Date createTime) {
        this.version = version;
        this.runEnvironment = runEnvironment;
        this.installPath = installPath;
        this.createTime = createTime;
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
