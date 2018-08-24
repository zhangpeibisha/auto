package org.nix.learn.auto.core.appium;

import io.appium.java_client.AppiumDriver;
import org.nix.learn.auto.utils.Base64;
import org.nix.learn.auto.utils.DateUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriverException;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * appium常用方法
 * <p>
 * !!!!思考是否会存在并发问题，如果存在，则枷锁处理
 *
 * @author zhangpei341@pingan.cn.com 2018/8/20 下午5:20
 * @version 1.0
 */
public class AppiumUtils {

    /**
     * 获取driver的配置信息
     *
     * @param driver 需要获取的driver
     * @return 该driver操作的手机序列号
     */
    public synchronized static String getPhoneId(final AppiumDriver driver) {
        return driver.getCapabilities().getCapability("udid").toString();
    }

    /**
     * 获取配置里面的参数信息
     *
     * @param driver         需要获取的driver
     * @param capabilityName 需要查找的信息
     * @return 如果没有这个键则返回null 否则返回对应的值
     */
    public synchronized static String getCapabilities(final AppiumDriver driver, final String capabilityName) {
        Capabilities capabilities = driver.getCapabilities();
        if (capabilities.is(capabilityName)) {
            return capabilities.getCapability(capabilityName).toString();
        }
        return null;
    }

    public synchronized static boolean checkWebView(final AppiumDriver driver, final String id) {
        return checkWebView(driver, driver.getContextHandles().iterator(), id);
    }

    /**
     * 查看这个驱动中webview是否有这个标签，如果有则切换context
     * 通过类名，driver，按钮ID来生成一个唯一的key保存这个context,设置过期时间为10分钟
     *
     * @param driver   驱动
     * @param webviews 该驱动中的webview集合
     * @param id       需要查询的id
     * @return 是否含有
     */
    public synchronized static boolean checkWebView(final AppiumDriver driver, final Iterator<String> webviews, final String id) {
        String web = "";
        try {
            if (webviews.hasNext()) {
                web = webviews.next();
                driver.context(web);
                driver.findElement(By.id(id));
                return true;
            }
            return false;
        } catch (WebDriverException e) {
            return checkWebView(driver, webviews, id);
        }
    }

    /**
     * 点击这个元素
     * 自动寻找
     *
     * @param driver 对应手机的driver
     * @param id     需要操作元素的ID
     * @throws NoSuchElementException 如果自动找这个ID对应的页面没有找到则抛出
     */
    public synchronized static void clickElement(final AppiumDriver driver, final String id) throws NoSuchElementException {
        boolean isHave = checkWebView(driver, id);
        if (!isHave) {
            throw new NoSuchElementException(driver.getRemoteAddress() + "没有找到这个元素" + id);
        }
        driver.findElement(By.id(id)).click();

    }

    /**
     * 在指定的手机的context中的指定ID按钮进行点击
     *
     * @param driver  对应手机的driver
     * @param context 对应上下文
     * @param id      需要操作元素的ID
     * @throws NoSuchElementException 如果自动找这个ID对应的页面没有找到则抛出
     */
    public synchronized static void clickElement(final AppiumDriver driver, final String context, final String id) throws NoSuchElementException {

        if (context == null) {
            clickElement(driver, id);
        } else {
            driver.context(context);
            driver.findElement(By.id(id)).click();
        }

    }

    /**
     * 向元素中输入指定的值
     * 自动寻找
     *
     * @param driver  对应手机的driver
     * @param id      需要操作元素的ID
     * @param sendKey 需要发送的字符串
     * @throws NoSuchElementException 如果自动找这个ID对应的页面没有找到则抛出
     */
    public synchronized static void sendKeyElement(final String id, final AppiumDriver driver, final CharSequence... sendKey) throws NoSuchElementException {

        boolean isHave = checkWebView(driver, id);
        if (!isHave) {
            throw new NoSuchElementException(driver.getRemoteAddress() + "没有找到这个元素" + id);
        }
        driver.findElement(By.id(id)).sendKeys(sendKey);

    }

    /**
     * 在指定的手机的context中的指定ID按钮进行输入信息
     *
     * @param driver  驱动
     * @param context 上下文
     * @param id      输入框ID
     * @param sendKey 输入的信息
     * @throws NoSuchElementException 如果自动找这个ID对应的页面没有找到则抛出
     */
    public synchronized static void sendKeyElement(final String context, final String id, final AppiumDriver driver, final CharSequence... sendKey) throws NoSuchElementException {
        if (context == null) {
            sendKeyElement(id, driver, sendKey);
        } else {
            driver.context(context);
            driver.findElement(By.id(id)).sendKeys(sendKey);
        }
    }

    /**
     * 保存手机截图
     * synchronized
     *
     * 同步问题暂时未检测出问题
     * @param driver 操作的driver
     * @param path   保存的路径
     * @return 保存的路径
     * @throws IOException 写文件异常
     * @throws WebDriverException appium操作异常
     */
    public  static Path screenshot(final AppiumDriver driver, Path path) throws IOException ,WebDriverException{
            try {
                File file = driver.getScreenshotAs(OutputType.FILE);
                path = Paths.get(path.toString(), DateUtils.getCurrentDate(),file.getName());
                Path prent = path.getParent();
                if (!Files.exists(prent)) {
                    Files.createDirectories(prent);
                }
                Files.write(path, Base64.readBytes(file));
                return path;
            } catch (IOException e) {
                throw new IOException("写入文件失败");
            }catch (WebDriverException e){
                throw new WebDriverException("手机截图失败",e);
            }
    }


}
