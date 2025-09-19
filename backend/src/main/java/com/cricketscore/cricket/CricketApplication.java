package com.cricketscore.cricket;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@EnableMongoRepositories(basePackages = "com.cricketscore.cricket.repository")
public class CricketApplication {
    
    public static void main(String[] args) {
        SpringApplication.run(CricketApplication.class, args);
    }
}