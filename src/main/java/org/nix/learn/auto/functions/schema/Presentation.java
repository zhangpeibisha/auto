package org.nix.learn.auto.functions.schema;

import org.openqa.selenium.Capabilities;

import java.util.Set;

/**
 * 定义一个收集信息的接口
 * 该接口主要是为了记录每个执行需要保存结果的信息
 * 并将信息进行各种格式化保存或者展示
 * @author zhangpei341@pingan.cn.com 2018/8/22 下午2:42
 * @version 1.0
 */
public interface Presentation{

    /**
     * 处理运行结果信息
     * @param capabilities 配置类信息集合
     * @param key 需要获取信息的keys,在执行过程中添加的参数
     */
    void dataTo(Capabilities capabilities, String key);

    /**
     * 通过key value将数据装入本层信息
     * @param key
     * @param value
     */
    void addKeyAndValue(String key , Object value);


    /**
     * 子级报告内容加入本级内容
     * @param son
     */
    void addPresentation(Presentation son);

    /**
     * 添加子级内容
     * @param name
     * @param description
     * @return
     */
    Presentation addSon(String name,String description);

    /**
     * 添加子级内容
     * @param name
     * @return
     */
    Presentation addSon(String name);
}
