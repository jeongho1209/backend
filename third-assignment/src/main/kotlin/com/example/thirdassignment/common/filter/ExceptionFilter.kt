package com.example.thirdassignment.common.filter

import com.example.thirdassignment.common.error.CustomException
import com.example.thirdassignment.common.error.ErrorResponse
import com.example.thirdassignment.common.error.GlobalErrorCode
import com.example.thirdassignment.common.error.of
import com.example.thirdassignment.common.exception.InternalServerErrorException
import com.fasterxml.jackson.databind.ObjectMapper
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.http.MediaType
import org.springframework.web.filter.OncePerRequestFilter
import java.nio.charset.StandardCharsets

class ExceptionFilter(
    private val objectMapper: ObjectMapper
) : OncePerRequestFilter() {

    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain,
    ) {
        try {
            filterChain.doFilter(request, response)
        } catch (e: CustomException) {
            errorToJson((e.cause as CustomException).globalErrorCode, response)
        } catch (e: Exception) {
            when (e.cause) {
                is CustomException -> {
                    errorToJson((e.cause as CustomException).globalErrorCode, response)
                }

                else -> {
                    e.printStackTrace()
                    errorToJson(GlobalErrorCode.INTERNAL_SERVER_ERROR, response)
                }
            }
        }
    }

    private fun errorToJson(globalErrorCode: GlobalErrorCode, response: HttpServletResponse) {
        response.apply {
            status = globalErrorCode.status
            characterEncoding = StandardCharsets.UTF_8.name()
            contentType = MediaType.APPLICATION_JSON_VALUE
            writer.write(objectMapper.writeValueAsString(ErrorResponse(globalErrorCode.status, globalErrorCode.message)))
        }
    }
}
