package com.acme.eazyschool.controller;

import com.acme.eazyschool.model.Holiday;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class HolidaysController {

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

        // TODO: Fetch holidays from DB
        List<Holiday> holidays = Arrays.asList(new Holiday(" Jan 1 ", "New Year's Day", Holiday.Type.FESTIVAL), new Holiday(" Oct 31 ", "Halloween", Holiday.Type.FESTIVAL), new Holiday(" Nov 24 ", "Thanksgiving Day", Holiday.Type.FESTIVAL), new Holiday(" Dec 25 ", "Christmas", Holiday.Type.FESTIVAL), new Holiday(" Jan 17 ", "Martin Luther King Jr. Day", Holiday.Type.FEDERAL), new Holiday(" July 4 ", "Independence Day", Holiday.Type.FEDERAL), new Holiday(" Sep 5 ", "Labor Day", Holiday.Type.FEDERAL), new Holiday(" Nov 11 ", "Veterans Day", Holiday.Type.FEDERAL));

        // Get values of enum
        Holiday.Type[] types = Holiday.Type.values();

        // Loop through holidays and filter each one based on the value
        for (Holiday.Type type : types) {
            model.addAttribute(type.toString(), holidays.stream().filter(holiday -> holiday.getType().equals(type)).collect(Collectors.toList()));
        }

        return "holidays.html";
    }
}