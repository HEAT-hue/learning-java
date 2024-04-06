package com.example.demo.controllers;

import com.example.demo.services.Person;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class HomeController {

    @GetMapping(value = {"", "/", "home"})
    public String sayHello() {
        return "hello from Emmanuel";
    }

    @GetMapping("/info")
    public Person fetchUserDetails(@RequestParam("name") String name) {
        return new Person(name);
    }

    @GetMapping("/users")
    public List<Person> fetchAllUsers() {
        Person person = new Person("Emmanuel", 23);
        Person person1 = new Person("Martins", 25);
        Person person2 = new Person("Daniel", 45);

        List<Person> users = new ArrayList<>();

        users.add(person);
        users.add(person1);
        users.add(person2);

        return users;
    }
}