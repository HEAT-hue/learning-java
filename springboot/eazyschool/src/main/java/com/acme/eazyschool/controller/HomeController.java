package com.acme.eazyschool.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class HomeController {

    @GetMapping(value = {"/", "/home"})
    public String displayHomepage() {
        return "home.html";
    }

}