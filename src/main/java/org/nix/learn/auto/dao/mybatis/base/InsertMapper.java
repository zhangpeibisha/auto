package org.nix.learn.auto.dao.mybatis.base;

import org.apache.ibatis.annotations.SelectProvider;
import org.nix.learn.auto.dao.mybatis.base.expansion.MySpecialProvider;
import tk.mybatis.mapper.common.Marker;
import tk.mybatis.mapper.common.MySqlMapper;
import tk.mybatis.mapper.common.base.insert.InsertSelectiveMapper;

import java.util.List;

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
    /**
     * 自定义写入
     * @param list 需要写入的list集合
     * @return 写入的数量
     */
    @SelectProvider(
            type = MySpecialProvider.class,
            method = "dynamicSQL"
    )
    void insertListValue(List<? extends T> list);
}
