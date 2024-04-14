package com.acme.eazyschool.service;

import com.acme.eazyschool.model.Contact;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.RequestScope;

@Slf4j      // Logging
@Service    // Generate a bean tho just a stereotype for Holding business logic
@RequestScope  // Initialized for every htp request
public class ContactService {

    @Getter
    @Setter
    private int counter = 0;

    public ContactService() {
        System.out.println("Counter service has been initialized!");
    }

    public boolean saveMessageDetails(Contact contact) {
        boolean isSaved = false;

        // TODO: Need to persist data into db table
        log.info(contact.toString());
        return isSaved;
    }
}