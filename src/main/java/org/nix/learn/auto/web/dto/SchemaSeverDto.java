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


    private List<Equipment> equipment;

    private Set<String> schema;


    public SchemaSeverDto() {
    }


    public List<Equipment> getEquipment() {
        return equipment;
    }

    public void setEquipment(List<Equipment> equipment) {
        this.equipment = equipment;
    }

    public Set<String> getSchema() {
        return schema;
    }

    public void setSchema(Set<String> schema) {
        this.schema = schema;
    }


    @Override
    public String toString() {
        return "SchemaSeverDto{" +
                "equipment=" + equipment +
                ", schema=" + schema +
                '}';
    }

    static class Equipment{
        private String ip;
        private String port;
        private String udid;

        public Equipment(String ip, String port, String udid) {
            this.ip = ip;
            this.port = port;
            this.udid = udid;
        }

        public String getIp() {
            return ip;
        }

        public void setIp(String ip) {
            this.ip = ip;
        }

        public String getPort() {
            return port;
        }

        public void setPort(String port) {
            this.port = port;
        }

        public String getUdid() {
            return udid;
        }

        public void setUdid(String udid) {
            this.udid = udid;
        }

        @Override
        public String toString() {
            return "Equipment{" +
                    "ip='" + ip + '\'' +
                    ", port='" + port + '\'' +
                    ", udid='" + udid + '\'' +
                    '}';
        }
    }

}
