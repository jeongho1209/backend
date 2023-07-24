package com.example.thirdassignment.common.error

abstract class CustomException(
    val errorProperty: ErrorProperty
) : RuntimeException() {

    override fun fillInStackTrace() = this
}
