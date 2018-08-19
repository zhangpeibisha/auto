package org.nix.learn.auto.core.obtain;

import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @author zhangpei
 * @version 1.0
 * @date 2018/8/19
 */
@Component
public class PhoneCommandUtils extends ParameterUtils{

    public PhoneCommandUtils() {
        super(new TerminalUtils());
    }

    public String get(String command, String devices) {
        try {
            return TerminalUtils.getResult(command,devices);
        } catch (IOException e) {
            throw new CommandException("命令执行失败",e);
        }
    }

}
