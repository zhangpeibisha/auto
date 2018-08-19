package org.nix.learn.auto.core.appium.create;

import io.appium.java_client.AppiumDriver;
import org.nix.learn.auto.core.appium.config.BasePhoneConfig;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.URL;

/**
 * appium的driver的创建地，所有appium
 * 的操作都将从此启动
 * @author zhangpei
 * @version 1.0
 * @date 2018/8/19
 */
public interface DriverFactory {

    /**
     * 创建一个驱动
     */
    void createDriver();

    /**
     * 获取appium服务器的地址
     * @return 获取appium服务器的地址
     */
    URL getAppiumPath();

    /**
     * 获取driver的配置信息
     * @param config 配置信息实体类
     * @return 获取创建session的配置信息
     */
     DesiredCapabilities getDesiredCapabilities(BasePhoneConfig config);
}
