package com.tekton.percentage.api.infraestructure.helper.cachemanager.config;

import org.springframework.cache.CacheManager;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import com.github.benmanes.caffeine.cache.Caffeine;

import java.time.Duration;

@Configuration
public class CacheConfig {

    @Bean
    public CacheManager cacheManager(Environment env) {
        CaffeineCacheManager cacheManager = new CaffeineCacheManager(env.getProperty("cache.name"));
        cacheManager.setCaffeine(Caffeine.newBuilder()
                .expireAfterWrite(Duration.ofMinutes(30))
                .maximumSize(1));
        return cacheManager;
    }

}
