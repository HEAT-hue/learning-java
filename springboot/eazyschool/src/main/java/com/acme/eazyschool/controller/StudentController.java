package com.acme.eazyschool.controller;

import com.acme.eazyschool.model.Person;
import com.acme.eazyschool.repository.CoursesRepository;
import com.acme.eazyschool.repository.EazyClassRepository;
import com.acme.eazyschool.repository.PersonRepository;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@Controller
@RequestMapping("student")
public class StudentController {

    @Autowired
    PersonRepository personRepository;

    @Autowired
    CoursesRepository coursesRepository;

    @Autowired
    EazyClassRepository eazyClassRepository;

    @GetMapping("/displayCourses")
    public ModelAndView displayCourses(HttpSession session) {
        Person person = (Person) session.getAttribute("loggedInUser");
        ModelAndView modelAndView = new ModelAndView("courses_enrolled.html");
        modelAndView.addObject("person", person);
        return modelAndView;
    }

}
