package com.Pareek.journalApp.Controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthCheck {
    @GetMapping("/check")
    public String healthCheck() {
        return "Working";
    }
}
