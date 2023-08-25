package com.example.thirdassignment.check

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.util.logging.Logger

@RestController
class CheckController {

    private val logger = Logger.getLogger(this.javaClass.name)

    @GetMapping("/hello")
    fun checkHello(): String {
        logger.info("qwertyuiopasdfghjklzxcvbnm")
        return "Hello World!"
    }

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
