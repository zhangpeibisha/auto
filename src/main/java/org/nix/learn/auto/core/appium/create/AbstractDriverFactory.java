package org.nix.learn.auto.core.appium.create;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import io.appium.java_client.AppiumDriver;
import org.nix.learn.auto.core.appium.AppiumException;
import org.nix.learn.auto.core.appium.config.BasePhoneConfig;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;

/**
 * @author zhangpei
 * @version 1.0
 * @date 2018/8/19
 */
public abstract class AbstractDriverFactory implements DriverFactory {

    protected AppiumDriver driver;

    protected String appiumPath;

    /**
     * 创建driver时需要携带的额外信息
     */
    protected AdditionalInfo additionalInfo;

    public AbstractDriverFactory(String appiumPath) {
        this.appiumPath = appiumPath;
    }

    public AbstractDriverFactory(String appiumPath, AdditionalInfo additionalInfo) {
        this.appiumPath = appiumPath;
        this.additionalInfo = additionalInfo;
    }

    /**
     * @return 获取到一个driver
     */
    public AppiumDriver getDriver() {
        createDriver();
        return driver;
    }

    /**
     * 用户自定义添加一部分的配置
     * @param capabilities 用户可以额外再添加一部分配置
     */
    void userInitDesiredCapabilities(DesiredCapabilities capabilities){
        if (additionalInfo!=null){
            for (Map.Entry<String,Object> va : readObjectParma().entrySet()) {
                capabilities.setCapability(va.getKey(),va.getValue());
            }
        }
    }

    private Map<String,Object> readObjectParma(){
        String str = JSONObject.toJSONString(additionalInfo);
        return JSONObject.parseObject(str,Map.class);
    }

    @Override
    public DesiredCapabilities getDesiredCapabilities(BasePhoneConfig config) {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        String json = JSONObject.toJSONString(config);
        Map<String,Object> map = JSONObject.parseObject(json,Map.class);
        for (Map.Entry<String,Object> value : map.entrySet()) {
            capabilities.setCapability(value.getKey(),value.getValue());
        }
        userInitDesiredCapabilities(capabilities);
        return capabilities;
    }

    @Override
    public URL getAppiumPath() {
        try {
            return new URL(appiumPath);
        } catch (MalformedURLException e) {
            throw new AppiumException("获取appium服务器地址失败，请检查" +
                    appiumPath+"地址是否正确",e);
        }
    }
}
