package com.melbourne.band.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BalanceController {

    @GetMapping("/balance")
    public String getBalanceDetails() {
        return "This is my balance";
    }
}
