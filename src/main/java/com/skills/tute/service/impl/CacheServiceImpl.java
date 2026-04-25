package com.skills.tute.service.impl;

import com.skills.tute.service.CacheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class CacheServiceImpl implements CacheService {

    @Value("${st.spring.caches}")
    private String caches;

    @Autowired
    private CacheManager cacheManager;

    @Override
    public void clearCache(String cacheName) {
        if("ALL".equals(cacheName)) {
            cacheManager.resetCaches();
        } else {
            Objects.requireNonNull(cacheManager.getCache(cacheName)).clear();
        }
    }

    @Override
    public String[] getAllCacheName() {
        return caches.split(",");
    }
}
