package org.nix.learn.auto.services;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.log4j.Logger;
import org.nix.learn.auto.dao.mybatis.mapper.ApkInfoModelMapper;
import org.nix.learn.auto.dao.mybatis.mapper.SchemaModelMapper;
import org.nix.learn.auto.functions.presentation.Presentation;
import org.nix.learn.auto.functions.presentation.TaskPresentation;
import org.nix.learn.auto.functions.presentation.mange.PresentationCache;
import org.nix.learn.auto.functions.schema.android.RunStaple;
import org.nix.learn.auto.functions.schema.android.SchemaRunColony;
import org.nix.learn.auto.model.ApkInfoModel;
import org.nix.learn.auto.model.SchemaModel;
import org.nix.learn.auto.web.dto.SchemaSubmitDto;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * 后面需要继承接口做一些处理，暂时不用处理
 *
 * @author zhangpei341@pingan.cn.com 2018/9/4 下午5:15
 * @version 1.0
 */
@Service
public class SchemaServerImpl {

    @Resource
    private PresentationCache presentationCache;

    @Resource
    private ApkInfoModelMapper apkInfoModelMapper;

    @Resource
    public SchemaModelMapper schemaModelMapper;

    private static final Logger logger = Logger.getLogger(SchemaServerImpl.class);

    /**
     * @param schemaSubmitDto 用户提交的信息
     * @return 该测试信息的查询报告的ID信息
     */
    public String schemaCompatibilityRun(SchemaSubmitDto schemaSubmitDto) {
        // 将报告加入缓存
        TaskPresentation taskPresentation = new TaskPresentation((long) schemaSubmitDto.getIp().size());

        // 整理信息准备测试
        List<RunStaple> servers = schemaSubmitDto.getRunStaples(taskPresentation);

        List<SchemaModel> models = findSchemaModelsByIds(schemaSubmitDto.getSchemas());
        if (models.size() == 0) {
            throw new ServerException("schema信息为空，不可测试");
        }
        ApkInfoModel apkInfoModel;
        apkInfoModel = new ApkInfoModel();
        apkInfoModel.setId(schemaSubmitDto.getApkId());
        apkInfoModel = apkInfoModelMapper.selectByPrimaryKey(apkInfoModel);
        if (apkInfoModel == null) {
            throw new ServerException("apk信息为空，不可测试");
        }

        SchemaRunColony colony = new SchemaRunColony(servers, models, taskPresentation, apkInfoModel, Paths.get("/Users/mac/IdeaProjects/auto_git/src/main/file/test/"));
        colony.runTask();

        String id = presentationCache.put(taskPresentation);
        return id;
    }

    private List<SchemaModel> findSchemaModelsByIds(List<String> schemaIds) {
        List<SchemaModel> schemaModels = new ArrayList<>();
        for (String id : schemaIds) {
            SchemaModel model;
            model = new SchemaModel();
            model.setId(id);
            model = schemaModelMapper.selectByPrimaryKey(model);
            if (model != null) {
                schemaModels.add(model);
            }
        }
        return schemaModels;
    }

    /**
     * 通过报告ID查询到报告信息
     * @param prId 报告ID
     * @return 报告信息
     */
    public Presentation findPresentationByPrId(String prId){
        return presentationCache.get(prId);
    }

    /**
     * 分页查询schema信息
     * @param curr 当前页码
     * @param quantity 查询数量
     * @return schema列表
     */
    public PageInfo findSchemaList(Integer curr,Integer quantity){
        PageHelper.startPage(curr,quantity);
        PageInfo<SchemaModel> infos = new PageInfo<>(schemaModelMapper.selectAll());
        return infos;
    }

}
