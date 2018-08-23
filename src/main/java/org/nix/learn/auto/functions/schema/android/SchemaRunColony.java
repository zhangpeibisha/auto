package org.nix.learn.auto.functions.schema.android;

import com.alibaba.fastjson.JSON;
import org.apache.log4j.Logger;
import org.nix.learn.auto.core.appium.server.AppiumServer;
import org.nix.learn.auto.entity.ApkInfo;
import org.nix.learn.auto.functions.schema.SchemaModel;
import org.nix.learn.auto.functions.schema.SchemaRun;
import org.nix.learn.auto.functions.schema.TaskPresentation;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

/**
 * 顶级任务执行类，任务执行的入口就是创建该类的对象
 *
 * @author zhangpei341@pingan.cn.com 2018/8/23 下午2:32
 * @version 1.0
 */
public class SchemaRunColony implements SchemaRun {

    private static final Logger logger = Logger.getLogger(SchemaRunColony.class);

    /**
     * 定义需要执行任务的机器
     */
    private List<RunStaple> runStaples;

    /**
     * 定义本次任务需要完成的事
     */
    private List<SchemaModel> schemaModels;

    /**
     * 顶级报告类
     */
    private TaskPresentation boosPresentation;

    /**
     * 本次执行任务执行的apk信息
     */
    private ApkInfo apkInfo;

    /**
     * 截图需要保存的地址目录，需要检测这个目录是否存在，且是否为目录
     * 不可为文件
     */
    private Path screenshotPath;

    /**
     * 执行任务需要的信息
     *
     * @param runStaples
     * @param schemaModels
     * @param apkInfo
     * @param screenshotPath
     */
    public SchemaRunColony(List<RunStaple> runStaples, List<SchemaModel> schemaModels, TaskPresentation boosPresentation, ApkInfo apkInfo, Path screenshotPath) {
        this.runStaples = runStaples;
        this.schemaModels = schemaModels;
        this.boosPresentation = boosPresentation;
        this.apkInfo = apkInfo;
        this.screenshotPath = screenshotPath;
    }

    /**
     * 向下级分发任务执行
     */
    private void distributionTask() {

    }

    /**
     * 初始化报告信息，用于状态实时更新
     */
    private void initPresentation() {

    }

    /**
     * 初始化任务环境
     * 1。比如检测软件是否存在，否则安装
     * 2。检测手机是否都解锁成功，如果没有则解锁动作
     */
    private void initEnvironmental() {

    }

    /**
     * 初始化schema
     */
    private void initSchemaModel() {

    }

    @Override
    public void runTask() {
        boosPresentation.addKeyAndValue("taskInfo", JSON.toJSON(this));
        for (RunStaple runStaple : runStaples) {
            SchemaRunComputer computer = new SchemaRunComputer(runStaple, boosPresentation, schemaModels, apkInfo.getVersion(), screenshotPath);
            computer.runTask();
        }
    }


    public static void main(String[] args) {

        TaskPresentation boos = new TaskPresentation("顶级任务管理", "任务创建区域");
        SchemaRunColony runColony = new SchemaRunColony(createRunStaples(boos), createSchemaModels(), boos, createApkInfo(),
                Paths.get("/Users/mac/IdeaProjects/auto_git/src/main/file"));

        runColony.runTask();
        System.out.println(JSON.toJSONString(runColony.getResult()));

    }

    private static ApkInfo createApkInfo() {
        ApkInfo apkInfo = new ApkInfo();
        apkInfo.setVersion("6.0.1");
        return apkInfo;
    }

    private static List<RunStaple> createRunStaples(TaskPresentation taskPresentation) {

        String path = "127.0.0.1";

        Map<String, Boolean> map = new HashMap<>();
        map.put("4723", true);
        map.put("4725", true);
        map.put("4727", true);

        AppiumServer server = new AppiumServer(path, map);

        List<String> udids = new ArrayList<>();
        udids.add("1267e25a");
//        udids.add("ENU7N15A12003472");

        List<RunStaple> runStaples = new ArrayList<>();
        RunStaple runStaple = new RunStaple(server, udids, taskPresentation);
        runStaples.add(runStaple);

        return runStaples;
    }

    private static List<SchemaModel> createSchemaModels() {
        List<SchemaModel> schemaModels = new ArrayList<>();
        String prent = "yqbnative://app.1qianbao.com/";
        String huoqianbao = prent + "huoqianbao/index";

        SchemaModel model = new SchemaModel("活钱宝",
                prent + huoqianbao, true, "", "4.0.0", null, "活钱宝");
        schemaModels.add(model);
        return schemaModels;
    }


    public List<RunStaple> getRunStaples() {
        return runStaples;
    }

    public void setRunStaples(List<RunStaple> runStaples) {
        this.runStaples = runStaples;
    }

    public List<SchemaModel> getSchemaModels() {
        return schemaModels;
    }

    public void setSchemaModels(List<SchemaModel> schemaModels) {
        this.schemaModels = schemaModels;
    }

    public ApkInfo getApkInfo() {
        return apkInfo;
    }

    public void setApkInfo(ApkInfo apkInfo) {
        this.apkInfo = apkInfo;
    }

    public Path getScreenshotPath() {
        return screenshotPath;
    }

    public void setScreenshotPath(Path screenshotPath) {
        this.screenshotPath = screenshotPath;
    }

    public TaskPresentation getResult() {
        return boosPresentation;
    }

    public void setBoosPresentation(TaskPresentation boosPresentation) {
        this.boosPresentation = boosPresentation;
    }
}
