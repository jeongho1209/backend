package com.example.thirdassignment.common.security

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties(prefix = "jwt")
class SecurityProperties(
    val accessExp: Long,
    val refreshExp: Long,
    val secretKey: String,
) {
}
