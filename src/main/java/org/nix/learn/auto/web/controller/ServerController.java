package org.nix.learn.auto.web.controller;

import com.alibaba.fastjson.JSON;
import org.nix.learn.auto.functions.presentation.Presentation;
import org.nix.learn.auto.functions.presentation.PresentationContent;
import org.nix.learn.auto.functions.presentation.AbstractPresentationContent;
import org.nix.learn.auto.functions.presentation.TaskPresentation;
import org.nix.learn.auto.functions.schema.android.SchemaRunColony;
import org.nix.learn.auto.utils.CryptoUtils;
import org.nix.learn.auto.utils.LogUtils;
import org.nix.learn.auto.web.dto.SchemaServerDtoSon;
import org.nix.learn.auto.web.dto.SchemaSeverDto;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


/**
 * @author zhangpei341@pingan.cn.com 2018/8/27 下午1:32
 * @version 1.0
 */
@RequestMapping(value = "servers")
@RestController
public class ServerController {


    private static final Map<String, PresentationContent> results = new ConcurrentHashMap<>();


    @PostMapping("schemaServer")
    public String schemaServer(@RequestBody SchemaSeverDto value, HttpServletRequest request){

        String path =  getClass().getResource("/").toString().replace("file:","");
        Path keepPath = Paths.get(path,"static","keep");
        LogUtils.printLog("path",keepPath);

        SchemaSeverDto schemaSeverDto = new SchemaSeverDto();
        schemaSeverDto.setSchemas(value.getSchemas());
        schemaSeverDto.setEquipmentDtos(value.getEquipmentDtos());

        Presentation presentation = new TaskPresentation();

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
    public PresentationContent getResult(@RequestParam("presentationId")String presentationId){
        LogUtils.printLog("请求信息:",presentationId);
        System.out.println();
        return results.get(presentationId);
    }

}
