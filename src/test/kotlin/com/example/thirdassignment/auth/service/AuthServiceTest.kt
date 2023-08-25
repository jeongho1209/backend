package com.example.thirdassignment.auth.service

import com.example.thirdassignment.auth.presentation.dto.request.UserSignInRequest
import com.example.thirdassignment.auth.presentation.dto.response.TokenResponse
import com.example.thirdassignment.common.security.SecurityProperties
import com.example.thirdassignment.common.security.jwt.JwtProvider
import com.example.thirdassignment.refresh_token.RefreshTokenRepository
import com.example.thirdassignment.user.domain.UserRepository
import com.example.thirdassignment.user.exception.UnAuthorizedException
import com.example.thirdassignment.user.stub.createUserStub
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.DescribeSpec
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.junit.jupiter.MockitoExtension
import org.springframework.security.crypto.password.PasswordEncoder
import java.time.LocalDateTime

@ExtendWith(MockitoExtension::class)
internal class AuthServiceTest : DescribeSpec({

    val passwordEncoder: PasswordEncoder = mockk(relaxed = true)
    val userRepository: UserRepository = mockk(relaxed = true)
    val refreshTokenRepository: RefreshTokenRepository = mockk(relaxed = true)
    val jwtProvider: JwtProvider = mockk(relaxed = true)
    val securityProperties: SecurityProperties = mockk(relaxed = true)

    val authService = AuthService(
        passwordEncoder, userRepository, refreshTokenRepository, jwtProvider, securityProperties
    )

    val userStub = createUserStub()

    val requestStub: UserSignInRequest by lazy {
        UserSignInRequest(
            accountId = "test accountId",
            password = "test password",
        )
    }

    val responseStub: TokenResponse by lazy {
        TokenResponse(
            accessToken = "test access token",
            refreshToken = "test refresh token",
            accessTokenExpiredAt = LocalDateTime.now(),
        )
    }

    describe("유저가 로그인을 진행할 때") {
        context("유저의 정보가 올바르게 주어지면") {
            coEvery {
                withContext(Dispatchers.IO) {
                    userRepository.findByAccountId(requestStub.accountId)
                }
            }
            coEvery { passwordEncoder.matches(requestStub.password, userStub.password) } returns true
            coEvery { jwtProvider.getToken(requestStub.accountId) } returns responseStub

            it("토큰을 반환한다") {
                authService.signIn(requestStub)
            }
        }
        context("유저의 아이디가 일치하지 않으면") {
            coEvery {
                withContext(Dispatchers.IO) {
                    userRepository.findByAccountId(requestStub.accountId)
                }
            } returns null

            it("Un Authorized 예외를 던진다") {
                shouldThrow<UnAuthorizedException> {
                    authService.signIn(requestStub)
                }
            }
        }
    }
})
