package com.example.thirdassignment.todo.domain

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table
import jakarta.validation.constraints.NotNull

@Entity
@Table(name = "tbl_todo")
class TodoEntity(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int = 0,

    title: String,

    content: String,

    isCompleted: Boolean,
) {

    @field:NotNull
    var title = title
        private set

    @field:NotNull
    var content = content
        private set

    @field:NotNull
    var isCompleted = isCompleted
        private set

    fun todoComplete() {
        this.isCompleted = true
    }

    fun updateTodo(title: String, content: String) {
        this.title = title
        this.content = content
    }
}
