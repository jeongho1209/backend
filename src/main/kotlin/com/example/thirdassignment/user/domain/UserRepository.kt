package com.example.thirdassignment.user.domain

import org.springframework.data.repository.CrudRepository

interface UserRepository : CrudRepository<UserEntity, Int> {
    fun existsByAccountId(accountId: String): Boolean
    fun findByAccountId(accountId: String?): UserEntity?
}
