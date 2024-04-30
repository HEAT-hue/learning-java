package com.acme.eazyschool.controller;

import com.acme.eazyschool.model.Contact;
import com.acme.eazyschool.service.ContactService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@Slf4j      // Logging
@Controller
public class ContactController {

    // DI object - immutable
    private final ContactService contactService;

    @Autowired
    public ContactController(ContactService contactService) {
        this.contactService = contactService;
    }

    @GetMapping("/contact")
    public String displayContactPage(Model model) {
        // Send new contact object for processing and validation of contact information
        model.addAttribute("contact", new Contact());
        return "contact.html";
    }

    /*
     * @ModelAttribute binds the contact model object to the contact (param) - required for validation using @Valid
     * Any errors discovered is sent to the error object
     * */
    @PostMapping("/saveMsg")
    public String saveMessage(@Valid @ModelAttribute("contact") Contact contact, Errors error) {

        // Check if any error exist
        if (error.hasErrors()) {
            log.error("Contact form validation failed due to: {}", error.toString());
            return "contact.html";
        }
        // save message details - handling business logic
        contactService.saveMessageDetails(contact);

        // Redirect user to contact
        // User gets new value due to no information set
        return "redirect:/contact";
    }
}