package org.nix.learn.auto.dao.mybatis.mapper;

import org.apache.ibatis.annotations.Param;
import org.nix.learn.auto.dao.mybatis.base.CrudMapper;
import org.nix.learn.auto.model.PresentationModel;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author zhangpei341@pingan.cn.com 2018/8/31 下午1:19
 * @version 1.0
 */
@Transactional(rollbackFor = Exception.class)
public interface PresentationModelMapper extends CrudMapper<PresentationModel> {

    /**
     * @return 通过报告ID查询到报告信息
     */
    PresentationModel findPresentationByPresentationId(String presentationId);

    /**
     * 分页查询报告
     * @param where 查询条件
     * @param curr 当前行号
     * @param limit 结束行号
     * @return 查到的信息
     */
    List<PresentationModel> findPresentationPage(@Param("where")String where,@Param("curr")Integer curr,@Param("limit")Integer limit);

}
