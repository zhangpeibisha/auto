package org.nix.learn.auto.core.appium.create;

import com.alibaba.fastjson.JSONObject;
import org.nix.learn.auto.core.appium.config.AndroidPhoneConfig;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.Map;

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

    /**
     * 用户自定义了携带参数
     * @param config 手机系统配置参数
     * @param appiumPath appium服务器配置
     * @param config 额外携带的参数信息
     */
    public DefaultAndroidDriver(String appiumPath, AdditionalInfo additionalInfo, AndroidPhoneConfig config) {
        super(appiumPath, additionalInfo, config);
    }
}
