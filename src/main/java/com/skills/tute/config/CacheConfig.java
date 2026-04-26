package com.skills.tute.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.CacheManager;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CacheConfig {

    @Value("${st.spring.caches}")
    private String caches;

    @Bean
    public CacheManager cacheManager() {
        return new ConcurrentMapCacheManager(caches.split(","));
    }
}