package com.example.thirdassignment.user.exception

import com.example.thirdassignment.common.error.CustomException
import com.example.thirdassignment.common.error.GlobalErrorCode

object UnAuthorizedException : CustomException(
    GlobalErrorCode.UN_AUTHORIZED,
)
