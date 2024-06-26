package com.acme.eazyschool.repository;

import com.acme.eazyschool.model.Courses;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

// Define custom path name exposed by spring data rest
@RepositoryRestResource(path = "courses")
public interface CoursesRepository extends JpaRepository<Courses, Integer> {
    // Find and sort courses by name
    List<Courses> findByOrderByNameDesc();

    // Asc keyword is optional as results are sorted in ascending order by default
    List<Courses> findByOrderByName();
}




