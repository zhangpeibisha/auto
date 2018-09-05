package org.nix.learn.auto.services;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.nix.learn.auto.dao.mybatis.mapper.ApkInfoModelMapper;
import org.nix.learn.auto.model.ApkInfoModel;
import org.nix.learn.auto.model.SchemaModel;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author zhangpei341@pingan.cn.com 2018/9/5 下午3:20
 * @version 1.0
 */
@Service
public class ApkInfoServerImpl {

    @Resource
    public ApkInfoModelMapper apkInfoModelMapper;

    /**
     * 分页查询apk信息
     * @param curr 当前页
     * @param quantity 每页数量
     * @return 分页查询结果
     */
    public PageInfo findApkList(Integer curr,Integer quantity){
        PageHelper.startPage(curr,quantity);
        PageInfo<ApkInfoModel> infos = new PageInfo<>(apkInfoModelMapper.selectAll());
        return infos;
    }


}
