package com.system.gestionautomobile.service.caching;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Component;

@Component
public class CacheManagerHelper {
    private final CacheManager cacheManager;

    @Autowired
    public CacheManagerHelper(CacheManager cacheManager) {
        this.cacheManager = cacheManager;
    }

    public void clearCache(String cacheName) {
        cacheManager.getCache(cacheName).clear();
    }

}
