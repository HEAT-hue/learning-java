package com.acme.eazyschool.controller;

import com.acme.eazyschool.model.Address;
import com.acme.eazyschool.model.Person;
import com.acme.eazyschool.model.Profile;
import com.acme.eazyschool.service.PersonService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@Controller("profileControllerBean")
public class ProfileController {

    @Autowired
    PersonService personService;

    @GetMapping("/displayProfile")
    public ModelAndView displayProfile(Model model, HttpSession httpSession) throws Exception {
        Profile profile = new Profile();            // Get POJO object

        Person person = (Person) httpSession.getAttribute("loggedInUser");

        if (null == person) {                       // If person object not found.
            throw new Exception("Http Session not found. Internal error has occurred");
        }

        // Populate information
        profile.setName(person.getName());
        profile.setEmail(person.getEmail());
        profile.setMobileNumber(person.getMobileNumber());

        if (null != person.getAddress()) {      // Check if address exists
            Address personAddress = person.getAddress();
            profile.setAddress1(personAddress.getAddress1());
            profile.setAddress2(personAddress.getAddress2());
            profile.setCity(personAddress.getCity());
            profile.setState(personAddress.getState());
            profile.setZipCode(personAddress.getZipCode());
        }


        // Generate view
        ModelAndView modelAndView = new ModelAndView("profile.html");

        // Add object
        modelAndView.addObject("profile", profile);

        // return view
        return modelAndView;
    }

    @PostMapping("/updateProfile")
    public String updateProfile(@Valid @ModelAttribute("profile") Profile profile, HttpSession httpSession, Errors errors) {
        // Validate for any errors present
        if (errors.hasErrors()) {
            return "profile.html";
        }

        // Retrieve current person
        Person person = (Person) httpSession.getAttribute("loggedInUser");

        // Update person profile
        try {
            Person savedPerson = personService.updatePerson(person, profile);
            if (null == savedPerson) {
                throw new Exception("Error updating profile");
            }
            httpSession.setAttribute("loggedInUser", savedPerson);
        } catch (Exception e) {
            log.error("An exception updating user has occurred {}", e.getMessage());
            handleException(e);
        }

        return "redirect:/dashboard";
    }


    // Handle Exceptions that occur in this controller
    @ExceptionHandler
    public ModelAndView handleException(Exception exception) {

        // Create a new view
        ModelAndView errorPage = new ModelAndView();

        // Get exception message
        final String errorMessage = "Public controller Error: " + exception.getMessage();

        // Fetch html page
        errorPage.setViewName("error");

        // Add error message to view
        errorPage.addObject("errorMsg", errorMessage);

        // Log error message to console
        log.error(errorMessage);

        return errorPage;
    }
}