package com.acme.eazyschool.service;

import com.acme.eazyschool.constants.EazySchoolConstants;
import com.acme.eazyschool.model.Contact;
import com.acme.eazyschool.repository.ContactRepository;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.RequestScope;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j      // Logging
@Service    // Generate a bean tho just a stereotype for Holding business logic
@RequestScope  // Initialized for every http request
public class ContactService {

    // DB operations
    private final ContactRepository contactRepository;

    @Autowired
    public ContactService(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }

    //    Save message
    public boolean saveMessageDetails(Contact contact) {
        boolean isSaved = false;

        // Prepare contact object
        contact.setStatus(EazySchoolConstants.OPEN);
        contact.setCreatedBy(EazySchoolConstants.ANONYMOUS);
        contact.setCreatedAt(LocalDateTime.now());

        // Save contact
        int result = contactRepository.saveContactMsg(contact);

        if (result > 0) {
            isSaved = true;
        }

        return isSaved;
    }

    //    Fetch all messages with open status
    public List<Contact> findMsgsWithOpenStatus() {
        List<Contact> contactMsgs = contactRepository.findMsgWithStatus(EazySchoolConstants.OPEN);
        return contactMsgs;
    }

    //  Update status of message in DB
    public boolean updateMsgStatus(int id, String status, String updatedBy) {
        boolean isUpdated = false;

        var queryResult = contactRepository.updateMsgStatus(id, status, updatedBy);

        if (queryResult > 0) {
            isUpdated = true;
        }

        return isUpdated;
    }
}