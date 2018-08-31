package org.nix.learn.auto.dao.mybatis.base;


/**
 * 基础增删改查功能mapper
 *
 * @author zhangpei341@pingan.cn.com 2018/8/31 下午1:37
 * @version 1.0
 */
public interface CrudMapper<T> extends
        InsertMapper<T>,
        DeleteMapper<T>,
        UpdateMapper<T>,
        SelectMapper<T> {

}
