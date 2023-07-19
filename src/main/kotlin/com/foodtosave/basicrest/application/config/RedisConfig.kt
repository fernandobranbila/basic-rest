package com.foodtosave.basicrest.application.config

import org.springframework.boot.autoconfigure.cache.RedisCacheManagerBuilderCustomizer
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Profile
import org.springframework.data.redis.cache.RedisCacheConfiguration
import org.springframework.data.redis.cache.RedisCacheManager.RedisCacheManagerBuilder
import org.springframework.data.redis.connection.RedisStandaloneConfiguration
import org.springframework.data.redis.connection.lettuce.LettuceClientConfiguration
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory
import java.time.Duration


@Configuration
@Profile("default", "prod")
class RedisConfig {

    @Bean
    fun lettuceConnectionFactory(): LettuceConnectionFactory? {
        val clientConfig = LettuceClientConfiguration.builder().build()
        val serverConfig = RedisStandaloneConfiguration(
            "redis",
            6379
        )
        val lettuceConnectionFactory = LettuceConnectionFactory(
            serverConfig,
            clientConfig
        )
        lettuceConnectionFactory.validateConnection = true
        return LettuceConnectionFactory(serverConfig, clientConfig)
    }

    @Bean
    fun redisCacheManagerBuilderCustomizer(): RedisCacheManagerBuilderCustomizer? {
        return RedisCacheManagerBuilderCustomizer { builder: RedisCacheManagerBuilder ->
            val configurationMap: MutableMap<String, RedisCacheConfiguration> =
                HashMap()
            configurationMap["foods"] = RedisCacheConfiguration.defaultCacheConfig()
                .entryTtl(Duration.ofSeconds(10))
            builder.withInitialCacheConfigurations(configurationMap)
        }
    }

}