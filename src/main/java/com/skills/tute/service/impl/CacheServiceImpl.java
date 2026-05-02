package com.skills.tute.service.impl;

import com.skills.tute.config.StCache;
import com.skills.tute.service.CacheService;
import com.skills.tute.service.InterviewQuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Objects;
import java.util.Set;

@Service
public class CacheServiceImpl implements CacheService {

    @Value("${st.spring.caches}")
    private String caches;

    @Autowired
    private CacheManager cacheManager;

    @Autowired
    private InterviewQuestionService interviewQuestionService;

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

    @Autowired
    public void refreshCaches() {
        Set<Map.Entry<String, Boolean>> caches = StCache.getTouchedTopics();
        for(Map.Entry<String, Boolean> cache: caches) {
            if(cache.getValue()) {
                String cacheName = cache.getKey();
                Objects.requireNonNull(cacheManager.getCache(cacheName)).clear();
                interviewQuestionService.findByTopicNameAndApproval(cacheName, null);
                caches.remove(cacheName);
                StCache.clear(cacheName);
                System.out.println(StCache.getTouchedTopics2());
            }

        }
    }
}
