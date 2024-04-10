package com.acme.eazyschool.service;

import com.acme.eazyschool.model.Contact;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j      // Logging
@Service    // Generate a bean tho just a stereotype for Holding business logic
public class ContactService {

    public boolean saveMessageDetails(Contact contact) {
        boolean isSaved = false;

        // TODO: Need to persist data into db table
        log.info(contact.toString());
        return isSaved;
    }
}