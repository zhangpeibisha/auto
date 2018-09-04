package org.nix.learn.auto.functions.presentation;

import org.apache.log4j.Logger;

/**
 * @author zhangpei341@pingan.cn.com 2018/9/4 下午3:10
 * @version 1.0
 */
public class PresentationException extends RuntimeException{

    public PresentationException() {
    }

    public PresentationException(String message) {
        super(message);
    }

    public PresentationException(String message, Throwable cause) {
        super(message, cause);
    }

    public PresentationException(Throwable cause) {
        super(cause);
    }

    public PresentationException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
