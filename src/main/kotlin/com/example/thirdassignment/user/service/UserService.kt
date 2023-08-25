package com.example.thirdassignment.user.service

import com.example.thirdassignment.user.domain.UserEntity
import com.example.thirdassignment.user.domain.UserRepository
import com.example.thirdassignment.user.presentation.dto.request.UserSignUpRequest
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class UserService(
    private val userRepository: UserRepository,
    private val passwordEncoder: PasswordEncoder,
) {

    fun signUp(request: UserSignUpRequest) {
        checkIsExistsUserAccountId(request.accountId)

        userRepository.save(
            UserEntity(
                accountId = request.accountId,
                password = passwordEncoder.encode(request.password),
            )
        )
    }

    internal fun checkIsExistsUserAccountId(accountId: String) {
        if (userRepository.existsByAccountId(accountId)) {
            throw IllegalArgumentException("아이디가 존재합니다.")
        }
    }
}
