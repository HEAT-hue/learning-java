package com.example.demo.services;

public class Person {

    private String name;
    private int age;
    private String email;

    public Person(String name) {
        this.name = name;
    }

    public Person(String name, int age) {
        this.age = age;
        this.name = name;
    }

    public Person(String name, int age, String email) {
        this.email = email;
        this.age = age;
        this.name = name;
    }

    // Getters
    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getEmail() {
        return email;
    }
}