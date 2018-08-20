package org.nix.learn.auto.task;

import org.nix.learn.auto.utils.DateUtils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * @author zhangpei341@pingan.cn.com 2018/8/20 上午9:26
 * @version 1.0
 */
public class SchemaTaskThread implements TaskResult, Runnable {

    /**
     * 将任务拆分为以一个scheme为一个单位进行
     */
    private Set<RunTask> runOneSchemas;

    /**
     * 保存报告的地址
     */
    private String keepResultPath;

    /**
     * 用户id
     */
    private String userId;

    /**
     * 任务集合运行线程池
     */
    private ExecutorService pool;

    private List<Future> result = new ArrayList<>();

    private String keepReuslt;

    private void initPool() {
        pool = Executors.newFixedThreadPool(runOneSchemas.size());
    }

    public SchemaTaskThread(Set<RunTask> runOneSchemas, String keepResultPath, String userId) {
        this.runOneSchemas = runOneSchemas;
        this.keepResultPath = keepResultPath;
        this.userId = userId;
        initPool();
    }

    @Override
    public void run() {
        for (RunTask oneSchema : runOneSchemas) {
            Future future = pool.submit(oneSchema);
            result.add(future);
        }
        close();
        obtainTaskResult();
        taskResultTo("");
    }

    @Override
    public String obtainTaskResult(String... value) {
        StringBuilder builder = new StringBuilder();
        int index = 0;
        for (Future future : result) {
            builder.append("序号:").append(index++).append("\n");
            try {
                builder.append("结果:").append(future.get().toString());
            } catch (InterruptedException | ExecutionException e) {
                builder.append("结果:").append("失败--").append(e.getMessage());
            }
        }
        keepReuslt = builder.toString();
        return keepReuslt;
    }

    @Override
    public void taskResultTo(String value) {
        Path path = Paths.get(keepResultPath,userId+"-"+ DateUtils.getCurrentDate()+".txt");
        try {
            Files.write(path,keepReuslt.getBytes());
        } catch (IOException e) {
            throw new TaskException("文件写入失败",e);
        }
        System.out.println("打印最后的结果集合 \n" + keepReuslt);
    }

    /**
     * 关闭线程池
     */
    private void close() {
        pool.shutdown();
    }

    public static void main(String[] args) {

        Set<RunTask> runOneSchemas = new HashSet<>();

//        RunOneSchema runOneSchema_1 = RunOneSchema.createAndoridOneSchema(
//                "emulator-5554","emulator-5554","com.android.browser","com.android.browser.BrowserActivity",
//                "WEBVIEW_com.android.browser");
//        RunOneSchema runOneSchema_2 = RunOneSchema.createAndoridOneSchema(
//                "1267e25a","1267e25a","com.vivo.browser",
//                "com.vivo.browser.MainActivity"," WEBVIEW_stetho_com.paic.zhifu.wallet.activitystg2");
//        RunOneSchema runOneSchema_3 = RunOneSchema.createOneSchema();


//        runOneSchemas.add(runOneSchema_1);
//        runOneSchemas.add(runOneSchema_2);
//        runOneSchemas.add(runOneSchema_3);
//
        HBuilderRunOneSchema hBuilderRunOneSchema = HBuilderRunOneSchema.createOne("1267e25a",
                "1267e25a", "io.dcloud.H5462000D", "io.dcloud.PandoraEntryActivity");

//        HBuilderRunOneSchema hBuilderRunOneSchema1 = HBuilderRunOneSchema.createOne("emulator-5554",
//                "emulator-5554","io.dcloud.H5462000D","io.dcloud.PandoraEntryActivity");

        runOneSchemas.add(hBuilderRunOneSchema);
//        runOneSchemas.add(hBuilderRunOneSchema1);

        SchemaTaskThread taskThread = new SchemaTaskThread(runOneSchemas,"/Users/mac/IdeaProjects/auto_git/src/main/file/","张沛341");

        Thread thread = new Thread(taskThread);
        thread.start();
    }
}
