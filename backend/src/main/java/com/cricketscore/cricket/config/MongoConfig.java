package com.cricketscore.cricket.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableMongoRepositories(basePackages = "com.cricketscore.cricket.repository")
public class MongoConfig {
    // Spring Boot will auto-configure MongoTemplate and MongoClient
}