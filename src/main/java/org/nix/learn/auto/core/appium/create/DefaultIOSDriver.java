package org.nix.learn.auto.core.appium.create;

import org.nix.learn.auto.core.appium.config.IOSPhoneConfig;
import org.openqa.selenium.remote.DesiredCapabilities;

/**
 * @author zhangpei
 * @version 1.0
 * @date 2018/8/19
 */
public class DefaultIOSDriver extends AbstractIOSDriver{

    public DefaultIOSDriver(IOSPhoneConfig config, String appiumPath) {
        super(config, appiumPath);
    }

    @Override
    void userInitDesiredCapabilities(DesiredCapabilities capabilities) {

    }
}
