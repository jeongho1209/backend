package com.example.thirdassignment.common.security.jwt

import com.example.thirdassignment.common.security.SecurityProperties
import com.example.thirdassignment.refresh_token.TokenResponse
import io.jsonwebtoken.Header.JWT_TYPE
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.springframework.stereotype.Component
import java.time.LocalDateTime
import java.util.Date

@Component
class JwtProvider(
    private val securityProperties: SecurityProperties,
) {

    private fun createToken(accountId: String, tokenType: String, tokenExpiredAt: Long) =
        Jwts.builder()
            .signWith(SignatureAlgorithm.HS256, securityProperties.secretKey)
            .setSubject(accountId)
            .setHeaderParam(JWT_TYPE, tokenType)
            .setIssuedAt(Date())
            .setExpiration(Date(System.currentTimeMillis() + tokenExpiredAt))
            .compact()

    private fun createAccessToken(accountId: String) =
        createToken(accountId, JwtProperty.ACCESS, securityProperties.accessExp)

    private fun createRefreshToken(accountId: String) =
        createToken(accountId, JwtProperty.REFRESH, securityProperties.refreshExp)

    fun getToken(accountId: String) = TokenResponse(
        accessToken = createAccessToken(accountId),
        accessTokenExpiredAt = LocalDateTime.now().plusSeconds(securityProperties.accessExp)
    )
}
