package com.acme.eazyschool.service;

import com.acme.eazyschool.model.Person;
import com.acme.eazyschool.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonService {

    //  DI - Person related DB operations
    @Autowired
    PersonRepository personRepository;

    //  Create new person and save to the DB
    public boolean createNewPerson(Person person) {
        boolean isSaved = false;

        System.out.println(person.toString());

        // Save person object to DB
        person = personRepository.save(person);

        // Check if person was saved
        if (null != person && person.getPersonId() > 0) {
            isSaved = true;
        }

        return isSaved;
    }
}
