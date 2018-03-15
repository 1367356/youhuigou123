package com.search;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableAutoConfiguration
@ComponentScan("com.search.service.Impl")
public class SpringBootSearchMainClass {
    public static void main(String[] args){
        SpringApplication.run(SpringBootSearchMainClass.class,args);
    }
}
