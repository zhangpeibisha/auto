package org.nix.learn.auto.functions.schema.android;

import com.alibaba.fastjson.JSON;
import io.appium.java_client.AppiumDriver;
import org.apache.log4j.Logger;
import org.nix.learn.auto.core.appium.AppiumException;
import org.nix.learn.auto.core.appium.create.DefaultAndroidDriver;
import org.nix.learn.auto.core.appium.server.AppiumServer;
import org.nix.learn.auto.functions.presentation.Presentation;
import org.nix.learn.auto.functions.presentation.PresentationContent;
import org.nix.learn.auto.model.SchemaModel;
import org.nix.learn.auto.functions.schema.*;
import org.nix.learn.auto.utils.LogUtils;

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

        Result result = new Result();

        List<DefaultAndroidDriver> list = runStaple.createDefaultAndroidDrivers();
        if (list != null) {
            AppiumServer server = runStaple.getServer();
            result.setIp(server.getIP());
            try {
                for (DefaultAndroidDriver defaultAndroidDriver : list) {
                    SchemaRunPhone runPhone = new SchemaRunPhone(
                            models,
                            defaultAndroidDriver,
                            // 下一级报告应该是针对于schema生成报告
                            prentPresentation.addNext("phone", (long) models.size()),
                            apkVersion, screenshotPath);
                    SchemaThreadPool.put(runPhone);
                }
                result.setMsg("执行成功");
            }catch (Exception e){
                LogUtils.printLog("computer "+e.getMessage());
                result.setMsg("执行失败："+e.getMessage());
            }

        } else {
            result.setMsg("没有手机可以运行");
        }

        prentPresentation.putCurr("data",result);
    }


    class Result{
        private String ip;
        private String msg;

        public Result() {
        }

        public String getIp() {
            return ip;
        }

        public void setIp(String ip) {
            this.ip = ip;
        }

        public String getMsg() {
            return msg;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }

        @Override
        public String toString() {
            return "Result{" +
                    "ip='" + ip + '\'' +
                    ", msg='" + msg + '\'' +
                    '}';
        }
    }

}
