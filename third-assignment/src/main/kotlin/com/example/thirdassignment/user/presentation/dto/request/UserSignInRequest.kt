package com.example.thirdassignment.user.presentation.dto.request

data class UserSignInRequest(
    val accountId: String,
    val password: String,
)
