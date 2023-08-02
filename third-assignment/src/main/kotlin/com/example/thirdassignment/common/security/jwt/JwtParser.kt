package com.example.thirdassignment.common.security.jwt

import com.example.thirdassignment.common.exception.ExpiredTokenException
import com.example.thirdassignment.common.exception.InternalServerErrorException
import com.example.thirdassignment.common.exception.InvalidTokenException
import com.example.thirdassignment.common.security.SecurityProperties
import com.example.thirdassignment.common.security.auth.AuthDetailsService
import io.jsonwebtoken.Claims
import io.jsonwebtoken.ExpiredJwtException
import io.jsonwebtoken.InvalidClaimException
import io.jsonwebtoken.Jwts
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.stereotype.Component

@Component
class JwtParser(
    private val securityProperties: SecurityProperties,
    private val authDetailsService: AuthDetailsService,
) {

    private fun getClaims(token: String): Claims {
        return try {
            Jwts.parser()
                .setSigningKey(securityProperties.secretKey)
                .parseClaimsJws(token).body
        } catch (e: Exception) {
            when (e) {
                is InvalidClaimException -> throw InvalidTokenException
                is ExpiredJwtException -> throw ExpiredTokenException
                else -> throw InternalServerErrorException
            }
        }
    }

    fun getAuthentication(token: String): Authentication {
        val claims = getClaims(token)
        val authDetails = authDetailsService.loadUserByUsername(claims.subject)
        return UsernamePasswordAuthenticationToken(authDetails, "")
    }
}
