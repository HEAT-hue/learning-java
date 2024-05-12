package com.acme.eazyschool.service;

import com.acme.eazyschool.constants.EazySchoolConstants;
import com.acme.eazyschool.model.Address;
import com.acme.eazyschool.model.Person;
import com.acme.eazyschool.model.Profile;
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

    public Person updatePerson(Person person, Profile profile) throws Exception {

        if (null == person) {
            return null;
        }

        // Populate Address field
        Address address = new Address();
        address.setAddress1(profile.getAddress1());
        address.setAddress2(profile.getAddress2());
        address.setCity(profile.getCity());
        address.setState(profile.getState());
        address.setZipCode(profile.getZipCode());

        // retrieve current object
        person.setName(profile.getName());
        person.setMobileNumber(profile.getMobileNumber());
        person.setEmail(profile.getEmail());
        person.setAddress(address);

        Person savedPerson = personRepository.save(person);

        if (null == savedPerson) {
            throw new Exception("Error saving person");
        }
        return savedPerson;
    }
}
