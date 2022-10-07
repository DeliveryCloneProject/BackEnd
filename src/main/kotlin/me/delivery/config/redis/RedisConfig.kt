package me.delivery.config.redis

import org.springframework.boot.autoconfigure.AutoConfigureAfter
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration
import org.springframework.boot.web.client.RestTemplateBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.redis.connection.RedisConnectionFactory
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.data.redis.serializer.StringRedisSerializer
import org.springframework.web.client.RestTemplate

@Configuration
@AutoConfigureAfter(RedisAutoConfiguration::class)
class RedisConfig {
    @Bean
    fun redisTemplate(redisConnectionFactory: RedisConnectionFactory?): RedisTemplate<String, Any>? {
        return RedisTemplate<String, Any>().apply {
            keySerializer = StringRedisSerializer()
            valueSerializer = StringRedisSerializer()
            hashKeySerializer = StringRedisSerializer()
            connectionFactory = redisConnectionFactory
        }
    }

    @Bean
    fun restTemplate(): RestTemplate? {
        return RestTemplateBuilder().build()
    }
}