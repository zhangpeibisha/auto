package org.nix.learn.auto.functions.schema;

import org.apache.log4j.Logger;

/**
 * @author zhangpei341@pingan.cn.com 2018/8/22 下午3:43
 * @version 1.0
 */
public class SchemaException extends RuntimeException{

    private static final Logger logger = Logger.getLogger(SchemaException.class);

    public SchemaException() {
    }

    public SchemaException(String message) {
        super(message);
    }

    public SchemaException(String message, Throwable cause) {
        super(message, cause);
    }

    public SchemaException(Throwable cause) {
        super(cause);
    }

    public SchemaException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
