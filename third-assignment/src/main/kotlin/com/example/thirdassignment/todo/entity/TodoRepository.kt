package com.example.thirdassignment.todo.entity

import org.springframework.data.repository.CrudRepository

interface TodoRepository : CrudRepository<TodoEntity, Int> {
}