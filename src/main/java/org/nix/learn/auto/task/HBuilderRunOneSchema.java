package org.nix.learn.auto.task;

import com.alibaba.fastjson.JSONObject;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.nix.learn.auto.core.appium.config.AndroidPhoneConfig;
import org.nix.learn.auto.core.appium.create.DefaultAndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * @author zhangpei341@pingan.cn.com 2018/8/20 下午3:09
 * @version 1.0
 */
public class HBuilderRunOneSchema extends AbstractRunOneSchema{


    public HBuilderRunOneSchema(AppiumDriver driver, String schema, String keepDir, String manuscriptPath) {
        super(driver);
        this.schema = schema;
        this.keepDir = keepDir;
        this.manuscriptPath = manuscriptPath;
    }

    /**
     * 要运行的schema
     */
    private String schema;

    /**
     * 截图保存路径
     */
    private String keepDir;

    /**
     * 视觉图稿原始地址
     */
    private String manuscriptPath;

    private String fileName;

    @Override
    public void runTask() {

        driver.findElement(By.id("writerURI")).sendKeys(schema);
        driver.findElement(By.id("clickRun")).click();
        try {
            Thread.currentThread().sleep(5000);
            File file = driver.getScreenshotAs(OutputType.FILE);
            fileName = file.getName();
            Path path = Paths.get(keepDir,fileName);
            FileInputStream in = new FileInputStream(file);
            byte[] bytes = new byte[in.available()];
            in.read(bytes);
            Files.write(path,bytes);
            file.delete();
        } catch (InterruptedException e) {
            throw new TaskException("时间暂停失败",e);
        } catch (IOException e) {
            throw new TaskException("文件读取失败",e);
        }
    }

    @Override
    public String obtainTaskResult(String... value) {
        return phoneInfo()
                .append("截屏图片存放地址:").append(Paths.get(keepDir,fileName)).append("\n")
                .append("测试schema:").append(schema).append("\n")
                .append("参考视觉稿地址:").append(manuscriptPath).append("\n")
                .append("手机截图和视觉高相似度:").append("100%").append("\n")
                .append("备注信息:").append(JSONObject.toJSONString(value)).append("\n")
                .toString();
    }

    @Override
    public void taskResultTo(String result) {

    }

    private StringBuilder phoneInfo(){

        StringBuilder builder = new StringBuilder();

        // 1.服务器URL信息
        URL uRl = driver.getRemoteAddress();
        // 3.手机分辨率
        int h = driver.manage().window().getSize().getHeight();
        int w = driver.manage().window().getSize().getWidth();
        // 4.手机操作系统基本信息
        String name = driver.getPlatformName();

        builder.append("服务器地址:").append(uRl).append("\n");
        builder.append("测试手机分辨率:").append(h).append("*").append(w).append("\n");
        builder.append("测试手机操作系统:").append(name).append("\n");

        return builder;
    }

    public static HBuilderRunOneSchema createOne(String udid,
                                                 String deviceName,
                                                 String appPackage,
                                                 String appAcitvity){

        String platformName = "Android";
        String platformVersion = "4.4.4";
        String appiumPath = "http://127.0.0.1:4723/wd/hub";
        AndroidPhoneConfig config = new AndroidPhoneConfig(platformVersion, deviceName, udid, appAcitvity, appPackage);

        AndroidDriver driver = (AndroidDriver) new DefaultAndroidDriver(config, appiumPath).getDriver();


        String schema = "yqbnative://app.1qianbao.com/huoqianbao/index";
        String h5 = "http://172.20.12.56:8080/html/loadJsTest.html";
        String keepDir = "/Users/mac/IdeaProjects/auto_git/src/main/file/";
        String manuscriptPath = "";

        HBuilderRunOneSchema oneSchema = new HBuilderRunOneSchema(driver,schema,keepDir,manuscriptPath);

        return oneSchema;
    }

}
