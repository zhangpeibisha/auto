package org.nix.learn.auto.core.appium.server;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 一台装有appium服务器的电脑的信息
 * 这个服务器的使用情况将存储在redis中
 * 一个服务端口只对应一个服务手机
 * @author zhangpei
 * @version 1.0
 * @date 2018/8/20
 */
public class AppiumServer {

    /**
     * 这台服务器的地址,电脑的IP地址
     */
    private String IP;

    /**
     * 这台服务器上的端口使用情况
     * key为端口号，value为是否使用
     */
    private Map<String,Boolean> ports;






    public String getIP() {
        return IP;
    }

    public void setIP(String IP) {
        this.IP = IP;
    }
}
