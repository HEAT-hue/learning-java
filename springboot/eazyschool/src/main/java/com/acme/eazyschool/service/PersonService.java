package com.acme.eazyschool.service;

import com.acme.eazyschool.constants.EazySchoolConstants;
import com.acme.eazyschool.model.Person;
import com.acme.eazyschool.model.Roles;
import com.acme.eazyschool.repository.PersonRepository;
import com.acme.eazyschool.repository.RolesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class PersonService {

    //  DI - Person related DB operations
    @Autowired
    PersonRepository personRepository;

    @Autowired
    RolesRepository rolesRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    //  Create new person and save to the DB
    public boolean createNewPerson(Person person) {
        boolean isSaved = false;

        // Get role
        Roles role = rolesRepository.getByRoleName(EazySchoolConstants.STUDENT_ROLE);

        // Set person role
        person.setRole(role);

        // Encode password
        person.setPassword(passwordEncoder.encode(person.getPassword()));

        // Save person object to DB
        person = personRepository.save(person);

        // Check if person was saved
        if (null != person && person.getPersonId() > 0) {
            isSaved = true;
        }

        return isSaved;
    }
}
