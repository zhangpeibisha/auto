package org.nix.learn.auto.functions.schema.android;

import io.appium.java_client.android.Activity;
import io.appium.java_client.android.AndroidDriver;
import org.apache.log4j.Logger;
import org.nix.learn.auto.core.appium.AppiumUtils;
import org.nix.learn.auto.functions.schema.Presentation;
import org.nix.learn.auto.functions.schema.SchemaException;
import org.nix.learn.auto.functions.schema.SchemaModel;
import org.nix.learn.auto.functions.schema.SchemaRun;
import org.nix.learn.auto.task.android.SchemaRunPresentation;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriverException;

/**
 * 运行一条schema的实现类
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
     * 用来执行的driver
     */
    private AndroidDriver driver;

    /**
     * 用来处理执行情况的报告类
     */
    private Presentation presentation;

    /**
     * 测试的目的apk信息
     */
    private TestApkInfo apkInfo;

    /**
     * 构建一个运行对象
     *
     * @param schemaModel  需要执行的schema
     * @param driver       执行的驱动
     * @param presentation 执行的告生成
     */
    public SchemaRunOne(SchemaModel schemaModel, AndroidDriver driver, Presentation presentation) {
        this.schemaModel = schemaModel;
        this.driver = driver;
        this.presentation = presentation;
    }

    @Override
    public void runTask() {
        SchemaRunOneResult presentation = new SchemaRunOneResult();
        presentation.setSchemaModel(schemaModel);
        presentation.setApkInfo(apkInfo);
        try {
            // 首先进入初始页面
            driver.startActivity(new Activity(FunctionsApk.APP_PACKAGE, FunctionsApk.APP_ACTIVITY));
            AppiumUtils.sendKeyElement(FunctionsApk.SEND_KEY_ID, driver, schemaModel.requestPath(apkInfo.getVersion()));
            AppiumUtils.clickElement(driver, FunctionsApk.CLICK_ID);
            Thread.currentThread().sleep(10000);

        } catch (WebDriverException | SchemaException | InterruptedException e) {
            presentation.setMsg(e.getMessage());
            presentation.setResult(false);
        }
    }


    static class SchemaRunOneResult {

        /**
         * 运行的schema信息
         */
        private SchemaModel schemaModel;

        /**
         * 运行的结果，成功或者失败
         */
        private Boolean result;

        /**
         * 运行信息
         */
        private String msg;

        /**
         * 测试的apk信息
         */
        private TestApkInfo apkInfo;

        public SchemaRunOneResult() {
        }

        public SchemaModel getSchemaModel() {
            return schemaModel;
        }

        public void setSchemaModel(SchemaModel schemaModel) {
            this.schemaModel = schemaModel;
        }

        public Boolean getResult() {
            return result;
        }

        public void setResult(Boolean result) {
            this.result = result;
        }

        public String getMsg() {
            return msg;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }

        public TestApkInfo getApkInfo() {
            return apkInfo;
        }

        public void setApkInfo(TestApkInfo apkInfo) {
            this.apkInfo = apkInfo;
        }

        @Override
        public String toString() {
            return "SchemaRunOneResult{" +
                    "schemaModel=" + schemaModel +
                    ", result=" + result +
                    ", msg='" + msg + '\'' +
                    ", apkInfo=" + apkInfo +
                    '}';
        }
    }
}
