package com.example.thirdassignment.user.presentation

import com.example.thirdassignment.refresh_token.TokenResponse
import com.example.thirdassignment.user.presentation.dto.request.UserSignInRequest
import com.example.thirdassignment.user.presentation.dto.request.UserSignUpRequest
import com.example.thirdassignment.user.presentation.dto.response.UserSignInResponse
import com.example.thirdassignment.user.service.UserService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RequestMapping("/user")
@RestController
class UserController(
    private val userService: UserService,
) {

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/signup")
    fun signUp(@RequestBody request: UserSignUpRequest) {
        userService.signUp(request)
    }

    @PostMapping("/login")
    fun signIn(@RequestBody request: UserSignInRequest): TokenResponse {
        return userService.signIn(request)
    }
}
