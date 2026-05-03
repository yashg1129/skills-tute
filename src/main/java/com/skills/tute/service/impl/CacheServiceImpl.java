package com.skills.tute.service.impl;

import com.skills.tute.entity.Topic;
import com.skills.tute.repository.TopicRepository;
import com.skills.tute.service.CacheService;
import com.skills.tute.service.InterviewQuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

@Service
public class CacheServiceImpl implements CacheService {

    @Value("${st.spring.caches}")
    private String caches;

    @Value("${st.spring.refresh.caches}")
    private String refreshCachesStr;

    @Autowired
    private CacheManager cacheManager;

    @Autowired
    private InterviewQuestionService interviewQuestionService;

    @Autowired
    private TopicRepository topicRepository;

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
        String[] refreshCaches = refreshCachesStr.split(",");
        for(String cache: refreshCaches) {
            Objects.requireNonNull(cacheManager.getCache(cache)).clear();
        }

        List<Topic> topics = topicRepository.findAll();
        for(Topic topic: topics) {
            interviewQuestionService.findByTopicNameAndApproval(topic.getName(), null);
        }
    }
}
