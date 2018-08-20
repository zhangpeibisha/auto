package org.nix.learn.auto.task;

import io.appium.java_client.AppiumDriver;
import org.nix.learn.auto.task.TaskResult;

import java.util.concurrent.Callable;

/**
 * @author zhangpei341@pingan.cn.com 2018/8/20 下午3:09
 * @version 1.0
 */
public interface RunTask extends Callable<String> {

    void runTask();

}
