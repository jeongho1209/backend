package com.example.thirdassignment.todo.dto.request

data class UpdateTodoRequest(
    val todoId: Int,
    val title: String,
    val content: String,
)
