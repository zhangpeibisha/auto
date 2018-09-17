package org.nix.learn.auto.web.controller;

import com.github.pagehelper.PageInfo;
import org.nix.learn.auto.functions.presentation.PresentationContent;
import org.nix.learn.auto.services.ApkInfoServerImpl;
import org.nix.learn.auto.services.PresentationServerImpl;
import org.nix.learn.auto.services.SchemaServerImpl;
import org.nix.learn.auto.services.comment.ResultPage;
import org.nix.learn.auto.web.dto.SchemaSubmitDto;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;


/**
 * @author zhangpei341@pingan.cn.com 2018/8/27 下午1:32
 * @version 1.0
 */
@RequestMapping(value = "schema")
@RestController
public class SchemaController {

    /**
     * schema的服务类
     */
    @Resource
    private SchemaServerImpl schemaServerImp;


    @Resource
    private ApkInfoServerImpl apkInfoServerImpl;

    @Resource
    private PresentationServerImpl presentationServer;

    /**
     * 提交测试信息
     * @param value 测试信息
     * @return 测试报告ID
     */
    @PostMapping("schemaServer")
    public String schemaServer(@Validated @RequestBody SchemaSubmitDto value){

        return schemaServerImp.schemaCompatibilityRun(value);
    }

    /**
     * 查询报告信息
     * @param presentationId 查询报告信息
     * @return 报告信息
     */
    @GetMapping("getResult")
    public PresentationContent getResult(@RequestParam("presentationId")String presentationId){

        return schemaServerImp.findPresentationByPrId(presentationId);
    }

    /**
     * 分页查询schema页
     * @param curr 当前页码
     * @param quantity 查询数量
     * @return schema数量
     */
    @GetMapping("findSchemaList/pagination")
    public ResultPage findSchemaList(@RequestParam("page") Integer curr, @RequestParam("limit") Integer quantity){
        return schemaServerImp.findSchemaList(curr,quantity);
    }

    @GetMapping("findApkList/pagination")
    public ResultPage findApkList(@RequestParam("page") Integer curr, @RequestParam("limit") Integer quantity){
        return apkInfoServerImpl.findApkList(curr,quantity);
    }

    @GetMapping("findPresentationList/pagination")
    public ResultPage findPresentationList(@RequestParam("page") Integer curr, @RequestParam("limit") Integer quantity){
        return presentationServer.findPresentationPage(curr,quantity);
    }
}
