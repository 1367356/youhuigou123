package com.background;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableAutoConfiguration
@SpringBootApplication
@MapperScan("com.background.dao.mapper")
public class SpringBootBackgrouondApplication {
    public static void main(String[] args){
        SpringApplication.run(SpringBootBackgrouondApplication.class,args);
    }
}
