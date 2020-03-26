package com.example.wby.common.memorycache.config;

import com.example.wby.common.memorycache.IMemoryCache;
import com.example.wby.common.memorycache.MemoryStoreClient;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * spring.factories文件中的配置
 * org.springframework.boot.autoconfigure.EnableAutoConfiguration=\
 * com.hikvision.seashell.memory.cache.config.MemoryCacheConfig
 */
@Configuration
public class MemoryCacheConfig {
    public MemoryCacheConfig() {
    }

    @Bean(name = {"seashellMemoryCache"})
    @ConditionalOnMissingBean
    public IMemoryCache buildCache() {
        return new MemoryStoreClient();
    }
}
