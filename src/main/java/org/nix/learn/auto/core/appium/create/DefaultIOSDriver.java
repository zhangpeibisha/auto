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

    /**
     * 用户自定义了携带参数
     * @param config 手机系统配置参数
     * @param appiumPath appium服务器配置
     * @param config 额外携带的参数信息
     */
    public DefaultIOSDriver(String appiumPath, AdditionalInfo additionalInfo, IOSPhoneConfig config) {
        super(appiumPath, additionalInfo, config);
    }
}
