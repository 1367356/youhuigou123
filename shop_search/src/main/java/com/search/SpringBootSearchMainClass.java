package com.search;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableAutoConfiguration
public class SpringBootSearchMainClass {
    public static void main(String[] args){
        SpringApplication.run(SpringBootSearchMainClass.class,args);
    }
}
