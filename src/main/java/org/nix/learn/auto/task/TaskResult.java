package org.nix.learn.auto.task;

/**
 * 定义一个任务报告需要做的一些事情
 * @author zhangpei341@pingan.cn.com 2018/8/20 上午8:58
 * @version 1.0
 */
public interface TaskResult{

    /**
     * 获取任务执行报告
     */
    String obtainTaskResult(String... value);

    /**
     * 定义任务结果应该去哪里
     */
    void taskResultTo(String result);
}
