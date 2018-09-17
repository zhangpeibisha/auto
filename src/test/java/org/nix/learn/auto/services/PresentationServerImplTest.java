package org.nix.learn.auto.services;


import com.alibaba.fastjson.JSON;
import org.junit.Test;
import org.nix.learn.auto.AutoApplicationTests;
import org.nix.learn.auto.model.PresentationModel;
import org.nix.learn.auto.utils.LogUtils;

import javax.annotation.Resource;

public class PresentationServerImplTest extends AutoApplicationTests {

    @Resource
    private PresentationServerImpl presentationServer;

    @Test
    public void findpresentationpage() {
        LogUtils.printLog("信息",presentationServer.findPresentationPage(1,10));
    }


    @Test
    public void findpresentationpageById() {
        LogUtils.printLog("信息",presentationServer.presentationModelMapper.findPresentationByPresentationId("2c532a1e3fdc6407951fb07af9cfc8a2"));

        System.out.println(JSON.toJSONString(presentationServer.presentationModelMapper.findPresentationByPresentationId("2c532a1e3fdc6407951fb07af9cfc8a2")));
    }

    @Test
    public void test() {
        PresentationModel presentationModel = new PresentationModel();
        presentationModel.setValue("ahha");
        presentationModel.setPresentationId("afafsafa");
        System.out.println(JSON.toJSONString(presentationModel));
    }

}