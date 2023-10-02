package com.example.thirdassignment.auth.stub

import com.example.thirdassignment.auth.presentation.dto.request.UserSignInRequest

internal fun createSignInRequestStub(
    accountId: String = "qwer",
    password: String = "secret1",
) = UserSignInRequest(
    accountId = accountId,
    password = password,
)
