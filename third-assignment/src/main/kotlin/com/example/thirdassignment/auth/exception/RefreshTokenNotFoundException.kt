package com.example.thirdassignment.auth.exception

import com.example.thirdassignment.common.error.CustomException
import com.example.thirdassignment.common.error.GlobalErrorCode

object RefreshTokenNotFoundException : CustomException(
    GlobalErrorCode.REFRESH_TOKEN_NOT_FOUND,
)
