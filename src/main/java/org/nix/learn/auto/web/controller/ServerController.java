package org.nix.learn.auto.web.controller;

import com.alibaba.fastjson.JSON;
import org.apache.log4j.Logger;
import org.nix.learn.auto.utils.LogUtils;
import org.nix.learn.auto.web.dto.SchemaSeverDto;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;


/**
 * @author zhangpei341@pingan.cn.com 2018/8/27 下午1:32
 * @version 1.0
 */
@RequestMapping(value = "servers")
@RestController
public class ServerController {

    @PostMapping("schemaServer")
    public String schemaServer(@RequestBody Map value){
        LogUtils.printLog("返回信息",value);
        return JSON.toJSONString(value);
    }

}
