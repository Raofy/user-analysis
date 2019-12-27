package com.jin10;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.jin10.mapper")
public class UserAnalysisApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserAnalysisApplication.class, args);
    }

}
