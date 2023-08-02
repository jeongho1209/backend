package com.example.thirdassignment.common.security.auth

import com.example.thirdassignment.user.domain.UserEntity
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

class AuthDetails(
    private val user: UserEntity,
) : UserDetails {
    override fun getAuthorities(): MutableCollection<out GrantedAuthority>? = null
    override fun getPassword(): String? = null
    override fun getUsername(): String = user.accountId
    override fun isAccountNonExpired() = true
    override fun isAccountNonLocked() = true
    override fun isCredentialsNonExpired() = true
    override fun isEnabled() = true
}
