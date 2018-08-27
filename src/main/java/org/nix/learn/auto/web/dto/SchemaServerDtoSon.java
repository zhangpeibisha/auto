package org.nix.learn.auto.web.dto;

import org.apache.log4j.Logger;
import org.nix.learn.auto.core.appium.server.AppiumServer;
import org.nix.learn.auto.functions.schema.Presentation;
import org.nix.learn.auto.functions.schema.android.RunStaple;

import java.util.*;

/**
 * @author zhangpei341@pingan.cn.com 2018/8/27 下午4:53
 * @version 1.0
 */
public class SchemaServerDtoSon {

    private SchemaSeverDto schemaSeverDto;

    private Presentation presentation;

    public SchemaServerDtoSon(SchemaSeverDto schemaSeverDto, Presentation presentation) {
        this.schemaSeverDto = schemaSeverDto;
        this.presentation = presentation;
    }

    public List<RunStaple> getRunStaple(){
        Set<String> ips = getIp();
        List<RunStaple> runStaples = new ArrayList<>();
        for (String ip : ips) {
            runStaples.add(getRunStaple(ip));
        }
        return runStaples;
    }

    /**
     * 找到有多少台电脑
     * @return
     */
    private Set<String> getIp(){
        Set<String> ips = new HashSet<>();
        for (EquipmentDto equipmentDto : schemaSeverDto.getEquipmentDtos()) {
            ips.add(equipmentDto.getIp());
        }
        return ips;
    }

    /**
     * 得到一个server
     * @param ip
     * @return
     */
    private RunStaple getRunStaple(String ip){
        AppiumServer server = new AppiumServer(ip);

        Map<String,Boolean> ports = new HashMap<>();
        List<String> udids = new ArrayList<>();

        for (EquipmentDto equipmentDto : schemaSeverDto.getEquipmentDtos()) {
            if (equipmentDto.getIp().equals(ip)){
                ports.put(equipmentDto.getPort(),true);
                udids.add(equipmentDto.getUdid());
            }
        }
        server.setPorts(ports);

        RunStaple runStaple = new RunStaple(server,udids,presentation);
        return runStaple;
    }


}
