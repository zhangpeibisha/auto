package org.nix.learn.auto.services;


import org.junit.Test;
import org.nix.learn.auto.AutoApplicationTests;
import org.nix.learn.auto.dao.mybatis.mapper.SchemaModelMapper;
import org.nix.learn.auto.model.SchemaModel;
import org.nix.learn.auto.utils.LogUtils;

import javax.annotation.Resource;


public class SchemaServerImpTest extends AutoApplicationTests {

    @Resource
    private SchemaServerImp schemaServerImp;

    @Resource
    private SchemaModelMapper modelMapper;

    @Test
    public void insertTest(){
        SchemaModel model = new SchemaModel();
        model.setTpl("{name:zhangpei}");
        model.setEg("huoqianbao");
        model.setMaxUseVersion("6.0.0.1");
        model.setUse(true);
        model.setUseVersion("3.0.0");
        model.setName("活钱宝");
        model.setPath("/add");
        model.setRemarks("测试数据");
        model.setParameter("不应该有我");
        modelMapper.insert(model);
    }

    @Test
    public void findSchemaList() {
        LogUtils.printLog("schema page",schemaServerImp.findSchemaList(0,10));
    }
}