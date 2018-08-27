package org.nix.learn.auto.web.dto;

import org.apache.log4j.Logger;

import java.util.List;
import java.util.Set;

/**
 * @author zhangpei341@pingan.cn.com 2018/8/27 下午1:38
 * @version 1.0
 */
public class SchemaSeverDto {

    private static final Logger logger = Logger.getLogger(SchemaSeverDto.class);


    private Set<EquipmentDto> phones;

    private Set<String> schemas;


    public SchemaSeverDto() {
    }

    public Set<EquipmentDto> getPhones() {
        return phones;
    }

    public void setPhones(Set<EquipmentDto> phones) {
        this.phones = phones;
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
                "phones=" + phones +
                ", schemas=" + schemas +
                '}';
    }
}
