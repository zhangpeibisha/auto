package org.nix.learn.auto.dao.mybatis.mapper;

import org.nix.learn.auto.dao.mybatis.base.CrudMapper;
import org.nix.learn.auto.model.ApkInfoModel;
import org.nix.learn.auto.utils.excelutil.ExcelProcessor;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author zhangpei341@pingan.cn.com 2018/8/31 上午8:56
 * @version 1.0
 */
@Transactional(rollbackFor = Exception.class)
public interface ApkInfoModelMapper extends CrudMapper<ApkInfoModel> {

}
