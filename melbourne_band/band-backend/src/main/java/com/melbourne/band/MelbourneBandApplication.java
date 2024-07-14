package com.melbourne.band;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories("com.melbourne.band.repository")
@EntityScan("com.melbourne.band.entity")
public class MelbourneBandApplication {
    public static void main(String[] args) {
        SpringApplication.run(MelbourneBandApplication.class, args);
    }
}