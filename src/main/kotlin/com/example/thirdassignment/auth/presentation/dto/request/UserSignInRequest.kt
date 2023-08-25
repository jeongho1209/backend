package com.example.thirdassignment.auth.presentation.dto.request

data class UserSignInRequest(
    val accountId: String,
    val password: String,
)
