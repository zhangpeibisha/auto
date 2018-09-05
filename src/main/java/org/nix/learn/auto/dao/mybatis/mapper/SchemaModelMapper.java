package org.nix.learn.auto.dao.mybatis.mapper;

import org.nix.learn.auto.dao.mybatis.base.CrudMapper;
import org.nix.learn.auto.model.SchemaModel;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author zhangpei341@pingan.cn.com 2018/8/31 下午1:19
 * @version 1.0
 */
@Transactional(rollbackFor = Exception.class)
public interface SchemaModelMapper extends CrudMapper<SchemaModel> {
}
