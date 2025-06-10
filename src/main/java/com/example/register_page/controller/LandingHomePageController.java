package com.example.register_page.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LandingHomePageController {

    @GetMapping("/home")
    public String homePage() {
        return "Welcome to Tiranga Dashboard!";
    }
}
