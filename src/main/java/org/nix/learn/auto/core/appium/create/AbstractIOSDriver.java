package org.nix.learn.auto.core.appium.create;

import io.appium.java_client.ios.IOSDriver;
import org.nix.learn.auto.core.appium.config.IOSPhoneConfig;

/**
 * @author zhangpei
 * @version 1.0
 * @date 2018/8/19
 */
public abstract class AbstractIOSDriver extends AbstractDriverFactory {

    protected IOSPhoneConfig config;

    public AbstractIOSDriver(IOSPhoneConfig config, String appiumPath) {
        super(appiumPath);
        this.config = config;
    }

    @Override
    public void createDriver() {
        driver = new IOSDriver(getAppiumPath(), getDesiredCapabilities(config));
    }
}
