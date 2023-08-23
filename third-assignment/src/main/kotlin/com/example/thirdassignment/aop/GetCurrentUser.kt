package com.example.thirdassignment.aop

import com.example.thirdassignment.user.domain.UserEntity
import com.example.thirdassignment.user.domain.UserRepository
import com.example.thirdassignment.user.exception.UserNotFoundException
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component

@Component
class GetCurrentUser(
    private val userRepository: UserRepository,
) {
    fun getCurrentUser(): UserEntity? {
        val accountId = SecurityContextHolder.getContext().authentication?.name
        return userRepository.findByAccountId(accountId)
            ?: throw UserNotFoundException
    }
}
