package org.nix.learn.auto.functions.presentation;

/**
 * 该接口为报告状态表现
 *
 * @author zhangpei341@pingan.cn.com 2018/9/3 下午1:33
 * @version 1.0
 */
public interface PresentationStatus<R> {

    /**
     * 设置成功的状态
     */
    void setSuccess();

    /**
     * 设置失败的状态
     */
    void setFail();

    /**
     * @return 得到当前运行的进度
     */
    R runSchedule();

    Boolean isOk();
}
