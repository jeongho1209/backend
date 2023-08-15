package com.example.thirdassignment.common.redis

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.data.redis.serializer.StringRedisSerializer

@Configuration
class RedisConfig(
    private val redisProperties: RedisProperties,
) {

    @Bean
    fun redisTemplate() = RedisTemplate<String, String>().apply {
        connectionFactory = LettuceConnectionFactory(redisProperties.host, redisProperties.port)
        keySerializer = StringRedisSerializer()
        valueSerializer = StringRedisSerializer()
    }
}
