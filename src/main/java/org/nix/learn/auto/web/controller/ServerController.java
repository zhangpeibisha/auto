package org.nix.learn.auto.web.controller;

import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

/**
 * @author zhangpei341@pingan.cn.com 2018/8/27 下午1:32
 * @version 1.0
 */
@RequestMapping(value = "servers")
public class ServerController {

    private static final Logger logger = Logger.getLogger(ServerController.class);

    public Map<String,Object> schemaServer(){

        return  null;
    }

}
