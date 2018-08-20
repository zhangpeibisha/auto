package org.nix.learn.auto.task.example;

import org.nix.learn.auto.task.KeepTaskTo;
import org.nix.learn.auto.task.TaskResult;
import org.nix.learn.auto.task.example.AndroidTaskDrivers;

import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author zhangpei341@pingan.cn.com 2018/8/20 上午9:26
 * @version 1.0
 */
public  class SchemaTaskThread implements TaskResult,Runnable {

    /**
     * 需要执行的schema集合
     */
    private Set<String> schema;

    /**
     * 需要运行的电脑
     */
    private Set<AndroidTaskDrivers> drivers;

    /**
     * 保存结果的目录
     */
    private String keepResultDir;

    /**
     * 任务集合运行线程池
     */
    private ExecutorService pool;

    public SchemaTaskThread(Set<String> schema, Set<AndroidTaskDrivers> drivers) {
        this.schema = schema;
        this.drivers = drivers;
        initPool();
    }

    private void initPool() {
        pool = Executors.newFixedThreadPool(drivers.size());
    }

    @Override
    public void run() {

    }

    @Override
    public void obtainTaskResult() {

    }

    @Override
    public void taskResultTo(KeepTaskTo taskTo) {

    }

    class RunOneSchema {


    }

}
