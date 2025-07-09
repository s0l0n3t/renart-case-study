package com.furkantkgz.renartbackend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                // Require HTTPS for all requests
                .requiresChannel(channel ->
                        channel.anyRequest().requiresSecure())

                // New authorization syntax (replaces authorizeRequests)
                .authorizeHttpRequests(auth -> auth
                                .anyRequest().permitAll()  // Allow all requests
                        // Example of more specific rules:
                        // .requestMatchers("/admin/**").hasRole("ADMIN")
                        // .requestMatchers("/api/**").authenticated()
                );

        return httpSecurity.build();
    }
}