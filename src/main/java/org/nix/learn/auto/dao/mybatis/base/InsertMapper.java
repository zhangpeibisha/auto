package org.nix.learn.auto.dao.mybatis.base;

import tk.mybatis.mapper.common.Marker;
import tk.mybatis.mapper.common.MySqlMapper;
import tk.mybatis.mapper.common.base.insert.InsertSelectiveMapper;

/**
 * 基础插入功能mapper
 *
 * @author zhangpei341@pingan.cn.com 2018/8/31 下午1:34
 * @version 1.0
 */
public interface InsertMapper<T> extends Marker,
        tk.mybatis.mapper.common.base.insert.InsertMapper<T>,
        InsertSelectiveMapper<T>,
        MySqlMapper<T> {
}
