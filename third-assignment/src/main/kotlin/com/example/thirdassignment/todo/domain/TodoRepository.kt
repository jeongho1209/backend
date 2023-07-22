package com.example.thirdassignment.todo.domain

import org.springframework.data.repository.CrudRepository

interface TodoRepository : CrudRepository<TodoEntity, Int> {
}