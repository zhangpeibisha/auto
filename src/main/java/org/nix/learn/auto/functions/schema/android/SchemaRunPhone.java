package org.nix.learn.auto.functions.schema.android;

import com.alibaba.fastjson.JSON;
import org.apache.log4j.Logger;
import org.nix.learn.auto.core.appium.AppiumUtils;
import org.nix.learn.auto.core.appium.create.DefaultAndroidDriver;
import org.nix.learn.auto.functions.presentation.Presentation;
import org.nix.learn.auto.functions.presentation.PresentationContent;
import org.nix.learn.auto.model.SchemaModel;
import org.nix.learn.auto.functions.schema.*;
import org.nix.learn.auto.utils.LogUtils;

import java.nio.file.Path;
import java.util.List;


/**
 * 一个手机运行多个scheme
 * <p>
 * 该层次需要进行并发执行任务，加快任务执行，且避免执行不成功的手机异常退出执行
 * <p>
 * 下一层单元运行需要串型运行，因为是检测一个手机的多个页面的兼容性
 * <p>
 * 层次等级：3
 *
 * @author zhangpei341@pingan.cn.com 2018/8/23 下午12:47
 * @version 1.0
 */
public class SchemaRunPhone extends Thread implements SchemaRun {

    private static final Logger logger = Logger.getLogger(SchemaRunPhone.class);

    /**
     * 需要执行的schema
     */
    private List<SchemaModel> models;

    /**
     * 创建driver的功能类
     */
    private DefaultAndroidDriver defaultAndroidDriver;

    /**
     * 父级报告类
     */
    private Presentation prentPresentation;

    /**
     * 测试的apk版本
     */
    private String apkVersion;

    /**
     * 测试截图地址
     */
    private Path screenshotPath;

    public SchemaRunPhone(List<SchemaModel> models, DefaultAndroidDriver defaultAndroidDriver, Presentation prentPresentation, String apkVersion, Path screenshotPath) {
        this.models = models;
        this.defaultAndroidDriver = defaultAndroidDriver;
        this.prentPresentation = prentPresentation;
        this.apkVersion = apkVersion;
        this.screenshotPath = screenshotPath;
    }

    /**
     * 不用报告
     *
     * @param models
     * @param defaultAndroidDriver
     * @param apkVersion
     * @param screenshotPath
     */
    public SchemaRunPhone(List<SchemaModel> models, DefaultAndroidDriver defaultAndroidDriver, String apkVersion, Path screenshotPath) {
        this.models = models;
        this.defaultAndroidDriver = defaultAndroidDriver;
        this.apkVersion = apkVersion;
        this.screenshotPath = screenshotPath;
    }

    @Override
    public void run() {
        runTask();
    }

    @Override
    public void runTask() {
        prentPresentation.putCurr("phoneInfo", defaultAndroidDriver.getDriver().getCapabilities().asMap());
        int index = 0;
        for (SchemaModel schemaModel : models) {
            AppiumUtils appiumUtils = new AppiumUtils();
            SchemaRunOne runOne = new SchemaRunOne(
                    schemaModel,
                    defaultAndroidDriver,
                    prentPresentation.addNext(index + " :schema", (long) models.size()),
                    apkVersion,
                    screenshotPath,
                    appiumUtils);
            runOne.runTask();
            index++;
        }
    }

}
