package org.nix.learn.auto.functions.schema;

import org.apache.log4j.Logger;
import org.nix.learn.auto.utils.LogUtils;

import java.util.concurrent.*;


/**
 * schema线程池管理类
 * @author zhangpei341@pingan.cn.com 2018/8/22 下午2:31
 * @version 1.0
 */
public class SchemaThreadPool {

    private static final Logger logger = Logger.getLogger(SchemaThreadPool.class);

    /**
     * 线程池的基本大小
     */
    private static int corePoolSize = 10;
    /**
     * 线程池最大数量
     */
    private static int maximumPoolSizeSize = 100;
    /**
     * 线程活动保持时间
     */
    private static long keepAliveTime = 1;
    /**
     * 任务队列
     */
    private static ArrayBlockingQueue workQueue = new ArrayBlockingQueue(10);


    private static final ExecutorService pool = new ThreadPoolExecutor(
            corePoolSize,
            maximumPoolSizeSize,
            keepAliveTime,
            TimeUnit.SECONDS,
            workQueue,
            new ThreadPoolExecutor.AbortPolicy()
    );

    public static void put(Thread thread){
        try {
            pool.submit(thread);
        }catch (NullPointerException e){
            LogUtils.printLog("加入任务失败",e);
        }
    }

    public static void put(Runnable thread){
        try {
            pool.submit(thread);
        }catch (NullPointerException e){
            LogUtils.printLog("加入任务失败",e);
        }
    }

    /**
     * 有返回值的线程
     * @param thread
     * @return
     */
    public static Future put(Callable thread){
        try {
           return pool.submit(thread);
        }catch (NullPointerException e){
            LogUtils.printLog("加入任务失败",e);
            throw new NullPointerException("加入任务失败，线程为空指针");
        }
    }


}
