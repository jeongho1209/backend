package com.example.thirdassignment.todo.presentation.dto.request

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Size

data class AddTodoRequest(
    @field:NotBlank
    @field:Size(max = 10)
    val title: String,
    @field:NotBlank
    @field:Size(max = 10)
    val content: String,
)
