package org.nix.learn.auto;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.nix.learn.auto.core.obtain.AndroidCommand;
import org.nix.learn.auto.core.obtain.PhoneCommandUtils;
import org.nix.learn.auto.dao.mybatis.mapper.ApkInfoModelMapper;
import org.nix.learn.auto.dao.mybatis.mapper.PresentationModelMapper;
import org.nix.learn.auto.model.ApkInfoModel;
import org.nix.learn.auto.model.PresentationModel;
import org.nix.learn.auto.utils.CryptoUtils;
import org.nix.learn.auto.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.nio.file.Paths;
import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AutoApplicationTests {

    @Autowired
    private SystemConfig config;

    @Resource
    private PhoneCommandUtils phoneCommandUtils;

    @Test
    public void contextLoads() {
        System.out.println(config.getPort()
                + " ===================");
        System.out.println(phoneCommandUtils.get(
                AndroidCommand.GET_DEVICES
        ));
    }


    @Resource
    private ApkInfoModelMapper apkInfoModelMapper;

    @Test
    public void testApkInfo(){
        ApkInfoModel model = new ApkInfoModel();
        model.setVersion("1.0");
        model.setAppActivity("io.dcloud.PandoraEntryActivity");
        model.setAppPackage("io.dcloud.H5462000D");
        model.setCreateTime(new Date());
        model.setUpdateTime(new Date());
        model.setRunEnvironment("2tg");
        model.setInstallPath("/Users/mac/IdeaProjects/auto_git/src/main/apk");
        model.setId("234234235345353");
        apkInfoModelMapper.insert(model);
        System.out.println(model);
    }

    @Resource
    private PresentationModelMapper modelMapper;

    @Test
    public void PresentationInsertTest(){

        PresentationModel model = new PresentationModel();
        model.setPresentationId("1332223");
        model.setValue("fsafasfsafja");

        modelMapper.insert(model);
    }
}
