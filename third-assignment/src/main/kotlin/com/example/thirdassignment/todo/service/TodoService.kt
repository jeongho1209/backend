package com.example.thirdassignment.todo.service

import com.example.thirdassignment.common.base64.decodingBase64
import com.example.thirdassignment.todo.domain.TodoEntity
import com.example.thirdassignment.todo.domain.TodoRepository
import com.example.thirdassignment.todo.exception.TodoNotFoundException
import com.example.thirdassignment.todo.presentation.dto.request.AddTodoRequest
import com.example.thirdassignment.todo.presentation.dto.request.UpdateTodoRequest
import com.example.thirdassignment.todo.presentation.dto.response.QueryTodoList
import com.example.thirdassignment.todo.presentation.dto.response.QueryTodoList.TodoResponse
import com.example.thirdassignment.user.domain.UserEntity
import com.example.thirdassignment.user.domain.UserRepository
import com.example.thirdassignment.user.exception.UserNotFoundException
import jakarta.servlet.http.HttpServletRequest
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class TodoService(
    private val todoRepository: TodoRepository,
    private val userRepository: UserRepository,
) {

    fun addTodo(request: AddTodoRequest, httpServletRequest: HttpServletRequest) {
        val decodingStrings = httpServletRequest.getHeader("Authorization").decodingBase64()
        val user = userRepository.findByAccountIdAndPassword(
            accountId = decodingStrings[0],
            password = decodingStrings[1],
        ) ?: throw TodoNotFoundException

        todoRepository.save(
            TodoEntity(
                title = request.title,
                content = request.content,
                isCompleted = false,
                user = user,
            )
        )
    }

    fun completeTodo(todoId: Int) {
        val todo = findTodoByTodoId(todoId)
        todo.todoComplete()
    }

    fun deleteTodo(todoId: Int, accountId: String) {
        val user = findUserByAccountId(accountId)

        val todoEntity = findTodoByTodoId(todoId)

        checkIsEqualTodoWriter(user.id, todoEntity.id)

        todoRepository.delete(todoEntity)
    }

    fun updateTodo(request: UpdateTodoRequest) {
        val user = findUserByAccountId(request.accountId)
        val todo = findTodoByTodoId(request.todoId)

        checkIsEqualTodoWriter(user.id, todo.id)

        todo.updateTodo(
            title = request.title,
            content = request.content,
        )
    }

    fun getAllTodoListByAccountId(accountId: String): QueryTodoList {
        val user = findUserByAccountId(accountId)
        val todoList = todoRepository.findAllByUser(user)

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

    private fun findTodoByTodoId(todoId: Int): TodoEntity =
        todoRepository.findByIdOrNull(todoId)
            ?: throw TodoNotFoundException

    private fun findUserByAccountId(accountId: String): UserEntity =
        userRepository.findByAccountId(accountId)
            ?: throw UserNotFoundException

    private fun checkIsEqualTodoWriter(currentUserId: Int, todoUserId: Int) {
        if (currentUserId != todoUserId) {
            throw TodoNotFoundException
        }
    }
}
