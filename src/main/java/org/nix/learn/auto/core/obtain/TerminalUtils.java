package org.nix.learn.auto.core.obtain;

import com.alibaba.fastjson.JSONObject;
import org.apache.log4j.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * @author zhangpei
 * @version 1.0
 * @date 2018/8/19
 */
public class TerminalUtils extends AbstractCommandUtils implements AndroidCommand,IOSCommand{

    private static final Logger logger = Logger.getLogger(TerminalUtils.class);

    @Override
    public String runCommand(String command) {
        try {
            return getResult(command);
        } catch (IOException e) {
            throw new CommandException("命令执行失败",e);
        }
    }

    public String runCommand(String command,String devices) {
        try {
            return getResult(command,devices);
        } catch (IOException e) {
            throw new CommandException("命令执行失败",e);
        }
    }


    /**
     * 处理多设备是调用的命令
     * @param command 输入单机命令
     * @param devices 输入指定手机的序列号
     * @return 获取结果
     * @throws IOException
     */
    public static String getResult(String command,String devices) throws IOException {
        StringBuilder builder;
        if (devices == null){
            return getResult(command);
        }else {
            builder = new StringBuilder(command);
            builder.insert(builder.indexOf("adb")+"adb".length()," -s " + devices + " ");
            return getResult(builder.toString());
        }
    }

    /**
     * 通过命令调用终端，并获取希望获取的数据
     * @param command 终端命令
     * @return 命令执行结果
     * @throws IOException io异常
     */
    public static String getResult(String command) throws IOException {
        StringBuilder builder = new StringBuilder();
        Process process = null;
        try {
            process = Runtime.getRuntime().exec(command);
            InputStream input = process.getInputStream();
            builder.append(readInputStream(input));
            InputStream error = process.getErrorStream();
            builder.append(readInputStream(error));

            printLog(command,builder);
            return builder.toString();
        } catch (IOException e) {
            printLog("调用命令" + command + "发生异常",e.getMessage());
            throw new IOException("调用命令" + command + "发生异常",e);
        }finally {
            if (process!=null){
                if (process.isAlive()){
                    process.destroy();
                }
            }
        }
    }

    /**
     * 获取输入流中的信息并以StringBuilder的格式返回
     * @param input input输入流
     * @return input中的信息
     * @throws IOException io异常
     */
    public static StringBuilder readInputStream(InputStream input) throws IOException {
        StringBuilder builder = new StringBuilder();
        BufferedReader reader = new BufferedReader(new InputStreamReader(input));
        String str;
        while ((str = reader.readLine())!=null){
            builder.append(str).append("\n");
        }
        return builder;
    }


    /**
     * 格式化打印日志
     * @param input
     * @param name
     * @return
     */
    public static StringBuilder printObjectToString(String name,Object... input) {
        if (name == null){
            name = "";
        }
        StringBuilder builder = new StringBuilder();
        builder.append("\n============================").append(name).append("============================\n");
        builder.append(JSONObject.toJSON(input)).append("\n");
        builder.append("============================").append(name).append("============================\n");
        return builder;
    }

    /**
     * 在控制台上打印出对象的json信息
     * @param input
     * @param name
     */
    public static void printLog(String name,Object... input){
        logger.info(printObjectToString(name,input));
    }

}
