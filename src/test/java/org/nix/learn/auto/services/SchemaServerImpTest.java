package org.nix.learn.auto.services;


import org.junit.Test;
import org.nix.learn.auto.AutoApplicationTests;
import org.nix.learn.auto.dao.mybatis.mapper.SchemaModelMapper;
import org.nix.learn.auto.model.SchemaModel;
import org.nix.learn.auto.utils.LogUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;


public class SchemaServerImpTest extends AutoApplicationTests {

    @Resource
    private SchemaServerImpl schemaServerImp;

    @Resource
    private SchemaModelMapper modelMapper;

    @Test
    public void insertTest(){

        long time = System.currentTimeMillis();

        SchemaModel huoqianbao = new SchemaModel();
        huoqianbao.setUse(true);
        huoqianbao.setUseVersion("3.0.0");
        huoqianbao.setName("活钱宝");
        huoqianbao.setPath("yqbnative://app.1qianbao.com/huoqianbao/index");
        huoqianbao.setRemarks("测试数据");
//        modelMapper.insert(huoqianbao);

        SchemaModel assets = new SchemaModel();
        assets.setUse(true);
        assets.setUseVersion("3.0.0");
        assets.setName("assets");
        assets.setPath("yqbnative://app.1qianbao.com/assets/integral");
        assets.setRemarks("测试数据");


        SchemaModel setting = new SchemaModel();
        setting.setUse(true);
        setting.setUseVersion("3.0.0");
        setting.setName("设置");
        setting.setPath("yqbnative://app.1qianbao.com/setting/index");
        setting.setRemarks("测试数据");



        SchemaModel lifepay = new SchemaModel();
        lifepay.setUse(true);
        lifepay.setUseVersion("3.0.0");
        lifepay.setName("生活支付");
        lifepay.setPath("yqbnative://app.1qianbao.com/lifepay/index");
        lifepay.setRemarks("测试数据");


        List<SchemaModel> models = new ArrayList<>();
        models.add(huoqianbao);
        models.add(assets);
        models.add(setting);
        models.add(lifepay);

        modelMapper.insertListValue(models);

        LogUtils.printLog("use time",System.currentTimeMillis()-time);
    }

    @Test
    public void MySpecialProvider(){

    }

    @Test
    public void findSchemaList() {
        LogUtils.printLog("schema page",schemaServerImp.findSchemaList(0,10));
    }
}