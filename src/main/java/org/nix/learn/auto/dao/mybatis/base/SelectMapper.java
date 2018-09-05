package org.nix.learn.auto.dao.mybatis.base;

import org.apache.ibatis.annotations.SelectProvider;
import org.nix.learn.auto.dao.mybatis.base.expansion.MySelectProvider;
import org.nix.learn.auto.dao.mybatis.base.expansion.MySpecialProvider;
import tk.mybatis.mapper.common.Marker;
import tk.mybatis.mapper.common.base.select.*;
import tk.mybatis.mapper.common.condition.SelectByConditionMapper;
import tk.mybatis.mapper.common.condition.SelectCountByConditionMapper;
import tk.mybatis.mapper.common.example.SelectByExampleMapper;
import tk.mybatis.mapper.common.ids.SelectByIdsMapper;

import java.util.List;

/**
 * 基础查询功能mapper
 *
 * @author zhangpei341@pingan.cn.com 2018/8/31 下午1:37
 * @version 1.0
 */
public interface SelectMapper<T> extends Marker,
        SelectOneMapper<T>,
        tk.mybatis.mapper.common.base.select.SelectMapper<T>,
        SelectAllMapper<T>,
        SelectCountMapper<T>,
        SelectByPrimaryKeyMapper<T>,
        ExistsWithPrimaryKeyMapper<T>,
        SelectByIdsMapper<T>,
        SelectByConditionMapper<T>,
        SelectCountByConditionMapper<T>,
        SelectByExampleMapper<T> {

}
