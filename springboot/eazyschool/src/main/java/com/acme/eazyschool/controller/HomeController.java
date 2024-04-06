package com.acme.eazyschool.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class HomeController {
    @GetMapping("/")
    public String home() {
        return "home.html";
    }

    @GetMapping("/{name}")
    public String homeName(Model model, @PathVariable("name") String name) {
        model.addAttribute("username", name);
        return "home.html";
    }

//    @GetMapping("/{name}")
//    public String displayName(@PathVariable("name") String id) {
//        return "Hello " + id + " hot reloads is  working";
//    }
}