package com.acme.eazyschool.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.servlet.util.matcher.MvcRequestMatcher;
import org.springframework.web.servlet.handler.HandlerMappingIntrospector;

@Configuration
public class SecurityConfig {

    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http, HandlerMappingIntrospector introspector) throws Exception {
        // Get the mvc request matcher

        // This allows Spring security pattern matching to be aligned with that of spring MVC for a given request
        MvcRequestMatcher.Builder mvcMatcherBuilder = new MvcRequestMatcher.Builder(introspector);

        // Permit all requests inside the web application
        http
                // Disable csrf i.e. enabled by default
                // .csrf(csrf -> csrf.disable())

                // Disable csrf for this endpoint
                .csrf(csrf -> csrf.ignoringRequestMatchers("/saveMsg").ignoringRequestMatchers("/public/**"))

                .authorizeHttpRequests(auth ->
                        auth.requestMatchers(mvcMatcherBuilder.pattern("/dashboard"))
                                .authenticated()
                                .requestMatchers(mvcMatcherBuilder.pattern("/displayMessages")).hasRole("ADMIN")
                                .requestMatchers(mvcMatcherBuilder.pattern("/closeMsg/**")).hasRole("ADMIN")
                                .requestMatchers(mvcMatcherBuilder.pattern("/admin/**")).hasRole("ADMIN")
                                .requestMatchers(mvcMatcherBuilder.pattern("/student/**")).hasRole("STUDENT")
                                .requestMatchers(mvcMatcherBuilder.pattern("/displayProfile")).authenticated()
                                .requestMatchers(mvcMatcherBuilder.pattern("/updateProfile")).authenticated()
                                .requestMatchers(mvcMatcherBuilder.pattern("/")).permitAll()
                                .requestMatchers(mvcMatcherBuilder.pattern("/home")).permitAll()
                                .requestMatchers(mvcMatcherBuilder.pattern("/holidays/**")).permitAll()
                                .requestMatchers(mvcMatcherBuilder.pattern("/contact")).permitAll()
                                .requestMatchers(mvcMatcherBuilder.pattern("/saveMsg")).permitAll()
                                .requestMatchers(mvcMatcherBuilder.pattern("/courses")).permitAll()
                                .requestMatchers(mvcMatcherBuilder.pattern("/about")).permitAll()
                                .requestMatchers(mvcMatcherBuilder.pattern("/login")).permitAll()
                                .requestMatchers(mvcMatcherBuilder.pattern("/logout")).permitAll()
                                .requestMatchers(mvcMatcherBuilder.pattern("/public/**")).permitAll()
                                .requestMatchers(mvcMatcherBuilder.pattern("/assets/**")).permitAll())

                // Customize form login behaviour
                .formLogin(loginConfigurer -> loginConfigurer
                        .loginPage("/login")
                        .defaultSuccessUrl("/dashboard")
                        .failureUrl("/login?error=true")

                        // Allow visibility of login  endpoints.
                        .permitAll())

                // Customize form logout behaviour implemented in controller class
                .logout(logoutConfigurer -> logoutConfigurer
                        // Logout url user is directed to
                        .logoutSuccessUrl("/login?logout=true")

                        // Invalidate http session
                        .invalidateHttpSession(true)

                        // Allow visibility of logout endpoints
                        .permitAll())

                .httpBasic(Customizer.withDefaults());

        return http.build();
    }

    // Generate a hash value that will be generated when user is registering or logging in.
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}