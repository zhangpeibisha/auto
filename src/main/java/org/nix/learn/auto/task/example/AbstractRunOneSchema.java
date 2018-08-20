package org.nix.learn.auto.task.example;

import io.appium.java_client.AppiumDriver;
import org.nix.learn.auto.task.RunTask;
import org.nix.learn.auto.task.TaskException;
import org.nix.learn.auto.task.TaskResult;

/**
 * @author zhangpei341@pingan.cn.com 2018/8/20 下午3:14
 * @version 1.0
 */
public abstract class AbstractRunOneSchema implements RunTask, TaskResult {

    protected AppiumDriver driver;

    public AbstractRunOneSchema(AppiumDriver driver) {
        this.driver = driver;
    }

    @Override
    public String call() {
        String result;
        try {
            runTask();
            result = obtainTaskResult("运行成功");
        } catch (TaskException e) {
            result = obtainTaskResult("运行失败", e.getMessage());
        }
        taskResultTo(result);
        return result;
    }

}
