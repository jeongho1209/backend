package com.example.thirdassignment.common.security

import com.example.thirdassignment.common.filter.FilterConfig
import com.example.thirdassignment.common.security.jwt.JwtParser
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.web.SecurityFilterChain

@Configuration
@EnableWebSecurity
class SecurityConfig(
    private val jwtParser: JwtParser,
    private val objectMapper: ObjectMapper,
) {

    @Bean
    @Throws(Exception::class)
    fun securityFilterChain(http: HttpSecurity): SecurityFilterChain? {
        http
            .formLogin { it.disable() }
            .csrf { it.disable() }
            .cors {}
            .authorizeHttpRequests {
                it.requestMatchers("/**").permitAll()
                    .anyRequest().permitAll()
            }
        http
            .apply(FilterConfig(jwtParser, objectMapper))
        return http.build()
    }

    @Bean
    protected fun passwordEncoder() = BCryptPasswordEncoder()
}
