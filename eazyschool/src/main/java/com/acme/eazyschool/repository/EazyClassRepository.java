package com.acme.eazyschool.repository;

import com.acme.eazyschool.model.EazyClass;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EazyClassRepository extends JpaRepository<EazyClass, Integer> {
}
