package com.acme.eazyschool.controller;

import com.acme.eazyschool.model.Person;
import com.acme.eazyschool.repository.PersonRepository;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/*
 * authentication object contains info about the currently logged-in user
 * */
@Slf4j          // Logging
@Controller     // Controller for returning views
public class DashboardController {

    @Autowired
    PersonRepository personRepository;

    @GetMapping("/dashboard")
    public String displayDashboard(Model model, Authentication authentication, HttpSession httpSession) {
        // Get Person
        Person person = personRepository.findByEmail(authentication.getName());

        // display enrolled class
        if (null != person.getEazyClass() && null != person.getEazyClass().getName()) {
            model.addAttribute("enrolledClass", person.getEazyClass().getName());
        }

        // Save person object in session
        httpSession.setAttribute("loggedInUser", person);

        // Set model
        model.addAttribute("username", person.getName());
        model.addAttribute("roles", authentication.getAuthorities().toString());
        return "dashboard.html";
    }
}