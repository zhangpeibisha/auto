package org.nix.learn.auto.dao.mybatis.base;


import org.apache.ibatis.annotations.SelectProvider;
import org.nix.learn.auto.dao.mybatis.base.expansion.MySpecialProvider;
import tk.mybatis.mapper.annotation.RegisterMapper;
import tk.mybatis.mapper.provider.base.BaseSelectProvider;

import java.util.List;

/**
 * 基础增删改查功能mapper
 *
 * @author zhangpei341@pingan.cn.com 2018/8/31 下午1:37
 * @version 1.0
 */
@RegisterMapper
public interface CrudMapper<T> extends
        InsertMapper<T>,
        DeleteMapper<T>,
        UpdateMapper<T>,
        SelectMapper<T> {



}
