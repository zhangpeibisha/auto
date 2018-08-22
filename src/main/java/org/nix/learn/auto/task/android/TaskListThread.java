package org.nix.learn.auto.task.android;

import com.alibaba.fastjson.JSONObject;
import io.appium.java_client.android.Activity;
import io.appium.java_client.android.AndroidDriver;
import org.nix.learn.auto.core.appium.config.AndroidPhoneConfig;
import org.nix.learn.auto.core.appium.create.DefaultAndroidDriver;
import org.nix.learn.auto.task.TaskException;
import org.nix.learn.auto.utils.Base64;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.remote.Response;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.*;

/**
 * 一组schema执行在一个driver中
 *
 * @author zhangpei341@pingan.cn.com 2018/8/21 上午10:48
 * @version 1.0
 */
public class TaskListThread implements Callable<PhoneRunPresentation> {

    /**
     * 需要运行的schema集合
     */
    private Set<Schema> schemas;

    /**
     * 执行的驱动
     */
    private AndroidDriver driver;

    /**
     * 这次执行保存结果的地方
     */
    private String keepDir;

    public TaskListThread(Set<Schema> schemas, AndroidDriver driver, String keepDir) {
        this.schemas = schemas;
        this.driver = driver;
        this.keepDir = keepDir;
    }

    @Override
    public PhoneRunPresentation call() throws Exception {

        PhoneRunPresentation presentation = createPresentation();
        for (Schema schema : schemas) {
            createPresentation(presentation,runOneSchema(schema));
        }
        return presentation;
    }

    private PhoneRunPresentation createPresentation() {
        URL url = driver.getRemoteAddress();
        // 3.手机分辨率
        int h = driver.manage().window().getSize().getHeight();
        int w = driver.manage().window().getSize().getWidth();
        // 4.手机操作系统基本信息
        String name = driver.getPlatformName();

        PhoneRunPresentation runPresentation = new PhoneRunPresentation(url.toString(), name, h + "*" + w);
        return runPresentation;
    }


    private void createPresentation(PhoneRunPresentation runPresentation, SchemaRunPresentation schemaRunPresentation) {
        runPresentation.addSchemaPresentation(schemaRunPresentation);
    }


    /**
     * 返回这个schema到运行结果
     *
     * @param schema 需要运行到schema
     * @return 返回这个schema到运行结果
     */
    private SchemaRunPresentation runOneSchema(Schema schema) {

        SchemaRunPresentation schemaRunPresentation = new SchemaRunPresentation(schema);

        try {
            System.err.println(driver.getContextHandles());
            // 进入到起始页面
            driver.startActivity(new Activity(DefaultApkDetails.APP_PACKAGE, DefaultApkDetails.APP_ACTIVITY));
            driver.findElement(By.id(DefaultApkDetails.SEND_KEY_ID)).sendKeys(schema.obtainCompletePath());
            driver.findElement(By.id(DefaultApkDetails.CLICK_ID)).click();
            Thread.currentThread().sleep(10000);
            File file = driver.getScreenshotAs(OutputType.FILE);
            String savePath = savePicture(file,schema.getName());

            schemaRunPresentation.setRunResult(true);
            schemaRunPresentation.setScreenshotPath(savePath);
            schemaRunPresentation.setRemarks("运行成功");
            return schemaRunPresentation;
        } catch (InterruptedException e) {
            schemaRunPresentation.setRemarks("时间暂停失败，运行失败:"+e.getMessage());
            schemaRunPresentation.setRunResult(false);
            return schemaRunPresentation;
        } catch (TaskException e) {
            schemaRunPresentation.setRemarks("文件保存失败，运行失败:"+e.getMessage());
            schemaRunPresentation.setRunResult(false);
            return schemaRunPresentation;
        }catch (WebDriverException e){
            schemaRunPresentation.setRemarks("appium异常，运行失败:"+e.getMessage());
            schemaRunPresentation.setRunResult(false);
            return schemaRunPresentation;
        }
    }

    /**
     * 返回截图保存的地址
     *
     * @param file 需要保存的文件
     * @return 保存文件的地址
     */
    private String savePicture(File file,String fileName) {
        Path path = Paths.get(keepDir, String.valueOf(driver.getRemoteAddress().getPort()),fileName, file.getName());
        Path dir = path.getParent();
        if (!Files.exists(dir)) {
            try {
                Files.createDirectories(dir);
            } catch (IOException e) {
                file.delete();
                throw new TaskException("创建目录失败:"+path, e);
            }
        }
        try {
            Files.write(path, Base64.readBytes(file));
            return path.toString();
        } catch (FileNotFoundException e) {
            throw new TaskException("想要保存的文件不存在", e);
        } catch (IOException e) {
            throw new TaskException("获取文件大小失败", e);
        } finally {
            file.delete();
        }
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        String prent = "yqbnative://app.1qianbao.com/";
        Set<Schema> schemas = new HashSet<>();
        String one_1 = prent + "huoqianbao/index";
        String two_1 = prent + "assets/integral";
        String three_1 = prent + "setting/index";
        String four_1 = prent + "lifepay/index";

        Schema one = new Schema("活钱宝", one_1, true, "4.0.0", "/Users/mac/IdeaProjects/auto_git/src/main/file/4723/screenshot60220872694868756.png");
        Schema two = new Schema("积分资产页", two_1, true, "4.0.0", "/Users/mac/IdeaProjects/auto_git/src/main/file/4723/screenshot60220872694868756.png");
        Schema three = new Schema("设置", three_1, true, "4.0.0", "/Users/mac/IdeaProjects/auto_git/src/main/file/4723/screenshot60220872694868756.png");
        Schema four = new Schema("生活缴费", four_1, true, "4.0.0", "/Users/mac/IdeaProjects/auto_git/src/main/file/4723/screenshot60220872694868756.png");

        schemas.add(one);
        schemas.add(two);
        schemas.add(three);
        schemas.add(four);

        //                   http://127.0.0.0:4723/wd/hub
        String appiumPath = "http://127.0.0.1:4723/wd/hub";
        String udid = "1267e25a";
        String keepDir = "/Users/mac/IdeaProjects/auto_git/src/main/file/";

        TaskListThread taskListThread = new TaskListThread(schemas, createAndroidDriver(appiumPath, udid), keepDir);
        ExecutorService service = Executors.newFixedThreadPool(1);
        Future f = service.submit(taskListThread);
        System.out.println(JSONObject.toJSONString(f.get()));
        service.shutdown();
    }


    private static AndroidDriver createAndroidDriver(String appiumPath, String udid) {
        String platformVersion = "4.4.4";

        AndroidPhoneConfig config = new AndroidPhoneConfig(
                DefaultApkDetails.APP_ACTIVITY, DefaultApkDetails.APP_PACKAGE,udid);

        DefaultAndroidDriver defaultAndroidDriver = new DefaultAndroidDriver(config, appiumPath);
        return (AndroidDriver) defaultAndroidDriver.getDriver();
    }


}
