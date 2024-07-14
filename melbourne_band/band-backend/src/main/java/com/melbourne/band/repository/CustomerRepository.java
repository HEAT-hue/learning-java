package com.melbourne.band.repository;

import com.melbourne.band.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {
    // Find customer by email - derived jpa method
    Optional<Customer> findByEmail(String email);
}
