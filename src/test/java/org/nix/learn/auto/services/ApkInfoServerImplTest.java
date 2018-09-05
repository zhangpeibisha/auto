package org.nix.learn.auto.services;


import org.junit.Test;
import org.nix.learn.auto.AutoApplicationTests;
import org.nix.learn.auto.dao.mybatis.mapper.ApkInfoModelMapper;
import org.nix.learn.auto.model.ApkInfoModel;
import org.nix.learn.auto.utils.LogUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

public class ApkInfoServerImplTest extends AutoApplicationTests {

    @Resource
    private ApkInfoModelMapper apkInfoModelMapper;

    @Test
    public void apkInsertTest(){

        ApkInfoModel defaultApk = new ApkInfoModel();
        defaultApk.setRunEnvironment("all");
        defaultApk.setAppPackage("io.dcloud.H5462000D");
        defaultApk.setAppActivity("io.dcloud.PandoraEntryActivity");
        defaultApk.setVersion("-");
        defaultApk.setInstallPath("-");

        ApkInfoModel yqb = new ApkInfoModel();
        yqb.setInstallPath("测试YQB安装路径");
        yqb.setVersion("6.0");
        yqb.setAppActivity("测试YQB启动页");
        yqb.setAppPackage("测试壹钱包包路径");
        yqb.setRunEnvironment("2");

        List<ApkInfoModel> apkInfoModels = new ArrayList<>();
        apkInfoModels.add(defaultApk);
        apkInfoModels.add(yqb);

        apkInfoModelMapper.insertListValue(apkInfoModels);
    }

    @Test
    public void selectOneTest(){

    }
}