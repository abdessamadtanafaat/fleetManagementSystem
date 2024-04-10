package com.system.gestionautomobile.caching.cacheManager;

import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Component;

@Component
    public class CacheInspector {

    private final CacheManager cacheManager;

    public CacheInspector(CacheManager cacheManager) {
        this.cacheManager = cacheManager;
    }

    public void inspectCache(String cacheName) {
        Cache cache = cacheManager.getCache(cacheName);
        if (cache != null) {
            System.out.println("Cache Name: " + cache.getName());
            //System.out.println("Cache Size: " + cache.getNativeCache().size()); // This gives the size of the cache
            // You can also iterate over cache contents and print them if needed
        } else {
            System.out.println("Cache '" + cacheName + "' not found.");
        }
    }

}

