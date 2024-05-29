package com.acme.eazyschool.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import java.util.Arrays;

/*
@ControllerAdvice is a specialization of the @Component annotation which allows to handle
exceptions across the whole application in one global handling component. It can be viewed
as an interceptor of exceptions thrown by methods annotated with @RequestMapping and similar.
* */
@Slf4j
// Execute for only classes annotated with @Controller
@ControllerAdvice(annotations = Controller.class)
public class GlobalExceptionController {

    /*
    @ExceptionHandler will register the given method for a given
    exception type, so that ControllerAdvice can invoke this method
    logic if a given exception type is thrown inside the web application.
    Here you specify which type of exception to handle. In this case, instances of Exception class

    @param exception: Get exception details
    * */
    @ExceptionHandler(Exception.class)
    public ModelAndView exceptionHandler(Exception exception) {
        // Create an error page for exceptions that occurred in the server in the server
        var errorPage = new ModelAndView();

        // Get error message about the exception
        final String errorMessage = "General error: " + exception.getMessage() + exception.getCause();

        errorPage.setViewName("error");

        // Add error message object to view
        errorPage.addObject("errorMsg", errorMessage);

        // LOG ERROR MESSAGE TO THE SERVER
        log.error(errorMessage);
        return errorPage;
    }
}