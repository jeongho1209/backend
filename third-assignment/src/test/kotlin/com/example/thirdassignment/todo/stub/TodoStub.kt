package com.example.thirdassignment.todo.stub

import com.example.thirdassignment.todo.presentation.dto.request.AddTodoRequest

internal fun createTodoRequestStub(
    title: String = "test title",
    content: String = "test content",
) = AddTodoRequest(
    title = title,
    content = content,
)
