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
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

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
        final Long time = lifetime == null ? 10 : lifetime;
        return new ConcurrentMapCacheManager() {
            @Override
            protected Cache createConcurrentMapCache(String name) {
            return new ConcurrentMapCache(
                    name,
                    CacheBuilder.newBuilder()
                                .expireAfterWrite(time, TimeUnit.SECONDS)
                                .build().asMap(),
                    false);
            }
        };
    }

    @Bean(name = "multipartResolver")
    public CommonsMultipartResolver multipartResolver() {
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
        multipartResolver.setMaxUploadSize(100000);
        return multipartResolver;
    }
}


