package com.mongo.manager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;


@SpringBootApplication
@Configuration
public class ManagerApplication {
    public static void main(String[] args) {
        SpringApplication.run(ManagerApplication.class, args);
    }
}
