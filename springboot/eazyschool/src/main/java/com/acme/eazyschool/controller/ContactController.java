package com.acme.eazyschool.controller;

import com.acme.eazyschool.constants.EazySchoolConstants;
import com.acme.eazyschool.model.Contact;
import com.acme.eazyschool.service.ContactService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Slf4j      // Logging
@Controller
public class ContactController {

    // handle business login related to contact
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

        // Redirect user to contact
        // User gets new value due to no information set
        contactService.saveMessageDetails(contact);
        return "redirect:/contact";
    }

    @GetMapping("/displayMessages")
    public ModelAndView displayMessages() {
        // Fetch messages
        List<Contact> contactMsgs = contactService.findMsgsWithOpenStatus();

        // Create a model view and return page
        var modelAndView = new ModelAndView("messages.html");

        modelAndView.addObject("contactMsgs", contactMsgs);
        return modelAndView;
    }

    @GetMapping("/closeMsg")
    public String closeMessage(@RequestParam int id, Authentication authentication) {
        // Get auth details
        String name = authentication.getName();

        // Update message status in DB
        var messageUpdated = contactService.updateMsgStatus(id, EazySchoolConstants.CLOSE, name);

        if (messageUpdated) {
            // Redirect to display messages
            return "redirect:/displayMessages";
        }

        throw new Error("Error updating message status");
    }

//
//    @ExceptionHandler
//    public ModelAndView handleException(Exception exception) {
//        // Create new view
//        var errorPage = new ModelAndView();
//
//        // Extract exception message
//        final String errorMessage = exception.getMessage();
//
//        // Fetch html page
//        errorPage.setViewName("displayMessages");
//
//        // Add error message to view
//        errorPage.addObject("errorMsg", errorMessage);
//
//        return errorPage;
//    }
}