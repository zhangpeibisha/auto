package org.nix.learn.auto.task.android;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import io.appium.java_client.android.AndroidDriver;
import org.nix.learn.auto.core.appium.server.UseAppiumServer;
import org.nix.learn.auto.task.TaskException;

import java.util.*;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

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
    private Set<Schema> schemas;

    /**
     * 任务执行线程池
     */
    private ExecutorService pool = Executors.newCachedThreadPool();

    /**
     * 截图保存目录
     */
    private String keepDir;

    /**
     * 统一执行任务
     * @param useAppiumServers 需要执行任务的电脑的集合
     * @param schemas 需要测试的schema
     * @param keepDir 将截图保存的目录
     */
    public TaskList(List<UseAppiumServer> useAppiumServers, Set<Schema> schemas, String keepDir) {
        this.useAppiumServers = useAppiumServers;
        this.schemas = schemas;
        this.keepDir = keepDir;
    }

    /**
     * 把需要执行任务的手机启动
     * @return 返回初始化好的driver
     */
    private List<AndroidDriver> initDriver(){
        List<AndroidDriver> drivers = new ArrayList<>();
        List<Future> futures = new ArrayList<>();
        for (UseAppiumServer server : useAppiumServers) {
           Future future = pool.submit(server);
           futures.add(future);
        }
        for (Future future : futures) {
            try {
                drivers.addAll((Collection<? extends AndroidDriver>) future.get());
            } catch (InterruptedException | ExecutionException e) {
                throw new TaskException("生成driver失败",e);
            }
        }
        return drivers;
    }

    /**
     * 最终执行任务的方法
     */
    public void run(){
        List<Future> futures = new ArrayList<>();
        for (AndroidDriver driver : initDriver()) {
            TaskListThread taskListThread = new TaskListThread(schemas,driver,keepDir);
            Future future = pool.submit(taskListThread);
            futures.add(future);
        }
        for (Future future : futures) {
            try {
                keepResult(future.get());
            } catch (InterruptedException | ExecutionException e) {

            }
        }
    }

    /**
     * 这是获取文字报告的方法，截图在之前自定义的方法中
     * @param o 返回的结果
     */
    public void keepResult(Object o){
        phoneRunPresentations.add((PhoneRunPresentation) o);
    }

    private List<PhoneRunPresentation> phoneRunPresentations = new ArrayList<>();

    public List<PhoneRunPresentation> getPhoneRunPresentations() {
        return phoneRunPresentations;
    }

    public void setPhoneRunPresentations(List<PhoneRunPresentation> phoneRunPresentations) {
        this.phoneRunPresentations = phoneRunPresentations;
    }

    public static void main(String[] args) {

        List<UseAppiumServer> servers = new ArrayList<>();
        servers.add(UseAppiumServer.getUserAppiumServer());

        TaskList taskList = new TaskList(servers,getSchemas(),"/Users/mac/IdeaProjects/auto_git/src/main/file/");
        try {
            taskList.run();
            System.out.println(JSONObject.toJSONString(taskList.getPhoneRunPresentations()));
            System.out.println();
        }catch (Exception e){

        }
    }

    public static Set<Schema> getSchemas(){
        String prent = "yqbnative://app.1qianbao.com/";
        Set<Schema> schemas = new HashSet<>();
        String one_1 = prent + "huoqianbao/index";
        String two_1 = prent + "assets/integral";
        String three_1 = prent + "setting/index";
        String four_1 = prent + "lifepay/index";

        Schema one = new Schema("活钱宝", one_1, true, "4.0.0", "/Users/mac/IdeaProjects/auto_git/src/main/file/4723/screenshot60220872694868756.png");
        Schema two = new Schema("积分资产页", two_1, true, "4.0.0", "/Users/mac/IdeaProjects/auto_git/src/main/file/4723/screenshot60220872694868756.png");
        Schema three = new Schema("设置", three_1, true, "4.0.0", "/Users/mac/IdeaProjects/auto_git/src/main/file/4723/screenshot60220872694868756.png");
        Schema four = new Schema("生活缴费", four_1, true, "4.0.0", "/Users/mac/IdeaProjects/auto_git/src/main/file/4723/screenshot60220872694868756.png");

        schemas.add(one);
//        schemas.add(two);
//        schemas.add(three);
//        schemas.add(four);

        return schemas;
    }

}
