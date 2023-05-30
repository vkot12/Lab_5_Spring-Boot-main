package com.example.lab5.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.AuthenticationProvider
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.provisioning.InMemoryUserDetailsManager
import org.springframework.security.web.SecurityFilterChain
import com.example.lab5.security.AuthProvider


@Configuration
@EnableWebSecurity
@EnableMethodSecurity(
    prePostEnabled = true,
    securedEnabled = true,
    jsr250Enabled = true
)

class SecurityConfig {

    @Bean
    fun securityFilterChain(http: HttpSecurity): SecurityFilterChain =
        http
            .authorizeHttpRequests { authorize ->
                authorize
                    .requestMatchers("/signup").permitAll()
                    .requestMatchers("/admin/**").hasRole("EDITOR")
                    .anyRequest().hasAnyRole("EDITOR", "VIEWER")
            }
            .httpBasic()
            .and()
            .csrf().disable()
            .build()

    @Bean
    fun userDetailsService(): UserDetailsService {
        val userDetailsService = InMemoryUserDetailsManager()

        val editorUser = User.withUsername("editor")
            .password("1234")
            .authorities("EDITOR")
            .build()

        val viewerUser = User.withUsername("viewer")
            .password("12345")
            .authorities("VIEWER")
            .build()

        userDetailsService.createUser(editorUser)
        userDetailsService.createUser(viewerUser)

        return userDetailsService
    }

    @Bean
    fun passwordEncoder(): PasswordEncoder = BCryptPasswordEncoder(12)

    @Bean
    fun authProvider(userService: UserDetailsService, passwordEncoder: PasswordEncoder): AuthenticationProvider {
        AuthProvider(userService, passwordEncoder)

        return authProvider (userService, passwordEncoder)
    }


    @Bean
    fun authManager(http: HttpSecurity, authenticationProvider: AuthenticationProvider): AuthenticationManager =
        http.getSharedObject(AuthenticationManagerBuilder::class.java).also {
            it.authenticationProvider(authenticationProvider)
        }.build()

    }
