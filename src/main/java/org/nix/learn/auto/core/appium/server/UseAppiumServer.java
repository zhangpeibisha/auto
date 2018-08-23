package org.nix.learn.auto.core.appium.server;

import com.alibaba.fastjson.JSONObject;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.nix.learn.auto.core.appium.AppiumException;
import org.nix.learn.auto.core.appium.config.AndroidPhoneConfig;
import org.nix.learn.auto.core.appium.create.DefaultAndroidDriver;
import org.nix.learn.auto.task.android.DefaultApkDetails;
import org.openqa.selenium.Capabilities;

import java.util.*;
import java.util.concurrent.Callable;

/**
 * 一个服务器只要IP确认，
 *
 * @author zhangpei341@pingan.cn.com 2018/8/21 上午10:34
 * @version 1.0
 */
public class UseAppiumServer extends AppiumServer implements Callable<List<AndroidDriver>> {

    /**
     * 在这个服务器上的手机的udids
     */
    private Set<String> udids;

    public UseAppiumServer(String IP, Set<String> udids) {
        super(IP);
        this.udids = udids;
    }

    public UseAppiumServer(String IP, Map<String, Boolean> ports, Set<String> udids) {
        super(IP, ports);
        this.udids = udids;
    }

    /**
     * 生成的路径数量应该和udid的数量相同，
     * 或者udid的数量少于服务器端口数量
     * 因为一个端口只能对应一个手机
     *
     * @return 创建的driver
     * @throws AppiumException 如果udid的数量超出服务器数量时抛出
     */
    private List<AndroidDriver> createDriver() throws AppiumException {

        List<String> paths = allAbleUseCompleteIp();
        List<AndroidDriver> drivers = new ArrayList<>();
        int pathLen = paths.size();
        int udisLen = udids.size();
        if (pathLen < udisLen) {
            throw new AppiumException("需要执行的手机数量已经超过了服务器数量，请检查服务器配置");
        }

        String[] udidsStr = new String[udisLen];
        udidsStr = udids.toArray(udidsStr);
        for (int i = 0; i < udisLen; i++) {
            AndroidDriver driver = createDriver(udidsStr[i], paths.get(i));
            drivers.add(driver);
        }
        return drivers;
    }

    private AndroidDriver createDriver(String udid, String appiumPath) {

        AndroidPhoneConfig config = new AndroidPhoneConfig(DefaultApkDetails.APP_ACTIVITY, DefaultApkDetails.APP_PACKAGE, udid);
        System.out.println();

        DefaultAndroidDriver defaultAndroidDriver = new DefaultAndroidDriver(config, appiumPath);
        AndroidDriver driver = (AndroidDriver) defaultAndroidDriver.getDriver();

        return driver;
    }

    public static void main(String[] args) {
        String path = "127.0.0.1";
        Map<String, Boolean> map = new HashMap<>();
        map.put("4723", true);
        map.put("4725", false);

        Set<String> udids = new HashSet<>();
        udids.add("1267e25a");

        UseAppiumServer server = new UseAppiumServer(path, map, udids);
        List<AndroidDriver> drivers = server.createDriver();
        AppiumDriver driver = drivers.get(0);

//        DesiredCapabilities capabilities1 = new DesiredCapabilities();
//        capabilities1.setCapability("phoneName","vivo");
//        driver.getCapabilities().merge(capabilities1);

        Capabilities capabilities = driver.getCapabilities();

        Map map1 = capabilities.asMap();
        System.out.println(JSONObject.toJSONString(map1));
    }

    public static UseAppiumServer getUserAppiumServer(){
        String path = "127.0.0.1";

        Map<String, Boolean> map = new HashMap<>();
        map.put("4723", true);
        map.put("4725", true);
        map.put("4727", true);

        Set<String> udids = new HashSet<>();
        udids.add("1267e25a");
        udids.add("ENU7N15A12003472");
//        udids.add("fa91b0a5");

        UseAppiumServer server = new UseAppiumServer(path, map, udids);
        return server;
    }

    @Override
    public List<AndroidDriver> call() throws Exception {
        return createDriver();
    }
}
