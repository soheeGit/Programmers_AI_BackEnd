package org.example.bootjwtkakao.auth;

import lombok.RequiredArgsConstructor;
import org.example.bootjwtkakao.service.CustomOAuth2UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;

import java.util.Collections;

@Configuration
@RequiredArgsConstructor
@EnableWebSecurity
public class SecurityConfig {
    private final JwtTokenProvider jwtTokenProvider;
    private final CustomOAuth2UserService customOAuth2UserService;
    private final CustomOAuth2UserService.OAuth2LoginSuccessHandler successHandler;
    private final UserDetailsService userDetailsService;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                // .csrf
                .csrf(AbstractHttpConfigurer::disable) // POST, PUT...
                // .cors
                .cors(cors -> cors.configurationSource(
                        req -> {
                            CorsConfiguration config = new CorsConfiguration();
                            config.setAllowedOrigins(Collections.singletonList("*"));
                            config.setAllowedMethods(Collections.singletonList("*"));
                            config.setAllowedHeaders(Collections.singletonList("*"));
                            config.setAllowCredentials(false);
                            return config;
                        }
                ))
                .sessionManagement(sm -> sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) // 상태 해제
                .authorizeHttpRequests(
                        auth ->
                                auth
                                        .requestMatchers("/api/data/**")
                                        .authenticated()
                                        .anyRequest().permitAll()
                )
                .oauth2Login(oauth -> oauth
//                        .loginPage("/login/oauth2") // 이건 커스텀으로 할 때...
                                .userInfoEndpoint(user -> user.userService(customOAuth2UserService))
                                .successHandler(successHandler)
                )
                .authenticationProvider(daoAuthProvider())
                .addFilterBefore(jwtFilter(),
                        UsernamePasswordAuthenticationFilter.class
                );
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider daoAuthProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    @Bean
    public JwtAuthenticationFilter jwtFilter() {
        return new JwtAuthenticationFilter(jwtTokenProvider);
    }

    @Bean
    public AuthenticationManager authenticationManager() {
        return new ProviderManager(Collections.singletonList(daoAuthProvider()));
    }
}