package org.nix.learn.auto.functions.presentation.mange;

import org.nix.learn.auto.functions.presentation.Presentation;
import org.nix.learn.auto.functions.presentation.PresentationContent;

/**
 * 对报告生成完成和生成中对状态和内容进行管理
 * @author zhangpei341@pingan.cn.com 2018/9/4 下午2:32
 * @version 1.0
 */
public interface PresentationManger {

    /**
     * 生成的报告存入缓存中
     * @param presentation 将要存入的报告内容
     * @return 报告的唯一ID
     */
    String put(Presentation presentation);

    /**
     * 获取到需要查询的报告
     * @param id 报告ID
     * @return 报告内容信息
     */
    Presentation get(String id);



}
