package com.example.thirdassignment.common.error

abstract class CustomException(
    val globalErrorCode: GlobalErrorCode,
) : RuntimeException() {

    override fun fillInStackTrace() = this
}
