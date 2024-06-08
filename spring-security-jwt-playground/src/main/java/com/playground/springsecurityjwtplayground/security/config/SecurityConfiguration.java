package com.playground.springsecurityjwtplayground.security.config;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.playground.springsecurityjwtplayground.security.filter.JwtAuthenticationFilter;
import com.playground.springsecurityjwtplayground.security.handler.JwtAuthenticafionEntryPoint;
import com.playground.springsecurityjwtplayground.security.handler.LoginFailureHandler;
import com.playground.springsecurityjwtplayground.security.handler.LoginSuccessHandler;
import com.playground.springsecurityjwtplayground.security.support.JwtManager;
import com.playground.springsecurityjwtplayground.token.service.TokenService;

import lombok.RequiredArgsConstructor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutFilter;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

@EnableWebSecurity(debug = false)
@EnableMethodSecurity(securedEnabled = true)
@Configuration
@RequiredArgsConstructor
public class SecurityConfiguration {

    private final ObjectMapper objectMapper;
    private final TokenService tokenService;
    private final JwtManager jwtManager;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .httpBasic(AbstractHttpConfigurer::disable)
                .csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )
                .exceptionHandling(ex -> ex
                        .authenticationEntryPoint(authenticationEntryPoint())
                )
                .formLogin(login -> login
                        .loginProcessingUrl("/api/auth/login")
                        .usernameParameter("username")
                        .passwordParameter("password")
                        .successHandler(loginSuccessHandler())
                        .failureHandler(loginFailureHandler())
                        .permitAll()
                )
                .logout(logout -> logout
                        .logoutUrl("/api/auth/logout")
                        .logoutSuccessHandler(logoutSuccessHandler())
                        .clearAuthentication(true)
                        .permitAll(false)
                )
                .authorizeHttpRequests(req -> req
                        .requestMatchers(HttpMethod.GET,
                                "/api/members/me").hasRole("MEMBER")
                        .requestMatchers(HttpMethod.POST,
                                "/api/members/signup",
                                "/api/token/reissue").permitAll()
                        .anyRequest().denyAll()
                )
                .addFilterBefore(jwtAuthenticationFilter(), LogoutFilter.class);
        return httpSecurity.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public JwtAuthenticationFilter jwtAuthenticationFilter() {
        return new JwtAuthenticationFilter(authenticationEntryPoint(), jwtManager);
    }

    @Bean
    public AuthenticationSuccessHandler loginSuccessHandler() {
        return new LoginSuccessHandler(objectMapper, tokenService, jwtManager);
    }

    @Bean
    public AuthenticationFailureHandler loginFailureHandler() {
        return new LoginFailureHandler(tokenService);
    }

    @Bean
    public LogoutSuccessHandler logoutSuccessHandler() {
        return (request, response, authentication) -> {
            Long memberId = (Long) authentication.getPrincipal();
            tokenService.tokenDelete(memberId);
        };
    }

    @Bean
    public AuthenticationEntryPoint authenticationEntryPoint() {
        return new JwtAuthenticafionEntryPoint();
    }

}
