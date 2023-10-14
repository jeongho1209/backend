package com.example.thirdassignment

import com.example.thirdassignment.aop.GetCurrentUser
import com.example.thirdassignment.auth.presentation.dto.request.UserSignInRequest
import com.example.thirdassignment.auth.presentation.dto.response.TokenResponse
import com.example.thirdassignment.auth.service.AuthService
import com.example.thirdassignment.common.security.SecurityProperties
import com.example.thirdassignment.common.security.jwt.JwtProvider
import com.example.thirdassignment.refresh_token.RefreshTokenRepository
import com.example.thirdassignment.todo.domain.TodoEntity
import com.example.thirdassignment.todo.domain.TodoRepository
import com.example.thirdassignment.todo.presentation.dto.request.AddTodoRequest
import com.example.thirdassignment.todo.service.TodoService
import com.example.thirdassignment.user.domain.UserEntity
import com.example.thirdassignment.user.domain.UserRepository
import com.example.thirdassignment.user.service.UserService
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.BDDMockito.given
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.MediaType
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.test.context.support.WithMockUser
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import java.time.LocalDateTime

@WithMockUser(username = "qwer")
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class IntegrationTests2 {
    @MockBean
    private lateinit var mockMvc: MockMvc

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

    private val signInRequestStub = UserSignInRequest(
        accountId = "test accountId",
        password = "test password",
    )


    private val tokenResponseStub = TokenResponse(
        accessToken = "access token",
        accessTokenExpiredAt = LocalDateTime.now().plusSeconds(7200),
        refreshToken = "refresh token",
    )

    private val userStub = UserEntity(
        accountId = "test accountId",
        password = "encode test password"
    )

    private val todoRequestStub = AddTodoRequest(
        title = "test title",
        content = "test content",
    )

    private val todoResponseStub = listOf(
        TodoEntity(
            title = "test title",
            content = "test content",
            isCompleted = false,
            user = userStub,
        )
    )

    @Test
    fun 할_일_추가() {
        // given
        given(getCurrentUser.getCurrentUser()).willReturn(userStub)

        // when & then
        mockMvc.perform(
            post("/todos")
                .contentType(MediaType.APPLICATION_JSON)
                .content(todoRequestStub.toString())
        ).andExpect(status().isCreated)
    }

    @Test
    fun 할_일_조회() {
        // given
        given(todoRepository.findAllByUser(userStub)).willReturn(todoResponseStub)

        // when & then
        assertNotNull(todoService.getAllTodoList())
    }

    @Test
    fun 로그인() {
        // given
        given(userRepository.findByAccountId(signInRequestStub.accountId)).willReturn(userStub)
        given(passwordEncoder.matches(signInRequestStub.password, userStub.password)).willReturn(true)
        given(jwtProvider.getToken(signInRequestStub.accountId)).willReturn(tokenResponseStub)

        // when
        val response = authService.signIn(signInRequestStub)
        val result = mockMvc.perform(
            post("/auth/token")
                .contentType(MediaType.APPLICATION_JSON)
                .content(signInRequestStub.toString())
        )

        // then
        assertEquals(response, tokenResponseStub)
        result.andExpect(status().isOk)
    }
}
