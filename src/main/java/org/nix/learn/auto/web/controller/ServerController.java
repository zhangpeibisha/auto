package org.nix.learn.auto.web.controller;

import com.alibaba.fastjson.JSON;
import org.apache.log4j.Logger;
import org.nix.learn.auto.core.appium.server.AppiumServer;
import org.nix.learn.auto.functions.schema.Presentation;
import org.nix.learn.auto.functions.schema.TaskPresentation;
import org.nix.learn.auto.functions.schema.android.RunStaple;
import org.nix.learn.auto.functions.schema.android.SchemaRunColony;
import org.nix.learn.auto.utils.CryptoUtils;
import org.nix.learn.auto.utils.LogUtils;
import org.nix.learn.auto.web.dto.EquipmentDto;
import org.nix.learn.auto.web.dto.SchemaServerDtoSon;
import org.nix.learn.auto.web.dto.SchemaSeverDto;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


/**
 * @author zhangpei341@pingan.cn.com 2018/8/27 下午1:32
 * @version 1.0
 */
@RequestMapping(value = "servers")
@RestController
public class ServerController {


    private static final Map<String,Presentation> results = new ConcurrentHashMap<>();


    @PostMapping("schemaServer")
    public String schemaServer(@RequestBody SchemaSeverDto value, HttpServletRequest request){
        LogUtils.printLog("返回信息",value);
        LogUtils.printLog("phoneInfo",value.getEquipmentDtos());
        LogUtils.printLog("schemas",value.getSchemas());


        String path =  getClass().getResource("/").toString().replace("file:","");
        Path keepPath = Paths.get(path,"static","keep");
        LogUtils.printLog("path",keepPath);

        SchemaSeverDto schemaSeverDto = new SchemaSeverDto();
        schemaSeverDto.setSchemas(value.getSchemas());
        schemaSeverDto.setEquipmentDtos(value.getEquipmentDtos());

        Presentation presentation = new TaskPresentation("报告");

        SchemaServerDtoSon schemaServerDtoSon = new SchemaServerDtoSon(schemaSeverDto,presentation);


        SchemaRunColony colony = new
                SchemaRunColony(schemaServerDtoSon.getRunStaple(),SchemaRunColony.createSchemaModels(),presentation,SchemaRunColony.createApkInfo(),keepPath);
        colony.runTask();

        String preId = CryptoUtils.encodeMD5(JSON.toJSONBytes(presentation));
        LogUtils.printLog("preId",preId);
        results.put(preId,presentation);

        return JSON.toJSONString(preId);
    }

    @GetMapping("getResult")
    public Presentation getResult(@RequestParam("presentationId")String presentationId){
        return results.get(presentationId);
    }

}
