package com.example.thirdassignment.check

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class CheckController {

    @GetMapping("/hello")
    fun checkHello() = "Hello World!"

    @GetMapping("/add")
    fun addTwoNumber(
        @RequestParam oneNumber: Int,
        @RequestParam twoNumber: Int,
    ) = NumberResponse(oneNumber + twoNumber)

    @GetMapping("/multiply")
    fun multiplyTwoNumber(
        @RequestParam oneNumber: Int,
        @RequestParam twoNumber: Int,
    ) = NumberResponse(oneNumber * twoNumber)
}

data class NumberResponse(
    val result: Int
)
