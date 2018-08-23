package org.nix.learn.auto.core.appium.create;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import io.appium.java_client.AppiumDriver;
import org.nix.learn.auto.core.appium.AppiumException;
import org.nix.learn.auto.core.appium.config.BasePhoneConfig;
import org.openqa.selenium.Capabilities;
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
     * 如果已经有了driver则不再创建
     * @return 获取到一个driver
     */
    public AppiumDriver getDriver() throws AppiumException {
        try {
            if (driver==null){
                createDriver();
            }
            return driver;
        }catch (Exception e){
            throw new AppiumException("driver创建失败:["+appiumPath+"]");
        }
    }


    /**
     * 创建后额外添加信息到driver中
     *
     * @param additionalInfo 需要添加的信息
     * @throws AppiumException 当driver为空时抛出
     */
    public void additionalInfo(AdditionalInfo additionalInfo) throws AppiumException {
        if (driver != null) {
            String json = JSONObject.toJSONString(additionalInfo);
            Map<String, Object> map = JSONObject.parseObject(json, Map.class);
            DesiredCapabilities capabilities = new DesiredCapabilities();
            addCapabilities(capabilities, map);
            driver.getCapabilities().merge(capabilities);
            return;
        }
        throw new AppiumException("driver为空，不可添加额外的信息进入driver");
    }

    /**
     * 根据键值生成配置参数
     *
     * @param key
     * @param value
     */
    public void addCapabilities(String key, Object value) throws AppiumException {
        if (driver != null && key != null && value != null) {
            if (checkKey(key)) {
                throw new AppiumException("键值" + key + "在配置中已经存在，请勿再进行添加");
            }
            DesiredCapabilities capabilities = new DesiredCapabilities();
            capabilities.setCapability(key, value);
            driver.getCapabilities().merge(capabilities);
            return;
        }
        throw new AppiumException("参数不能为空或者driver不能为空");
    }

    /**
     * 检测这个键值是否存在
     *
     * @param key
     * @return
     */
    private boolean checkKey(String key) throws AppiumException {
        if (driver.getCapabilities().getCapability(key) != null) {
            return true;
        }
        return false;
    }

    /**
     * 获取配置信息
     *
     * @return driver的配置信息
     */
    public DesiredCapabilities getDesiredCapabilities() throws AppiumException {
        if (driver != null) {
            return (DesiredCapabilities) driver.getCapabilities();
        }
        throw new AppiumException("参数不能为空或者driver不能为空");
    }


    /**
     * 用户自定义添加一部分的配置
     *
     * @param capabilities 用户可以额外再添加一部分配置
     */
    private void userInitDesiredCapabilities(DesiredCapabilities capabilities) throws AppiumException {
        if (additionalInfo != null) {
            addCapabilities(capabilities, readObjectParma());
        }
    }

    /**
     * 创建时添加额外信息
     *
     * @return 读取的配置信息
     */
    private Map<String, Object> readObjectParma() {
        return readObjectParma(additionalInfo);
    }

    /**
     * 创建后添加额外信息使用
     *
     * @param additionalInfo 额外信息
     * @return 以map形式的配置信息
     */
    private Map<String, Object> readObjectParma(AdditionalInfo additionalInfo) {
        String str = JSONObject.toJSONString(additionalInfo);
        return JSONObject.parseObject(str, Map.class);
    }

    /**
     * 创建时需要配置的确定信息
     *
     * @param config 配置信息实体类
     * @return
     */
    @Override
    public DesiredCapabilities getDesiredCapabilities(BasePhoneConfig config) throws AppiumException {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        String json = JSONObject.toJSONString(config);
        Map<String, Object> map = JSONObject.parseObject(json, Map.class);
        for (Map.Entry<String, Object> va : map.entrySet()) {
            capabilities.setCapability(va.getKey(), va.getValue());
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
                    appiumPath + "地址是否正确", e);
        }
    }

    /**
     * 通过map进行配置环境
     *
     * @param capabilities 将提交给driver的配置信息
     * @param map          准备加入capabilities中的信息
     */
    private void addCapabilities(DesiredCapabilities capabilities, Map<String, Object> map) throws AppiumException {
        for (Map.Entry<String, Object> va : map.entrySet()) {
            if (checkKey(va.getKey())) {
                throw new AppiumException("键值" + va.getKey() + "在配置中已经存在，请勿再进行添加");
            }
            capabilities.setCapability(va.getKey(), va.getValue());
        }
    }
}
