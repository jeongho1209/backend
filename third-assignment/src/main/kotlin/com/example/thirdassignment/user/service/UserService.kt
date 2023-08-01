package com.example.thirdassignment.user.service

import com.example.thirdassignment.user.domain.UserEntity
import com.example.thirdassignment.user.domain.UserRepository
import com.example.thirdassignment.user.exception.UnAuthorizedException
import com.example.thirdassignment.user.presentation.dto.request.UserSignInRequest
import com.example.thirdassignment.user.presentation.dto.request.UserSignUpRequest
import com.example.thirdassignment.user.presentation.dto.response.UserSignInResponse
import org.springframework.stereotype.Service
import java.util.UUID

val userHashMap = HashMap<UUID, String>()

@Service
class UserService(
    private val userRepository: UserRepository,
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

    fun signIn(request: UserSignInRequest): UserSignInResponse {
        val user = userRepository.findByAccountId(request.accountId)
            ?: throw UnAuthorizedException

        if (request.password != user.password) {
            throw UnAuthorizedException
        }

        val randomUUID = UUID.randomUUID()
        userHashMap[randomUUID] = request.accountId

        return UserSignInResponse(randomUUID = randomUUID)
    }
}
