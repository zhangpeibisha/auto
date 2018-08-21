package org.nix.learn.auto.task.android;

import org.nix.learn.auto.core.appium.server.UseAppiumServer;

import java.util.List;

/**
 * 执行需求：
 * 1。每条schema需要在每台手机上执行，并有结果
 * 2。每条schema在每台手机上运行后的结果将集中放在一起，方便进行比较
 * 3。一次任务生成一个文件保存结果
 * @author zhangpei
 * @version 1.0
 * @date 2018/8/20
 */
public class TaskList {

    /**
     * 将要执行任务的服务器列表
     */
    private List<UseAppiumServer> useAppiumServers;

    /**
     * 需要测试的schema，每个schema都需要运行一次
     * 并保存在一个报告中
     */
    private List<String> schemas;






}
