package com.example.thirdassignment

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.boot.runApplication

@ConfigurationPropertiesScan
@SpringBootApplication
class ThirdAssignmentApplication

fun main(args: Array<String>) {
    runApplication<ThirdAssignmentApplication>(*args)
}
