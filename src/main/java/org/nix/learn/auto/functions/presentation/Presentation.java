package org.nix.learn.auto.functions.presentation;



/**
 * 报告头节点信息
 * @author zhangpei341@pingan.cn.com 2018/9/3 下午2:05
 * @version 1.0
 */
public interface Presentation<R> extends PresentationContent, PresentationStatus<R> {

    /**
     * @param sonKey 添加儿子的键值
     * @param runNumber 子任务有多少个
     * @return 返回生成的子报告，让使用者可以操作它的一些动作
     */
    Presentation addNext(String sonKey,Long runNumber);
}
