package org.nix.learn.auto;

import com.alibaba.fastjson.JSON;
import org.apache.log4j.Logger;
import org.nix.learn.auto.functions.presentation.LinkedMapPresentation;

import java.util.Map;

/**
 * @author zhangpei341@pingan.cn.com 2018/9/17 下午6:13
 * @version 1.0
 */
public class Main {

    private static final Logger logger = Logger.getLogger(Main.class);

    public static void main(String[] args) {
        String str =
                "{\"next\":[{\"data\":{\"0\":1},\"next\":[{\"data\":{\"0\":{\"androidDeviceReadyTimeout\":60,\"appActivity\":\".MainActivity\",\"appPackage\":\"com.yqb.pingan.yqb\",\"autoLaunch\":true,\"autoWebview\":false,\"autoWebviewTimeout\":2000,\"automationName\":\"Appium\",\"avdLaunchTimeout\":60000,\"avdReadyTimeout\":60000,\"databaseEnabled\":false,\"desired\":{\"appPackage\":\"com.yqb.pingan.yqb\",\"noReset\":true,\"dontStopAppOnReset\":false,\"disableAndroidWatchers\":false,\"deviceName\":\"1267e25a\",\"deviceReadyTimeout\":60,\"fullReset\":false,\"ignoreUnimportantViews\":false,\"newCommandTimeout\":60,\"automationName\":\"Appium\",\"autoWebview\":false,\"unicodeKeyboard\":false,\"platformName\":\"Android\",\"udid\":\"1267e25a\",\"enablePerformanceLogging\":false,\"resetKeyboard\":false,\"useKeystore\":false,\"androidDeviceReadyTimeout\":60,\"orientation\":\"PORTRAIT\",\"autoWebviewTimeout\":2000,\"noSign\":false,\"appActivity\":\".MainActivity\",\"avdLaunchTimeout\":60000,\"autoLaunch\":true,\"avdReadyTimeout\":60000},\"deviceManufacturer\":\"vivo\",\"deviceModel\":\"vivo X9s Plus\",\"deviceName\":\"1267e25a\",\"deviceReadyTimeout\":60,\"deviceScreenSize\":\"1080x1920\",\"deviceUDID\":\"1267e25a\",\"disableAndroidWatchers\":false,\"dontStopAppOnReset\":false,\"enablePerformanceLogging\":false,\"fullReset\":false,\"ignoreUnimportantViews\":false,\"javascriptEnabled\":true,\"locationContextEnabled\":false,\"networkConnectionEnabled\":true,\"newCommandTimeout\":60,\"noReset\":true,\"noSign\":false,\"orientation\":\"PORTRAIT\",\"platform\":\"LINUX\",\"platformName\":\"LINUX\",\"platformVersion\":\"7.1.2\",\"resetKeyboard\":false,\"takesScreenshot\":true,\"udid\":\"1267e25a\",\"unicodeKeyboard\":false,\"useKeystore\":false,\"warnings\":{},\"webStorageEnabled\":false}},\"next\":[{\"data\":{\"0\":{\"path\":\"yqbnative://app.1qianbao.com/huoqianbao/index\",\"useVersion\":\"3.0.0\",\"createTime\":\"2018-09-17 16:58:52\",\"use\":true,\"name\":\"???\",\"updateTime\":\"2018-09-17 16:58:52\",\"id\":\"e66c9095ba5711e8a1f65cf3fcddc180\",\"remarks\":\"????\"},\"1\":\"/Users/mac/IdeaProjects/auto_git/src/main/file/test/2018-09-18/screenshot911084796150346974.png\"},\"ok\":true,\"status\":{\"all\":1,\"fail\":0,\"ok\":true,\"runSchedule\":1.0,\"success\":1}},{\"data\":{\"0\":{\"path\":\"yqbnative://app.1qianbao.com/assets/integral\",\"useVersion\":\"3.0.0\",\"createTime\":\"2018-09-17 16:58:52\",\"use\":true,\"name\":\"assets\",\"updateTime\":\"2018-09-17 16:58:52\",\"id\":\"e66c94f4ba5711e8a1f65cf3fcddc180\",\"remarks\":\"????\"},\"1\":\"/Users/mac/IdeaProjects/auto_git/src/main/file/test/2018-09-18/screenshot1832644616335084343.png\"},\"ok\":true,\"status\":{\"all\":1,\"fail\":0,\"ok\":true,\"runSchedule\":1.0,\"success\":1}},{\"data\":{\"0\":{\"path\":\"yqbnative://app.1qianbao.com/setting/index\",\"useVersion\":\"3.0.0\",\"createTime\":\"2018-09-17 16:58:52\",\"use\":true,\"name\":\"??\",\"updateTime\":\"2018-09-17 16:58:52\",\"id\":\"e66c963aba5711e8a1f65cf3fcddc180\",\"remarks\":\"????\"},\"1\":\"/Users/mac/IdeaProjects/auto_git/src/main/file/test/2018-09-18/screenshot8905976448454191761.png\"},\"ok\":true,\"status\":{\"all\":1,\"fail\":0,\"ok\":true,\"runSchedule\":1.0,\"success\":1}}],\"ok\":true,\"status\":{\"all\":3,\"fail\":0,\"ok\":true,\"runSchedule\":1.0,\"success\":3}}],\"ok\":true,\"status\":{\"all\":1,\"fail\":0,\"ok\":true,\"runSchedule\":1.0,\"success\":1}}],\"ok\":true,\"status\":{\"all\":1,\"fail\":0,\"ok\":true,\"runSchedule\":1.0,\"success\":1}}";
        LinkedMapPresentation map = JSON.parseObject(str, LinkedMapPresentation.class);
        System.out.println();
    }
}
