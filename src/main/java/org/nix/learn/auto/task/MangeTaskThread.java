package org.nix.learn.auto.task;


import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 提交任务线程进入到线程池管理
 * @author zhangpei341@pingan.cn.com 2018/8/20 上午9:09
 * @version 1.0
 */
public class MangeTaskThread {

    private static final ExecutorService pool = Executors.newCachedThreadPool();

    public static synchronized void push(Thread thread){
        pool.execute(thread);
    }

}
