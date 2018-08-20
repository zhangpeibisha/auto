package org.nix.learn.auto.task;

/**
 * @author zhangpei341@pingan.cn.com 2018/8/20 上午10:09
 * @version 1.0
 */
public class TaskException extends RuntimeException{
    public TaskException() {
    }

    public TaskException(String message) {
        super(message);
    }

    public TaskException(String message, Throwable cause) {
        super(message, cause);
    }

    public TaskException(Throwable cause) {
        super(cause);
    }

    public TaskException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
