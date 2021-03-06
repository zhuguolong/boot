package com.zgu.boot;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@MapperScan(basePackages = {"com.zgu.boot.*.mapper"})
@EnableAsync
@SpringBootApplication(scanBasePackages = {"com.zgu.boot"})
public class BootApplication {

    public static void main(String[] args) {
        SpringApplication.run(BootApplication.class, args);
    }

}
