//package com.melbourne.band.mapper;
//
//import com.melbourne.band.dto.CustomerAuthRequestDto;
//import com.melbourne.band.entity.Customer;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.password.PasswordEncoder;
//
//
//public final class CustomerMapper {
//    @Autowired
//    PasswordEncoder passwordEncoder;
//
//    private CustomerMapper() {
//
//    }
//
//    public static Customer mapToCustomer(CustomerAuthRequestDto customerAuthRequestDto) {
//        Customer customer = new Customer();
//        customer.setEmail(customerAuthRequestDto.getEmail());
//        customer.setPassword_hash(passwordEncoder.encode(customerAuthRequestDto.getPassword()));
//    }
//}
