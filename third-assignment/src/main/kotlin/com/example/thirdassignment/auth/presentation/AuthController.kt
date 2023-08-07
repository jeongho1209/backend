package com.example.thirdassignment.auth.presentation

import com.example.thirdassignment.auth.presentation.dto.request.UserSignInRequest
import com.example.thirdassignment.auth.presentation.dto.response.TokenResponse
import com.example.thirdassignment.auth.service.AuthService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RequestMapping("/auth")
@RestController
class AuthController(
    private val authService: AuthService,
) {

    @PostMapping("/token")
    fun signIn(@RequestBody request: UserSignInRequest): TokenResponse {
        return authService.signIn(request)
    }

    @PutMapping("/token")
    fun tokenRefresh(@RequestHeader("Refresh-Token") refreshToken: String): TokenResponse {
        return authService.tokenRefresh(refreshToken)
    }
}
