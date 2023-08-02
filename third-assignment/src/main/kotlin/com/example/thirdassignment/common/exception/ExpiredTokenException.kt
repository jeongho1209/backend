package com.example.thirdassignment.common.exception

import com.example.thirdassignment.common.error.CustomException
import com.example.thirdassignment.common.error.GlobalErrorCode

object ExpiredTokenException : CustomException(
    GlobalErrorCode.EXPIRED_TOKEN,
)
