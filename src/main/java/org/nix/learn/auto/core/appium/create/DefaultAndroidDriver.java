package org.nix.learn.auto.core.appium.create;

import org.nix.learn.auto.core.appium.config.AndroidPhoneConfig;
import org.nix.learn.auto.core.appium.config.BasePhoneConfig;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.URL;

/**
 * @author zhangpei
 * @version 1.0
 * @date 2018/8/19
 */
public class DefaultAndroidDriver extends AbstractAndroidDriver{

    /**
     * 创建一个driver必备的条件
     * @param config 配置信息条件
     * @param appiumPath appium服务器地址
     */
    public DefaultAndroidDriver(AndroidPhoneConfig config, String appiumPath) {
        super(config,appiumPath);
    }

    @Override
    void userInitDesiredCapabilities(DesiredCapabilities capabilities) {

    }
}
