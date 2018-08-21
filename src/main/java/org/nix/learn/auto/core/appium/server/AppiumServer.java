package org.nix.learn.auto.core.appium.server;

import org.nix.learn.auto.core.appium.AppiumException;

import java.util.*;

/**
 * 参考方法 https://blog.csdn.net/qq_14908027/article/details/78886386
 * <p>
 * 该类的两个目的：
 * 1。用来用户提交执行任务的电脑和端口号
 * 2。存储现有电脑中已经启动的服务的信息集合，将持久化保存
 *
 * @author zhangpei
 * @version 1.0
 * @date 2018/8/20
 */
public class AppiumServer {

    /**
     * 这台服务器的地址,电脑的IP地址
     */
    private String IP;

    /**
     * 这台服务器上的端口使用情况
     * key为端口号，value为是否使用
     */
    private Map<String, Boolean> ports;

    public AppiumServer(String IP) {
        this.IP = IP;
    }

    public AppiumServer(String IP, Map<String, Boolean> ports) {
        this.IP = IP;
        this.ports = ports;
    }

    /**
     * 这台电脑新增端口号
     *
     * @param port 端口号
     * @param use  是否允许使用
     */
    public void addPort(String port, Boolean use) {
        if (ports == null) {
            ports = new HashMap<>();
        }
        ports.put(port, use);
    }

    /**
     * 移除一个端口，相当于关闭一个端口
     *
     * @param port 关闭的端口号
     * @return 是否移除成功
     */
    public Boolean removePort(String port) {
        if (ports == null) {
            return null;
        }
        return ports.remove(port);
    }

    /**
     * 准备调用该电脑上的该端口进行使用
     *
     * @param port 准备使用的端口号
     * @return 完整的appium服务器地址
     * @throws AppiumException 当端口不可用时抛出
     */
    public String getCompleteIP(String port) throws AppiumException {
        Boolean use = ports.get(port);
        if (use != null && use) {
            StringBuilder builder = new StringBuilder();
            return builder
                    .append("http://")
                    .append(getIP())
                    .append(":")
                    .append(port)
                    .append("/wd/hub").toString();
        }
        throw new AppiumException("该端口暂暂时不可用或者不存在该端口的服务器，" +
                "请检查您地址为:" + getIP() + "上的appium服务器的端口是否正确或存在");
    }

    /**
     * @return 获取所有可用端口组成的完整地址
     */
    public List<String> allAbleUseCompleteIp() {
        List<String> paths = null;

        paths = new ArrayList<>();
        for (Map.Entry<String, Boolean> value : ports.entrySet()) {
            try {
                String path = getCompleteIP(value.getKey());
                paths.add(path);
            } catch (AppiumException e) {
                // 不做处理
            }
        }
        return paths;
    }

    public String getIP() {
        return IP;
    }

    public void setIP(String IP) {
        this.IP = IP;
    }

    public Map<String, Boolean> getPorts() {
        return ports;
    }

    public void setPorts(Map<String, Boolean> ports) {
        this.ports = ports;
    }

    @Override
    public String toString() {
        return "AppiumServer{" +
                "IP='" + IP + '\'' +
                ", ports=" + ports +
                '}';
    }

    public static void main(String[] args) {
        AppiumServer server = new AppiumServer("127.0.0.0");
        server.addPort("4037", true);
        server.addPort("5000", false);
        server.addPort("4036", true);

        // 打印所有可用端口完整路径
        System.out.println(server.allAbleUseCompleteIp());

        System.out.println(server.getCompleteIP("4037"));
    }
}
