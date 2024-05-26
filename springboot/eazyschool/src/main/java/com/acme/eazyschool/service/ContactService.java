package com.acme.eazyschool.service;

import com.acme.eazyschool.config.EazySchoolProps;
import com.acme.eazyschool.constants.EazySchoolConstants;
import com.acme.eazyschool.model.Contact;
import com.acme.eazyschool.repository.ContactRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.RequestScope;

@Slf4j      // Logging
@Service    // Generate a bean tho just a stereotype for Holding business logic
@RequestScope  // Initialized for every http request
public class ContactService {

    // Dependency Injection - DB operations
    private final ContactRepository contactRepository;

    // Get PAGE_SIZE from environment
//    @Autowired
//    Environment environment;

    @Autowired
    EazySchoolProps eazySchoolProps;

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

    // Find all messages with open status with pagination
    public Page<Contact> findMsgsWithOpenStatus(int pageNum, String sortField, String sortDir) {

        // Get default page size from environment, defaulting to 5 if not found
        //  final short PAGE_SIZE = Short.parseShort(environment.getProperty("eazyschool.pageSize", "5"));

        final short PAGE_SIZE = eazySchoolProps.getPageSize();

        System.out.println(PAGE_SIZE);

        // Build sort object
        Sort sort = sortDir.equalsIgnoreCase("desc") ? Sort.by(sortField).descending() : Sort.by(sortField).ascending();

        // Specify how to receive a subset of data from a larger dataset - index starts a 0
        Pageable pageable = PageRequest.of(pageNum - 1, PAGE_SIZE, sort);

        // Represented paginated data returned from a query | Allow navigation of result query
        Page<Contact> messagesPage = contactRepository.findByStatusWithQuery(EazySchoolConstants.OPEN, pageable);

        return messagesPage;
    }

    //  Update status of message in DB
    public boolean updateMsgStatus(int contactId, String status) {
        boolean isUpdated = false;

        // Update STATUS
        int rowsAffected = contactRepository.updateMsgStatus(status, contactId);

        // Any changes made is an update
        if (rowsAffected > 0) {
            isUpdated = true;
        }

        return isUpdated;
    }
}