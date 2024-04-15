package com.acme.eazyschool.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

/*
@ControllerAdvice is a specialization of the @Component annotation which allows to handle
exceptions across the whole application in one global handling component. It can be viewed
as an interceptor of exceptions thrown by methods annotated with @RequestMapping and similar.
* */

@Slf4j
@ControllerAdvice
public class GlobalExceptionController {

    /*
    @ExceptionHandler will register the given method for a given
    exception type, so that ControllerAdvice can invoke this method
    logic if a given exception type is thrown inside the web application.
    Here you specify which type of exception to handle. In this case, instances of Exception class
    * */
    @ExceptionHandler(Exception.class)
    public ModelAndView exceptionHandler(Exception exception) {
        var errorPage = new ModelAndView();
        final String errorMessage = "General error: " + exception.getMessage();

        errorPage.setViewName("error");
        errorPage.addObject("errorMsg", errorMessage);
        log.error(errorMessage);
        return errorPage;
    }
}
