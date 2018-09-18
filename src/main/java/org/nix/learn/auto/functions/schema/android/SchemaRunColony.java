package org.nix.learn.auto.functions.schema.android;

import com.alibaba.fastjson.JSON;
import org.apache.log4j.Logger;
import org.nix.learn.auto.core.appium.server.AppiumServer;
import org.nix.learn.auto.functions.presentation.Presentation;
import org.nix.learn.auto.functions.presentation.TaskPresentation;
import org.nix.learn.auto.model.ApkInfoModel;
import org.nix.learn.auto.model.SchemaModel;
import org.nix.learn.auto.functions.schema.SchemaRun;
import org.nix.learn.auto.utils.LogUtils;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

/**
 * 顶级任务执行类，任务执行的入口就是创建该类的对象
 * <p>
 * <p>
 * 报告实际使用将采用缓存处理，每次运行完都将存入一次缓存中
 * 从而可以实时读取运行信息。
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
    private transient Presentation presentation;

    /**
     * 本次执行任务执行的apk信息
     */
    private ApkInfoModel apkInfo;

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
    public SchemaRunColony(List<RunStaple> runStaples, List<SchemaModel> schemaModels, Presentation presentation, ApkInfoModel apkInfo, Path screenshotPath) {
        this.runStaples = runStaples;
        this.schemaModels = schemaModels;
        this.presentation = presentation;
        this.apkInfo = apkInfo;
        this.screenshotPath = screenshotPath;
    }

    @Override
    public void runTask() {

        Result result = new Result();

        if (runStaples.size() == 0){
            presentation.setFail();
            result.setMsg("没有可执行的设备");
        }else {
            try {
                for (RunStaple runStaple : runStaples) {
                    SchemaRunComputer computer = new SchemaRunComputer(runStaple, presentation.addNext("computer", (long) runStaples.size())
                            , schemaModels, apkInfo.getVersion(), screenshotPath);
                    computer.runTask();

                }
                result.setApk(apkInfo);
                result.setSchemas(schemaModels);
                result.setMsg("启动执行成功");
            }catch (Exception e){
                result.setMsg("启动执行失败:"+e.getMessage());
            }
        }
        presentation.putCurr("data",result);
    }



    class Result{
        private ApkInfoModel apk;
        private List<SchemaModel> schemas;
        private String msg;

        public ApkInfoModel getApk() {
            return apk;
        }

        public void setApk(ApkInfoModel apk) {
            this.apk = apk;
        }

        public List<SchemaModel> getSchemas() {
            return schemas;
        }

        public void setSchemas(List<SchemaModel> schemas) {
            this.schemas = schemas;
        }

        public String getMsg() {
            return msg;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }
    }















    public static void main(String[] args) throws InterruptedException {

        Presentation boos = new TaskPresentation((long) 4);
        SchemaRunColony runColony = new SchemaRunColony(createRunStaples(boos), createSchemaModels(), boos, createApkInfo(),
                Paths.get("/Users/mac/IdeaProjects/auto_git/src/main/file"));

        runColony.runTask();

        LogUtils.printLog("  result =======> 1  ", runColony.getResult());
        Thread.sleep(10000);
        LogUtils.printLog("  result =======> 2  ", runColony.getResult());
        Thread.sleep(10000);
        LogUtils.printLog("  result =======> 3  ", runColony.getResult());
        Thread.sleep(30000);
        LogUtils.printLog("  result =======> 4  ", runColony.getResult());
        Thread.sleep(30000);
        LogUtils.printLog("  result =======> 5  ", runColony.getResult());
        Thread.sleep(100000);
        LogUtils.printLog("  result =======> 5  ", runColony.getResult());
        Thread.sleep(200000);
        LogUtils.printLog("  result =======> 5  ", runColony.getResult());

    }

    public static ApkInfoModel createApkInfo() {
        ApkInfoModel apkInfo = new ApkInfoModel();
        apkInfo.setVersion("6.0.1");
        return apkInfo;
    }

    private static List<RunStaple> createRunStaples(Presentation taskPresentation) {

        String path = "127.0.0.1";

        Map<String, Boolean> map = new HashMap<>();
        map.put("4723", true);

        AppiumServer server = new AppiumServer(path, map);

        List<String> udids = new ArrayList<>();
        udids.add("1267e25a");
//        udids.add("a7366dea");

        List<RunStaple> runStaples = new ArrayList<>();
        RunStaple runStaple = new RunStaple(server, udids, taskPresentation);



        // 172.20.12.31
//        String path1 = "172.20.12.31";
//
//
//        Map<String, Boolean> map1 = new HashMap<>();
//        map1.put("4723", true);
//        map1.put("4724", true);
//
//        AppiumServer server1 = new AppiumServer(path1, map1);
//
//        List<String> ud = new ArrayList<>();
//        ud.add("a7366dea");
//        ud.add("1267e25a");

//        RunStaple runStaple1 = new RunStaple(server1, ud, taskPresentation);
//        runStaples.add(runStaple1);

        runStaples.add(runStaple);
        return runStaples;
    }

    public static List<SchemaModel> createSchemaModels() {
        List<SchemaModel> schemaModels = new ArrayList<>();
        String prent = "yqbnative://app.1qianbao.com/";
        String huoqianbao = prent + "huoqianbao/index";
        String assets = prent + "assets/integral";
        String setting = prent + "setting/index";
        String lifepay = prent + "lifepay/index";

        SchemaModel model = new SchemaModel("活钱宝",
                huoqianbao, true, "", "4.0.0", null, "活钱宝");


        SchemaModel assetsModel = new SchemaModel("assets",
                assets, true, "", "4.0.0", null, "assets");


        SchemaModel settingModel = new SchemaModel("设置",
                setting, true, "", "4.0.0", null, "设置");


        SchemaModel lifePayModel = new SchemaModel("生活充值",
                lifepay, true, "", "4.0.0", null, "生活充值");


        schemaModels.add(model);
        schemaModels.add(assetsModel);
        schemaModels.add(settingModel);
        schemaModels.add(lifePayModel);

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

    public ApkInfoModel getApkInfo() {
        return apkInfo;
    }

    public void setApkInfo(ApkInfoModel apkInfo) {
        this.apkInfo = apkInfo;
    }

    public Path getScreenshotPath() {
        return screenshotPath;
    }

    public void setScreenshotPath(Path screenshotPath) {
        this.screenshotPath = screenshotPath;
    }

    public Presentation getResult() {
        return presentation;
    }

    public void setpresentation(Presentation presentation) {
        this.presentation = presentation;
    }
}
