package com.acme.eazyschool.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@Component
public class ProfileController {

    @GetMapping("/displayProfile")
    public ModelAndView displayProfile(Model model) {
        ModelAndView modelAndView = new ModelAndView("profile.html");
        return modelAndView;
    }
}
