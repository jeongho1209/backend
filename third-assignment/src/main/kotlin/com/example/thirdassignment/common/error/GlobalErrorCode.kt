package com.example.thirdassignment.common.error

enum class GlobalErrorCode(
    val status: Int,
    val message: String,
) {
    BAD_REQUEST(status = 400, message = "Bad Request"),

    UN_AUTHORIZED(status = 401, message = "Un Authorized"),
    INVALID_TOKEN(status = 401, message = "Invalid Token"),
    EXPIRED_TOKEN(status = 401, message = "Expired Token"),

    TODO_NOT_FOUND(status = 404, message = "Todo Not Found"),
    USER_NOT_FOUND(status = 404, message = "User Not Found"),

    INTERNAL_SERVER_ERROR(status = 500, message = "Internal Server Error");
}
