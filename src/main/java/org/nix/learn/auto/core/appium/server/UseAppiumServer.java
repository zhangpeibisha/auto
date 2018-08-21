package org.nix.learn.auto.core.appium.server;

import java.util.Map;
import java.util.Set;

/**
 * 一个服务器只要IP确认，
 * @author zhangpei341@pingan.cn.com 2018/8/21 上午10:34
 * @version 1.0
 */
public class UseAppiumServer extends AppiumServer{

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


}
