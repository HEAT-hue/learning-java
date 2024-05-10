package com.acme.eazyschool.repository;

import com.acme.eazyschool.model.Person;
import com.acme.eazyschool.model.Roles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/*
 * Extending JPA repository makes all methods available
 * */
@Repository
public interface RolesRepository extends JpaRepository<Roles, Integer> {

    // Add derived classes here
    Roles getByRoleName(String role);
}
