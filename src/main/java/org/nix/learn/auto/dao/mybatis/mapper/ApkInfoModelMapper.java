package org.nix.learn.auto.dao.mybatis.mapper;

import org.apache.ibatis.annotations.Param;
import org.nix.learn.auto.dao.mybatis.base.CrudMapper;
import org.nix.learn.auto.model.ApkInfoModel;
import org.nix.learn.auto.utils.excelutil.ExcelProcessor;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author zhangpei341@pingan.cn.com 2018/8/31 上午8:56
 * @version 1.0
 */
@Transactional(rollbackFor = Exception.class)
public interface ApkInfoModelMapper extends CrudMapper<ApkInfoModel> {

    /**
     * 分页查询apk信息
     * @param where 筛选条件
     * @param curr 当前行号
     * @param limit 结束行号
     * @return 指定位置的信息
     */
    List<ApkInfoModel> findApk(@Param("where")String where,@Param("curr")Integer curr,@Param("limit")Integer limit);

}
