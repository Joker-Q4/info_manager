package com.manager;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.manager.mapper")
@SpringBootApplication
public class InfoManagerApplication {

    public static void main(String[] args) {
        SpringApplication.run(InfoManagerApplication.class, args);
    }

}
