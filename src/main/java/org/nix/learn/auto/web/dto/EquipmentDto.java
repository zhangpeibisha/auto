package org.nix.learn.auto.web.dto;

import org.apache.log4j.Logger;

/**
 * @author zhangpei341@pingan.cn.com 2018/8/27 下午4:07
 * @version 1.0
 */
public class EquipmentDto {

    private String ip;
    private String port;
    private String udid;

    public EquipmentDto(String ip, String port, String udid) {
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
