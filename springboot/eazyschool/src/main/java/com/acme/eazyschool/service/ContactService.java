package com.acme.eazyschool.service;

import com.acme.eazyschool.model.Contact;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;


// @Service is used to handle business logic
@Service
public class ContactService {

    private static Logger log = LoggerFactory.getLogger(ContactService.class);

    public boolean saveMessageDetails(Contact contact) {
        boolean isSaved = false;

        // TODO: Need to persist data into db table
        log.info(contact.toString());
        return isSaved;
    }

}
