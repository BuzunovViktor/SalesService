package ru.ourservices.salesInfoService.config;

import com.google.common.cache.CacheBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCache;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

@Configuration
@EnableCaching
public class Beans {
    @Bean("RestClient")
    public RestTemplateBuilder restTemplateBuilder() {
        return new RestTemplateBuilder();
    }

    @Bean("CacheManager")
    public CacheManager cacheManager(@Value("${cache.lifetime}") Long lifetime) {
        return new ConcurrentMapCacheManager() {
            @Override
            protected Cache createConcurrentMapCache(String name) {
                System.out.println("Cache name " + name);
                return new ConcurrentMapCache(
                        name,
                        CacheBuilder.newBuilder()
                                    .expireAfterWrite(lifetime, TimeUnit.SECONDS)
                                    .build().asMap(),
                        false);
            }
        };
    }

}


