package com.melbourne.band.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.AuthorizeHttpRequestsConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
@Configuration
public class SecurityConfiguration {

    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                // Disable csrf tokens
                .csrf(AbstractHttpConfigurer::disable)

                // Matchers
                .authorizeHttpRequests((requests) -> {
                    ((AuthorizeHttpRequestsConfigurer.AuthorizedUrl)
                            requests
//                                    .anyRequest()).permitAll();
                                    .requestMatchers("/notices", "/contact", "/auth/**").permitAll()
                                    .requestMatchers("/account", "/balance", "/loans", "/cards").authenticated()
                                    .anyRequest()).authenticated();
                })
                .formLogin(Customizer.withDefaults());
//                .httpBasic(Customizer.withDefaults());
        return (SecurityFilterChain) httpSecurity.build();
    }
}
