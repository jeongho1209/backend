package com.example.thirdassignment.common.security.auth

import com.example.thirdassignment.user.domain.UserRepository
import com.example.thirdassignment.user.exception.UserNotFoundException
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Component

@Component
class AuthDetailsService(
    private val userRepository: UserRepository,
) : UserDetailsService {

    override fun loadUserByUsername(accountId: String): UserDetails {
        val user = userRepository.findByAccountId(accountId)
            ?: throw UserNotFoundException

        return AuthDetails(user)
    }
}
