package com.system.gestionautomobile;

import com.system.gestionautomobile.service.caching.CacheInspector;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.CacheManager;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableAspectJAutoProxy

public class GestionAutomobileApplication implements ApplicationContextAware {

    private static ApplicationContext context;

    public static void main(String[] args) {

        SpringApplication.run(GestionAutomobileApplication.class, args);

        // Retrieve the CacheInspector bean from the application context
        CacheInspector cacheInspector = context.getBean(CacheInspector.class);

        // Call the inspectCache method to inspect the cache
        cacheInspector.inspectCache("availableDrivers");

    }


    @Override
    public void setApplicationContext(ApplicationContext applicationContext) {
        context = applicationContext;
    }

    // Other configurations and beans...

    @Bean
    public CacheInspector cacheInspector(CacheManager cacheManager) {
        return new CacheInspector(cacheManager);
    }


}
