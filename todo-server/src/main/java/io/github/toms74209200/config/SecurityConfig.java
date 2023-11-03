package io.github.toms74209200.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.web.SecurityFilterChain;

@RequiredArgsConstructor
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final JwtDecoder jwtDecoder;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .oauth2ResourceServer(
                        oauth2 -> oauth2.jwt(jwtConfigurer -> jwtConfigurer.decoder(jwtDecoder)))
                .authorizeHttpRequests(
                        auth ->
                                auth.requestMatchers("/users", "/auth")
                                        .permitAll()
                                        .requestMatchers("/tasks/**")
                                        .authenticated());
        httpSecurity.cors();
        httpSecurity.csrf().disable();
        return httpSecurity.build();
    }
}
