package org.nix.learn.auto.task.example;

import io.appium.java_client.android.AndroidDriver;
import org.nix.learn.auto.core.appium.config.AndroidPhoneConfig;
import org.nix.learn.auto.core.appium.create.DefaultAndroidDriver;

/**
 * @author zhangpei341@pingan.cn.com 2018/8/20 上午10:00
 * @version 1.0
 */
public class AndroidTaskDrivers {

    /**
     * appium服务器地址
     */
    private String appiumPath;

    /**
     * 一条案例对应一个手机配置信息
     */
    private AndroidPhoneConfig config;

    public AndroidTaskDrivers(String appiumPath, AndroidPhoneConfig config) {
        this.appiumPath = appiumPath;
        this.config = config;
    }

    /**
     * 得到这个driver
     *
     * @return 一个安卓的驱动
     */
    public AndroidDriver getDriver() {
        return (AndroidDriver) new DefaultAndroidDriver(config, appiumPath).getDriver();
    }


}
