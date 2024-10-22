package com.system.gestionautomobile;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableAspectJAutoProxy
@EnableCaching
public class GestionAutomobileApplication {

    public static void main(String[] args) {
        SpringApplication.run(GestionAutomobileApplication.class, args);
    }
}
