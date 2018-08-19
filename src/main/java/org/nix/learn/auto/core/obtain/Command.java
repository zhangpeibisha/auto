package org.nix.learn.auto.core.obtain;

/**
 * 通用命令
 * @author zhangpei
 * @version 1.0
 * @date 2018/8/19
 */
public interface Command {

    /**
     * 如何运行命令得到结果
     * @param command 命令
     * @return 通过命令得到结果
     */
    String runCommand(String command);

}
