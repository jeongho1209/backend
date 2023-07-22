package com.example.thirdassignment.todo.service

import com.example.thirdassignment.todo.presentation.dto.request.AddTodoRequest
import com.example.thirdassignment.todo.presentation.dto.request.UpdateTodoRequest
import com.example.thirdassignment.todo.presentation.dto.response.QueryTodoList
import com.example.thirdassignment.todo.presentation.dto.response.QueryTodoList.TodoResponse
import com.example.thirdassignment.todo.domain.TodoEntity
import com.example.thirdassignment.todo.domain.TodoRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class TodoService(
    private val todoRepository: TodoRepository,
) {

    fun addTodo(request: AddTodoRequest) {
        todoRepository.save(
            TodoEntity(
                title = request.title,
                content = request.content,
                isCompleted = false,
            )
        )
    }

    fun completeTodo(todoId: Int) {
        val todoEntity = todoRepository.findByIdOrNull(todoId)
            ?: throw IllegalArgumentException("todo를 찾을 수 없습니다.")

        todoEntity.todoComplete()
    }

    fun deleteTodo(todoId: Int) {
        val todoEntity = todoRepository.findByIdOrNull(todoId)
            ?: throw IllegalArgumentException("todo를 찾을 수 없습니다.")

        todoRepository.delete(todoEntity)
    }

    fun updateTodo(request: UpdateTodoRequest) {
        val todoEntity = todoRepository.findByIdOrNull(request.todoId)
            ?: throw IllegalArgumentException("todo를 찾을 수 없습니다.")

        todoEntity.updateTodo(
            title = request.title,
            content = request.content,
        )
    }

    fun getAllTodoList(): QueryTodoList {
        val todoList = todoRepository.findAll()

        val response = todoList.map { todo ->
            TodoResponse(
                todoId = todo.id,
                title = todo.title,
                content = todo.content,
                isCompleted = todo.isCompleted,
            )
        }

        return QueryTodoList(response)
    }
}
