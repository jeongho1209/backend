package com.example.thirdassignment.todo.stub

import com.example.thirdassignment.todo.presentation.dto.request.AddTodoRequest
import com.example.thirdassignment.todo.presentation.dto.response.QueryTodoList
import com.example.thirdassignment.todo.presentation.dto.response.QueryTodoList.TodoResponse

internal fun createTodoRequestStub(
    title: String = "test title",
    content: String = "test content",
) = AddTodoRequest(
    title = title,
    content = content,
)

internal fun getTodoResponse(
    todoList: List<TodoResponse> = listOf(
        TodoResponse(
            todoId = 1,
            title = "adsfasf",
            content = "asdfasdf",
            isCompleted = false,
        )
    )
) = QueryTodoList(
    todoList = todoList,
)
