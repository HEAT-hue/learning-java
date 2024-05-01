package com.acme.eazyschool.config;

import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
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
                .csrf(csrf -> csrf
                        .ignoringRequestMatchers("/saveMsg")
                        .ignoringRequestMatchers(PathRequest.toH2Console()))

                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(mvcMatcherBuilder.pattern("/dashboard")).authenticated()
                        .requestMatchers(mvcMatcherBuilder.pattern("/displayMessages")).hasRole("ADMIN")
                        .requestMatchers(mvcMatcherBuilder.pattern("/closeMsg/**")).hasRole("ADMIN")
                        .requestMatchers(mvcMatcherBuilder.pattern("/")).permitAll()
                        .requestMatchers(PathRequest.toH2Console()).permitAll()
                        .requestMatchers(mvcMatcherBuilder.pattern("/home")).permitAll()
                        .requestMatchers(mvcMatcherBuilder.pattern("/holidays/**")).permitAll()
                        .requestMatchers(mvcMatcherBuilder.pattern("/contact")).permitAll()
                        .requestMatchers(mvcMatcherBuilder.pattern("/saveMsg")).permitAll()
                        .requestMatchers(mvcMatcherBuilder.pattern("/courses")).permitAll()
                        .requestMatchers(mvcMatcherBuilder.pattern("/about")).permitAll()
                        .requestMatchers(mvcMatcherBuilder.pattern("/logout")).permitAll()
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

                // Disable frames
                .headers(header ->
                        header.frameOptions(HeadersConfigurer.FrameOptionsConfig::disable))

                .httpBasic(Customizer.withDefaults());

        return http.build();
    }

    // Create in memory user details manager
    @Bean
    public InMemoryUserDetailsManager userDetailsManager() {
        UserDetails user = User
                .withDefaultPasswordEncoder()
                .username("user")
                .password("12345")
                .roles("USER").build();

        UserDetails admin = User
                .withDefaultPasswordEncoder()
                .username("admin")
                .password("12345")
                .roles("ADMIN").build();

        return new InMemoryUserDetailsManager(user, admin);
    }
}