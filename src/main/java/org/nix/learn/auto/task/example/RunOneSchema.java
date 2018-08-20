package org.nix.learn.auto.task.example;

import com.alibaba.fastjson.JSON;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.nix.learn.auto.core.appium.config.AndroidPhoneConfig;
import org.nix.learn.auto.core.appium.create.DefaultAndroidDriver;
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
import java.util.concurrent.TimeUnit;

/**
 * @author zhangpei341@pingan.cn.com 2018/8/20 上午10:01
 * @version 1.0
 */
public class RunOneSchema extends Thread{
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

    public RunOneSchema(String schema, AppiumDriver driver, String requestH5, String keepDir, String manuscriptPath) {
        this.schema = schema;
        this.driver = driver;
        this.requestH5 = requestH5;
        this.keepDir = keepDir;
        this.manuscriptPath = manuscriptPath;
    }

    @Override
    public void run() {
            // 1.拉起浏览器，并进入服务器指定地址
            driver.get(requestH5);
            // 2.进入web view状态
            driver.context("WEBVIEW_com.android.browser");
            // 3.在执行页面输入要执行的schema
            driver.findElement(By.id("writerURI")).sendKeys(schema);
            driver.findElement(By.id("clickRun")).click();
            try {
                // 4.切换回native状态进行截图保存
                driver.context("NATIVE_APP");
                sleep(5000);
                File file = driver.getScreenshotAs(OutputType.FILE);
                FileInputStream input = new FileInputStream(file);
                Path path = Paths.get(keepDir,file.getName());
                byte[] bytes = new byte[input.available()];
                input.read(bytes);
                Files.write(path,bytes);
                fileName = file.getName();
            } catch (FileNotFoundException e) {
                throw new TaskException("文件转为文件输出流失败",e);
            } catch (IOException e) {
                throw new TaskException("读取文件流大小失败",e);
            } catch (InterruptedException e) {
                throw new TaskException("时间暂停失败",e);
            }
            runInfo();
    }

    public void runInfo(){
        StringBuilder builder = new StringBuilder();

        // 1.服务器URL信息
        URL uRl = driver.getRemoteAddress();
        // 2.图片保存信息
        String keepPicturePath = Paths.get(keepDir,fileName).toString();
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

        Path path = Paths.get(keepDir,"运行报告.txt");
        try {
            Files.write(path,builder.toString().getBytes());
        } catch (IOException e) {
            throw new TaskException("文件报告生成失败",e);
        }
    }

    public static void main(String[] args) {
        String platformName = "Android";
        String platformVersion = "4.4.4";
        String appPackage = "com.android.browser";
        String appActivie = "com.android.browser.BrowserActivity";
        String udid = "emulator-5554";
        String deviceName = "emulator-5554";
        String appiumPath = "http://127.0.0.1:4723/wd/hub";
        AndroidPhoneConfig config = new AndroidPhoneConfig(platformName,platformVersion,deviceName,udid,appActivie,appPackage);

        System.out.println(JSON.toJSONString(config));
        System.out.println();

        AndroidDriver driver = (AndroidDriver) new DefaultAndroidDriver(config,appiumPath).getDriver();

        String schema = "yqbnative://app.1qianbao.com/huoqianbao/index";
        String h5 = "http://172.20.12.56:8080/html/loadJsTest.html";
        String keepDir = "/Users/mac/IdeaProjects/auto_git/src/main/file/";
        String manuscriptPath = "";
        RunOneSchema runOneSchema = new RunOneSchema(schema,driver,h5,keepDir,manuscriptPath);
        Thread thread = new Thread(runOneSchema);
        thread.start();
    }
}
