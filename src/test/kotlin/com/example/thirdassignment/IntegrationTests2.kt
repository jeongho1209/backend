package com.example.thirdassignment

import com.example.thirdassignment.aop.GetCurrentUser
import com.example.thirdassignment.auth.service.AuthService
import com.example.thirdassignment.common.security.SecurityProperties
import com.example.thirdassignment.common.security.jwt.JwtProvider
import com.example.thirdassignment.refresh_token.RefreshTokenRepository
import com.example.thirdassignment.todo.domain.TodoRepository
import com.example.thirdassignment.todo.service.TodoService
import com.example.thirdassignment.user.domain.UserRepository
import com.example.thirdassignment.user.service.UserService
import org.junit.jupiter.api.BeforeEach
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.security.crypto.password.PasswordEncoder

class IntegrationTests2 {
    @MockBean
    private lateinit var todoRepository: TodoRepository

    @MockBean
    private lateinit var userRepository: UserRepository

    @MockBean
    private lateinit var refreshTokenRepository: RefreshTokenRepository

    @MockBean
    private lateinit var jwtProvider: JwtProvider

    @MockBean
    private lateinit var passwordEncoder: PasswordEncoder

    @MockBean
    private lateinit var securityProperties: SecurityProperties

    @MockBean
    private lateinit var getCurrentUser: GetCurrentUser

    private lateinit var userService: UserService
    private lateinit var todoService: TodoService
    private lateinit var authService: AuthService

    @BeforeEach
    fun setUp() {
        userService = UserService(userRepository, passwordEncoder)
        todoService = TodoService(todoRepository, userRepository, getCurrentUser)
        authService = AuthService(passwordEncoder, userRepository, refreshTokenRepository, jwtProvider, securityProperties)
    }

    
}