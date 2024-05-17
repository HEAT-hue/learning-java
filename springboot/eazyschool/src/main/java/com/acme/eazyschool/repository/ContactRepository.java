package com.acme.eazyschool.repository;

import com.acme.eazyschool.model.Contact;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/*
@Repository stereotype annotation is used to add a bean of this class
type to the Spring context and indicate that given Bean is used to perform
DB related operations and
* */
@Repository
public interface ContactRepository extends JpaRepository<Contact, Integer> {

    // Derived custom classes
    List<Contact> findByStatus(String status);

    // Pageable query
    Page<Contact> findByStatus(String status, Pageable pageable);
}