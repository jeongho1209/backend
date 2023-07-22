package com.example.thirdassignment.user.service

import com.example.thirdassignment.user.domain.UserEntity
import com.example.thirdassignment.user.domain.UserRepository
import com.example.thirdassignment.user.presentation.dto.request.UserSignUpRequest
import org.springframework.stereotype.Service

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
}
