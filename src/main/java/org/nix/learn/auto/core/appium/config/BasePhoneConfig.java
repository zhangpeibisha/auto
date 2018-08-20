package org.nix.learn.auto.core.appium.config;


import com.alibaba.fastjson.JSONObject;

import java.io.Serializable;
import java.util.Map;

/**
 * 定义基础的配置信息
 * 字段名字需要和 DesiredCapabilities 定义的关键字名字相同，因为
 * 默认获取配置信息是通过这个类进行的，如果不按照这个规定，请继承
 *
 * @author zhangpei341@pingan.cn.com 2018/8/7 上午10:50
 * @version 1.0
 */
public class BasePhoneConfig implements Serializable {

    /**
     * 创建app需要等driver
     * @param platformName 操作系统名字
     * @param platformVersion 操作系统版本
     * @param deviceName 手机名字
     * @param udid 手机标示序列号
     */
    public BasePhoneConfig(String platformName, String platformVersion, String deviceName, String udid) {
        this.platformName = platformName;
        this.platformVersion = platformVersion;
        this.deviceName = deviceName;
        this.udid = udid;
    }



    /**
     * 你想使用的自动化测试引擎|`Appium` (默认) 或 `Selendroid
     */
    protected String automationName = "Appium";

    /**
     * 你要测试的手机操作系统|`iOS`, `Android`, 或 `FirefoxOS`
     */
    protected String platformName;

    /**
     * 手机操作系统版本|例如： `7.1`, `4.4`
     */
    protected String platformVersion;

    /**
     * 使用的手机类型或模拟器类型
     * `iPhone Simulator`, `iPad Simulator`, `iPhone Retina 4-inch`, `Android Emulator`,
     * `Galaxy S4`, 等。在 iOS 上，这个关键字的值必须是使用 `instruments -s devices`
     * 得到的可使用的设备名称之一。在 Android 上，这个关键字目前不起作用。
     */
    protected String deviceName;

    /**
     * `.ipa` or `.apk`文件所在的本地绝对路径或者远程路径,
     * 也可以是一个包括两者之一的`.zip`。 Appium会先尝试安装路径对应的应用在适当的真机或模拟器上。
     * 针对Android系统，如果你指定`app-package`和`app-activity`
     * (具体见下面)的话，那么就可以不指定`app`。
     * **会与 `browserName` 冲突** |比如`/abs/path/to/my.apk`或`http://myapp.com/app.ipa`
     */
    protected String app;

    /**
     * 需要进行自动化测试的手机 web 浏览器名称。如果是对应用进行自动化测试，这个关键字的值应为空。
     * |iOS 系统上可以用 ‘Safari‘ ，Android 系统上可以用 ‘Chrome‘, ‘Chromium‘, 或 ‘Browser‘。|
     * |`newCommandTimeout`|设置命令超时时间，单位：秒。
     * 达到超时时间仍未接收到新的命令时 Appium 会假设客户端退出然后自动结束会话。|比如 `60`
     */
    protected String browserName;

    /**
     * 设置命令超时时间，单位：秒。达到超时时间仍未接收到新的命令时 Appium 会假设客户端退出然后自动结束会话。|比如 `60`
     */
    protected String newCommandTimeout = "60";

    /**
     * Appium是否需要自动安装和启动应用。默认值`true`|`true`, `false`|
     */
    protected boolean autoLaunch = true;

    /**
     * (Sim/Emu-only) 设定模拟器 ( simulator / emulator ) 的语言。|如： `fr`|
     */
    protected String language;

    /**
     * (Sim/Emu-only) 设定模拟器 ( simulator / emulator ) 的区域设置。|如： `fr_CA`|
     */
    protected String locale;

    /**
     * 连接的物理设备的唯一设备标识|如： `1ae203187fc012g`
     */
    protected String udid;

    /**
     * (Sim/Emu-only) 在一个设定的方向模式中开始测试|`LANDSCAPE` (横向)  或 `PORTRAIT` (纵向)
     */
    protected String orientation = "PORTRAIT";

    /**
     * 直接转换到 WebView 上下文。 默认值 `false`、|`true`, `false`|
     */
    protected boolean autoWebview = false;

    /**
     * 不要在会话前重置应用状态。默认值`false`。|`true`, `false`|
     */
    protected boolean noReset = true;

    /**
     * iOS) 删除整个模拟器目录。(Android) 通过卸载——而不是清空数据——来重置应用状态。
     * 在 Android 上，这也会在会话结束后自动清除被测应用。默认值 `false`|`true`, `false`|
     */
    protected boolean fullReset = false;


    public BasePhoneConfig() {

    }

    public String getAutomationName() {
        return automationName;
    }

    public void setAutomationName(String automationName) {
        this.automationName = automationName;
    }

    public String getPlatformName() {
        return platformName;
    }

    public void setPlatformName(String platformName) {
        this.platformName = platformName;
    }

    public String getPlatformVersion() {
        return platformVersion;
    }

    public void setPlatformVersion(String platformVersion) {
        this.platformVersion = platformVersion;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public String getApp() {
        return app;
    }

    public void setApp(String app) {
        this.app = app;
    }

    public String getBrowserName() {
        return browserName;
    }

    public void setBrowserName(String browserName) {
        this.browserName = browserName;
    }

    public String getNewCommandTimeout() {
        return newCommandTimeout;
    }

    public void setNewCommandTimeout(String newCommandTimeout) {
        this.newCommandTimeout = newCommandTimeout;
    }

    public boolean isAutoLaunch() {
        return autoLaunch;
    }

    public void setAutoLaunch(boolean autoLaunch) {
        this.autoLaunch = autoLaunch;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getLocale() {
        return locale;
    }

    public void setLocale(String locale) {
        this.locale = locale;
    }

    public String getUdid() {
        return udid;
    }

    public void setUdid(String udid) {
        this.udid = udid;
    }

    public String getOrientation() {
        return orientation;
    }

    public void setOrientation(String orientation) {
        this.orientation = orientation;
    }

    public boolean isAutoWebview() {
        return autoWebview;
    }

    public void setAutoWebview(boolean autoWebview) {
        this.autoWebview = autoWebview;
    }

    public boolean isNoReset() {
        return noReset;
    }

    public void setNoReset(boolean noReset) {
        this.noReset = noReset;
    }

    public boolean isFullReset() {
        return fullReset;
    }

    public void setFullReset(boolean fullReset) {
        this.fullReset = fullReset;
    }

    @Override
    public String toString() {
        return "BasePhoneInfo{" +
                "automationName='" + automationName + '\'' +
                ", platformName='" + platformName + '\'' +
                ", platformVersion='" + platformVersion + '\'' +
                ", deviceName='" + deviceName + '\'' +
                ", app='" + app + '\'' +
                ", browserName='" + browserName + '\'' +
                ", newCommandTimeout='" + newCommandTimeout + '\'' +
                ", autoLaunch=" + autoLaunch +
                ", language='" + language + '\'' +
                ", locale='" + locale + '\'' +
                ", udid='" + udid + '\'' +
                ", orientation='" + orientation + '\'' +
                ", autoWebview=" + autoWebview +
                ", noReset=" + noReset +
                ", fullReset=" + fullReset +
                '}';
    }
}
