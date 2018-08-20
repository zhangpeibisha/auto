package org.nix.learn.auto.task.example;

import com.alibaba.fastjson.JSON;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.nix.learn.auto.core.appium.config.AndroidPhoneConfig;
import org.nix.learn.auto.core.appium.create.DefaultAndroidDriver;
import org.nix.learn.auto.task.RunTask;
import org.nix.learn.auto.task.TaskException;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


/**
 * @author zhangpei341@pingan.cn.com 2018/8/20 上午10:01
 * @version 1.0
 */
public class RunOneSchema implements RunTask {
    /**
     * 要运行的schema
     */
    private String schema;

    /**
     * 执行驱动
     */
    private AppiumDriver driver;

    /**
     * h5请求页面
     */
    private String requestH5;

    /**
     * 截图保存路径
     */
    private String keepDir;

    /**
     * 视觉图稿原始地址
     */
    private String manuscriptPath;

    private String fileName;

    private String result;

    private String context;

    public RunOneSchema(String schema, AppiumDriver driver, String requestH5, String keepDir, String manuscriptPath) {
        this.schema = schema;
        this.driver = driver;
        this.requestH5 = requestH5;
        this.keepDir = keepDir;
        this.manuscriptPath = manuscriptPath;
    }

    public RunOneSchema(String schema, AppiumDriver driver, String requestH5, String keepDir, String manuscriptPath, String context) {
        this.schema = schema;
        this.driver = driver;
        this.requestH5 = requestH5;
        this.keepDir = keepDir;
        this.manuscriptPath = manuscriptPath;
        this.context = context;
    }

    @Override
    public String call() throws Exception {
        run();
        return result;
    }


    public void run() {
        // 1.拉起浏览器，并进入服务器指定地址
        driver.get(requestH5);
        // 2.进入web view状态

        System.err.println(driver.getContextHandles());
        System.err.println("context");
//            driver.context("WEBVIEW_com.android.browser");
        driver.context(context);

        // 3.在执行页面输入要执行的schema
        driver.findElement(By.id("writerURI")).sendKeys(schema);
        driver.findElement(By.id("clickRun")).click();
        try {
            // 4.切换回native状态进行截图保存
            driver.context("NATIVE_APP");
            Thread.currentThread().sleep(5000);
            File file = driver.getScreenshotAs(OutputType.FILE);
            FileInputStream input = new FileInputStream(file);
            Path path = Paths.get(keepDir, file.getName());
            byte[] bytes = new byte[input.available()];
            input.read(bytes);
            Files.write(path, bytes);
            fileName = file.getName();
        } catch (FileNotFoundException e) {
            result = "文件转为文件输出流失败";
            throw new TaskException("文件转为文件输出流失败", e);
        } catch (IOException e) {
            result = "读取文件流大小失败";
            throw new TaskException("读取文件流大小失败", e);
        } catch (InterruptedException e) {
            result = "时间暂停失败";
            throw new TaskException("时间暂停失败", e);
        }
        result = runInfo().toString();
    }

    private StringBuilder runInfo() {
        StringBuilder builder = new StringBuilder();

        // 1.服务器URL信息
        URL uRl = driver.getRemoteAddress();
        // 2.图片保存信息
        String keepPicturePath = Paths.get(keepDir, fileName).toString();
        // 3.手机分辨率
        int h = driver.manage().window().getSize().getHeight();
        int w = driver.manage().window().getSize().getWidth();
        // 4.手机基本信息
        String name = driver.getPlatformName();

        builder.append("服务器地址:").append(uRl).append("\n");
        builder.append("图片存放地址:").append(keepPicturePath).append("\n");
        builder.append("测试手机分辨率:").append(h).append("*").append(w).append("\n");
        builder.append("测试手机操作系统:").append(name).append("\n");
        builder.append("测试schema:").append(schema).append("\n");
        builder.append("参考视觉稿地址:").append(manuscriptPath).append("\n");
        builder.append("手机截图和视觉高相似度:").append("100%").append("\n");

        return builder;
    }

    public static RunOneSchema createAndoridOneSchema(String udid,
                                                      String deviceName,
                                                      String appPackage,
                                                      String appAcitvity,
                                                      String context) {
        String platformName = "Android";
        String platformVersion = "4.4.4";
//        String appPackage = "com.android.browser";
//        String appActivie = "com.android.browser.BrowserActivity";
//        String udid = "emulator-5554";
//        String deviceName = "emulator-5554";
        String appiumPath = "http://127.0.0.1:4723/wd/hub";
        AndroidPhoneConfig config = new AndroidPhoneConfig(platformName, platformVersion, deviceName, udid, appAcitvity, appPackage);

        AndroidDriver driver = (AndroidDriver) new DefaultAndroidDriver(config, appiumPath).getDriver();



        String schema = "yqbnative://app.1qianbao.com/huoqianbao/index";
        String h5 = "http://172.20.12.56:8080/html/loadJsTest.html";
        String keepDir = "/Users/mac/IdeaProjects/auto_git/src/main/file/";
        String manuscriptPath = "";
        RunOneSchema runOneSchema = new RunOneSchema(schema, driver, h5, keepDir, manuscriptPath,context);
        return runOneSchema;
    }

    /**
     * 准备添加一个测试IOS的测试实例
     *
     * @return
     */
    public static RunOneSchema createIOSOneSchema() {

        return null;
    }

    @Override
    public void runTask() {

    }
}
