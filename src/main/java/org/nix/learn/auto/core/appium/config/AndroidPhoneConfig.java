package org.nix.learn.auto.core.appium.config;

import java.util.Objects;

/**
 * 安卓手机特有的信息
 *
 * @author zhangpei341@pingan.cn.com 2018/8/7 下午3:28
 * @version 1.0
 */
public class AndroidPhoneConfig extends BasePhoneConfig {

    /**
     * 测试安卓手机的必须参数
     * @param platformVersion 操作系统版本号
     * @param deviceName 手机驱动名字
     * @param udid 手机adb链接的序列号
     * @param appActivity app的启动活动
     * @param appPackage app的包名
     */
    public AndroidPhoneConfig(String platformVersion,
                              String deviceName,
                              String udid,
                              String appActivity,
                              String appPackage) {
        super("Android", platformVersion, deviceName, udid);
        this.appActivity = appActivity;
        this.appPackage = appPackage;
    }



    /**
     * 你要从你的应用包中启动的 Android Activity 名称。它通常需要在前面添加 `.`
     * (如：使用`.MainActivity` 而不是 `MainActivity`) |`MainActivity`, `.Settings`|
     */
    private String appActivity;

    /**
     * 你想运行的Android应用的包名|比如`com.example.android.myApp`, `com.android.settings`
     */
    private String appPackage;

    /**
     * 你想要等待启动的 Android Activity 名称|`SplashActivity`|
     */
    private String appWaitActivity;

    /**
     * 设置等待一个模拟器或真机准备就绪的超时时间|`5`|
     */
    private String deviceReadyTimeout = "60";

    /**
     * 用于执行测试的 instrumentation 类。作为命令 `adb shell am instrument -e coverage true -w` 的 `-w` 参数。
     * | `com.my.Pkg/com.my.Pkg.instrumentation.MyInstrumentation`|
     */
    private String androidCoverage;

    /**
     * (仅适用于 Chrome 和 webview) 开启 Chromedriver 的性能日志。 (默认 `false`) | `true`, `false`|
     */
    private Boolean enablePerformanceLogging = false;

    /**
     * 等待设备在启动应用后准备就绪的超时时间。以秒为单位。|如 `30`|
     */
    private String androidDeviceReadyTimeout = "60";

    /**
     * |开发工具的 socket 名称。只有在被测应用是一个使用 Chromium 内核的浏览器时需要。
     * socket 会被浏览器打开，然后 Chromedriver 把它作为开发者工具来进行连接。|如 `chrome_devtools_remote`|
     */
    private String androidDeviceSocket;

    /**
     * 需要启动的 AVD  (安卓虚拟设备) 名称。|如 `api19`|
     */
    private String avd;

    /**
     * 以毫秒为单位，等待 AVD 启动并连接到 ADB 的超时时间。(默认值 `120000`)| `300000`|
     */
    private String avdLaunchTimeout = "60000";

    /**
     * 以毫秒为单位，等待 AVD 完成启动动画的超时时间。(默认值 `120000`)| `300000`|
     */
    private String avdReadyTimeout = "60000";

    /**
     * 启动 AVD 时需要加入的额外的参数。|如 `-netfast`|
     */
    private String avdArgs;

    /**
     * 使用一个自定义的 keystore 来对 apk 进行重签名。默认值 `false`|`true` or `false`|
     */
    private Boolean useKeystore = false;

    /**
     * 自定义 keystore 的路径。默认： ~/.android/debug.keystore|如 `/path/to.keystore`|
     */
    private String keystorePath;

    /**
     * 自定义 keystore 的密码。|如 `foo`|
     */
    private String keystorePassword;

    /**
     * key 的别名 |如 `androiddebugkey`|
     */
    private String keyAlias;

    /**
     * key 的密码 |如 `foo`|
     */
    private String keyPassword;

    /**
     * webdriver 可执行文件的绝对路径
     * (如果 Chromium 核心提供了对应的 webdriver， 应该用它代替 Appium 自带的 webdriver)
     * |`/abs/path/to/webdriver`|
     */
    private String chromedriverExecutable;

    /**
     * 以毫秒为单位，等待 Webview 上下文激活的时间。默认值 `2000`| 如 `4`|
     */
    private String autoWebviewTimeout = "2000";

    /**
     * 用于启动 activity 的 intent action。
     * (默认值 `android.intent.action.MAIN`)| 如 `android.intent.action.MAIN`,
     * `android.intent.action.VIEW`|
     */
    private String intentAction;

    /**
     * 用于启动 activity 的 intent category。
     * (默认值 `android.intent.category.LAUNCHER`)  | 如 `android.intent.category.LAUNCHER`,
     * `android.intent.category.APP_CONTACTS`
     */
    private String intentCategory;

    /**
     * 用于启动 activity 的标识 ( flags )
     * (默认值 `0x10200000`)  | 如 `0x10200000`
     */
    private String intentFlags;

    /**
     * 用于启动 activity 的额外 intent 参数。
     * 请查看 [Intent 参数](http://developer.android.com/tools/help/adb.html#IntentSpec)
     * | 如 `--esn <EXTRA_KEY>`, `--ez <EXTRA_KEY> <EXTRA_Boolean_VALUE>`
     */
    private String optionalIntentArguments;

    /**
     * 在使用 adb 启动应用时不要停止被测应用的进程。
     * 如果被测应用是由另一个锚应用（anchor app）创建的，请把这个参数设置为 `false` 以允许锚应用在使用
     * adb 启动被测应用时保持运行。换句话说，当 `dontStopAppOnReset` 设为 `true` 时，
     * 我们在 `adb shell am start` 命令中不会包含 `-S` 标志。如果这个参数没有被设置或设为 `false`，
     * 我们会加入 `-S` 标志。默认值： `false`| `true` 或 `false`|
     */
    private Boolean dontStopAppOnReset = false;

    /**
     * 使用 Unicode 输入法。默认值 `false`| `true` 或 `false`|
     */
    private Boolean unicodeKeyboard = false;

    /**
     * 在设定了 `unicodeKeyboard` 关键字的 Unicode 测试结束后，
     * 重置输入法到原有状态。如果单独使用，将会被忽略。默认值 `false`| `true` 或 `false`|
     */
    private Boolean resetKeyboard = false;

    /**
     * 跳过检查和对应用进行 debug 签名的步骤。
     * 只能在使用 UiAutomator 时使用，使用 selendroid 是不行。
     * 默认值 `false` | `true` 或 `false`|
     */
    private Boolean noSign = false;

    /**
     * 调用 uiautomator 的函数 `setCompressedLayoutHierarchy()`。
     * 由于 Accessibility 命令在忽略部分元素的情况下执行速度会加快，这个关键字能加快测试执行的速度。
     * 被忽略的元素将不能够被找到，因此这个关键字同时也被实现成可以随时改变的 *设置 ( settings ) * 。默认值 `false`
     * | `true` 或 `false`|
     */
    private Boolean ignoreUnimportantViews = false;

    /**
     * 关闭 android 监测应用无响应（ANR）和崩溃（crash）的监视器，这能够在 android
     * 设备/模拟器上减少 cpu 使用量。这个参数只能在使用 UiAutomator 时工作，在使用 selendroid
     * 时无效。默认值： `false`。| `true` 或者 `false`|
     */
    private Boolean disableAndroidWatchers = false;

    /**
     * 允许传入 chrome driver 使用的 chromeOptions 参数。请查阅
     * [chromeOptions](https://sites.google.com/a/chromium.org/chromedriver/capabilities) 了解更多信息。
     * | `chromeOptions: {args: [‘--disable-popup-blocking‘]}` |
     */
    private String chromeOptions;

    /**
     * 拉启应用时必须提供活动和包
     *
     * @param appActivity app的活动
     * @param appPackage  app的执行包
     * @param udid        设备唯一标示
     */
    public AndroidPhoneConfig(String appActivity, String appPackage, String udid) {
        this.appActivity = appActivity;
        this.appPackage = appPackage;
        this.udid = udid;
        this.deviceName = udid;
    }

    public String getAppActivity() {
        return appActivity;
    }

    public void setAppActivity(String appActivity) {
        this.appActivity = appActivity;
    }

    public String getAppPackage() {
        return appPackage;
    }

    public void setAppPackage(String appPackage) {
        this.appPackage = appPackage;
    }

    public String getAppWaitActivity() {
        return appWaitActivity;
    }

    public void setAppWaitActivity(String appWaitActivity) {
        this.appWaitActivity = appWaitActivity;
    }

    public String getDeviceReadyTimeout() {
        return deviceReadyTimeout;
    }

    public void setDeviceReadyTimeout(String deviceReadyTimeout) {
        this.deviceReadyTimeout = deviceReadyTimeout;
    }

    public String getAndroidCoverage() {
        return androidCoverage;
    }

    public void setAndroidCoverage(String androidCoverage) {
        this.androidCoverage = androidCoverage;
    }

    public Boolean getEnablePerformanceLogging() {
        return enablePerformanceLogging;
    }

    public void setEnablePerformanceLogging(Boolean enablePerformanceLogging) {
        this.enablePerformanceLogging = enablePerformanceLogging;
    }

    public String getAndroidDeviceReadyTimeout() {
        return androidDeviceReadyTimeout;
    }

    public void setAndroidDeviceReadyTimeout(String androidDeviceReadyTimeout) {
        this.androidDeviceReadyTimeout = androidDeviceReadyTimeout;
    }

    public String getAndroidDeviceSocket() {
        return androidDeviceSocket;
    }

    public void setAndroidDeviceSocket(String androidDeviceSocket) {
        this.androidDeviceSocket = androidDeviceSocket;
    }

    public String getAvd() {
        return avd;
    }

    public void setAvd(String avd) {
        this.avd = avd;
    }

    public String getAvdLaunchTimeout() {
        return avdLaunchTimeout;
    }

    public void setAvdLaunchTimeout(String avdLaunchTimeout) {
        this.avdLaunchTimeout = avdLaunchTimeout;
    }

    public String getAvdReadyTimeout() {
        return avdReadyTimeout;
    }

    public void setAvdReadyTimeout(String avdReadyTimeout) {
        this.avdReadyTimeout = avdReadyTimeout;
    }

    public String getAvdArgs() {
        return avdArgs;
    }

    public void setAvdArgs(String avdArgs) {
        this.avdArgs = avdArgs;
    }

    public Boolean getUseKeystore() {
        return useKeystore;
    }

    public void setUseKeystore(Boolean useKeystore) {
        this.useKeystore = useKeystore;
    }

    public String getKeystorePath() {
        return keystorePath;
    }

    public void setKeystorePath(String keystorePath) {
        this.keystorePath = keystorePath;
    }

    public String getKeystorePassword() {
        return keystorePassword;
    }

    public void setKeystorePassword(String keystorePassword) {
        this.keystorePassword = keystorePassword;
    }

    public String getKeyAlias() {
        return keyAlias;
    }

    public void setKeyAlias(String keyAlias) {
        this.keyAlias = keyAlias;
    }

    public String getKeyPassword() {
        return keyPassword;
    }

    public void setKeyPassword(String keyPassword) {
        this.keyPassword = keyPassword;
    }

    public String getChromedriverExecutable() {
        return chromedriverExecutable;
    }

    public void setChromedriverExecutable(String chromedriverExecutable) {
        this.chromedriverExecutable = chromedriverExecutable;
    }

    public String getAutoWebviewTimeout() {
        return autoWebviewTimeout;
    }

    public void setAutoWebviewTimeout(String autoWebviewTimeout) {
        this.autoWebviewTimeout = autoWebviewTimeout;
    }

    public String getIntentAction() {
        return intentAction;
    }

    public void setIntentAction(String intentAction) {
        this.intentAction = intentAction;
    }

    public String getIntentCategory() {
        return intentCategory;
    }

    public void setIntentCategory(String intentCategory) {
        this.intentCategory = intentCategory;
    }

    public String getIntentFlags() {
        return intentFlags;
    }

    public void setIntentFlags(String intentFlags) {
        this.intentFlags = intentFlags;
    }

    public String getOptionalIntentArguments() {
        return optionalIntentArguments;
    }

    public void setOptionalIntentArguments(String optionalIntentArguments) {
        this.optionalIntentArguments = optionalIntentArguments;
    }

    public Boolean getDontStopAppOnReset() {
        return dontStopAppOnReset;
    }

    public void setDontStopAppOnReset(Boolean dontStopAppOnReset) {
        this.dontStopAppOnReset = dontStopAppOnReset;
    }

    public Boolean getUnicodeKeyboard() {
        return unicodeKeyboard;
    }

    public void setUnicodeKeyboard(Boolean unicodeKeyboard) {
        this.unicodeKeyboard = unicodeKeyboard;
    }

    public Boolean getResetKeyboard() {
        return resetKeyboard;
    }

    public void setResetKeyboard(Boolean resetKeyboard) {
        this.resetKeyboard = resetKeyboard;
    }

    public Boolean getNoSign() {
        return noSign;
    }

    public void setNoSign(Boolean noSign) {
        this.noSign = noSign;
    }

    public Boolean getIgnoreUnimportantViews() {
        return ignoreUnimportantViews;
    }

    public void setIgnoreUnimportantViews(Boolean ignoreUnimportantViews) {
        this.ignoreUnimportantViews = ignoreUnimportantViews;
    }

    public Boolean getDisableAndroidWatchers() {
        return disableAndroidWatchers;
    }

    public void setDisableAndroidWatchers(Boolean disableAndroidWatchers) {
        this.disableAndroidWatchers = disableAndroidWatchers;
    }

    public String getChromeOptions() {
        return chromeOptions;
    }

    public void setChromeOptions(String chromeOptions) {
        this.chromeOptions = chromeOptions;
    }

    @Override
    public String toString() {
        return "AndroidPhoneInfo{" +
                "appActivity='" + appActivity + '\'' +
                ", appPackage='" + appPackage + '\'' +
                ", appWaitActivity='" + appWaitActivity + '\'' +
                ", deviceReadyTimeout='" + deviceReadyTimeout + '\'' +
                ", androidCoverage='" + androidCoverage + '\'' +
                ", enablePerformanceLogging=" + enablePerformanceLogging +
                ", androidDeviceReadyTimeout='" + androidDeviceReadyTimeout + '\'' +
                ", androidDeviceSocket='" + androidDeviceSocket + '\'' +
                ", avd='" + avd + '\'' +
                ", avdLaunchTimeout='" + avdLaunchTimeout + '\'' +
                ", avdReadyTimeout='" + avdReadyTimeout + '\'' +
                ", avdArgs='" + avdArgs + '\'' +
                ", useKeystore=" + useKeystore +
                ", keystorePath='" + keystorePath + '\'' +
                ", keystorePassword='" + keystorePassword + '\'' +
                ", keyAlias='" + keyAlias + '\'' +
                ", keyPassword='" + keyPassword + '\'' +
                ", chromedriverExecutable='" + chromedriverExecutable + '\'' +
                ", autoWebviewTimeout='" + autoWebviewTimeout + '\'' +
                ", intentAction='" + intentAction + '\'' +
                ", intentCategory='" + intentCategory + '\'' +
                ", intentFlags='" + intentFlags + '\'' +
                ", optionalIntentArguments='" + optionalIntentArguments + '\'' +
                ", dontStopAppOnReset=" + dontStopAppOnReset +
                ", unicodeKeyboard=" + unicodeKeyboard +
                ", resetKeyboard=" + resetKeyboard +
                ", noSign=" + noSign +
                ", ignoreUnimportantViews=" + ignoreUnimportantViews +
                ", disableAndroidWatchers=" + disableAndroidWatchers +
                ", chromeOptions='" + chromeOptions + '\'' +
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

    @Override
    public boolean equals(Object o) {
        if (this == o){
            return true;
        }
        if (o == null || getClass() != o.getClass()){
            return false;
        }
        AndroidPhoneConfig that = (AndroidPhoneConfig) o;
        return Objects.equals(appActivity, that.appActivity) &&
                Objects.equals(appPackage, that.appPackage) &&
                Objects.equals(appWaitActivity, that.appWaitActivity) &&
                Objects.equals(deviceReadyTimeout, that.deviceReadyTimeout) &&
                Objects.equals(androidCoverage, that.androidCoverage) &&
                Objects.equals(enablePerformanceLogging, that.enablePerformanceLogging) &&
                Objects.equals(androidDeviceReadyTimeout, that.androidDeviceReadyTimeout) &&
                Objects.equals(androidDeviceSocket, that.androidDeviceSocket) &&
                Objects.equals(avd, that.avd) &&
                Objects.equals(avdLaunchTimeout, that.avdLaunchTimeout) &&
                Objects.equals(avdReadyTimeout, that.avdReadyTimeout) &&
                Objects.equals(avdArgs, that.avdArgs) &&
                Objects.equals(useKeystore, that.useKeystore) &&
                Objects.equals(keystorePath, that.keystorePath) &&
                Objects.equals(keystorePassword, that.keystorePassword) &&
                Objects.equals(keyAlias, that.keyAlias) &&
                Objects.equals(keyPassword, that.keyPassword) &&
                Objects.equals(chromedriverExecutable, that.chromedriverExecutable) &&
                Objects.equals(autoWebviewTimeout, that.autoWebviewTimeout) &&
                Objects.equals(intentAction, that.intentAction) &&
                Objects.equals(intentCategory, that.intentCategory) &&
                Objects.equals(intentFlags, that.intentFlags) &&
                Objects.equals(optionalIntentArguments, that.optionalIntentArguments) &&
                Objects.equals(dontStopAppOnReset, that.dontStopAppOnReset) &&
                Objects.equals(unicodeKeyboard, that.unicodeKeyboard) &&
                Objects.equals(resetKeyboard, that.resetKeyboard) &&
                Objects.equals(noSign, that.noSign) &&
                Objects.equals(ignoreUnimportantViews, that.ignoreUnimportantViews) &&
                Objects.equals(disableAndroidWatchers, that.disableAndroidWatchers) &&
                Objects.equals(chromeOptions, that.chromeOptions);
    }

    @Override
    public int hashCode() {
        return Objects.hash(appActivity, appPackage, appWaitActivity, deviceReadyTimeout, androidCoverage, enablePerformanceLogging, androidDeviceReadyTimeout, androidDeviceSocket, avd, avdLaunchTimeout, avdReadyTimeout, avdArgs, useKeystore, keystorePath, keystorePassword, keyAlias, keyPassword, chromedriverExecutable, autoWebviewTimeout, intentAction, intentCategory, intentFlags, optionalIntentArguments, dontStopAppOnReset, unicodeKeyboard, resetKeyboard, noSign, ignoreUnimportantViews, disableAndroidWatchers, chromeOptions);
    }
}
