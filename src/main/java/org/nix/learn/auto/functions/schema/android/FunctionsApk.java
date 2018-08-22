package org.nix.learn.auto.functions.schema.android;

import org.apache.log4j.Logger;

/**
 * 为了维持稳定的按键功能，将采用一个自制的apk来作为中介去
 * 操控目的测试apk。
 *
 * 但是由于某些机型的问题，导入的apk的操作页面可能是webView或者是NATIVE_APP
 * 是不固定的，所有需要解决这个问题。
 *
 * 该类的功能主要是为了统一管理功能apk的按钮id和应用包和应用活动启动项
 * 也可以作为其它需要统一管理的参数的承载类
 *
 * @author zhangpei341@pingan.cn.com 2018/8/22 下午4:23
 * @version 1.0
 */
public class FunctionsApk {

    private static final Logger logger = Logger.getLogger(FunctionsApk.class);

    public static final String APP_PACKAGE = "io.dcloud.H5462000D";

    public static final String APP_ACTIVITY = "io.dcloud.PandoraEntryActivity";

    public static final String SEND_KEY_ID = "writerURI";

    public static final String CLICK_ID = "clickRun";
}
