package com.example.thirdassignment.common.exception

import com.example.thirdassignment.common.error.CustomException
import com.example.thirdassignment.common.error.GlobalErrorCode

object InvalidTokenException : CustomException(
    GlobalErrorCode.INVALID_TOKEN,
)
