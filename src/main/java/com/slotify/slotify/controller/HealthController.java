package com.slotify.slotify.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthController {
    @GetMapping("/health")
    public String health() {
        return "Slotify backend alive on JAVA 22";
    }

    @GetMapping("/health2")
    public String health2() {
        return "Slotify backend alive on Java 22";
    }
}