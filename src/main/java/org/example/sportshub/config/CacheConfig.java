package org.example.sportshub.config;

import com.github.benmanes.caffeine.cache.Caffeine;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

@Configuration
@EnableCaching
public class CacheConfig {

    @Bean
    public CacheManager cacheManager() {
        CaffeineCacheManager cacheManager = new CaffeineCacheManager(
                "liveFixtures",
                "todayFixtures",
                "fixtureStats"
        );

        cacheManager.setAsyncCacheMode(true);

        cacheManager.setCacheSpecification(
                "maximumSize=100,expireAfterWrite=5m,recordStats"
        );

        return cacheManager;
    }
}
