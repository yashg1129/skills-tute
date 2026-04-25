package com.skills.tute.service;

import java.util.List;

public interface CacheService {

    void clearCache(String cacheName);

    String[] getAllCacheName();
}
