package org.nix.learn.auto.core.appium.config;

/**
 * ios系统手机的初始化信息
 *
 * @author zhangpei341@pingan.cn.com 2018/8/7 下午4:03
 * @version 1.0
 */
public class IOSPhoneConfig extends BasePhoneConfig {

    /**
     * (Sim-only) 为iOS的模拟器设置日历格式|如 `gregorian` (公历)
     */
    private String calendarFormat;

    /**
     * 被测应用的 bundle ID 。用于在真实设备中启动测试，也用于使用其他需要 bundle ID 的关键字启动测试。
     * 在使用 bundle ID 在真实设备上执行测试时，你可以不提供 `app` 关键字，但你必须提供 `udid` 。
     * |如 `io.appium.TestApp`|
     */
    private String bundleId;

    /**
     * 连接的真实设备的唯一设备编号 ( Unique device identifier ) |如 `1ae203187fc012g`|
     */
    private String udid;

    /**
     * 以毫秒为单位，在 Appium 运行失败之前设置一个等待 instruments 的时间 |比如： `20000`
     */
    private String launchTimeout;

    /**
     * (Sim-only) 强制打开或关闭定位服务。默认值是保持当前模拟器的设定|`true` 或 `false`|
     */
    private Boolean locationServicesEnabled;

    /**
     * (Sim-only) 通过修改 plist 文件设定是否允许应用使用定位服务，从而避免定位服务的警告出现。
     * 默认值是保持当前模拟器的设定。请注意在使用这个关键字时，你同时需要使用 `bundleId`
     * 关键字来发送你的应用的 bundle ID。|`true` 或者 `false`|
     */
    private String locationServicesAuthorized;

    /**
     * 当 iOS 的个人信息访问警告 (如 位置、联系人、图片) 出现时，自动选择接受( Accept )。默认值 `false`。|`true` 或者 `false`|
     */
    private Boolean autoAcceptAlerts = false;

    /**
     * 当 iOS 的个人信息访问警告 (如 位置、联系人、图片) 出现时，自动选择不接受( Dismiss )。默认值 `false`。|`true` 或者 `false`|
     */
    private Boolean autoDismissAlerts = false;

    /**
     * 使用原生 intruments 库 (即关闭 instruments-without-delay ) |`true` 或者 `false`|
     */
    private Boolean nativeInstrumentsLib;

    /**
     * (Sim-only) 在Safari中允许"真实的"，非基于 javascript 的 web 点击 (tap) 。
     * 默认值： `false`。注意：取决于 viewport 大小/比例，
     * 点击操作不一定能精确地点中对应的元素。|`true` 或者 `false`|
     */
    private Boolean nativeWebTap = false;

    /**
     * (Sim-only) (>= 8.1) Safari 的初始地址。默认值是一个本地的欢迎页面 | 例如： `https://www.github.com` |
     */
    private String safariInitialUrl;

    /**
     * (Sim-only) 允许 javascript 在 Safari 中创建新窗口。默认保持模拟器当前设置。|`true` 或者 `false`|
     */
    private Boolean safariAllowPopups;

    /**
     * (Sim-only) 阻止 Safari 显示此网站可能存在风险的警告。默认保持浏览器当前设置。|`true` 或者 `false`|
     */
    private Boolean safariIgnoreFraudWarning;

    /**
     * (Sim-only) Safari 是否允许链接在新窗口打开。默认保持浏览器当前设置。|`true` 或者 `false`|
     */
    private Boolean safariOpenLinksInBackground;

    /**
     * | (Sim-only) 当 Appium 会话开始/结束时是否保留存放密码存放记录 (keychains)
     * (库(Library)/钥匙串(Keychains)) |`true` 或者 `false`|
     */
    private Boolean keepKeyChains;

    /**
     * 从哪里查找本地化字符串。默认值 `en.lproj`|`en.lproj`
     */
    private String localizableStringsDir;

    /**
     * 通过 instruments 传递到 AUT 的参数 |如 `-myflag`
     */
    private String processArguments;

    /**
     * 以毫秒为单位，按下每一个按键之间的延迟时间。|如 `100`
     */
    private String interKeyDelay;

    /**
     * 是否在 Appium 的日志中显示设备的日志。
     * 默认值 `false`|`true` 或者 `false`
     */
    private Boolean showIOSLog = false;

    /**
     * 输入文字到文字框的策略。模拟器默认值：`oneByOne` (一个接着一个) 。
     * 真实设备默认值：`grouped` (分组输入)  |`oneByOne`, `grouped` 或 `setValue`|
     */
    private String sendKeyStrategy;

    /**
     * 以秒为单位，生成屏幕截图的最长等待时间。默认值： 10。 |如 `5`|
     */
    private String screenshotWaitTimeout;

    /**
     * 用于判断 "应用是否被启动” 的 iOS 自动化脚本代码。默认情况下系统等待直到页面内容非空。
     * 结果必须是布尔类型。 |例如 `true;`, `target.elements().length > 0;`, `$.delay(5000); true;` |
     */
    private String waitForAppScript;

    public IOSPhoneConfig() {
    }

    public String getCalendarFormat() {
        return calendarFormat;
    }

    public void setCalendarFormat(String calendarFormat) {
        this.calendarFormat = calendarFormat;
    }

    public String getBundleId() {
        return bundleId;
    }

    public void setBundleId(String bundleId) {
        this.bundleId = bundleId;
    }

    public String getUdid() {
        return udid;
    }

    public void setUdid(String udid) {
        this.udid = udid;
    }

    public String getLaunchTimeout() {
        return launchTimeout;
    }

    public void setLaunchTimeout(String launchTimeout) {
        this.launchTimeout = launchTimeout;
    }

    public Boolean getLocationServicesEnabled() {
        return locationServicesEnabled;
    }

    public void setLocationServicesEnabled(Boolean locationServicesEnabled) {
        this.locationServicesEnabled = locationServicesEnabled;
    }

    public String getLocationServicesAuthorized() {
        return locationServicesAuthorized;
    }

    public void setLocationServicesAuthorized(String locationServicesAuthorized) {
        this.locationServicesAuthorized = locationServicesAuthorized;
    }

    public Boolean getAutoAcceptAlerts() {
        return autoAcceptAlerts;
    }

    public void setAutoAcceptAlerts(Boolean autoAcceptAlerts) {
        this.autoAcceptAlerts = autoAcceptAlerts;
    }

    public Boolean getAutoDismissAlerts() {
        return autoDismissAlerts;
    }

    public void setAutoDismissAlerts(Boolean autoDismissAlerts) {
        this.autoDismissAlerts = autoDismissAlerts;
    }

    public Boolean getNativeInstrumentsLib() {
        return nativeInstrumentsLib;
    }

    public void setNativeInstrumentsLib(Boolean nativeInstrumentsLib) {
        this.nativeInstrumentsLib = nativeInstrumentsLib;
    }

    public Boolean getNativeWebTap() {
        return nativeWebTap;
    }

    public void setNativeWebTap(Boolean nativeWebTap) {
        this.nativeWebTap = nativeWebTap;
    }

    public String getSafariInitialUrl() {
        return safariInitialUrl;
    }

    public void setSafariInitialUrl(String safariInitialUrl) {
        this.safariInitialUrl = safariInitialUrl;
    }

    public Boolean getSafariAllowPopups() {
        return safariAllowPopups;
    }

    public void setSafariAllowPopups(Boolean safariAllowPopups) {
        this.safariAllowPopups = safariAllowPopups;
    }

    public Boolean getSafariIgnoreFraudWarning() {
        return safariIgnoreFraudWarning;
    }

    public void setSafariIgnoreFraudWarning(Boolean safariIgnoreFraudWarning) {
        this.safariIgnoreFraudWarning = safariIgnoreFraudWarning;
    }

    public Boolean getSafariOpenLinksInBackground() {
        return safariOpenLinksInBackground;
    }

    public void setSafariOpenLinksInBackground(Boolean safariOpenLinksInBackground) {
        this.safariOpenLinksInBackground = safariOpenLinksInBackground;
    }

    public Boolean getKeepKeyChains() {
        return keepKeyChains;
    }

    public void setKeepKeyChains(Boolean keepKeyChains) {
        this.keepKeyChains = keepKeyChains;
    }

    public String getLocalizableStringsDir() {
        return localizableStringsDir;
    }

    public void setLocalizableStringsDir(String localizableStringsDir) {
        this.localizableStringsDir = localizableStringsDir;
    }

    public String getProcessArguments() {
        return processArguments;
    }

    public void setProcessArguments(String processArguments) {
        this.processArguments = processArguments;
    }

    public String getInterKeyDelay() {
        return interKeyDelay;
    }

    public void setInterKeyDelay(String interKeyDelay) {
        this.interKeyDelay = interKeyDelay;
    }

    public Boolean getShowIOSLog() {
        return showIOSLog;
    }

    public void setShowIOSLog(Boolean showIOSLog) {
        this.showIOSLog = showIOSLog;
    }

    public String getSendKeyStrategy() {
        return sendKeyStrategy;
    }

    public void setSendKeyStrategy(String sendKeyStrategy) {
        this.sendKeyStrategy = sendKeyStrategy;
    }

    public String getScreenshotWaitTimeout() {
        return screenshotWaitTimeout;
    }

    public void setScreenshotWaitTimeout(String screenshotWaitTimeout) {
        this.screenshotWaitTimeout = screenshotWaitTimeout;
    }

    public String getWaitForAppScript() {
        return waitForAppScript;
    }

    public void setWaitForAppScript(String waitForAppScript) {
        this.waitForAppScript = waitForAppScript;
    }

    @Override
    public String toString() {
        return "IOSPhoneInfo{" +
                "calendarFormat='" + calendarFormat + '\'' +
                ", bundleId='" + bundleId + '\'' +
                ", udid='" + udid + '\'' +
                ", launchTimeout='" + launchTimeout + '\'' +
                ", locationServicesEnabled=" + locationServicesEnabled +
                ", locationServicesAuthorized='" + locationServicesAuthorized + '\'' +
                ", autoAcceptAlerts=" + autoAcceptAlerts +
                ", autoDismissAlerts=" + autoDismissAlerts +
                ", nativeInstrumentsLib=" + nativeInstrumentsLib +
                ", nativeWebTap=" + nativeWebTap +
                ", safariInitialUrl='" + safariInitialUrl + '\'' +
                ", safariAllowPopups=" + safariAllowPopups +
                ", safariIgnoreFraudWarning=" + safariIgnoreFraudWarning +
                ", safariOpenLinksInBackground=" + safariOpenLinksInBackground +
                ", keepKeyChains=" + keepKeyChains +
                ", localizableStringsDir='" + localizableStringsDir + '\'' +
                ", processArguments='" + processArguments + '\'' +
                ", interKeyDelay='" + interKeyDelay + '\'' +
                ", showIOSLog=" + showIOSLog +
                ", sendKeyStrategy='" + sendKeyStrategy + '\'' +
                ", screenshotWaitTimeout='" + screenshotWaitTimeout + '\'' +
                ", waitForAppScript='" + waitForAppScript + '\'' +
                ", automationName='" + automationName + '\'' +
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
