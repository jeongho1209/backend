package com.example.thirdassignment

import com.example.thirdassignment.aop.GetCurrentUser
import com.example.thirdassignment.auth.stub.createSignInRequestStub
import com.example.thirdassignment.todo.domain.TodoRepository
import com.example.thirdassignment.todo.service.TodoService
import com.example.thirdassignment.todo.stub.createTodoRequestStub
import com.example.thirdassignment.todo.stub.getTodoResponse
import com.example.thirdassignment.user.domain.UserRepository
import com.example.thirdassignment.user.service.UserService
import com.fasterxml.jackson.databind.ObjectMapper
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.data.repository.findByIdOrNull
import org.springframework.http.MediaType
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.test.context.support.WithMockUser
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.MvcResult
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@WithMockUser(username = "qwer")
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class IntegrationTests(
    @Autowired
    val mockMvc: MockMvc,
    @Autowired
    val objectMapper: ObjectMapper,
    @Autowired
    var userService: UserService,
    @Autowired
    var todoService: TodoService
) {

    @MockBean
    private lateinit var todoRepository: TodoRepository

    @MockBean
    private lateinit var userRepository: UserRepository

    @MockBean
    private lateinit var passwordEncoder: PasswordEncoder

    @MockBean
    private lateinit var getCurrentUser: GetCurrentUser

    @BeforeEach
    fun setUp() {
        userService = UserService(userRepository, passwordEncoder)
        todoService = TodoService(todoRepository, userRepository, getCurrentUser)
    }

    @Test
    fun 할_일_조회() {
        mockMvc.perform(get("/todos/me"))
            .andExpect(status().isOk)
            .andExpect(content().json(objectMapper.writeValueAsString(getTodoResponse())))
    }

    @Test
    fun 할_일_추가() {
        mockMvc.perform(
            post("/todos")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(createTodoRequestStub()))
        )
            .andExpect(status().isCreated)
    }

    @Test
    fun 로그인() {
        val mvcResult: MvcResult = mockMvc.perform(
            post("/auth/token")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(createSignInRequestStub()))
        )
            .andExpect(status().isOk)
            .andReturn()

        assertEquals("Token", mvcResult.response.contentAsString)
    }

    @Test
    fun 할_일_삭제() {
        // given & when
        mockMvc.perform(delete("/todos/41"))

        // then
        assertEquals(todoRepository.findByIdOrNull(41), null)
    }
}
