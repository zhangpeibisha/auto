package org.nix.learn.auto.functions.schema;

/**
 * 定义一个收集信息的接口
 * 该接口主要是为了记录每个执行需要保存结果的信息
 * 并将信息进行各种格式化保存或者展示
 * @author zhangpei341@pingan.cn.com 2018/8/22 下午2:42
 * @version 1.0
 */
public interface Presentation{

    /**
     * 该方法表示如何处理这些数据
     *
     * @param json json格式的数据
     * @param classzz json格式的Java类
     */
    void dataTo(String json,Class classzz);


}
