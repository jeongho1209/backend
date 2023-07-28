package com.example.thirdassignment.todo.exception

import com.example.thirdassignment.common.error.CustomException
import com.example.thirdassignment.common.error.GlobalErrorCode

object TodoNotFoundException : CustomException(
    GlobalErrorCode.TODO_NOT_FOUND,
)
