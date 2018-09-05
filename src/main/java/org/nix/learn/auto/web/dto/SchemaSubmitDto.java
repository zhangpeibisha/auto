package org.nix.learn.auto.web.dto;

import org.apache.log4j.Logger;
import org.hibernate.validator.constraints.Length;
import org.nix.learn.auto.core.appium.server.AppiumServer;
import org.nix.learn.auto.functions.presentation.Presentation;
import org.nix.learn.auto.functions.schema.android.RunStaple;
import org.nix.learn.auto.model.ApkInfoModel;

import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 用户提交的信息
 *
 * @author zhangpei341@pingan.cn.com 2018/9/4 下午5:34
 * @version 1.0
 */
public class SchemaSubmitDto {

    private static final Logger logger = Logger.getLogger(SchemaSubmitDto.class);

    /**
     * 电脑信息集合
     */
    @NotEmpty(message = "运行电脑信息不能为空")
    private List<ComputerDto> computers;

    /**
     * schema的ID信息
     */
    @NotEmpty(message = "需要测试的schema信息不能为空")
    private List<String> schemas;

    /**
     * 要测试apk的ID
     */
    @NotEmpty(message = "apk信息不能为空")
    @Length(min = 32, max = 33, message = "这个不是apk的ID信息")
    private String apkId;

    public SchemaSubmitDto(@NotEmpty(message = "运行电脑信息不能为空") List<ComputerDto> computers,
                           @NotEmpty(message = "需要测试的schema信息不能为空") List<String> schemas,
                           @NotEmpty(message = "apk信息不能为空") @Length(min = 32, max = 33, message = "这个不是apk的ID信息") String apkId) {
        this.computers = computers;
        this.schemas = schemas;
        this.apkId = apkId;
    }

    /**
     * @return RunStaple
     */
    public List<RunStaple> getRunStaples(Presentation presentation){
        List<RunStaple> runStaples = new ArrayList<>();
        for (String ip : getIp()) {
            AppiumServer server = new AppiumServer(ip);
            RunStaple runStaple = new RunStaple(server,presentation);
            for (ComputerDto dto : computers) {
                if (dto.getIp().equals(ip)){
                    server.addPort(dto.getPort(),true);
                    runStaple.addUdid(dto.getUdid());
                }
            }
            runStaples.add(runStaple);
        }
        return runStaples;
    }

    /**
     * @return 找到有多少台电脑
     */
    public Set<String> getIp(){
        Set<String> ips = new HashSet<>();
        for (ComputerDto dto : computers) {
            ips.add(dto.getIp());
        }
        return ips;
    }

    public List<ComputerDto> getComputers() {
        return computers;
    }

    public void setComputers(List<ComputerDto> computers) {
        this.computers = computers;
    }

    public List<String> getSchemas() {
        return schemas;
    }

    public void setSchemas(List<String> schemas) {
        this.schemas = schemas;
    }

    public String getApkId() {
        return apkId;
    }

    public void setApkId(String apkId) {
        this.apkId = apkId;
    }

    @Override
    public String toString() {
        return "SchemaSubmitDto{" +
                "computers=" + computers +
                ", schemas=" + schemas +
                ", apkId='" + apkId + '\'' +
                '}';
    }
}
