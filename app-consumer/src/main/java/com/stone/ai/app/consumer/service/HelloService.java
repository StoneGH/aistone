package com.stone.ai.app.consumer.service;

import com.stone.ai.app.consumer.config.FeignClientConfiguration;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(value = "app-provider", configuration = FeignClientConfiguration.class)
public interface HelloService extends com.stone.ai.app.api.HelloService {
}
