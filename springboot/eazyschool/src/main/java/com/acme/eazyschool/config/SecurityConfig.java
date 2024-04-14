
package com.acme.eazyschool.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
        // Permit all requests inside the web application
        // Breakdown endpoints for ease access
        http
                // Disable csrf i.e. enabled by default
                .csrf(csrf -> csrf.disable())

                .authorizeHttpRequests(request -> request
                        .requestMatchers("/", "/home").permitAll()
                        .requestMatchers("/dashboard").authenticated()
                        .requestMatchers("/holidays/**").permitAll()
                        .requestMatchers("/contact").permitAll()
                        .requestMatchers("/saveMsg").permitAll()
                        .requestMatchers("/courses").permitAll()
                        .requestMatchers("/about").permitAll()
                        .requestMatchers("/assets/**").permitAll())

                // Customize form login behaviour
                .formLogin(loginConfigurer -> loginConfigurer
                        .loginPage("/login")
                        .defaultSuccessUrl("/dashboard")
                        .failureUrl("/login?error=true")
                        // Permit all is added for user to be able to access protected resource
                        .permitAll())

                // Customize form logout behaviour
                .logout(logoutConfigurer -> logoutConfigurer
                        // Logout url
                        .logoutSuccessUrl("/login?logout=true")

                        // Invalidate http session
                        .invalidateHttpSession(true)

                        // Allow visibility of logout endpoints
                        .permitAll()
                )

                .httpBasic(Customizer.withDefaults());

        return http.build();
    }

    // Create in memory user details manager
    @Bean
    public InMemoryUserDetailsManager userDetailsManager() {
        UserDetails user = User.withDefaultPasswordEncoder()
                .username("user")
                .password("12345")
                .roles("USER")
                .build();

        UserDetails admin = User.withDefaultPasswordEncoder()
                .username("admin")
                .password("12345")
                .roles("ADMIN")
                .build();

        return new InMemoryUserDetailsManager(user, admin);
    }
}