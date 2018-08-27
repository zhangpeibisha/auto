package org.nix.learn.auto.web.dto;

import org.apache.log4j.Logger;
import org.nix.learn.auto.core.appium.server.AppiumServer;
import org.nix.learn.auto.functions.schema.android.RunStaple;

import java.util.*;

/**
 * @author zhangpei341@pingan.cn.com 2018/8/27 下午1:38
 * @version 1.0
 */
public class SchemaSeverDto {

    private static final Logger logger = Logger.getLogger(SchemaSeverDto.class);


    private Set<EquipmentDto> equipmentDtos;

    private Set<String> schemas;

    public static Logger getLogger() {
        return logger;
    }

    public Set<EquipmentDto> getEquipmentDtos() {
        return equipmentDtos;
    }

    public void setEquipmentDtos(Set<EquipmentDto> equipmentDtos) {
        this.equipmentDtos = equipmentDtos;
    }

    public Set<String> getSchemas() {
        return schemas;
    }

    public void setSchemas(Set<String> schemas) {
        this.schemas = schemas;
    }

    @Override
    public String toString() {
        return "SchemaSeverDto{" +
                "equipmentDtos=" + equipmentDtos +
                ", schemas=" + schemas +
                '}';
    }
}
