package com.example.thirdassignment.todo.presentation

import com.example.thirdassignment.todo.presentation.dto.request.AddTodoRequest
import com.example.thirdassignment.todo.presentation.dto.request.UpdateTodoRequest
import com.example.thirdassignment.todo.presentation.dto.response.QueryTodoList
import com.example.thirdassignment.todo.service.TodoService
import jakarta.servlet.http.HttpServletRequest
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RequestMapping("/todo")
@RestController
class TodoController(
    private val todoService: TodoService,
) {
    @GetMapping("/list/{account-id}")
    fun getAllTodoList(@PathVariable("account-id") accountId: String): QueryTodoList {
        return todoService.getAllTodoListByAccountId(accountId)
    }

    @PostMapping("/add")
    fun addTodo(@RequestBody request: AddTodoRequest, httpServletRequest: HttpServletRequest) {
        todoService.addTodo(request, httpServletRequest)
    }

    @PatchMapping("/{todo-id}")
    fun completeTodo(@PathVariable("todo-id") todoId: Int) {
        todoService.completeTodo(todoId)
    }

    @PutMapping
    fun updateTodo(@RequestBody request: UpdateTodoRequest) {
        todoService.updateTodo(request)
    }

    @DeleteMapping
    fun deleteTodo(
        @RequestParam("todo-id") todoId: Int,
        @RequestParam("account-id") accountId: String,
    ) {
        todoService.deleteTodo(todoId, accountId)
    }
}
