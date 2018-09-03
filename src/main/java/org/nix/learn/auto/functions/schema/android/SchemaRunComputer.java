package org.nix.learn.auto.functions.schema.android;

import org.apache.log4j.Logger;
import org.nix.learn.auto.core.appium.create.DefaultAndroidDriver;
import org.nix.learn.auto.functions.presentation.Presentation;
import org.nix.learn.auto.functions.presentation.PresentationContent;
import org.nix.learn.auto.model.SchemaModel;
import org.nix.learn.auto.functions.schema.*;

import java.nio.file.Path;
import java.util.List;

/**
 * 在一台电脑上运行多个手机
 * <p>
 * 层次等级：2
 *
 * @author zhangpei341@pingan.cn.com 2018/8/23 下午12:47
 * @version 1.0
 */
public class SchemaRunComputer implements SchemaRun {

    private static final Logger logger = Logger.getLogger(SchemaRunComputer.class);

    /**
     * 这台电脑需要执行的任务
     */
    private RunStaple runStaple;

    /**
     * 父级报告类
     */
    private Presentation prentPresentation;

    /**
     * 需要执行的scheme
     */
    private List<SchemaModel> models;

    /**
     * 测试的apk版本
     */
    private String apkVersion;

    /**
     * 测试截图地址
     */
    private Path screenshotPath;

    /**
     * @param runStaple
     * @param prentPresentation
     * @param models
     * @param apkVersion
     * @param screenshotPath
     */
    public SchemaRunComputer(RunStaple runStaple, Presentation prentPresentation, List<SchemaModel> models, String apkVersion, Path screenshotPath) {
        this.runStaple = runStaple;
        this.prentPresentation = prentPresentation;
        this.models = models;
        this.apkVersion = apkVersion;
        this.screenshotPath = screenshotPath;
    }

    /**
     * 任务执行分发
     */
    @Override
    public void runTask() {
        List<DefaultAndroidDriver> list = runStaple.createDefaultAndroidDrivers();
        if (list != null) {
            int index = 0;
            prentPresentation.putCurr("current phones",list.size());
            for (DefaultAndroidDriver defaultAndroidDriver : list) {
                SchemaRunPhone runPhone = new SchemaRunPhone(
                        models,
                        defaultAndroidDriver,
                        prentPresentation.addNext(index + " :phone", (long) list.size()),
                        apkVersion, screenshotPath);
                SchemaThreadPool.put(runPhone);
                index++;
            }
        } else {
            prentPresentation.putCurr("current phones","null");
        }

    }
}
