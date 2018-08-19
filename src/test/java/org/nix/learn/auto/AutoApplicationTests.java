package org.nix.learn.auto;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.nix.learn.auto.core.obtain.AndroidCommand;
import org.nix.learn.auto.core.obtain.PhoneCommandUtils;
import org.nix.learn.auto.core.obtain.SystemCommand;
import org.nix.learn.auto.core.obtain.SystemCommandUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

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

}
