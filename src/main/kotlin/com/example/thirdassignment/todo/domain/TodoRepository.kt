package com.example.thirdassignment.todo.domain

import com.example.thirdassignment.user.domain.UserEntity
import org.springframework.data.repository.CrudRepository

interface TodoRepository : CrudRepository<TodoEntity, Int> {
    fun findAllByUser(user: UserEntity): List<TodoEntity>
    override fun findAll(): List<TodoEntity>
}
