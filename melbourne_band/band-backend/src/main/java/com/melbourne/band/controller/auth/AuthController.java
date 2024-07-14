package com.melbourne.band.controller.auth;

import com.melbourne.band.dto.CustomerAuthRequestDto;
import com.melbourne.band.entity.Customer;
import com.melbourne.band.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final CustomerRepository customerRepository;
    private final PasswordEncoder passwordEncoder;

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody CustomerAuthRequestDto customerAuthRequestDto) {
        Customer customer = new Customer();
        customer.setEmail(customerAuthRequestDto.getEmail());
        customer.setPassword_hash(passwordEncoder.encode(customerAuthRequestDto.getPassword()));
        customer.setRole("USER");
        try {
            customerRepository.save(customer);
            return ResponseEntity.status(HttpStatus.CREATED).body("Successful register");
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new RuntimeException("Unable to save User " + e.getMessage());
        }
    }
}