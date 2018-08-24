package org.nix.learn.auto.functions.schema.android;

import org.apache.log4j.Logger;
import org.nix.learn.auto.core.appium.config.AndroidPhoneConfig;
import org.nix.learn.auto.core.appium.create.DefaultAndroidDriver;

/**
 * @author zhangpei341@pingan.cn.com 2018/8/24 下午2:26
 * @version 1.0
 */
public class Test {

    private static final Logger logger = Logger.getLogger(Test.class);

    public static void main(String[] args) {
        AndroidPhoneConfig config = new AndroidPhoneConfig(FunctionsApk.APP_ACTIVITY,FunctionsApk.APP_PACKAGE,"a7366dea");

        DefaultAndroidDriver defaultAndroidDriver = new DefaultAndroidDriver(config,"http://172.20.12.31:4723/wd/hub");
        defaultAndroidDriver.getDriver();
    }

}
