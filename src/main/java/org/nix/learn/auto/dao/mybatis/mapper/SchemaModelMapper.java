package org.nix.learn.auto.dao.mybatis.mapper;

import org.apache.ibatis.annotations.Param;
import org.nix.learn.auto.dao.mybatis.base.CrudMapper;
import org.nix.learn.auto.model.SchemaModel;
import org.nix.learn.auto.services.comment.Page;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author zhangpei341@pingan.cn.com 2018/8/31 下午1:19
 * @version 1.0
 */
@Transactional(rollbackFor = Exception.class)
public interface SchemaModelMapper extends CrudMapper<SchemaModel> {

    /**
     * 执行schema的分页查询========暂时使用最简单的
     * @return 查询到的信息
     */
    List<SchemaModel> findSchemaListPagination(@Param("curr") Integer curr, @Param("limit") Integer limit);

}
