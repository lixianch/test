package com.lixianch;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * created by lixianch on 2022/12/6
 */
@SpringBootApplication(scanBasePackages = "com.lixianch")
@EnableAspectJAutoProxy(proxyTargetClass = true)
@MapperScan(basePackages = "com.lixianch.dao.mapper")
public class MyApplication {
    public static void main(String[] args) {
        SpringApplication.run(MyApplication.class);
    }
}
