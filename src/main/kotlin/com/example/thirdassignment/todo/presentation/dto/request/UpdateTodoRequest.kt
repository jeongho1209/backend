package com.example.thirdassignment.todo.presentation.dto.request

data class UpdateTodoRequest(
    val todoId: Int,
    val title: String,
    val content: String,
    val accountId: String,
)
