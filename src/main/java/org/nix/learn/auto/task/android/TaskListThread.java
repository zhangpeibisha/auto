package org.nix.learn.auto.task.android;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.Activity;
import io.appium.java_client.android.AndroidDriver;
import org.nix.learn.auto.core.appium.config.AndroidPhoneConfig;
import org.nix.learn.auto.core.appium.create.DefaultAndroidDriver;
import org.nix.learn.auto.task.TaskException;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.*;

/**
 * 一组schema执行在一个driver中
 *
 * @author zhangpei341@pingan.cn.com 2018/8/21 上午10:48
 * @version 1.0
 */
public class TaskListThread implements Callable<String> {

    /**
     * 需要运行的schema集合
     */
    private Set<String> schemas;

    /**
     * 执行的驱动
     */
    private AndroidDriver driver;

    /**
     * 这次执行保存结果的地方
     */
    private String keepDir;

    public TaskListThread(Set<String> schemas, AndroidDriver driver, String keepDir) {
        this.schemas = schemas;
        this.driver = driver;
        this.keepDir = keepDir;
    }

    @Override
    public String call() throws Exception {

        StringBuilder builder = new StringBuilder();
        getPhoneInfo(builder);
        for (String schema : schemas) {
            builder.append(runOneSchema(schema)).append("\n");
        }
        return builder.toString();
    }

    /**
     * 返回这个schema到运行结果
     *
     * @param schema 需要运行到schema
     * @return 返回这个schema到运行结果
     */
    private String runOneSchema(String schema) {

        String result = schema;

        // 进入到起始页面
        driver.startActivity(new Activity(DefaultApkDetails.APP_PACKAGE, DefaultApkDetails.APP_ACTIVITY));
        driver.findElement(By.id(DefaultApkDetails.SEND_KEY_ID)).sendKeys(schema);
        driver.findElement(By.id(DefaultApkDetails.CLICK_ID)).click();
        try {
            Thread.currentThread().sleep(10000);
            File file = driver.getScreenshotAs(OutputType.FILE);
            String savePath = savePicture(file);
            return result + "[执行成功] --- 截图保存地址为：[" + savePath + "]";
        } catch (InterruptedException e) {
            return result + "[执行失败，时间暂停失败，]" + e.getMessage();
        } catch (TaskException e) {
            return result + "[执行失败，文件保存失败，]" + e.getMessage();
        }
    }

    /**
     * 返回截图保存的地址
     *
     * @param file 需要保存的文件
     * @return 保存文件的地址
     */
    private String savePicture(File file) {
        Path path = Paths.get(keepDir, driver.getRemoteAddress().getPort() + "", file.getName());
        Path dir = path.getParent();
        if (!Files.exists(dir)) {
            try {
                Files.createDirectory(dir);
            } catch (IOException e) {
                file.delete();
                throw new TaskException("创建目录失败", e);
            }
        }
        try {
            FileInputStream input = new FileInputStream(file);
            byte[] bytes = new byte[input.available()];
            input.read(bytes);
            Files.write(path, bytes);
            return path.toString();
        } catch (FileNotFoundException e) {
            throw new TaskException("想要保存的文件不存在", e);
        } catch (IOException e) {
            throw new TaskException("获取文件大小失败", e);
        }finally {
            file.delete();
        }
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        String prent = "yqbnative://app.1qianbao.com/";
        Set<String> schemas = new HashSet<>();
        String one = prent + "huoqianbao/index";
        String two = prent + "cardmange/index";
        String three = prent + "score/index";
        String four = prent + "lifepay/index";
        schemas.add(one);
        schemas.add(two);
        schemas.add(three);
        schemas.add(four);

        String appiumPath = "http://127.0.0.1:4723/wd/hub";
        String udid = "1267e25a";
        String keepDir = "/Users/mac/IdeaProjects/auto_git/src/main/file/";

        TaskListThread taskListThread = new TaskListThread(schemas, createAndroidDriver(appiumPath, udid), keepDir);
        ExecutorService service = Executors.newFixedThreadPool(1);
        Future f = service.submit(taskListThread);
        System.out.println(f.get());
        service.shutdown();
    }


    private static AndroidDriver createAndroidDriver(String appiumPath, String udid) {
        String platformVersion = "4.4.4";

        AndroidPhoneConfig config = new AndroidPhoneConfig(
                platformVersion, udid, udid, DefaultApkDetails.APP_ACTIVITY, DefaultApkDetails.APP_PACKAGE);

        DefaultAndroidDriver defaultAndroidDriver = new DefaultAndroidDriver(config, appiumPath);
        return (AndroidDriver) defaultAndroidDriver.getDriver();
    }

    private void getPhoneInfo(StringBuilder builder) {
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
    }
}
