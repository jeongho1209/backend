package com.example.thirdassignment.auth.service

import com.example.thirdassignment.auth.exception.RefreshTokenNotFoundException
import com.example.thirdassignment.auth.presentation.dto.request.UserSignInRequest
import com.example.thirdassignment.auth.presentation.dto.response.TokenResponse
import com.example.thirdassignment.common.security.SecurityProperties
import com.example.thirdassignment.common.security.jwt.JwtProvider
import com.example.thirdassignment.refresh_token.RefreshTokenRepository
import com.example.thirdassignment.user.domain.UserRepository
import com.example.thirdassignment.user.exception.UnAuthorizedException
import com.example.thirdassignment.user.exception.UserNotFoundException
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class AuthService(
    private val passwordEncoder: PasswordEncoder,
    private val userRepository: UserRepository,
    private val refreshTokenRepository: RefreshTokenRepository,
    private val jwtProvider: JwtProvider,
    private val securityProperties: SecurityProperties,
) {

    fun signIn(request: UserSignInRequest): TokenResponse {
        val user = userRepository.findByAccountId(request.accountId)
            ?: throw UnAuthorizedException

        if (!passwordEncoder.matches(request.password, user.password)) {
            throw UnAuthorizedException
        }

        return jwtProvider.getToken(request.accountId)
    }

    fun tokenRefresh(refreshToken: String): TokenResponse {
        val redisRefreshToken = refreshTokenRepository.findByToken(refreshToken)
            ?: throw RefreshTokenNotFoundException

        val newRefreshToken = jwtProvider.createRefreshToken(redisRefreshToken.accountId)
        redisRefreshToken.updateRefreshToken(newRefreshToken, securityProperties.refreshExp)

        val newAccessToken = jwtProvider.createAccessToken(redisRefreshToken.accountId)

        return TokenResponse(
            accessToken = newAccessToken,
            refreshToken = newRefreshToken,
            accessTokenExpiredAt = LocalDateTime.now().plusSeconds(securityProperties.accessExp)
        )
    }
}
