package com.acme.eazyschool.controller;

import com.acme.eazyschool.model.Address;
import com.acme.eazyschool.model.Person;
import com.acme.eazyschool.model.Profile;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@Controller
public class ProfileController {

    @GetMapping("/displayProfile")
    public ModelAndView displayProfile(Model model, HttpSession httpSession) throws Exception {
        Profile profile = new Profile();            // Get POJO object
        Person person = (Person) httpSession.getAttribute("loggedInUser");
        if (null == person) {                       // If person object not found.
            throw new Exception("Http Session not found. Internal error has occurred");
        }
        profile.setName(person.getName());          // Populate information
        profile.setEmail(person.getEmail());
        profile.setMobileNumber(person.getMobileNumber());
        if (null != person.getAddress() && Integer.parseInt(person.getAddress().getAddressId()) > 1) {      // Check if address exists
            Address personAddress = person.getAddress();
            profile.setAddress1(personAddress.getAddress1());
            profile.setAddress2(personAddress.getAddress2());
            profile.setCity(personAddress.getCity());
            profile.setState(personAddress.getState());
            profile.setZipCode(personAddress.getZipCode());
        }
        System.out.println(profile.toString());
        // Generate view
        ModelAndView modelAndView = new ModelAndView("profile.html");
        // Add object
        modelAndView.addObject("profile", profile);
        // return view
        return modelAndView;
    }

    // Handle Exceptions that occur in this controller
    @ExceptionHandler
    public ModelAndView handleException(Exception exception) {

        // Create a new view
        var errorPage = new ModelAndView();

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