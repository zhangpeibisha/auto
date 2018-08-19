package org.nix.learn.auto.core.obtain;


/**
 * 获取参数的单元类
 * @author zhangpei
 * @version 1.0
 * @date 2018/8/19
 */
public class ParameterUtils implements Parameter {

    /**
     * 每个命令的命令执行器
     */
    protected Command command;

    public ParameterUtils(Command command) {
        this.command = command;
    }

    @Override
    public String get(String command) {
        return this.command.runCommand(command);
    }
}
