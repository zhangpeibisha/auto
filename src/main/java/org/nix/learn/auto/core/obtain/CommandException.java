package org.nix.learn.auto.core.obtain;

/**
 * @author zhangpei
 * @version 1.0
 * @date 2018/8/19
 */
public class CommandException extends RuntimeException{

    public CommandException() {
    }

    public CommandException(String message) {
        super(message);
    }

    public CommandException(String message, Throwable cause) {
        super(message, cause);
    }

    public CommandException(Throwable cause) {
        super(cause);
    }

    public CommandException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
