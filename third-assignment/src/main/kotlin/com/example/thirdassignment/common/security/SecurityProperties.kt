package com.example.thirdassignment.common.security

import org.springframework.boot.context.properties.ConfigurationProperties
import java.util.Base64

@ConfigurationProperties(prefix = "jwt")
class SecurityProperties(
    accessExp: Long,
    refreshExp: Long,
    val secretKey: String,
) {
    val accessTokenExpiredAt = accessExp * 1000
    val refreshTokenExpiredAt = refreshExp * 1000
}
