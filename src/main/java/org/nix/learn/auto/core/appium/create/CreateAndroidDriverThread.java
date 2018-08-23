package org.nix.learn.auto.core.appium.create;

import org.apache.log4j.Logger;
import org.nix.learn.auto.core.appium.AppiumException;

import java.util.concurrent.Callable;

/**
 * @author zhangpei341@pingan.cn.com 2018/8/23 下午2:37
 * @version 1.0
 */
public class CreateAndroidDriverThread implements Callable<DefaultAndroidDriver>{

    private static final Logger logger = Logger.getLogger(CreateAndroidDriverThread.class);

    private DefaultAndroidDriver defaultAndroidDriver;

    public CreateAndroidDriverThread(DefaultAndroidDriver defaultAndroidDriver) {
        this.defaultAndroidDriver = defaultAndroidDriver;
    }

    @Override
    public DefaultAndroidDriver call() throws Exception {
        // 仅仅是为了并发创建driver，避免一个创建失败导致所有driver创建失败
        try {
            defaultAndroidDriver.getDriver();
            return defaultAndroidDriver;
        }catch (AppiumException e){
            throw new AppiumException(e.getMessage(),e);
        }
    }
}
