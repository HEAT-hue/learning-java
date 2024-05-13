package com.acme.eazyschool.controller;

import com.acme.eazyschool.model.EazyClass;
import com.acme.eazyschool.model.Person;
import com.acme.eazyschool.repository.EazyClassRepository;
import com.acme.eazyschool.repository.PersonRepository;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Optional;

@Slf4j
@Controller
@RequestMapping("admin")
public class AdminController {

    @Autowired
    PersonRepository personRepository;

    @Autowired
    EazyClassRepository eazyClassRepository;

    // Display classes
    @GetMapping("/displayClasses")
    public ModelAndView displayClasses() {
        // Get all classes
        List<EazyClass> eazyClasses = eazyClassRepository.findAll();

        ModelAndView model = new ModelAndView("classes.html");

        model.addObject("eazyClasses", eazyClasses);

        // Add empty POJO object to create new class
        model.addObject("eazyClass", new EazyClass());

        return model;
    }

    @PostMapping("/addNewClass")
    public String addNewClass(@Valid @ModelAttribute EazyClass eazyClass, Errors errors) throws Exception {
        if (errors.hasErrors()) {
            return "classes.html";
        }

        // Save new class
        Optional<EazyClass> savedClass = Optional.of(eazyClassRepository.save(eazyClass));

        if (savedClass.isPresent()) {
            return "redirect:/admin/displayClasses";
        }

        throw new Exception("Error saving class");
    }

    @GetMapping("/deleteClass")
    public String deleteClass(@RequestParam int id) {
        Optional<EazyClass> eazyClass = eazyClassRepository.findById(id);

        // Remove all references to this class in each person entity
        // Because of the Optional class, to get actual class, we need to use .get()
        for (Person person : eazyClass.get().getPersons()) {
            person.setEazyClass(null);
            personRepository.save(person);
        }

        // Delete eazy class
        eazyClassRepository.deleteById(id);
        return "redirect:/admin/displayClasses";
    }

    // Display student
    @GetMapping("/displayStudents")
    public ModelAndView displayStudent(@RequestParam int classId, @RequestParam(value = "error", required = false) String error, HttpSession httpSession) throws Exception {
        // Find students
        Optional<EazyClass> eazyClass = eazyClassRepository.findById(classId);

        // If class is not found
        if (!eazyClass.isPresent()) {
            throw new Exception("Class not found");
        }

        // Create view
        ModelAndView modelAndView = new ModelAndView("students.html");

        // If any error found in adding student
        if (null != error) {
            String errorMessage = "Invalid Email entered!!";
            modelAndView.addObject("errorMessage", errorMessage);
            return modelAndView;
        }

        modelAndView.addObject("eazyClass", eazyClass.get());

        // Add eazyClass object to session to be retrieved in another method controller (AddStudent)
        httpSession.setAttribute("eazyClass", eazyClass.get());

        // Add a person object to create a new person
        modelAndView.addObject("person", new Person());

        return modelAndView;
    }

    @PostMapping("/addStudent")
    public ModelAndView addStudent(Model model, @ModelAttribute Person personParam, HttpSession httpSession) {
        ModelAndView modelAndView = new ModelAndView();

        // Retrieve current class
        EazyClass eazyClass = (EazyClass) httpSession.getAttribute("eazyClass");

        // Retrieve personEntity from DB
        Optional<Person> personEntity = Optional.ofNullable(personRepository.findByEmail(personParam.getEmail()));

        // if person not found
        if (!personEntity.isPresent()) {
            modelAndView.setViewName("redirect:/admin/displayStudents?classId=" + eazyClass.getClassId() + "&error=true");
            return modelAndView;
        }

        Person person = personEntity.get();

        // Update person entity to include class
        person.setEazyClass(eazyClass);
        personRepository.save(person);

        // Add person to eazyClass list
        eazyClass.getPersons().add(person);
        eazyClassRepository.save(eazyClass);

        // Update session
        httpSession.setAttribute("eazyClass", eazyClass);

        modelAndView.setViewName("redirect:/admin/displayStudents?classId=" + eazyClass.getClassId());
        return modelAndView;
    }

    @GetMapping("/deleteStudent")
    public ModelAndView deleteStudent(@RequestParam int personId, HttpSession httpSession) {
        ModelAndView modelAndView = new ModelAndView();

        // Retrieve current class
        EazyClass eazyClass = (EazyClass) httpSession.getAttribute("eazyClass");

        System.out.println(eazyClass.toString());

        // Find Person
        Optional<Person> personEntity = personRepository.findById(personId);

        // If person not found
        if (!personEntity.isPresent()) {
            modelAndView.setViewName("redirect:/admin/displayStudents?classId=" + eazyClass.getClassId() + "&error=true");
            return modelAndView;
        }

        // Retrieve POJO object from null check class
        Person person = personEntity.get();

        // Remove association of student with class
        person.setEazyClass(null);
        personRepository.save(person);

        // Remove person from list
        eazyClass.getPersons().remove(person);
        eazyClassRepository.save(eazyClass);

        // return view name
        modelAndView.setViewName("redirect:/admin/displayStudents?classId=" + eazyClass.getClassId());
        return modelAndView;
    }

}