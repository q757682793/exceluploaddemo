package com.example.exceluploaddemo;

import org.springframework.boot.SpringApplication;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
@MapperScan("com.example.exceluploaddemo.dao")
public class ExceluploaddemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(ExceluploaddemoApplication.class, args);
    }

}
