package com.example.thirdassignment.todo.presentation

import com.example.thirdassignment.todo.presentation.dto.request.AddTodoRequest
import com.example.thirdassignment.todo.presentation.dto.request.UpdateTodoRequest
import com.example.thirdassignment.todo.presentation.dto.response.QueryTodoList
import com.example.thirdassignment.todo.service.TodoService
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RequestMapping("/todo")
@RestController
class TodoController(
    private val todoService: TodoService,
) {
    @GetMapping("/list")
    fun getAllTodoList(): QueryTodoList {
        return todoService.getAllTodoList()
    }

    @PostMapping("/add")
    fun addTodo(@RequestBody request: AddTodoRequest) {
        todoService.addTodo(request)
    }

    @PatchMapping("/{todo-id}")
    fun completeTodo(@PathVariable("todo-id") todoId: Int) {
        todoService.completeTodo(todoId)
    }

    @PutMapping
    fun updateTodo(@RequestBody request: UpdateTodoRequest) {
        todoService.updateTodo(request)
    }

    @DeleteMapping("/{todo-id}")
    fun deleteTodo(@PathVariable("todo-id") todoId: Int) {
        todoService.deleteTodo(todoId)
    }
}
