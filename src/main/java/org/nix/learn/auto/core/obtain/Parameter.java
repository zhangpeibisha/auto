package org.nix.learn.auto.core.obtain;

/**
 * 获取各类参数的接口
 * @author zhangpei
 * @version 1.0
 * @date 2018/8/19
 */
public interface Parameter {

    /**
     * 命令执行后获取结果
     * @param command 输入的参数
     * @return
     */
    String get(String command);
}
