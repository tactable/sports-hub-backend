package org.example.sportshub;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class SportsHubApplication {

    public static void main(String[] args) {
        SpringApplication.run(SportsHubApplication.class, args);
    }
}
