package com.example.thirdassignment.todo.dto.response

data class QueryTodoList(
    val todoList: List<TodoResponse>
) {
    data class TodoResponse(
        val todoId: Int,
        val title: String,
        val content: String,
        val isCompleted: Boolean,
    )
}
