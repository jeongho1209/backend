package com.example.thirdassignment.todo.presentation.dto.request

data class AddTodoRequest(
    val title: String,
    val content: String,
    val accountId: String,
    val password: String,
)
