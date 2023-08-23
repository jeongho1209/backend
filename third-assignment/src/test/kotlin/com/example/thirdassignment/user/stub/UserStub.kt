package com.example.thirdassignment.user.stub

import com.example.thirdassignment.user.domain.UserEntity

internal fun createUserStub(
    accountId: String = "test accountId",
    password: String = "encode test password",
) = UserEntity(
    accountId = accountId,
    password = password,
)
