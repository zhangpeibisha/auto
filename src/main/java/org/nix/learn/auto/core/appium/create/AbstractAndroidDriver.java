package org.nix.learn.auto.core.appium.create;

import io.appium.java_client.android.AndroidDriver;
import org.nix.learn.auto.core.appium.config.AndroidPhoneConfig;
import org.nix.learn.auto.core.appium.config.BasePhoneConfig;

/**
 * 安卓的抽象driver创建
 * @author zhangpei
 * @version 1.0
 * @date 2018/8/19
 */
public abstract class AbstractAndroidDriver extends AbstractDriverFactory{

    protected AndroidPhoneConfig config;

    public AbstractAndroidDriver(AndroidPhoneConfig config,String appiumPath) {
        super(appiumPath);
        this.config = config;
    }

    @Override
    public void createDriver() {
        driver = new AndroidDriver(getAppiumPath(),getDesiredCapabilities(config));
    }
}
