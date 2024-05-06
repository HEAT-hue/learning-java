package com.acme.eazyschool.controller;

import com.acme.eazyschool.model.Person;
import com.acme.eazyschool.service.PersonService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequestMapping(value = "public")
public class PublicController {

    // DI - Person service to carry our business logic related to person
    private final PersonService personService;

    public PublicController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping("/register")
    public String displayRegistrationPage(Model model) {
        // Get person object
        var person = new Person();

        // Send empty pojo object to capture user details
        model.addAttribute("person", person);

        // return view to user
        return "register.html";
    }

    @PostMapping("/createUser")
    public String createNewUser(@Valid @ModelAttribute("person") Person person, Errors error) {
        if (error.hasErrors()) {
            return "register.html";
        }

        // NO errors found in creating person
        boolean createPersonResult = personService.createNewPerson(person);

        if (createPersonResult) {
            return "redirect:login?register=true";
        }

        // return failed registration link
        return "redirect:login?register=false";
    }
}