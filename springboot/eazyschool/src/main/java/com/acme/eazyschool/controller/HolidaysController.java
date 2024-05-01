package com.acme.eazyschool.controller;

import com.acme.eazyschool.model.Holiday;
import com.acme.eazyschool.repository.HolidayRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class HolidaysController {

    @Autowired
    private final HolidayRepository holidayRepository;

    public HolidaysController(HolidayRepository holidayRepository) {
        this.holidayRepository = holidayRepository;
    }

    @GetMapping("/holidays/{display}")
    public String displayHolidays(@PathVariable String display, Model model) {

        switch (display) {
            case "all" -> {
                model.addAttribute("festival", true);
                model.addAttribute("federal", true);
            }
            case "festival" -> {
                model.addAttribute("festival", true);
            }
            case "federal" -> {
                model.addAttribute("federal", true);
            }
        }

        // Fetch holidays from DB
        List<Holiday> holidays = holidayRepository.findAllHolidays();

        // Get values of enum = FEDERAL, FESTIVAL,...
        Holiday.Type[] types = Holiday.Type.values();

        // Loop through holidays and filter each one based on the value
        for (Holiday.Type type : types) {
            model.addAttribute(type.toString(), holidays.stream().filter(holiday -> holiday.getType().equals(type)).collect(Collectors.toList()));
        }

        return "holidays.html";
    }
}