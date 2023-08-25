package com.example.thirdassignment.common.security.jwt

import com.example.thirdassignment.auth.presentation.dto.response.TokenResponse
import com.example.thirdassignment.common.security.SecurityProperties
import com.example.thirdassignment.refresh_token.RefreshTokenEntity
import com.example.thirdassignment.refresh_token.RefreshTokenRepository
import io.jsonwebtoken.Header.JWT_TYPE
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.springframework.stereotype.Component
import java.time.LocalDateTime
import java.util.Date

@Component
class JwtProvider(
    private val securityProperties: SecurityProperties,
    private val refreshTokenRepository: RefreshTokenRepository,
) {

    private fun createToken(accountId: String, tokenType: String, tokenExpiredAt: Long) =
        Jwts.builder()
            .signWith(SignatureAlgorithm.HS256, securityProperties.secretKey)
            .setSubject(accountId)
            .setHeaderParam(JWT_TYPE, tokenType)
            .setIssuedAt(Date())
            .setExpiration(Date(System.currentTimeMillis() + tokenExpiredAt * 1000))
            .compact()

    fun createAccessToken(accountId: String): String =
        createToken(accountId, JwtProperty.ACCESS, securityProperties.accessExp)

    fun createRefreshToken(accountId: String): String {
        val refreshToken = createToken(accountId, JwtProperty.REFRESH, securityProperties.refreshExp)

        refreshTokenRepository.save(
            RefreshTokenEntity(
                accountId = accountId,
                token = refreshToken,
                expiredAt = securityProperties.refreshExp,
            )
        )

        return refreshToken
    }

    fun getToken(accountId: String) = TokenResponse(
        accessToken = createAccessToken(accountId),
        refreshToken = createRefreshToken(accountId),
        accessTokenExpiredAt = LocalDateTime.now().plusSeconds(securityProperties.accessExp)
    )
}
