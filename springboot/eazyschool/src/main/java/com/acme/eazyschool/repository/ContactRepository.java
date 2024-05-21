package com.acme.eazyschool.repository;

import com.acme.eazyschool.model.Contact;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/*
@Repository stereotype annotation is used to add a bean of this class
type to the Spring context and indicate that given Bean is used to perform
DB related operations and
* */
@Repository
public interface ContactRepository extends JpaRepository<Contact, Integer> {

    // Find messages by status
    List<Contact> findByStatus(String status);

    // Pageable customized query
    @Query("SELECT c FROM Contact c WHERE c.status = :status")
    Page<Contact> findByStatusWithQuery(@Param("status") String status, Pageable pageable);

    // @Transactional: If query fails, roll back all updates made
    // @Modifying: To indicate changes being made
    @Transactional
    @Modifying
    @Query("UPDATE Contact c SET c.status = :status where c.id = :id")
    int updateMsgStatus(@Param("status") String status, @Param("id") int id);
}