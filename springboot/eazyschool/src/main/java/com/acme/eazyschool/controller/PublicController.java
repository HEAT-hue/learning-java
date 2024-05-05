package com.acme.eazyschool.controller;

import com.acme.eazyschool.model.Person;
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

    @GetMapping("/register")
    public String displayRegistrationPage(Model model) {
        // Get person object
        var person = new Person();
        // Send empty pojo object to capture user details
        model.addAttribute("person", person);
        return "register.html";
    }

    @PostMapping("/createUser")
    public String createNewUser(@Valid @ModelAttribute("person") Person person, Errors error) {
        if (error.hasErrors()) {
            return "register.html";
        }
        return "redirect:login?register=true";
    }
}