package com.acme.eazyschool.repository;

import com.acme.eazyschool.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends JpaRepository<Person, Integer> {
    // Add derived classes here

    // Retrieve person object using email as parameter
    Person findByEmail(String email);
}
