package org.nix.learn.auto.functions.schema;

/**
 * 一个定义schema应该如何运行的接口
 * 这个接口主要用于扩展执行scheme测试时的方式
 * 只要实现该接口就可以将实现类装载到执行类中运行相应的方法
 * @author zhangpei341@pingan.cn.com 2018/8/22 下午2:38
 * @version 1.0
 */
public interface SchemaRun {

    /**
     * 定义实现如何完成任务
     */
    void runTask();

}
