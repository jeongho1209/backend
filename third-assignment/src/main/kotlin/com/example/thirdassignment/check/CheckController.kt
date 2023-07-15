package com.example.thirdassignment.check

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class CheckController {

    @GetMapping("/hello")
    fun checkHello() = "Hello World!"
}
