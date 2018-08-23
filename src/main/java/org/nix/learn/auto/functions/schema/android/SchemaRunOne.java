package org.nix.learn.auto.functions.schema.android;

import io.appium.java_client.android.Activity;
import io.appium.java_client.android.AndroidDriver;
import org.apache.log4j.Logger;
import org.nix.learn.auto.core.appium.AppiumUtils;
import org.nix.learn.auto.core.appium.create.AdditionalInfo;
import org.nix.learn.auto.core.appium.create.DefaultAndroidDriver;
import org.nix.learn.auto.entity.ApkInfo;
import org.nix.learn.auto.functions.schema.*;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.IOException;
import java.nio.file.Path;


/**
 * 运行一条schema的实现类
 * <p>
 * 最终任务的执行类
 * <p>
 * 这一层将不能并发执行，将并发任务交个上层管理执行
 * 该层的主要任务就是执行具体任务，并实时更新执行状态
 * <p>
 * 执行状态分布：创建时为未执行，到该类后为执行中，末为执行成功和执行失败
 * <p>
 * 在创建任务时，就应当初始化每个schema的运行状态
 * <p>
 * 层次等级：4
 *
 * @author zhangpei341@pingan.cn.com 2018/8/22 下午4:34
 * @version 1.0
 */
public class SchemaRunOne implements SchemaRun {

    private static final Logger logger = Logger.getLogger(SchemaRunOne.class);

    /**
     * 需要执行的schema
     */
    private SchemaModel schemaModel;

    /**
     * 创建driver的功能类
     */
    private DefaultAndroidDriver defaultAndroidDriver;

    /**
     * 用来处理执行情况的报告类，提供报告处理功能
     */
    private TaskPresentation presentation;

    /**
     * 测试的apk版本
     */
    private String apkVersion;

    /**
     * 测试截图地址
     */
    private Path screenshotPath;

    /**
     *
     * @param schemaModel
     * @param defaultAndroidDriver
     * @param presentation
     * @param apkVersion
     * @param screenshotPath
     */
    public SchemaRunOne(SchemaModel schemaModel, DefaultAndroidDriver defaultAndroidDriver, TaskPresentation presentation, String apkVersion, Path screenshotPath) {
        this.schemaModel = schemaModel;
        this.defaultAndroidDriver = defaultAndroidDriver;
        this.presentation = presentation;
        this.apkVersion = apkVersion;
        this.screenshotPath = screenshotPath;
    }

    @Override
    public void runTask() {
        TaskPresentation taskPresentation = new TaskPresentation("底层执行类",schemaModel.requestPath(apkVersion));

        // 核心运行部分
        try {
            AndroidDriver driver = (AndroidDriver) defaultAndroidDriver.getDriver();
            // 首先进入初始页面
            driver.startActivity(new Activity(FunctionsApk.APP_PACKAGE, FunctionsApk.APP_ACTIVITY));
            AppiumUtils.sendKeyElement(FunctionsApk.SEND_KEY_ID, driver,
                    schemaModel.requestPath(apkVersion));
            AppiumUtils.clickElement(driver, FunctionsApk.CLICK_ID);
            Thread.currentThread().sleep(10000);
            Path savePath = AppiumUtils.screenshot(driver, screenshotPath);

            // 开始手机信息进行保存，为报告生成做准备
            taskPresentation.addKeyAndValue("result","[运行成功]");
            taskPresentation.addKeyAndValue("screenshotPath",savePath);
        } catch (WebDriverException | SchemaException | InterruptedException | IOException e) {
            taskPresentation.addKeyAndValue("result","[运行失败]");
            taskPresentation.addKeyAndValue("msg",e.getMessage());
        }

        presentation.addPresentation(taskPresentation);
    }

}
