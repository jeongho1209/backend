package com.example.thirdassignment.common.filter

import com.example.thirdassignment.common.security.jwt.JwtParser
import com.example.thirdassignment.common.security.jwt.JwtProperty
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.filter.OncePerRequestFilter

class JwtFilter(
    private val jwtParser: JwtParser,
) : OncePerRequestFilter() {

    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        validatedToken(request)?.run {
            SecurityContextHolder.getContext().authentication = jwtParser.getAuthentication(this)
        }

        filterChain.doFilter(request, response)
    }

    private fun validatedToken(request: HttpServletRequest): String? {
        val bearerToken = request.getHeader(JwtProperty.HEADER)
        val isEmptyTokenOrInvalidPrefix = bearerToken == null || !bearerToken.startsWith(JwtProperty.PREFIX)
        if (isEmptyTokenOrInvalidPrefix) {
            return null
        }
        return bearerToken.substring(7)
    }
}
