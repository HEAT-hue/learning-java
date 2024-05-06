package com.acme.eazyschool.service;

import com.acme.eazyschool.constants.EazySchoolConstants;
import com.acme.eazyschool.model.Contact;
import com.acme.eazyschool.repository.ContactRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.RequestScope;

import java.util.List;
import java.util.Optional;

@Slf4j      // Logging
@Service    // Generate a bean tho just a stereotype for Holding business logic
@RequestScope  // Initialized for every http request
public class ContactService {

    // Dependency Injection - DB operations
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

        // Save contact
        Contact savedContact = contactRepository.save(contact);

        if (null != savedContact && savedContact.getContactId() > 0) {
            isSaved = true;
        }

        return isSaved;
    }

    //    Fetch all messages with open status
    public List<Contact> findMsgsWithOpenStatus() {
        List<Contact> contactMsgs = contactRepository.findByStatus(EazySchoolConstants.OPEN);
        return contactMsgs;
    }

    //  Update status of message in DB
    public boolean updateMsgStatus(int contactId, String status) {
        boolean isUpdated = false;

        // Fetch current contact
        Optional<Contact> contact = contactRepository.findById(contactId);

        // Update contact if present
        contact.ifPresent(contact1 -> {
            contact1.setStatus(status);
        });

        // Save contact

        Contact savedContact = contactRepository.save(contact.get());

        if (null != savedContact && null != savedContact.getUpdatedBy()) {
            isUpdated = true;
        }

        return isUpdated;
    }
}