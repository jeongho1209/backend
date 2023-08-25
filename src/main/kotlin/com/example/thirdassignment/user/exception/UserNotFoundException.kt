package com.example.thirdassignment.user.exception

import com.example.thirdassignment.common.error.CustomException
import com.example.thirdassignment.common.error.GlobalErrorCode

object UserNotFoundException : CustomException(
    GlobalErrorCode.USER_NOT_FOUND,
)
