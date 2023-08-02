package com.example.thirdassignment.refresh_token

import java.time.LocalDateTime

data class TokenResponse(
    val accessToken: String,
    val accessTokenExpiredAt: LocalDateTime,
)
