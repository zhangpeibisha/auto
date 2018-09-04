package org.nix.learn.auto;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * @author zhangpei
 */

@EnableTransactionManagement // 开启事务管理
@EnableCaching // 开启缓存
@EnableScheduling // 开启定时器
@SpringBootApplication
@MapperScan(value = "org.nix.learn.auto.dao.mybatis.mapper")
public class AutoApplication {

    public static void main(String[] args) {
        SpringApplication.run(AutoApplication.class, args);
    }
}
