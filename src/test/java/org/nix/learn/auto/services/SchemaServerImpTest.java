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
    public void insertTest() {

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

// ===============================================================================================


        SchemaModel xuqibao = new SchemaModel();
        xuqibao.setUse(true);
        xuqibao.setUseVersion("3.0.0");
        xuqibao.setName("续期宝");
        xuqibao.setPath("yqbnative://app.1qianbao.com/xuqibao/index");
        xuqibao.setRemarks("测试数据");


        SchemaModel msgcenter = new SchemaModel();
        msgcenter.setUse(true);
        msgcenter.setUseVersion("3.0.0");
        msgcenter.setName("消息中心");
        msgcenter.setPath("yqbnative://app.1qianbao.com/msgcenter/index");
        msgcenter.setRemarks("测试数据");


        SchemaModel transfer = new SchemaModel();
        transfer.setUse(true);
        transfer.setUseVersion("3.0.0");
        transfer.setName("手机号转账");
        transfer.setPath("yqbnative://app.1qianbao.com/transfer/index");
        transfer.setRemarks("测试数据");


        SchemaModel mobilerecharge = new SchemaModel();
        mobilerecharge.setUse(true);
        mobilerecharge.setUseVersion("3.0.0");
        mobilerecharge.setName("手机充值");
        mobilerecharge.setPath("yqbnative://app.1qianbao.com/mobilerecharge/index");
        mobilerecharge.setRemarks("测试数据");


        SchemaModel barcode = new SchemaModel();
        barcode.setUse(true);
        barcode.setUseVersion("3.0.0");
        barcode.setName("扫一扫");
        barcode.setPath("yqbnative://app.1qianbao.com/barcode/index");
        barcode.setRemarks("测试数据");


        SchemaModel lovedonate = new SchemaModel();
        lovedonate.setUse(true);
        lovedonate.setUseVersion("3.0.0");
        lovedonate.setName("爱心捐赠");
        lovedonate.setPath("yqbnative://app.1qianbao.com/lovedonate/index");
        lovedonate.setRemarks("测试数据");


        SchemaModel rebatemall = new SchemaModel();
        rebatemall.setUse(true);
        rebatemall.setUseVersion("3.0.0");
        rebatemall.setName("返利商城");
        rebatemall.setPath("yqbnative://app.1qianbao.com/rebatemall/index");
        rebatemall.setRemarks("测试数据");
        rebatemall.setTpl("{tplId:'dfafafasfa',tplParams:{mallUrl:'',isNativeNav:'',contentCalss:''}}");

        SchemaModel creditrepay = new SchemaModel();
        creditrepay.setUse(true);
        creditrepay.setUseVersion("3.1.0");
        creditrepay.setName("信用卡还款");
        creditrepay.setPath("yqbnative://app.1qianbao.com/creditrepay/index");
        creditrepay.setRemarks("测试数据");


        SchemaModel financialmall = new SchemaModel();
        financialmall.setUse(true);
        financialmall.setUseVersion("3.1.0");
        financialmall.setName("理财商城");
        financialmall.setPath("yqbnative://app.1qianbao.com/financialmall/index");
        financialmall.setRemarks("测试数据");


        SchemaModel wanlitong = new SchemaModel();
        wanlitong.setUse(true);
        wanlitong.setUseVersion("3.1.6");
        wanlitong.setName("万里通游戏");
        wanlitong.setPath("yqbnative://app.1qianbao.com/wanlitong/game");
        wanlitong.setRemarks("测试数据");


        SchemaModel myfinancial = new SchemaModel();
        myfinancial.setUse(true);
        myfinancial.setUseVersion("3.3.7");
        myfinancial.setName("我的理财");
        myfinancial.setPath("yqbnative://app.1qianbao.com/myfinancial/index");
        myfinancial.setRemarks("测试数据");


        List<SchemaModel> models = new ArrayList<>();
//        models.add(huoqianbao);
//        models.add(assets);
//        models.add(setting);
//        models.add(lifepay);
        models.add(xuqibao);
        models.add(msgcenter);
        models.add(transfer);
        models.add(mobilerecharge);
        models.add(barcode);
        models.add(lovedonate);
        models.add(rebatemall);
        models.add(wanlitong);
        models.add(myfinancial);

        modelMapper.insertListValue(models);

        LogUtils.printLog("use time", System.currentTimeMillis() - time);
    }

    @Test
    public void MySpecialProvider() {

    }

    @Resource
    private SchemaModelMapper schemaModelMapper;

    @Test
    public void findSchemaList() {
        LogUtils.printLog("schema page", schemaModelMapper.findSchemaListPagination(0, 2));
    }
}