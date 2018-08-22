package org.nix.learn.auto.core.appium;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.Capabilities;

/**
 * appium常用方法
 * @author zhangpei341@pingan.cn.com 2018/8/20 下午5:20
 * @version 1.0
 */
public class AppiumUtils {


    public static String getPhoneId(AppiumDriver driver){
        return driver.getCapabilities().getCapability("udid").toString();
    }

    /**
     * 获取配置里面的参数信息
     * @param driver
     * @param capabilityName
     * @return
     */
    public static String getCapabilities(AppiumDriver driver , String capabilityName){
        Capabilities capabilities = driver.getCapabilities();
        if (capabilities.is(capabilityName)){
            return capabilities.getCapability(capabilityName).toString();
        }
        return null;
    }
}
