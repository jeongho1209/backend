//package com.example.thirdassignment.todo
//
//import com.example.thirdassignment.common.security.SecurityConfig
//import com.example.thirdassignment.todo.presentation.TodoController
//import com.example.thirdassignment.todo.presentation.dto.response.QueryTodoList
//import com.example.thirdassignment.todo.presentation.dto.response.QueryTodoList.TodoResponse
//import com.example.thirdassignment.todo.service.TodoService
//import org.junit.jupiter.api.BeforeEach
//import org.junit.jupiter.api.Test
//import org.springframework.beans.factory.annotation.Autowired
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
//import org.springframework.boot.test.mock.mockito.MockBean
//import org.springframework.context.annotation.Import
//import org.springframework.http.MediaType
//import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf
//import org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity
//import org.springframework.test.web.servlet.MockMvc
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
//import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
//import org.springframework.test.web.servlet.setup.DefaultMockMvcBuilder
//import org.springframework.test.web.servlet.setup.MockMvcBuilders
//import org.springframework.web.context.WebApplicationContext
//
//@Import(SecurityConfig::class)
//@WebMvcTest(TodoController::class)
//class TodoControllerTest @Autowired(required = false) constructor(
//    private var mockMvc: MockMvc,
//    private val webApplicationContext: WebApplicationContext,
//) {
//
//    @MockBean
//    lateinit var todoService: TodoService
//
//    private val todoResponseStub by lazy {
//        val todoResponse = TodoResponse(
//            todoId = 1,
//            title = "제목",
//            content = "내용",
//            isCompleted = false,
//        )
//        QueryTodoList(todoList = listOf(todoResponse))
//    }
//
//    @BeforeEach
//    fun setUp() {
//        mockMvc = MockMvcBuilders
//            .webAppContextSetup(webApplicationContext)
//            .apply<DefaultMockMvcBuilder>(springSecurity())
//            .build()
//    }
//
//    @Test
//    fun 할_일_추가() {
//        mockMvc.perform(
//            MockMvcRequestBuilders
//                .post("/todos")
//                .with(csrf())
//                .accept(MediaType.APPLICATION_JSON)
//        ).andExpect(status().isCreated)
//    }
//
//    @Test
//    fun 할_일_조회() {
//        mockMvc.perform(
//            MockMvcRequestBuilders
//                .get("/todos/me")
//                .with(csrf())
//                .accept(MediaType.APPLICATION_JSON)
//        ).andExpect(status().isOk)
//    }
//}
