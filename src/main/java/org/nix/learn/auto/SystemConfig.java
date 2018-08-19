package org.nix.learn.auto;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * 配置系统的一些基础配置信息
 * @author zhangpei
 * @version 1.0
 * @date 2018/8/19
 */
@Configuration
@PropertySource(value = "classpath:properties/system.properties")
@ConfigurationProperties(prefix = "system")
public class SystemConfig {

    private String port;

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }
}
