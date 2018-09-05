package org.nix.learn.auto.dao.mybatis.mapper;

import org.nix.learn.auto.dao.mybatis.base.CrudMapper;
import org.nix.learn.auto.model.PresentationModel;
import org.springframework.transaction.annotation.Transactional;

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

}
