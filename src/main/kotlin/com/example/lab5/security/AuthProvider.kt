package com.example.lab5.security

import org.springframework.security.authentication.AuthenticationProvider
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.password.PasswordEncoder

class AuthProvider(
    private val userDetailsService: UserDetailsService,
    private val passwordEncoder: PasswordEncoder
): AuthenticationProvider {
    override fun authenticate(authentication: Authentication): Authentication {
        val name = authentication.name
        val password = authentication.credentials as String
        val user = userDetailsService.loadUserByUsername(name)

        if (!passwordEncoder.matches(password, user.password)) {
            throw BadCredentialsException("Wrong password")
        }

        return UsernamePasswordAuthenticationToken(user, password, user.authorities)
    }

    override fun supports(authentication: Class<*>?): Boolean = true
}
