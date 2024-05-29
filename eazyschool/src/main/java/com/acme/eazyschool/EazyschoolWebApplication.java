package com.acme.eazyschool;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication                                          // Spring boot configuration
@EnableJpaRepositories("com.acme.eazyschool.repository")        // Spring data JPA config
@EntityScan("com.acme.eazyschool.model")                        // Set path to entity classes
@EnableJpaAuditing(auditorAwareRef = "auditAwareImpl")          // Enable Auditing
public class EazyschoolWebApplication {
    public static void main(String[] args) {
        SpringApplication.run(EazyschoolWebApplication.class, args);
    }
}