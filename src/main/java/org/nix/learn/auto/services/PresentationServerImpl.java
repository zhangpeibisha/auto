package org.nix.learn.auto.services;

import org.apache.log4j.Logger;
import org.nix.learn.auto.dao.mybatis.mapper.PresentationModelMapper;
import org.nix.learn.auto.model.PresentationModel;
import org.nix.learn.auto.services.comment.ResultPage;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author zhangpei341@pingan.cn.com 2018/9/17 下午5:01
 * @version 1.0
 */
@Service
public class PresentationServerImpl {

    private static final Logger logger = Logger.getLogger(PresentationServerImpl.class);

    @Resource
    public PresentationModelMapper presentationModelMapper;

    /**
     * 分页查询
     * @param curr
     * @param quantity
     * @return
     */
    public ResultPage findPresentationPage(Integer curr, Integer quantity){
        int currP = (curr - 1) * quantity;
        int end = currP + quantity;
        return new ResultPage(presentationModelMapper.selectCount(new PresentationModel()),
                presentationModelMapper.findPresentationPage(null,currP,end));
    }

}
