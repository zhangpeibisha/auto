package org.nix.learn.auto.functions.presentation;

/**
 * 该接口只负责保存报告信息和创建下一级报告实体
 * @author zhangpei341@pingan.cn.com 2018/8/22 下午2:42
 * @version 1.0
 */
public interface PresentationContent {

    /**
     * 向报告添加当前层级的信息
     * @param key key
     * @param value 需要保存的值
     */
    void putCurr(String key,Object value);

    /**
     * 添加下一级
     * @param sonKey 该子级保存的名字
     * @return 下一级的报告
     */
    PresentationContent addNext(String sonKey);

}
