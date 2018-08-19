package org.nix.learn.auto.core.appium;

/**
 * @author zhangpei
 * @version 1.0
 * @date 2018/8/19
 */
public class AppiumException extends RuntimeException{

    public AppiumException() {
    }

    public AppiumException(String message) {
        super(message);
    }

    public AppiumException(String message, Throwable cause) {
        super(message, cause);
    }

    public AppiumException(Throwable cause) {
        super(cause);
    }

    public AppiumException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
