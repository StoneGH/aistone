package com.stone.ai.app.provider;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@EnableDiscoveryClient
@SpringBootApplication
public class AppProviderApplication {
    public static void main(String[] args) {
        SpringApplication.run(AppProviderApplication.class, args);
    }
}
