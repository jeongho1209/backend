package com.example.thirdassignment.common.redis

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.redis.connection.RedisConnectionFactory
import org.springframework.data.redis.connection.RedisStandaloneConfiguration
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory

@Configuration
class RedisConfig(
    private val redisProperties: RedisProperties,
) {

    @Bean
    fun redisConnectionFactory(): RedisConnectionFactory {
        val redisConfig = RedisStandaloneConfiguration(redisProperties.host, redisProperties.port)

        if (redisProperties.password.isNotBlank()) {
            redisConfig.setPassword(redisProperties.password)
        }

        return LettuceConnectionFactory(redisConfig)
    }
}
