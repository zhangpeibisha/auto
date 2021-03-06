package org.nix.learn.auto.functions.schema.android;

import org.apache.log4j.Logger;
import org.nix.learn.auto.core.appium.config.AndroidPhoneConfig;
import org.nix.learn.auto.core.appium.create.DefaultAndroidDriver;
import org.nix.learn.auto.core.appium.server.AppiumServer;
import org.nix.learn.auto.functions.presentation.Presentation;
import org.nix.learn.auto.functions.presentation.PresentationContent;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * 对每台电脑的任务封装
 *
 * @author zhangpei341@pingan.cn.com 2018/8/23 下午3:59
 * @version 1.0
 */
public class RunStaple {

    private static final Logger logger = Logger.getLogger(RunStaple.class);

    private AppiumServer server;

    private List<String> udids;

    /**
     * 父级报告集合
     */
    private Presentation prentPresentation;

    /**
     * 暂时没有手机测试时创建
     * @param server
     * @param prentPresentation
     */
    public RunStaple(AppiumServer server, Presentation prentPresentation) {
        this.server = server;
        this.prentPresentation = prentPresentation;
    }

    /**
     * 一台电脑上的任务部署
     *
     * @param server            一台服务器的信息
     * @param udids             链接在这台电脑上的手机
     * @param prentPresentation 父级报告
     */
    public RunStaple(AppiumServer server, List<String> udids, Presentation prentPresentation) {
        this.server = server;
        this.udids = udids;
        this.prentPresentation = prentPresentation;
    }

    /**
     * 目前完成：当udid小于等于server的开放端口时完成
     * 后期目标：可以采用队列形式，当udid的数量大于server时，采用排队等待或者轮换机制进行运行
     *
     * @return
     */
    public List<DefaultAndroidDriver> createDefaultAndroidDrivers() {

        /**
         * 获取到这台电脑上所有注册的appium服务器
         */
        List<String> paths = server.allAbleUseCompleteIp();
        int pathsLen = paths.size();
        int udidsLen = udids.size();

        int min = Math.min(pathsLen, udidsLen);
        int max = Math.max(pathsLen, udidsLen);
        List<DefaultAndroidDriver> defaultAndroidDrivers = new ArrayList<>();

        for (int i = 0; i < min; i++) {
            AndroidPhoneConfig config = new AndroidPhoneConfig
                    (FunctionsApk.APP_ACTIVITY, FunctionsApk.APP_PACKAGE, udids.get(i));
            DefaultAndroidDriver defaultAndroidDriver = new DefaultAndroidDriver(config, paths.get(i));
            defaultAndroidDrivers.add(defaultAndroidDriver);
        }

        // 意味着将有部分手机不能使用，执行任务
        if (max == udidsLen && max != min) {
            prentPresentation.putCurr("the phones can't run ", udids.subList(min+1, max));
        }
        return defaultAndroidDrivers;
    }

    /**
     * 添加udid
     * @param udid
     */
    public void addUdid(String udid){
        if (udids == null){
            udids = new ArrayList<>();
        }
        udids.add(udid);
    }

    public AppiumServer getServer() {
        return server;
    }

    public void setServer(AppiumServer server) {
        this.server = server;
    }
}
