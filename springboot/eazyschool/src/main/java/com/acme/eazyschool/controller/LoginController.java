package com.acme.eazyschool.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@Controller
public class LoginController {

    @RequestMapping(path = "/login", method = {RequestMethod.GET, RequestMethod.POST})
    public String displayLoginPage(@RequestParam(required = false) String error, @RequestParam(required = false) String logout, Model model) {
        String errorMessage = null;

        if (error != null) {
            errorMessage = "Username or Password is incorrect!!";
        }

        if (logout != null) {
            errorMessage = "You have been successfully logged out !!";
        }

        model.addAttribute("errorMessage", errorMessage);

        //  throw new RuntimeException("This is a bad day");
        return "login.html";
    }

    @GetMapping("/logout")
    public String logoutPage(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("Logout endpoint hit");

        // Get existing Authentication details using SecurityContextHolder
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        // Login currently successful, logout user please
        if (auth != null) {
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }

        // Call above login method with parameters
        return "redirect:login?logout=true";
    }

    // Handle Exceptions that occur in this controller
    @ExceptionHandler
    public ModelAndView handleException(Exception exception) {

        // Create a new view
        var errorPage = new ModelAndView();

        // Get exception message
        final String errorMessage = "Login Error: " + exception.getMessage();

        // Fetch html page
        errorPage.setViewName("error");

        // Add error message to view
        errorPage.addObject("errorMsg", errorMessage);

        // Log error message to console
        log.error(errorMessage);

        return errorPage;
    }
}