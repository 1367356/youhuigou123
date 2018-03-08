package com.background;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@EnableAutoConfiguration
@SpringBootApplication
@MapperScan("com.background.dao.mapper")
public class SpringBackgrouondMainClass {
    public static void main(String[] args){
        SpringApplication.run(SpringBackgrouondMainClass.class,args);
    }
}
