package com.iprwc2.config;


import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.CorsConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static org.springframework.http.HttpMethod.*;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@EnableMethodSecurity
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthFilter;
    private final AuthenticationProvider authenticationProvider;

    @Bean
    public SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(req ->
                        req.requestMatchers("/api/v1/auth/authenticate", "/api/v1/auth/refresh-token", "/api/v1/auth/register-user")
                                .permitAll()
                                .requestMatchers("/api/v1/auth/register)").denyAll()
                                .requestMatchers(PUT, "/api/v1/category").hasAnyAuthority("ROLE_ADMIN")
                                .requestMatchers(GET, "/api/v1/category").permitAll()
                                .requestMatchers(POST, "/api/v1/category").hasAnyAuthority("ROLE_ADMIN")
                                .requestMatchers(PUT, "/api/v1/category/{id}").hasAnyAuthority("ROLE_ADMIN")
                                .requestMatchers(DELETE, "/api/v1/category/{id}").hasAnyAuthority("ROLE_ADMIN")
                                .requestMatchers(GET, "/api/v1/products").permitAll()
                                .requestMatchers(PUT, "api/v1/products").hasAnyAuthority("ROLE_ADMIN")
                                .requestMatchers(POST, "api/v1/products").hasAnyAuthority("ROLE_ADMIN")
                                .requestMatchers(DELETE, "api/v1/products/{id}").hasAnyAuthority("ROLE_ADMIN")
                                .requestMatchers(PUT, "api/v1/products/{id}").hasAnyAuthority("ROLE_ADMIN")
                                .requestMatchers(GET, "api/v1/products/by-category/{categoryId}").permitAll()
                                .requestMatchers(GET, "/api/v1/order-items/all").hasAnyAuthority("ROLE_ADMIN")
                                .requestMatchers(GET, "/api/v1/order-items{orderItemId}").hasAnyAuthority("ROLE_USER","ROLE_ADMIN")
                                .requestMatchers(GET, "/api/v1/order-items{orderItemId}").hasAnyAuthority("ROLE_USER","ROLE_ADMIN")
                                .requestMatchers(GET, "/api/v1/order-items/new-order-item").hasAnyAuthority("ROLE_USER","ROLE_ADMIN")
                                .requestMatchers(GET, "/api/v1/order-items/by-order/{orderId}").hasAnyAuthority("ROLE_USER","ROLE_ADMIN")
                                .requestMatchers(GET, "/api/v1/orders/all-orders").hasAnyAuthority("ROLE_ADMIN")
                                .requestMatchers(GET, "/api/v1/orders/{orderId}").hasAnyAuthority("ROLE_USER","ROLE_ADMIN")
                                .requestMatchers(PUT, "/api/v1/orders/new-order").hasAnyAuthority("ROLE_USER","ROLE_ADMIN")
                                .requestMatchers(GET, "/api/v1/orders/user/{email}").hasAnyAuthority("ROLE_USER","ROLE_ADMIN")
                                .requestMatchers(GET, "/api/v1/user/*").hasAnyAuthority("ROLE_USER","ROLE_ADMIN")

                                .anyRequest()
                                .authenticated()
                )
                .sessionManagement(session ->
                        session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
                .apply(corsConfigurer());


        return http.build();
    }

    private CorsConfigurer<HttpSecurity> corsConfigurer() {
        return new CorsConfigurer<>();
    }
}

