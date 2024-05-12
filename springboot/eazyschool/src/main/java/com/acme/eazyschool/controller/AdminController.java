package com.acme.eazyschool.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@Controller
@RequestMapping("admin")
public class AdminController {
    @GetMapping
    public ModelAndView displayClasses() {
        ModelAndView model = new ModelAndView("classes.html");
        return model;
    }
}
