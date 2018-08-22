package org.nix.learn.auto.functions.schema.android;

import org.apache.log4j.Logger;

/**
 * 进行测试的目的apk详细信息
 * @author zhangpei341@pingan.cn.com 2018/8/22 下午6:16
 * @version 1.0
 */
public class TestApkInfo {

    private static final Logger logger = Logger.getLogger(TestApkInfo.class);

    private String version;


    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }
}
