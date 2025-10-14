package com.example.backend.config;

import java.time.Duration;

import org.springframework.boot.autoconfigure.cache.RedisCacheManagerBuilderCustomizer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;

@Configuration
@EnableCaching
public class CacheConfig {
    
    @Bean
    public RedisCacheManagerBuilderCustomizer redisCacheConfiguration() {
        var serializer = new GenericJackson2JsonRedisSerializer();
        var pair = RedisSerializationContext.SerializationPair.fromSerializer(serializer);

        return (builder) -> builder.cacheDefaults(
            RedisCacheConfiguration.defaultCacheConfig()
                .entryTtl(Duration.ofMinutes(10))             
                .prefixCacheNameWith("itriscache:")             
                .serializeValuesWith(pair)               
        );
    }
}