package com.skills.tute.service;

import java.util.Map;
import java.util.Set;

public interface CacheService {

    void clearCache(String cacheName);

    String[] getAllCacheName();

    void refreshCaches();
}
