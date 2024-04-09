package com.acme.eazyschool.controller;

import com.acme.eazyschool.model.Contact;
import com.acme.eazyschool.service.ContactService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ContactController {

    private static Logger log = LoggerFactory.getLogger(ContactController.class);

    private final ContactService contactService;

    @Autowired
    public ContactController(ContactService contactService) {
        this.contactService = contactService;
    }

    @GetMapping("/contact")
    public String displayContactPage() {
        return "contact.html";
    }

    @PostMapping("/saveMsg")
    public ModelAndView saveMessage(Contact contact) {
        // save message details
        contactService.saveMessageDetails(contact);

        // Redirect user to contact
        // User gets new value due to no information set
        return new ModelAndView("redirect:/contact");
    }

//    @PostMapping("/saveMsg")
//    public ModelAndView saveMessage(@RequestParam String name, @RequestParam String mobileNum,
//                                    @RequestParam String email, @RequestParam String subject, @RequestParam String message) {
//        log.info("Name: " + name);
//        log.info("Mobile number: " + mobileNum);
//        log.info("Email address: " + email);
//        log.info("Subject: " + subject);
//        log.info("Message: " + message);
//
//        // Redirect user to contact
//        // User gets new value due to no information set
//        return new ModelAndView("redirect:/contact");
//    }

}