package org.nix.learn.auto.functions.schema.android;

import io.appium.java_client.android.Activity;
import io.appium.java_client.android.AndroidDriver;
import org.apache.log4j.Logger;
import org.nix.learn.auto.core.appium.AppiumUtils;
import org.nix.learn.auto.core.appium.create.DefaultAndroidDriver;
import org.nix.learn.auto.functions.presentation.Presentation;
import org.nix.learn.auto.functions.presentation.PresentationContent;
import org.nix.learn.auto.model.SchemaModel;
import org.nix.learn.auto.functions.schema.*;
import org.nix.learn.auto.utils.LogUtils;
import org.openqa.selenium.WebDriverException;

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
    private Presentation presentation;

    /**
     * 测试的apk版本
     */
    private String apkVersion;

    /**
     * 测试截图地址
     */
    private Path screenshotPath;

    private AppiumUtils appiumUtils;
    /**
     * 需要生成报告
     * @param schemaModel
     * @param defaultAndroidDriver
     * @param presentation
     * @param apkVersion
     * @param screenshotPath
     */
    public SchemaRunOne(SchemaModel schemaModel, DefaultAndroidDriver defaultAndroidDriver, Presentation presentation, String apkVersion, Path screenshotPath,AppiumUtils appiumUtils) {
        this.schemaModel = schemaModel;
        this.defaultAndroidDriver = defaultAndroidDriver;
        this.presentation = presentation;
        this.apkVersion = apkVersion;
        this.screenshotPath = screenshotPath;
        this.appiumUtils = appiumUtils;
    }

    /**
     * 不需要生成报告
     * @param schemaModel
     * @param defaultAndroidDriver
     * @param apkVersion
     * @param screenshotPath
     */
    public SchemaRunOne(SchemaModel schemaModel, DefaultAndroidDriver defaultAndroidDriver, String apkVersion, Path screenshotPath,AppiumUtils appiumUtils) {
        this.schemaModel = schemaModel;
        this.defaultAndroidDriver = defaultAndroidDriver;
        this.apkVersion = apkVersion;
        this.screenshotPath = screenshotPath;
        this.appiumUtils = appiumUtils;
    }

    @Override
    public void runTask() {
        // 核心运行部分
        try {
            AndroidDriver driver = (AndroidDriver) defaultAndroidDriver.getDriver();
            // 首先进入初始页面
            driver.startActivity(new Activity(FunctionsApk.APP_PACKAGE, FunctionsApk.APP_ACTIVITY));
            appiumUtils.sendKeyElement(FunctionsApk.SEND_KEY_ID, driver,
                    schemaModel.requestPath(apkVersion));
            appiumUtils.clickElement(driver, FunctionsApk.CLICK_ID);
            Thread.currentThread().sleep(10000);
            Path savePath = appiumUtils.screenshot(driver, screenshotPath);

            // 开始手机信息进行保存，为报告生成做准备
            presentation.setSuccess();
            presentation.putCurr("savePath",savePath);
        } catch (WebDriverException | SchemaException | InterruptedException | IOException e) {
            presentation.setFail();
            presentation.putCurr("fail msg",e.getMessage());
        }
    }

}
