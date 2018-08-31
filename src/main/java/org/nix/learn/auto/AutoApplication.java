package org.nix.learn.auto;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * @author zhangpei
 */

@EnableTransactionManagement
@SpringBootApplication
@MapperScan(value = "org.nix.learn.auto.dao.mybatis.mapper")
public class AutoApplication {

    public static void main(String[] args) {
        SpringApplication.run(AutoApplication.class, args);
    }
}
