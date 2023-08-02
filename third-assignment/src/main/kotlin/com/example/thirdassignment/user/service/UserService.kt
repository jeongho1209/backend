package com.example.thirdassignment.user.service

import com.example.thirdassignment.common.security.jwt.JwtProvider
import com.example.thirdassignment.refresh_token.TokenResponse
import com.example.thirdassignment.user.domain.UserEntity
import com.example.thirdassignment.user.domain.UserRepository
import com.example.thirdassignment.user.exception.UnAuthorizedException
import com.example.thirdassignment.user.presentation.dto.request.UserSignInRequest
import com.example.thirdassignment.user.presentation.dto.request.UserSignUpRequest
import org.springframework.stereotype.Service
import java.util.UUID

val userHashMap = HashMap<UUID, String>()

@Service
class UserService(
    private val userRepository: UserRepository,
    private val jwtProvider: JwtProvider,
) {

    fun signUp(request: UserSignUpRequest) {
        if (userRepository.existsByAccountId(request.accountId)) {
            throw IllegalArgumentException("아이디가 존재합니다.")
        }

        userRepository.save(
            UserEntity(
                accountId = request.accountId,
                password = request.password,
            )
        )
    }

    fun signIn(request: UserSignInRequest): TokenResponse {
        val user = userRepository.findByAccountId(request.accountId)
            ?: throw UnAuthorizedException

        if (request.password != user.password) {
            throw UnAuthorizedException
        }

        return jwtProvider.getToken(request.accountId)
    }
}
