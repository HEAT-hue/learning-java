package com.acme.eazyschool.controller;

import com.acme.eazyschool.model.Courses;
import com.acme.eazyschool.model.EazyClass;
import com.acme.eazyschool.model.Person;
import com.acme.eazyschool.repository.CoursesRepository;
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

    @Autowired
    CoursesRepository coursesRepository;

    // Display classes
    @GetMapping("/displayClasses")
    public ModelAndView displayClasses() {
        // Get all classes
        List<EazyClass> eazyClasses = eazyClassRepository.findAll();

        // Create new model
        ModelAndView model = new ModelAndView("classes.html");

        // Add list of classes to model
        model.addObject("eazyClasses", eazyClasses);

        // Add empty POJO object to create new class
        model.addObject("eazyClass", new EazyClass());

        return model;
    }

    // Add new class
    @PostMapping("/addNewClass")
    public ModelAndView addNewClass(@Valid @ModelAttribute EazyClass eazyClass, Errors errors) throws Exception {
        ModelAndView modelAndView = new ModelAndView();

        // Errors generated from bean validation
        if (errors.hasErrors()) {
            modelAndView.setViewName("classes.html");
            return modelAndView;
        }

        // Save new class
        Optional<EazyClass> savedClass = Optional.of(eazyClassRepository.save(eazyClass));

        if (savedClass.isPresent()) {
            modelAndView.setViewName("redirect:/admin/displayClasses");
            return modelAndView;
        }

        throw new Exception("Error saving class");
    }

    // Delete class
    @GetMapping("/deleteClass")
    public ModelAndView deleteClass(@RequestParam int id) throws Exception {
        ModelAndView modelAndView = new ModelAndView();

        // Fetch the classhttps://github.com/eazybytes/spring/tree/3.1.2/example_42/src/main/java/com/eazybytes/eazyschool/controll
        Optional<EazyClass> eazyClass = eazyClassRepository.findById(id);

        // NO ID found, refresh page
        if (eazyClass.isEmpty()) {
            modelAndView.setViewName("redirect:/admin/displayClasses");
            return modelAndView;
        }

        // Remove all references to this class in each person entity
        // Because of the Optional class, to get actual class, we need to use .get()
        for (Person person : eazyClass.get().getPersons()) {
            person.setEazyClass(null);
            personRepository.save(person);
        }

        // Delete eazy class
        eazyClassRepository.deleteById(id);
        modelAndView.setViewName("redirect:/admin/displayClasses");
        return modelAndView;
    }

    // Display student
    @GetMapping("/displayStudents")
    public ModelAndView displayStudent(@RequestParam int classId, @RequestParam(value = "error", required = false) String error, HttpSession httpSession) throws Exception {
        // Find students
        Optional<EazyClass> eazyClass = eazyClassRepository.findById(classId);

        // If class is not found
        if (eazyClass.isEmpty()) {
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
        if (personEntity.isEmpty()) {
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

    @GetMapping("/displayCourses")
    public ModelAndView displayCourse() {
        ModelAndView modelAndView = new ModelAndView("courses_secure.html");
        // Plain old java object to add nw course
        // Add course POJO object to get fields
        modelAndView.addObject("course", new Courses());

        // Fetch list of courses
        List<Courses> courses = coursesRepository.findAll();
        modelAndView.addObject("courses", courses);
        return modelAndView;
    }


    // Add enw course to DB
    @PostMapping("/addNewCourse")
    public ModelAndView addNewCourse(@ModelAttribute("course") Courses course) {
        // Get view
        ModelAndView modelAndView = new ModelAndView();

        // Save courses
        coursesRepository.save(course);

        // redirect to display courses page
        modelAndView.setViewName("redirect:/admin/displayCourses");
        return modelAndView;
    }

    @GetMapping("/deleteCourse")
    public ModelAndView deleteCourse(@RequestParam("id") int courseId) throws Exception {
        ModelAndView modelAndView = new ModelAndView();

        // Fetch course
        Optional<Courses> courseEntity = coursesRepository.findById(courseId);

        // If person not found
        if (!courseEntity.isPresent()) {
            modelAndView.setViewName("redirect:/admin/displayCourses");
            return modelAndView;
        }

        // Remove course from each person instance
        for (Person person : courseEntity.get().getPersons()) {
            person.getCourses().remove(courseEntity.get());
            personRepository.save(person);
        }

        // Find course by ID
        coursesRepository.deleteById(courseId);

        // redirect to display courses page
        modelAndView.setViewName("redirect:/admin/displayCourses");
        return modelAndView;
    }

    @GetMapping("/viewStudents")
    public ModelAndView viewStudents(@RequestParam("id") int courseId, HttpSession session, @RequestParam(required = false) String error) throws Exception {
        // Set model and view
        ModelAndView modelAndView = new ModelAndView("course_students");

        if (error != null) {
            String errorMessage = "Invalid Email entered!";
            modelAndView.addObject("errorMessage", errorMessage);
        }

        // Fetch course
        Optional<Courses> courses = coursesRepository.findById(courseId);

        // if course not found or wrong id supplied
        if (courses.isEmpty()) {
            throw new Exception("An error occurred while fetching students");
        }

        // Add course to session
        session.setAttribute("course", courses.get());

        // Add person POJO object to view to register a student fora course
        modelAndView.addObject("person", new Person());

        // Add list of students to view
        modelAndView.addObject("courses", courses.get());

        return modelAndView;
    }

    @PostMapping("/addStudentToCourse")
    public ModelAndView addStudentToCourse(@ModelAttribute("person") Person person, HttpSession session) {

        ModelAndView modelAndView = new ModelAndView();

        // Retrieve course
        Courses course = (Courses) session.getAttribute("course");

        System.out.println(person.getEmail());

        // Retrieve person object
        Person personEntity = personRepository.findByEmail(person.getEmail());

        if (null == personEntity) {
            String errorMessage = "Person not found!";
            modelAndView.addObject("errorMessage", errorMessage);
            modelAndView.setViewName("redirect:/admin/viewStudents?id=" + course.getCourseId() + "&error=true");
            return modelAndView;
        }

        // Add it in both sides of the relationship

        personEntity.getCourses().add(course);
        course.getPersons().add(personEntity);

        // Save operation
        personRepository.save(personEntity);
        coursesRepository.save(course);

        // Update session with latest course
        session.setAttribute("courses", course);

        // Redirect to view students
        modelAndView.setViewName("redirect:/admin/viewStudents?id=" + course.getCourseId());
        return modelAndView;
    }

    @GetMapping("/deleteStudentFromCourse")
    public ModelAndView deleteStudentFromCourse(@RequestParam("personId") int personId, HttpSession session) {
        ModelAndView modelAndView = new ModelAndView();

        // Find person
        Optional<Person> personEntity = personRepository.findById(personId);

        Courses course = (Courses) session.getAttribute("course");

        // Check if person is found
        if (!personEntity.isPresent()) {
            String errorMessage = "Person not found!";
            modelAndView.addObject("errorMessage", errorMessage);
            modelAndView.setViewName("redirect:/admin/viewStudents?id=" + course.getCourseId() + "&error=true");
            return modelAndView;
        }

        // Remove course from person's list
        personEntity.get().getCourses().remove(course);
        personRepository.save(personEntity.get());

        // Remove person from courses list
        course.getPersons().remove(personEntity.get());


        // Save new modified course to session
        session.setAttribute("courses", course);

        // Redirect to view students
        modelAndView.setViewName("redirect:/admin/viewStudents?id=" + course.getCourseId());
        return modelAndView;
    }
}